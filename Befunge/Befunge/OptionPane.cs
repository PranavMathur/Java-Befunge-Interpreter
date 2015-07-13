using System;
using System.Drawing;
using System.Windows.Forms;

namespace Befunge {
	static class OptionPane {
		public static DialogResult ShowConfirmDialog(string str) {
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
			inputBox.Close();
			return result;
		}

		public static DialogResult ShowInputDialog(ref string input, string str) {
			Size size = new Size(200, 100);
			Form inputBox = new Form();

			inputBox.FormBorderStyle = FormBorderStyle.FixedDialog;
			inputBox.ClientSize = size;
			inputBox.Text = "Input";

			Label label = new Label();
			label.Size = new Size(size.Width - 10, 23);
			label.Location = new Point(5, 5);
			label.Text = str;
			inputBox.Controls.Add(label);

			TextBox textBox = new TextBox();
			textBox.Size = new Size(size.Width - 10, 23);
			textBox.Location = new Point(5, 30);
			textBox.Text = input;
			inputBox.Controls.Add(textBox);

			Button okButton = new Button();
			okButton.DialogResult = DialogResult.OK;
			okButton.Name = "okButton";
			okButton.Size = new Size(75, 23);
			okButton.Text = "&OK";
			okButton.Location = new Point(size.Width - 175, 60);
			inputBox.Controls.Add(okButton);

			Button cancelButton = new Button();
			cancelButton.DialogResult = DialogResult.Cancel;
			cancelButton.Name = "cancelButton";
			cancelButton.Size = new Size(75, 23);
			cancelButton.Text = "&Cancel";
			cancelButton.Location = new Point(size.Width - 95, 60);
			inputBox.Controls.Add(cancelButton);

			inputBox.AcceptButton = okButton;
			inputBox.CancelButton = cancelButton;

			DialogResult result = inputBox.ShowDialog();
			input = textBox.Text;
			inputBox.Close();
			return result;
		}
	}
}
