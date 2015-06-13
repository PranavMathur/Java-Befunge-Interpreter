package befunge;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import java.io.*;
import java.util.Scanner;

public class BefungeMain extends JApplet implements ActionListener {
	
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
	private JMenuItem importItem;
	private JMenuItem saveItem;
	private JMenuItem saveAsItem;
	private JMenuItem exitItem;
	private JMenuItem runItem;
	private JMenuItem walkItem;
	private JMenuItem crawlItem;
	private JMenuItem resetItem;
	private JMenuItem terminateItem;
	private JButton stepItem;
	private JTextField statusStream;
	private JTextField stackStream;
	private JTextField outputStream;

	/** The Parser used to run the program */
	private Parser parser;

	/** The file that is currently open */
	private String currentFile;
	/** The original text of the current File */
	private String originalText;

	private boolean terminated;

	//The ending position of the Parser's pointer
	private int lastX;
	private int lastY;

	/**
	 * Creates each aspect of the GUI
	 */
	public BefungeMain() {
		frame = new JFrame("Befunge Interpreter");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		importItem = new JMenuItem("Open");
		importItem.addActionListener(this);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		fileMenu.add(newItem);
		fileMenu.add(importItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		runMenu = new JMenu("Run");
		runItem = new JMenuItem("Run");
		runItem.addActionListener(this);
		walkItem = new JMenuItem("Walk");
		walkItem.addActionListener(this);
		crawlItem = new JMenuItem("Crawl");
		crawlItem.addActionListener(this);
		resetItem = new JMenuItem("Reset");
		resetItem.addActionListener(this);
		terminateItem = new JMenuItem("Terminate");
		terminateItem.addActionListener(this);
		runMenu.add(runItem);
		runMenu.add(walkItem);
		runMenu.add(crawlItem);
		runMenu.addSeparator();
		runMenu.add(terminateItem);
		runMenu.add(resetItem);
		menuBar.add(runMenu);
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		stepItem = new JButton("Step");
		stepItem.addActionListener(this);
		menuBar.add(stepItem);
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

		Scanner s = null;
		StringBuilder fileStr = new StringBuilder();

		try {
			s = new Scanner(new BufferedReader(new FileReader(str)));
			while (s.hasNext()) {
				fileStr.append(s.nextLine());
				fileStr.append("\n");
			}
			textArea.setText(fileStr.toString());
			originalText = fileStr.toString();
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null,  "Error: File Not Found");
		}
		finally {
			if (s != null) {
				s.close();
			}
		}

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
		try {
			PrintWriter writer = new PrintWriter(str, "UTF-8");
			writer.print(textArea.getText());
			writer.close();
		}
		catch (IOException ex) {
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
		else if (e.getSource().equals(importItem)) {
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
			saveFile();
			while (parser.isRunning() && !terminated) {
				parser.interpret();
				parser.advance();
				lastX = parser.getCurrentX();
				lastY = parser.getCurrentY();
			}
			statusStream.setText("Status: Stopped. x = " + parser.getCurrentX() + ", y = " + parser.getCurrentY());
			stackStream.setText(parser.getInterpreter().getStack().toString());
			outputStream.setText(parser.getOutput());
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
					parser.advance();
					statusStream.setText("Status: Running. x = "
							+ (lastX = parser.getCurrentX()) + ", y = " + (lastY = parser.getCurrentY()));
					stackStream.setText(parser.getInterpreter().getStack().toString());
					outputStream.setText(parser.getOutput());
					if (parser.isUpdateNeeded()) {
						textArea.setText(parser.getRawTokens().trim());
						parser.setUpdateNeeded(false);
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
					parser.advance();
					statusStream.setText("Status: Running. x = "
							+ (lastX = parser.getCurrentX()) + ", y = " + (lastY = parser.getCurrentY()));
					stackStream.setText(parser.getInterpreter().getStack().toString());
					outputStream.setText(parser.getOutput());
					if (parser.isUpdateNeeded()) {
						textArea.setText(parser.getRawTokens().trim());
						parser.setUpdateNeeded(false);
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
			if (originalText != null)
				textArea.setText(originalText.trim());
			originalText = null;
			terminated = false;
			statusStream.setText("Status: Stopped. x = 0, y = 0");
			stackStream.setText("");
			outputStream.setText("");
		}
		else if (e.getSource().equals(stepItem)) {
			if (originalText == null)
				originalText = textArea.getText();
			if (parser == null) {
				parser = new Parser(textArea.getText());
			}
			if (parser.isRunning()) {
				parser.interpret();
				parser.advance();
			}
			if (parser.isRunning())
				statusStream.setText("Status: Running. x = " + parser.getCurrentX() + ", y = " + parser.getCurrentY());
			else
				statusStream.setText("Status: Stopped. x = " + parser.getCurrentX() + ", y = " + parser.getCurrentY());
			stackStream.setText(parser.getInterpreter().getStack().toString());
			outputStream.setText(parser.getOutput());
			if (parser.isUpdateNeeded()) {
				textArea.setText(parser.getRawTokens().trim());
				parser.setUpdateNeeded(false);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BefungeMain GUI = new BefungeMain();
		GUI.frame.setVisible(true);
		GUI.frame.setResizable(true);
	}

}
