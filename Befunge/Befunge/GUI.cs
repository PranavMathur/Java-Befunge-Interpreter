using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Timers;

namespace Befunge {
	public partial class GUI : Form {

		Parser p;

		private int lastX;
		private int lastY;

		private const int WALK_STEP_TIME = 100;
		private const int CRAWL_STEP_TIME = 333;

		private string currentFile;
		private string originalText;
		private string editedText;

		private bool terminated;

		System.Timers.Timer timer;

		delegate void UpdateCallback();

		public GUI() {
			InitializeComponent();
			timer = new System.Timers.Timer();
			timer.Elapsed += this.StepHandler;
			lastX = 0;
			lastY = 0;
			currentFile = null;
			originalText = null;
			editedText = null;
			terminated = false;
		}

		public GUI(string file) : this() {
			OpenFile(file);
			
		}

		private void UpdatePositions() {
			lastX = (p ?? new Parser("")).GetCurrentX();
			lastY = (p ?? new Parser("")).GetCurrentY();
		}

		private void UpdateStreams() {
			if (outputStream.InvokeRequired) {
				Invoke(new UpdateCallback(UpdateStreams));
			}
			else {
				xStatus.Text = "x = " + lastX;
				yStatus.Text = "y = " + lastY;
				bool running = (p ?? new Parser("")).IsRunning();
				xStatus.ForeColor = running ? Color.Green : Color.Red;
				yStatus.ForeColor = running ? Color.Green : Color.Red;
				stackStream.Text = (p ?? new Parser("")).GetInterpreter().GetStack().ToString();
				outputStream.Text = (p ?? new Parser("")).GetOutput();
			}
		}

		private void UpdateSource() {
			if (textArea.InvokeRequired) {
				Invoke(new UpdateCallback(UpdateSource));
			}
			else {
				textArea.Text = p.GetRawTokens().TrimEnd();
				p.SetUpdateNeeded(false);
				editedText = p.GetRawTokens();
			}
		}

		private void NewHandler(object sender, EventArgs e) {
			DialogResult response = DialogResult.No;
			if ((originalText != null || textArea.Text != "") && ((originalText == null ^ textArea.Text == "")
					|| (originalText.TrimEnd() == textArea.Text.TrimEnd())))
				response = OptionPane.ShowConfirmDialog("Do you want to save your work?");
			if (response == DialogResult.Yes) {
				SaveFile();
				textArea.Text = "";
				currentFile = null;
			}
			else if (response == DialogResult.No) {
				textArea.Text = "";
				currentFile = null;
			}
		}

		private void OpenHandler(object sender, EventArgs e) {
			OpenFile();
		}

		private void SaveHandler(object sender, EventArgs e) {
			SaveFile();
		}

		private void SaveAsHandler(object sender, EventArgs e) {
			currentFile = null;
			SaveFile();
		}

		private void CloseHandler(object sender, EventArgs e) {
			DialogResult response = DialogResult.No;
			if ((originalText != null || textArea.Text != "") && ((originalText == null ^ textArea.Text == "")
					|| (originalText.TrimEnd() == textArea.Text.TrimEnd())))
				response = OptionPane.ShowConfirmDialog("Do you want to save your work?");
			if (response == DialogResult.Yes) {
				SaveFile();
				Close();
			}
			else if (response == DialogResult.No) {
				Close();
			}
		}

		private void RunHandler(object sender, EventArgs e) {
			if (p == null)
				p = new Parser(textArea.Text);
			terminated = false;
			while (p.IsRunning() && !terminated) {
				p.Interpret();
				UpdatePositions();
				p.Advance();
			}
			UpdateStreams();
			if (p.IsUpdateNeeded()) {
				UpdateSource();
			}
			p = null;
		}

		private void WalkHandler(object sender, EventArgs e) {
			timer.Enabled = false;
			timer.Interval = WALK_STEP_TIME;
			timer.Enabled = true;
		}

		private void CrawlHandler(object sender, EventArgs e) {
			timer.Enabled = false;
			timer.Interval = CRAWL_STEP_TIME;
			timer.Enabled = true;
		}

		private void StepHandler(object sender, EventArgs e) {
			if (originalText == null)
				originalText = textArea.Text;
			if (p == null) {
				p = new Parser(textArea.Text);
			}

			if (p.IsRunning()) {
				p.Interpret();
				UpdatePositions();
				p.Advance();
			}
			UpdateStreams();
			if (p.IsUpdateNeeded()) {
				UpdateSource();
			}
			if (!p.IsRunning() || terminated) {
				p = null;
				timer.Enabled = false;
			}
		}

		private void ResetHandler(object sender, EventArgs e) {
			p = null;
			if (editedText != null && editedText != originalText)
				textArea.Text = originalText.TrimEnd();
			originalText = null;
			UpdateStreams();
		}

		private void TerminateHandler(object sender, EventArgs e) {
			terminated = true;
		}

		private void AboutHandler(object sender, EventArgs e) {
			MessageBox.Show("About Befunge");
		}

		private void OpenFile() {
			OpenFileDialog dialog = new OpenFileDialog();
			dialog.Filter = "bf files (*.bf)|*.bf|All files (*.*)|*.*";
			dialog.FilterIndex = 0;
			dialog.RestoreDirectory = false;

			if (dialog.ShowDialog() == DialogResult.OK) {
				OpenFile(dialog.FileName);
			}
		}

		private void OpenFile(string file) {
			try {
				string fileStr = file;
				currentFile = fileStr;
				using (StreamReader sr = new StreamReader(fileStr)) {
					textArea.Text = sr.ReadToEnd().TrimEnd();
					originalText = textArea.Text;
				}
			}
			catch (Exception ex) {
				Console.WriteLine("Error: Could not read file from disk. Original error: " + ex.Message);
			}
		}

		private void SaveFile() {
			string fileStr = currentFile;
			originalText = textArea.Text;
			if (fileStr == null) {
				SaveFileDialog dialog = new SaveFileDialog();
				dialog.Filter = "bf files (*.bf)|*.bf|All files (*.*)|*.*";
				dialog.FilterIndex = 0;
				dialog.RestoreDirectory = false;

				if (dialog.ShowDialog() == DialogResult.OK) {
					fileStr = dialog.FileName;
					currentFile = fileStr;
				}
			}
			try {
				using (StreamWriter sr = new StreamWriter(fileStr)) {
					sr.WriteLine(textArea.Text);
				}
			}
			catch (Exception ex) {
				Console.WriteLine("Error: Could not write file to disk. Original error: " + ex.Message);
			}
		}

	}
}
