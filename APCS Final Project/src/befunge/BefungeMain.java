package befunge;

import java.awt.BorderLayout;
import java.awt.Button;
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

	private static final int CRAWL_STEP_TIME = 333;
	private static final int WALK_STEP_TIME = 100;

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

	private Parser p;
	
	private String currentFile;
	private String originalText;
	
	private boolean terminated;
	
	private int lastX;
	private int lastY;

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
		importItem = new JMenuItem("Open");
		importItem.addActionListener(reader);
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(saver);
		saveAsItem = new JMenuItem("Save As");
		saveAsItem.addActionListener(saveAser);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(closer);
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
		statusStream = new JTextField("");
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
		frame.setVisible(true);
		frame.setResizable(true);
	}
	
	private void saveFile() {
		String str = BefungeMain.this.currentFile;
		if (str == null) {
			str = (String)JOptionPane.showInputDialog(
					frame,
					"Enter a file name:");
			if (str == null)
				return;
			BefungeMain.this.currentFile = str;
		}
		try {
			PrintWriter writer = new PrintWriter(str, "UTF-8");
			writer.print(BefungeMain.this.textArea.getText());
			writer.close();
		}
		catch (IOException ex) {
			System.out.println("Exception");
		}
	}

	public static void main(String[] args) throws IOException {

		BefungeMain GUI = new BefungeMain();
		
	}

	private class RunActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (originalText == null)
				originalText = BefungeMain.this.textArea.getText();
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			terminated = false;
			while (p.isRunning() && !terminated) {
				p.interpret();
				p.advance();
				lastX = p.getCurrentX();
				lastY = p.getCurrentY();
			}
			statusStream.setText("Status: Stopped. x = " + p.getCurrentX() + ", y = " + p.getCurrentY());
			stackStream.setText(p.getInterpreter().getStack().toString());
			outputStream.setText(p.getOutput());
			p = null;
		}

	}

	private class WalkActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (originalText == null)
				originalText = BefungeMain.this.textArea.getText();
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			terminated = false;
			Timer timer = new Timer(WALK_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					statusStream.setText("Status: Running. x = "
								+ (lastX = p.getCurrentX()) + ", y = " + (lastY = p.getCurrentY()));
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
					if (p.isUpdateNeeded()) {
						BefungeMain.this.textArea.setText(p.getRawTokens().trim());
						p.setUpdateNeeded(false);
					}
					if (!p.isRunning() || terminated) {
						p = null;
						statusStream.setText("Status: Stopped. x = " + p.getCurrentX() + ", y = " + p.getCurrentY());
						((Timer)e.getSource()).stop();
					}
				}
			});
			timer.start();
		}

	}

	private class CrawlActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (originalText == null)
				originalText = BefungeMain.this.textArea.getText();
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			terminated = false;
			Timer timer = new Timer(CRAWL_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					statusStream.setText("Status: Running. x = "
								+ (lastX = p.getCurrentX()) + ", y = " + (lastY = p.getCurrentY()));
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
					if (p.isUpdateNeeded()) {
						BefungeMain.this.textArea.setText(p.getRawTokens().trim());
						p.setUpdateNeeded(false);
					}
					if (!p.isRunning() || terminated) {
						p = null;
						statusStream.setText("Status: Stopped. x = " + p.getCurrentX() + ", y = " + p.getCurrentY());
						((Timer)e.getSource()).stop();
					}
				}
			});
			timer.start();
		}

	}
	
	private class ResetActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			p = null;
			textArea.setText(originalText == null ? "" : originalText.trim());
			originalText = null;
			terminated = false;
			stackStream.setText("");
			outputStream.setText("");
		}

	}

	private class StepActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (originalText == null)
				originalText = BefungeMain.this.textArea.getText();
			if (p == null) {
				p = new Parser(BefungeMain.this.textArea.getText());
			}
			if (p.isRunning()) {
				p.interpret();
				p.advance();
			}
			if (p.isRunning())
				statusStream.setText("Status: Running. x = " + p.getCurrentX() + ", y = " + p.getCurrentY());
			else
				statusStream.setText("Status: Stopped. x = " + p.getCurrentX() + ", y = " + p.getCurrentY());
			stackStream.setText(p.getInterpreter().getStack().toString());
			outputStream.setText(p.getOutput());
			if (p.isUpdateNeeded()) {
				BefungeMain.this.textArea.setText(p.getRawTokens().trim());
				p.setUpdateNeeded(false);
			}
		}

	}
	
	private class NewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}

	private class FileReaderActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e){
			String str = (String)JOptionPane.showInputDialog(
					frame,
					"Enter a file name:");
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
				System.out.println("Exception");
			}
			finally {
				if (s != null) {
					s.close();
				}
			}

		}

	}
	
	private class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BefungeMain.this.saveFile();
		}

	}
	
	private class SaveAsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BefungeMain.this.currentFile = null;
			BefungeMain.this.saveFile();
		}

	}
	
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
	
	private class TerminateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			terminated = true;
		}

	}

}
