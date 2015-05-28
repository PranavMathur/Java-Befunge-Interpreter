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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

public class BefungeMain {
	
	private static final int CRAWL_STEP_TIME = 333;
	private static final int WALK_STEP_TIME = 100;
	
	private JFrame frame;
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
	private JTextField stackStream;
	private JTextArea outputStream;
	
	private RunActionListener runner = new RunActionListener();
	private WalkActionListener walker = new WalkActionListener();
	private CrawlActionListener crawler = new CrawlActionListener();
	private StepActionListener stepper = new StepActionListener();
	private ResetActionListener resetter = new ResetActionListener();
	
	private Parser p;

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
		runButton = new JButton("Run");
		runButton.addActionListener(runner);
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		walkButton = new JButton("Walk");
		walkButton.addActionListener(walker);
		crawlButton = new JButton("Crawl");
		crawlButton.addActionListener(crawler);
		stepButton = new JButton("Step");
		stepButton.addActionListener(stepper);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(resetter);
		panel.add(scroller);
		inputPanel.add(runButton);
		inputPanel.add(walkButton);
		inputPanel.add(crawlButton);
		inputPanel.add(stepButton);
		inputPanel.add(resetButton);
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
	
	public static void main(String[] args) {
		BefungeMain gui = new BefungeMain();
	}
	
	private class RunActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText(), new JFrame("Dialog Box"));
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
				p = new Parser(BefungeMain.this.textArea.getText(), new JFrame("Dialog Box"));
			Timer timer = new Timer(WALK_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
				}
			});
			timer.start();
		}
		
	}
	
	private class CrawlActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null)
				p = new Parser(BefungeMain.this.textArea.getText(), new JFrame("Dialog Box"));
			Timer timer = new Timer(CRAWL_STEP_TIME, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p.interpret();
					p.advance();
					stackStream.setText(p.getInterpreter().getStack().toString());
					outputStream.setText(p.getOutput());
				}
			});
			timer.start();
		}
		
	}
	
	private class StepActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (p == null) {
				p = new Parser(BefungeMain.this.textArea.getText(), new JFrame("Dialog Box"));
			}
			p.interpret();
			p.advance();
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

}
