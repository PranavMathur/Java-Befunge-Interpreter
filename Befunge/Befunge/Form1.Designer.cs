namespace Befunge
{
	partial class Form1
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.inputText = new System.Windows.Forms.TextBox();
			this.retrieveInput = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// inputText
			// 
			this.inputText.Location = new System.Drawing.Point(86, 82);
			this.inputText.Name = "inputText";
			this.inputText.Size = new System.Drawing.Size(100, 20);
			this.inputText.TabIndex = 0;
			// 
			// retrieveInput
			// 
			this.retrieveInput.Location = new System.Drawing.Point(86, 109);
			this.retrieveInput.Name = "retrieveInput";
			this.retrieveInput.Size = new System.Drawing.Size(75, 23);
			this.retrieveInput.TabIndex = 1;
			this.retrieveInput.Text = "Retrieve";
			this.retrieveInput.UseVisualStyleBackColor = true;
			this.retrieveInput.Click += new System.EventHandler(this.retrieveInput_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 261);
			this.Controls.Add(this.retrieveInput);
			this.Controls.Add(this.inputText);
			this.Name = "Form1";
			this.Text = "Form1";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.TextBox inputText;
		private System.Windows.Forms.Button retrieveInput;
	}
}