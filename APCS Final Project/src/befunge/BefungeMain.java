package befunge;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class BefungeMain {

	/** The amount of time in milliseconds between steps when crawling through a program */
	private static final int CRAWL_STEP_TIME = 333;
	/** The amount of time in milliseconds between steps when crawling through a program */
	private static final int WALK_STEP_TIME = 100;

	//Objects used for GUI creation
	private JFrame frame;
	private JFrame fileFrame;
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scroller;
	private JScrollPane stackScroller;
	private JScrollPane outputScroller;
	private JPanel inputPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu runMenu;
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

	//ActionListeners for buttons and menuItems
	private RunActionListener runner = new RunActionListener();
	private WalkActionListener walker = new WalkActionListener();
	private CrawlActionListener crawler = new CrawlActionListener();
	private StepActionListener stepper = new StepActionListener();
	private FileReaderActionListener reader = new FileReaderActionListener();
	private SaveActionListener saver = new SaveActionListener();
	private SaveAsActionListener saveAser = new SaveAsActionListener();
	private CloseActionListener closer = new CloseActionListener();
	private ResetActionListener resetter = new ResetActionListener();
	private TerminateActionListener terminator = new TerminateActionListener();
	private NewActionListener creator = new NewActionListener();

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
		newItem.addActionListener(creator);
		importItem = new JMenuItem("Open");
		importItem.addActionListener(reader);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(saver);
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(saveAser);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(closer);
		fileMenu.add(newItem);
		fileMenu.add(importItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		runMenu = new JMenu("Run");
		runItem = new JMenuItem("Run");
		runItem.addActionListener(runner);
		walkItem = new JMenuItem("Walk");
		walkItem.addActionListener(walker);
		crawlItem = new JMenuItem("Crawl");
		crawlItem.addActionListener(crawler);
		resetItem = new JMenuItem("Reset");
		resetItem.addActionListener(resetter);
		terminateItem = new JMenuItem("Terminate");
		terminateItem.addActionListener(terminator);
		runMenu.add(runItem);
		runMenu.add(walkItem);
		runMenu.add(crawlItem);
		runMenu.addSeparator();
		runMenu.add(terminateItem);
		runMenu.add(resetItem);
		menuBar.add(runMenu);
		stepItem = new JButton("Step");
		stepItem.addActionListener(stepper);
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
	 * Open's a file using a FileDialog and places the text of the file in the text area
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
		BefungeMain.this.currentFile = str;

		Scanner s = null;
		StringBuilder fileStr = new StringBuilder();
		
		try {
			s = new Scanner(new BufferedReader(new FileReader(str)));
			while (s.hasNext()) {
				fileStr.append(s.nextLine());
				fileStr.append("\n");
			}
			BefungeMain.this.textArea.setText(fileStr.toString());
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
		String str = BefungeMain.this.currentFile;
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
			BefungeMain.this.currentFile = str;
		}
		try {
			PrintWriter writer = new PrintWriter(str, "UTF-8");
			writer.print(BefungeMain.this.textArea.getText());
			writer.close();
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null,  "Error: File Not Found");
		}
	}

	/**
	 * ActionListener for the "New" button of the GUI
	 * @author Pranav
	 *
	 */
	private class NewActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			int response = 1;
			if ((originalText == null ^ textArea.getText() == null)
					|| originalText.trim().equals(textArea.getText().trim()))
				response = JOptionPane.showConfirmDialog(BefungeMain.this.frame, "Do you want to save your work?");
			if (response == 0) {
				BefungeMain.this.saveFile();
				textArea.setText("");
				currentFile = null;
			}
			else if (response == 1) {
				textArea.setText("");
				currentFile = null;
			}
		}
	
	}
	
	/**
	 * ActionListener for the "Open" button of the GUI
	 * @author Pranav
	 *
	 */
	private class FileReaderActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e){
			openFile();
		}
	
	}
	
	/**
	 * ActionListener for the "Save" button of the GUI
	 * @author Pranav
	 *
	 */
	private class SaveActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	
	}
	/**
	 * ActionListener for the "Save as" button of the GUI
	 * @author Pranav
	 *
	 */
	private class SaveAsActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			BefungeMain.this.currentFile = null;
			BefungeMain.this.saveFile();
		}
	
	}
	/**
	 * ActionListener for the "Exit" button of the GUI
	 * @author Pranav
	 *
	 */
	private class CloseActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((originalText == null ^ textArea.getText() == null)
						|| originalText.trim().equals(textArea.getText().trim())) {
				BefungeMain.this.frame.setVisible(false);
				BefungeMain.this.frame.dispose();
				return;
			}
			int response = JOptionPane.showConfirmDialog(BefungeMain.this.frame, "Do you want to save your work?");
			if (response == 0) {
				BefungeMain.this.saveFile();
				BefungeMain.this.frame.setVisible(false);
				BefungeMain.this.frame.dispose();
			}
			else if (response == 1) {
				BefungeMain.this.frame.setVisible(false);
				BefungeMain.this.frame.dispose();
			}
		}
	
	}
	/**
	 * ActionListener for the "Run" button of the GUI
	 * @author Pranav
	 *
	 */
	private class RunActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			originalText = BefungeMain.this.textArea.getText();
			if (parser == null)
				parser = new Parser(BefungeMain.this.textArea.getText());
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

	}
	/**
	 * ActionListener for the "Walk" button of the GUI
	 * @author Pranav
	 *
	 */
	private class WalkActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			originalText = BefungeMain.this.textArea.getText();
			if (parser == null)
				parser = new Parser(BefungeMain.this.textArea.getText());
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
						BefungeMain.this.textArea.setText(parser.getRawTokens().trim());
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

	}
	/**
	 * ActionListener for the "Crawl" button of the GUI
	 * @author Pranav
	 *
	 */
	private class CrawlActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			originalText = BefungeMain.this.textArea.getText();
			if (parser == null)
				parser = new Parser(BefungeMain.this.textArea.getText());
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
						BefungeMain.this.textArea.setText(parser.getRawTokens().trim());
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

	}
	/**
	 * ActionListener for the "Terminate" button of the GUI
	 * @author Pranav
	 *
	 */
	private class TerminateActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			terminated = true;
		}
	
	}
	/**
	 * ActionListener for the "Reset" button of the GUI
	 * @author Pranav
	 *
	 */
	private class ResetActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			parser = null;
			if (originalText != null)
				textArea.setText(originalText.trim());
			originalText = null;
			terminated = false;
			statusStream.setText("Status: Stopped. x = 0, y = 0");
			stackStream.setText("");
			outputStream.setText("");
		}

	}
	/**
	 * ActionListener for the "Step" button of the GUI
	 * @author Pranav
	 *
	 */
	private class StepActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (originalText == null)
				originalText = BefungeMain.this.textArea.getText();
			if (parser == null) {
				parser = new Parser(BefungeMain.this.textArea.getText());
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
				BefungeMain.this.textArea.setText(parser.getRawTokens().trim());
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
