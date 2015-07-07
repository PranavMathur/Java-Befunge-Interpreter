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

namespace Befunge {
	public partial class GUI : Form {

		Parser p;

		private string currentFile;
		private string originalText;

		public GUI() {
			InitializeComponent();
		}

		private void NewHandler(object sender, EventArgs e) {
			DialogResult response = DialogResult.No;
			if ((originalText == null ^ inputArea.Text == null)
					|| originalText.TrimEnd() == inputArea.Text.TrimEnd())
				response = ShowConfirmDialog("Do you want to save your work?");
			if (response == DialogResult.Yes) {
				SaveFile();
				inputArea.Text = "";
				currentFile = null;
			}
			else if (response == DialogResult.No) {
				inputArea.Text = "";
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

		private void RunHandler(object sender, EventArgs e) {
			if (p == null)
				p = new Parser(inputArea.Text);
			while (p.IsRunning()) {
				p.Interpret();
				p.Advance();
			}
			outputStream.Text = p.GetOutput();
			p = null;
		}

		private void StepHandler(object sender, EventArgs e) {
			if (p == null)
				p = new Parser(inputArea.Text);
			if (p.IsRunning()) {
				p.Interpret();
				p.Advance();
			}
			stackStream.Text = p.GetInterpreter().GetStack().ToString();
			outputStream.Text = p.GetOutput();
			if (!p.IsRunning())
				p = null;
		}

		private void OpenFile() {
			OpenFileDialog dialog = new OpenFileDialog();
			dialog.Filter = "bf files (*.bf)|*.bf|All files (*.*)|*.*";
			dialog.FilterIndex = 0;
			dialog.RestoreDirectory = false;

			if (dialog.ShowDialog() == DialogResult.OK) {
				try {
					string fileStr = dialog.FileName;
					currentFile = fileStr;
					using (StreamReader sr = new StreamReader(fileStr)) {
						inputArea.Text = sr.ReadToEnd().TrimEnd();
						originalText = inputArea.Text;
					}
				}
				catch (Exception ex) {
					Console.WriteLine("Error: Could not read file from disk. Original error: " + ex.Message);
				}
			}
		}

		private void SaveFile() {
			string fileStr = currentFile;
			originalText = inputArea.Text;
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
					sr.WriteLine(inputArea.Text);
				}
			}
			catch (Exception ex) {
				Console.WriteLine("Error: Could not write file to disk. Original error: " + ex.Message);
			}
		}

		private static DialogResult ShowConfirmDialog(string str) {
			Size size = new Size(245, 70);
			Form inputBox = new Form();

			inputBox.FormBorderStyle = FormBorderStyle.FixedDialog;
			inputBox.ClientSize = size;
			inputBox.Text = "Confirm";

			Label label = new Label();
			label.Size = new Size(size.Width - 10, 23);
			label.Location = new Point(5, 5);
			label.Text = str;
			inputBox.Controls.Add(label);

			Button yesButton = new Button();
			yesButton.DialogResult = DialogResult.Yes;
			yesButton.Name = "yesButton";
			yesButton.Size = new Size(75, 23);
			yesButton.Text = "&Yes";
			yesButton.Location = new Point(5, 30);
			inputBox.Controls.Add(yesButton);

			Button noButton = new Button();
			noButton.DialogResult = DialogResult.No;
			noButton.Name = "noButton";
			noButton.Size = new Size(75, 23);
			noButton.Text = "&No";
			noButton.Location = new Point(85, 30);
			inputBox.Controls.Add(noButton);

			Button cancelButton = new Button();
			cancelButton.DialogResult = DialogResult.Cancel;
			cancelButton.Name = "cancelButton";
			cancelButton.Size = new Size(75, 23);
			cancelButton.Text = "&Cancel";
			cancelButton.Location = new Point(165, 30);
			inputBox.Controls.Add(cancelButton);

			inputBox.AcceptButton = yesButton;
			inputBox.CancelButton = cancelButton;

			DialogResult result = inputBox.ShowDialog();
			return result;
		}

	}
}
