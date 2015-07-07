namespace Befunge {
	partial class GUI {
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing) {
			if (disposing && (components != null)) {
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent() {
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GUI));
			this.inputArea = new System.Windows.Forms.TextBox();
			this.stackStream = new System.Windows.Forms.Label();
			this.outputStream = new System.Windows.Forms.Label();
			this.stepButton = new System.Windows.Forms.Button();
			this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.newToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.openToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.saveToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.saveAsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.closeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.runToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.runToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
			this.walkToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.crawlToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.resetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.terminateToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.menu = new System.Windows.Forms.MenuStrip();
			this.xStatus = new System.Windows.Forms.Label();
			this.yStatus = new System.Windows.Forms.Label();
			this.menu.SuspendLayout();
			this.SuspendLayout();
			// 
			// inputArea
			// 
			this.inputArea.AcceptsReturn = true;
			this.inputArea.AcceptsTab = true;
			this.inputArea.AllowDrop = true;
			this.inputArea.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.inputArea.Location = new System.Drawing.Point(13, 42);
			this.inputArea.Multiline = true;
			this.inputArea.Name = "inputArea";
			this.inputArea.Size = new System.Drawing.Size(393, 225);
			this.inputArea.TabIndex = 0;
			// 
			// stackStream
			// 
			this.stackStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.stackStream.Location = new System.Drawing.Point(9, 301);
			this.stackStream.Name = "stackStream";
			this.stackStream.Size = new System.Drawing.Size(393, 13);
			this.stackStream.TabIndex = 3;
			this.stackStream.Text = "Stack: ";
			// 
			// outputStream
			// 
			this.outputStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.outputStream.Location = new System.Drawing.Point(10, 314);
			this.outputStream.Name = "outputStream";
			this.outputStream.Size = new System.Drawing.Size(393, 13);
			this.outputStream.TabIndex = 4;
			this.outputStream.Text = "Output: ";
			// 
			// stepButton
			// 
			this.stepButton.Location = new System.Drawing.Point(12, 354);
			this.stepButton.Name = "stepButton";
			this.stepButton.Size = new System.Drawing.Size(75, 23);
			this.stepButton.TabIndex = 5;
			this.stepButton.Text = "Step";
			this.stepButton.UseVisualStyleBackColor = true;
			this.stepButton.Click += new System.EventHandler(this.StepHandler);
			// 
			// fileToolStripMenuItem
			// 
			this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.newToolStripMenuItem,
            this.openToolStripMenuItem,
            this.saveToolStripMenuItem,
            this.saveAsToolStripMenuItem,
            this.closeToolStripMenuItem});
			this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
			this.fileToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
			this.fileToolStripMenuItem.Text = "File";
			// 
			// newToolStripMenuItem
			// 
			this.newToolStripMenuItem.Name = "newToolStripMenuItem";
			this.newToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.newToolStripMenuItem.Text = "New";
			this.newToolStripMenuItem.Click += new System.EventHandler(this.NewHandler);
			// 
			// openToolStripMenuItem
			// 
			this.openToolStripMenuItem.Name = "openToolStripMenuItem";
			this.openToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.openToolStripMenuItem.Text = "Open";
			this.openToolStripMenuItem.Click += new System.EventHandler(this.OpenHandler);
			// 
			// saveToolStripMenuItem
			// 
			this.saveToolStripMenuItem.Name = "saveToolStripMenuItem";
			this.saveToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.saveToolStripMenuItem.Text = "Save";
			this.saveToolStripMenuItem.Click += new System.EventHandler(this.SaveHandler);
			// 
			// saveAsToolStripMenuItem
			// 
			this.saveAsToolStripMenuItem.Name = "saveAsToolStripMenuItem";
			this.saveAsToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.saveAsToolStripMenuItem.Text = "Save As";
			this.saveAsToolStripMenuItem.Click += new System.EventHandler(this.SaveAsHandler);
			// 
			// closeToolStripMenuItem
			// 
			this.closeToolStripMenuItem.Name = "closeToolStripMenuItem";
			this.closeToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.closeToolStripMenuItem.Text = "Close";
			// 
			// runToolStripMenuItem
			// 
			this.runToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.runToolStripMenuItem1,
            this.walkToolStripMenuItem,
            this.crawlToolStripMenuItem,
            this.resetToolStripMenuItem,
            this.terminateToolStripMenuItem});
			this.runToolStripMenuItem.Name = "runToolStripMenuItem";
			this.runToolStripMenuItem.Size = new System.Drawing.Size(40, 20);
			this.runToolStripMenuItem.Text = "Run";
			// 
			// runToolStripMenuItem1
			// 
			this.runToolStripMenuItem1.Name = "runToolStripMenuItem1";
			this.runToolStripMenuItem1.Size = new System.Drawing.Size(128, 22);
			this.runToolStripMenuItem1.Text = "Run";
			this.runToolStripMenuItem1.Click += new System.EventHandler(this.RunHandler);
			// 
			// walkToolStripMenuItem
			// 
			this.walkToolStripMenuItem.Name = "walkToolStripMenuItem";
			this.walkToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.walkToolStripMenuItem.Text = "Walk";
			// 
			// crawlToolStripMenuItem
			// 
			this.crawlToolStripMenuItem.Name = "crawlToolStripMenuItem";
			this.crawlToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.crawlToolStripMenuItem.Text = "Crawl";
			// 
			// resetToolStripMenuItem
			// 
			this.resetToolStripMenuItem.Name = "resetToolStripMenuItem";
			this.resetToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.resetToolStripMenuItem.Text = "Reset";
			// 
			// terminateToolStripMenuItem
			// 
			this.terminateToolStripMenuItem.Name = "terminateToolStripMenuItem";
			this.terminateToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.terminateToolStripMenuItem.Text = "Terminate";
			// 
			// helpToolStripMenuItem
			// 
			this.helpToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.aboutToolStripMenuItem});
			this.helpToolStripMenuItem.Name = "helpToolStripMenuItem";
			this.helpToolStripMenuItem.Size = new System.Drawing.Size(44, 20);
			this.helpToolStripMenuItem.Text = "Help";
			// 
			// aboutToolStripMenuItem
			// 
			this.aboutToolStripMenuItem.Name = "aboutToolStripMenuItem";
			this.aboutToolStripMenuItem.Size = new System.Drawing.Size(107, 22);
			this.aboutToolStripMenuItem.Text = "About";
			// 
			// menu
			// 
			this.menu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.runToolStripMenuItem,
            this.helpToolStripMenuItem});
			this.menu.Location = new System.Drawing.Point(0, 0);
			this.menu.Name = "menu";
			this.menu.Size = new System.Drawing.Size(418, 24);
			this.menu.TabIndex = 1;
			this.menu.Text = "menuStrip1";
			// 
			// label1
			// 
			this.xStatus.AutoSize = true;
			this.xStatus.Location = new System.Drawing.Point(12, 285);
			this.xStatus.Name = "X Status";
			this.xStatus.Size = new System.Drawing.Size(35, 13);
			this.xStatus.TabIndex = 6;
			this.xStatus.Text = "x = 0";
			this.xStatus.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			// 
			// label2
			// 
			this.yStatus.AutoSize = true;
			this.yStatus.Location = new System.Drawing.Point(53, 285);
			this.yStatus.Name = "Y Status";
			this.yStatus.Size = new System.Drawing.Size(35, 13);
			this.yStatus.TabIndex = 7;
			this.yStatus.Text = "y = 0";
			this.yStatus.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			// 
			// GUI
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(418, 382);
			this.Controls.Add(this.yStatus);
			this.Controls.Add(this.xStatus);
			this.Controls.Add(this.stepButton);
			this.Controls.Add(this.outputStream);
			this.Controls.Add(this.stackStream);
			this.Controls.Add(this.inputArea);
			this.Controls.Add(this.menu);
			this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
			this.Name = "GUI";
			this.Text = "Befunge Interpreter";
			this.menu.ResumeLayout(false);
			this.menu.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		public System.Windows.Forms.TextBox inputArea;
		private System.Windows.Forms.Label stackStream;
		private System.Windows.Forms.Label outputStream;
		private System.Windows.Forms.Button stepButton;
		private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem newToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem openToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveAsToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem closeToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem runToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem runToolStripMenuItem1;
		private System.Windows.Forms.ToolStripMenuItem walkToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem crawlToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem resetToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem terminateToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
		private System.Windows.Forms.MenuStrip menu;
		private System.Windows.Forms.Label xStatus;
		private System.Windows.Forms.Label yStatus;

	}
}