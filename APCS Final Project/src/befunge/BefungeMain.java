package befunge;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JButton runButton;
	private JButton closeButton;
	private JButton walkButton;
	private JButton crawlButton;
	private JButton stepButton;
	private JButton resetButton;
	private JButton fileButton;
	private JButton saveButton;
	private JTextField stackStream;
	private JTextArea outputStream;

	private RunActionListener runner = new RunActionListener();
	private WalkActionListener walker = new WalkActionListener();
	private CrawlActionListener crawler = new CrawlActionListener();
	private StepActionListener stepper = new StepActionListener();
	private ResetActionListener resetter = new ResetActionListener();
	private FileReaderActionListener reader = new FileReaderActionListener();
	private SaveActionListener saver = new SaveActionListener();
	private CloseActionListener closer = new CloseActionListener();

	private Parser p;
	
	private String currentFile;

	public BefungeMain() {
		frame = new JFrame("Befunge Interpreter");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fileFrame = new JFrame("Befunge Interpreter");
		fileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		runButton = new JButton("Run");
		runButton.addActionListener(runner);
		walkButton = new JButton("Walk");
		walkButton.addActionListener(walker);
		crawlButton = new JButton("Crawl");
		crawlButton.addActionListener(crawler);
		stepButton = new JButton("Step");
		stepButton.addActionListener(stepper);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(resetter);
		fileButton = new JButton("Import File");
		fileButton.addActionListener(reader);
		saveButton = new JButton("Save File");
		saveButton.addActionListener(saver);
		closeButton = new JButton("Close");
		closeButton.addActionListener(closer);
		panel.add(scroller);
		inputPanel.add(runButton);
		inputPanel.add(walkButton);
		inputPanel.add(crawlButton);
		inputPanel.add(stepButton);
		inputPanel.add(resetButton);
		inputPanel.add(fileButton);
		inputPanel.add(saveButton);
		inputPanel.add(closeButton);
		panel.add(inputPanel);
		stackStream = new JTextField("");
		stackStream.setFont(new Font("Courier", Font.PLAIN, 12));
		stackStream.setEditable(false);
		panel.add(stackStream);
		outputStream = new JTextArea("");
		outputStream.setFont(new Font("Courier", Font.PLAIN, 12));
		outputStream.setEditable(false);
		panel.add(outputStream);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.setResizable(true);
	}

	public static void main(String[] args) throws IOException {

		BefungeMain gui = new BefungeMain();
		
	}

	private class RunActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			while (p.isRunning()) {
				p.interpret();
				p.advance();
			}
			stackStream.setText(p.getInterpreter().getStack().toString());
			outputStream.setText(p.getOutput());
			p = null;
		}

	}

	private class WalkActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			Timer timer = new Timer(WALK_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
					if (!p.isRunning()) {
						p = null;
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
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText());
			Timer timer = new Timer(CRAWL_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
					if (!p.isRunning()) {
						p = null;
						((Timer)e.getSource()).stop();
					}
					
				}
			});
			timer.start();
		}

	}

	private class StepActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null) {
				p = new Parser(BefungeMain.this.textArea.getText());
			}
			if (p.isRunning()) {
				p.interpret();
				p.advance();
			}
			stackStream.setText(p.getInterpreter().getStack().toString());
			outputStream.setText(p.getOutput());
		}

	}

	private class ResetActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			p = null;
			stackStream.setText("");
			outputStream.setText("");
		}

	}

	private class FileReaderActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e){
			String str = (String)JOptionPane.showInputDialog(
					frame,
					"Enter a file name:");
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
			String str = (String)JOptionPane.showInputDialog(
					frame,
					"Enter a file name:");
			BefungeMain.this.currentFile = str;
			
			try {
				PrintWriter writer = new PrintWriter(str, "UTF-8");
				writer.print(BefungeMain.this.textArea.getText());
				writer.close();
			}
			catch (IOException ex) {
				System.out.println("Exception");
			}
		}

	}
	
	private class CloseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int response = JOptionPane.showConfirmDialog(BefungeMain.this.frame, "Do you want to save your work?");
			if (response == 0) {
				try {
					if (currentFile != null) {
						PrintWriter writer = new PrintWriter(BefungeMain.this.currentFile, "UTF-8");
						writer.print(BefungeMain.this.textArea.getText());
						writer.close();
					}
					else {
						String str = (String)JOptionPane.showInputDialog(
								BefungeMain.this.frame,
								"Enter a file name:");
						PrintWriter writer = new PrintWriter(str, "UTF-8");
						writer.print(BefungeMain.this.textArea.getText());
						writer.close();
					}
				}
				catch (IOException ex) {
					System.out.println("Exception");
				}
				BefungeMain.this.frame.setVisible(false);
				BefungeMain.this.frame.dispose();
			}
			else if (response == 1) {
				BefungeMain.this.frame.setVisible(false);
				BefungeMain.this.frame.dispose();
			}
		}

	}

}