package io.github.pranavmathur.befunge;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import java.net.URL;
import java.util.Scanner;

public class BefungeMain implements ActionListener {
	
	static final long serialVersionUID = 0l;

	/** The amount of time in milliseconds between steps when crawling through a program */
	private static final int CRAWL_STEP_TIME = 333;
	/** The amount of time in milliseconds between steps when walking through a program */
	private static final int WALK_STEP_TIME = 100;

	//Objects used for GUI creation
	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scroller;
	private JPanel inputPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu runMenu;
	private JMenu helpMenu;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem exitItem;
	private JMenuItem runItem;
	private JMenuItem walkItem;
	private JMenuItem crawlItem;
	private JMenuItem stepItem;
	private JMenuItem resetItem;
	private JMenuItem terminateItem;
	private JMenuItem helpItem;
	private JButton stepButton;
	private JTextField statusStream;
	private JTextField stackStream;
	private JTextField outputStream;

	/** The Parser used to run the program */
	private Parser parser;
	/** The file that is currently open */
	private String currentFile;
	/** The original text of the current File */
	private String originalText;
	/** The code after execution */
	private String editedText;
	
	/** Whether or not the program has been stopped by the user */
	private boolean terminated;

	//The ending position of the Parser's pointer
	private int lastX = 0;
	private int lastY = 0;

	/**
	 * Creates each aspect of the GUI
	 */
	public BefungeMain() {
		frame = new JFrame("Befunge Interpreter");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL url = getClass().getResource("/bf.png");
		Image img = tk.createImage(url);
		tk.prepareImage(img, -1, -1, null);
		frame.setIconImage(img);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		textArea = new JTextArea(15, 50);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);
		textArea.setFont(new Font("Courier", Font.PLAIN, 12));
		scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		newItem.addActionListener(this);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		openItem = new JMenuItem("Open");
		openItem.addActionListener(this);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		runMenu = new JMenu("Run");
		runItem = new JMenuItem("Run");
		runItem.addActionListener(this);
		runItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
		walkItem = new JMenuItem("Walk");
		walkItem.addActionListener(this);
		walkItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
		crawlItem = new JMenuItem("Crawl");
		crawlItem.addActionListener(this);
		crawlItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		stepItem = new JMenuItem("Step");
		stepItem.addActionListener(this);
		stepItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		resetItem = new JMenuItem("Reset");
		resetItem.addActionListener(this);
		terminateItem = new JMenuItem("Terminate");
		terminateItem.addActionListener(this);
		runMenu.add(runItem);
		runMenu.add(walkItem);
		runMenu.add(crawlItem);
		runMenu.addSeparator();
		runMenu.add(stepItem);
		runMenu.addSeparator();
		runMenu.add(terminateItem);
		runMenu.add(resetItem);
		menuBar.add(runMenu);
		helpMenu = new JMenu("Help");
		helpItem = new JMenuItem("About Befunge");
		helpItem.addActionListener(this);
		helpMenu.add(helpItem);
		menuBar.add(helpMenu);
		stepButton = new JButton("Step");
		stepButton.addActionListener(this);
		menuBar.add(stepButton);
		panel.add(scroller);
		panel.add(inputPanel);
		statusStream = new JTextField("Status: Stopped. x = 0, y = 0");
		statusStream.setFont(new Font("Courier", Font.PLAIN, 12));
		statusStream.setEditable(false);
		panel.add(statusStream);
		stackStream = new JTextField("");
		stackStream.setFont(new Font("Courier", Font.PLAIN, 12));
		stackStream.setEditable(false);
		panel.add(stackStream);
		outputStream = new JTextField("");
		outputStream.setFont(new Font("Courier", Font.PLAIN, 12));
		outputStream.setEditable(false);
		panel.add(outputStream);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setLocationByPlatform(true);
		terminated = false;
	}

	/**
	 * Opens a file using a FileDialog and places the text of the file in the text area
	 */
	private void openFile() {
		FileDialog dialog = new FileDialog(frame, "Open File");
		dialog.setMode(FileDialog.LOAD);
		dialog.setMultipleMode(false);
		dialog.setVisible(true);
		File[] files = dialog.getFiles();
		if (files.length == 0)
			return;
		String str = dialog.getFiles()[0].getPath();
		if (str == null)
			return;
		currentFile = str;

		StringBuilder fileStr = new StringBuilder();

		try (Scanner s = new Scanner(new BufferedReader(new FileReader(str)))) {
			while (s.hasNext()) {
				fileStr.append(s.nextLine());
				fileStr.append("\n");
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,  "Error: File Not Found");
		}

		textArea.setText(fileStr.toString());
		originalText = fileStr.toString();

	}

	/**
	 * Saves the current text of the text area to a file
	 * If the file has not been saved before, prompts the user for a file using a FileDialog
	 */
	private void saveFile() {
		String str = currentFile;
		originalText = textArea.getText();
		if (str == null) {
			FileDialog dialog = new FileDialog(frame, "Open File");
			dialog.setMode(FileDialog.LOAD);
			dialog.setMultipleMode(false);
			dialog.setVisible(true);
			File[] files = dialog.getFiles();
			if (files.length == 0)
				return;
			str = dialog.getFiles()[0].getPath();
			currentFile = str;
		}
		try (PrintWriter writer = new PrintWriter(str, "UTF-8")) {
			for (String i : textArea.getText().split("\n")) {
				writer.print(i);
				writer.print("\r\n");
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,  "Error: File Not Found");
		}
	}

	/**
	 * Action Listeners for each JMenuItem and JButton
	 * Runs a different block of code for each menu item/button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newItem)) {
			int response = 1;
			if ((originalText == null ^ textArea.getText() == null)
					|| originalText.trim().equals(textArea.getText().trim()))
				response = JOptionPane.showConfirmDialog(frame, "Do you want to save your work?");
			if (response == 0) {
				saveFile();
				textArea.setText("");
				currentFile = null;
			}
			else if (response == 1) {
				textArea.setText("");
				currentFile = null;
			}
		}
		else if (e.getSource().equals(openItem)) {
			openFile();
		}
		else if (e.getSource().equals(saveItem)) {
			saveFile();
		}
		else if (e.getSource().equals(saveAsItem)) {
			currentFile = null;
			saveFile();
		}
		else if (e.getSource().equals(exitItem)) {
			if ((originalText == null ^ textArea.getText() == null)
					|| originalText.trim().equals(textArea.getText().trim())) {
				frame.setVisible(false);
				frame.dispose();
				return;
			}
			int response = JOptionPane.showConfirmDialog(frame, "Do you want to save your work?");
			if (response == 0) {
				saveFile();
				frame.setVisible(false);
				frame.dispose();
			}
			else if (response == 1) {
				frame.setVisible(false);
				frame.dispose();
			}
		}
		else if (e.getSource().equals(runItem)) {
			originalText = textArea.getText();
			if (parser == null)
				parser = new Parser(textArea.getText());
			terminated = false;
			if (currentFile != null)
				saveFile();
			while (parser.isRunning() && !terminated) {
				parser.interpret();
				lastX = parser.getCurrentX();
				lastY = parser.getCurrentY();
				parser.advance();
			}
			statusStream.setText("Status: Stopped. x = " + lastX + ", y = " + lastY);
			stackStream.setText(parser.getInterpreter().getStack().toString());
			outputStream.setText(parser.getOutput());
			if (parser.isUpdateNeeded()) {
				textArea.setText(trimEnd(parser.getRawTokens()));
				parser.setUpdateNeeded(false);
				editedText = parser.getRawTokens();
			}
			parser = null;
		}
		else if (e.getSource().equals(walkItem)) {
			originalText = textArea.getText();
			if (parser == null)
				parser = new Parser(textArea.getText());
			terminated = false;
			saveFile();
			Timer timer = new Timer(WALK_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parser.interpret();
					lastX = parser.getCurrentX();
					lastY = parser.getCurrentY();
					parser.advance();
					statusStream.setText("Status: Running. x = " + lastX + ", y = " + lastY);
					stackStream.setText(parser.getInterpreter().getStack().toString());
					outputStream.setText(parser.getOutput());
					if (parser.isUpdateNeeded()) {
						textArea.setText(trimEnd(parser.getRawTokens()));
						parser.setUpdateNeeded(false);
						editedText = parser.getRawTokens();
					}
					if (!parser.isRunning() || terminated) {
						parser = null;
						statusStream.setText("Status: Stopped. x = " + lastX + ", y = " + lastY);
						((Timer)e.getSource()).stop();
					}
				}
			});
			timer.start();
		}
		else if (e.getSource().equals(crawlItem)) {
			originalText = textArea.getText();
			if (parser == null)
				parser = new Parser(textArea.getText());
			terminated = false;
			saveFile();
			Timer timer = new Timer(CRAWL_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parser.interpret();
					lastX = parser.getCurrentX();
					lastY = parser.getCurrentY();
					parser.advance();
					statusStream.setText("Status: Running. x = " + lastX + ", y = " + lastY);
					stackStream.setText(parser.getInterpreter().getStack().toString());
					outputStream.setText(parser.getOutput());
					if (parser.isUpdateNeeded()) {
						textArea.setText(trimEnd(parser.getRawTokens()));
						parser.setUpdateNeeded(false);
						editedText = parser.getRawTokens();
					}
					if (!parser.isRunning() || terminated) {
						parser = null;
						statusStream.setText("Status: Stopped. x = " + lastX + ", y = " + lastY);
						((Timer)e.getSource()).stop();
					}
				}
			});
			timer.start();
		}
		else if (e.getSource().equals(terminateItem)) {
			terminated = true;
		}
		else if (e.getSource().equals(resetItem)) {
			parser = null;
			if (editedText != null && !editedText.equals(originalText))
				textArea.setText(trimEnd(originalText));
			originalText = null;
			terminated = false;
			statusStream.setText("Status: Stopped. x = 0, y = 0");
			stackStream.setText("");
			outputStream.setText("");
		}
		else if (e.getSource().equals(stepButton) || e.getSource().equals(stepItem)) {
			if (originalText == null)
				originalText = textArea.getText();
			if (parser == null) {
				parser = new Parser(textArea.getText());
			}
			if (parser.isRunning()) {
				parser.interpret();
				lastX = parser.getCurrentX();
				lastY = parser.getCurrentY();
				parser.advance();
				statusStream.setText("Status: Running. x = " + lastX + ", y = " + lastY);
			}
			else
				statusStream.setText("Status: Stopped. x = " + lastX + ", y = " + lastY);
			stackStream.setText(parser.getInterpreter().getStack().toString());
			outputStream.setText(parser.getOutput());
			if (parser.isUpdateNeeded()) {
				textArea.setText(trimEnd(parser.getRawTokens()));
				parser.setUpdateNeeded(false);
				editedText = parser.getRawTokens();
			}
			if (!parser.isRunning())
				parser = null;
		}
		else if (e.getSource().equals(helpItem)) {
			JOptionPane.showMessageDialog(frame, "Hi", "About Befunge", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static String trimEnd(String str) {
		int i = str.length();
		while (str.substring(i-1, i).equals(" "))
			i--;
		return str.substring(0, i);
	}

	public static void main(String[] args) throws IOException {
		
		BefungeMain GUI = new BefungeMain();
		GUI.frame.setVisible(true);
		GUI.frame.setResizable(true);
		
	}

}