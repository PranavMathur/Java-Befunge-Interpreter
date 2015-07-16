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
			this.textArea = new System.Windows.Forms.TextBox();
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
			this.stepToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripSeparator();
			this.resetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.terminateToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.menu = new System.Windows.Forms.MenuStrip();
			this.xStatus = new System.Windows.Forms.Label();
			this.yStatus = new System.Windows.Forms.Label();
			this.resetButton = new System.Windows.Forms.Button();
			this.menu.SuspendLayout();
			this.SuspendLayout();
			// 
			// textArea
			// 
			this.textArea.AcceptsReturn = true;
			this.textArea.AcceptsTab = true;
			this.textArea.AllowDrop = true;
			this.textArea.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.textArea.Location = new System.Drawing.Point(13, 27);
			this.textArea.Multiline = true;
			this.textArea.Name = "textArea";
			this.textArea.Size = new System.Drawing.Size(409, 281);
			this.textArea.TabIndex = 0;
			// 
			// stackStream
			// 
			this.stackStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.stackStream.Location = new System.Drawing.Point(9, 325);
			this.stackStream.Name = "stackStream";
			this.stackStream.Size = new System.Drawing.Size(393, 13);
			this.stackStream.TabIndex = 3;
			this.stackStream.Text = "Stack: ";
			// 
			// outputStream
			// 
			this.outputStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.outputStream.Location = new System.Drawing.Point(9, 338);
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
			this.closeToolStripMenuItem.Click += new System.EventHandler(this.CloseHandler);
			// 
			// runToolStripMenuItem
			// 
			this.runToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.runToolStripMenuItem1,
            this.walkToolStripMenuItem,
            this.crawlToolStripMenuItem,
            this.stepToolStripMenuItem,
            this.toolStripMenuItem1,
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
			this.walkToolStripMenuItem.Click += new System.EventHandler(this.WalkHandler);
			// 
			// crawlToolStripMenuItem
			// 
			this.crawlToolStripMenuItem.Name = "crawlToolStripMenuItem";
			this.crawlToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.crawlToolStripMenuItem.Text = "Crawl";
			this.crawlToolStripMenuItem.Click += new System.EventHandler(this.CrawlHandler);
			// 
			// stepToolStripMenuItem
			// 
			this.stepToolStripMenuItem.Name = "stepToolStripMenuItem";
			this.stepToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.stepToolStripMenuItem.Text = "Step";
			this.stepToolStripMenuItem.Click += new System.EventHandler(this.StepHandler);
			// 
			// toolStripMenuItem1
			// 
			this.toolStripMenuItem1.Name = "toolStripMenuItem1";
			this.toolStripMenuItem1.Size = new System.Drawing.Size(125, 6);
			// 
			// resetToolStripMenuItem
			// 
			this.resetToolStripMenuItem.Name = "resetToolStripMenuItem";
			this.resetToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.resetToolStripMenuItem.Text = "Reset";
			this.resetToolStripMenuItem.Click += new System.EventHandler(this.ResetHandler);
			// 
			// terminateToolStripMenuItem
			// 
			this.terminateToolStripMenuItem.Name = "terminateToolStripMenuItem";
			this.terminateToolStripMenuItem.Size = new System.Drawing.Size(128, 22);
			this.terminateToolStripMenuItem.Text = "Terminate";
			this.terminateToolStripMenuItem.Click += new System.EventHandler(this.TerminateHandler);
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
			this.aboutToolStripMenuItem.Click += new System.EventHandler(this.AboutHandler);
			// 
			// menu
			// 
			this.menu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.runToolStripMenuItem,
            this.helpToolStripMenuItem});
			this.menu.Location = new System.Drawing.Point(0, 0);
			this.menu.Name = "menu";
			this.menu.Size = new System.Drawing.Size(434, 24);
			this.menu.TabIndex = 1;
			this.menu.Text = "menuStrip1";
			// 
			// xStatus
			// 
			this.xStatus.AutoSize = true;
			this.xStatus.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.xStatus.ForeColor = System.Drawing.Color.Red;
			this.xStatus.Location = new System.Drawing.Point(9, 311);
			this.xStatus.Name = "xStatus";
			this.xStatus.Size = new System.Drawing.Size(42, 14);
			this.xStatus.TabIndex = 6;
			this.xStatus.Text = "x = 0";
			// 
			// yStatus
			// 
			this.yStatus.AutoSize = true;
			this.yStatus.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.yStatus.ForeColor = System.Drawing.Color.Red;
			this.yStatus.Location = new System.Drawing.Point(57, 311);
			this.yStatus.Name = "yStatus";
			this.yStatus.Size = new System.Drawing.Size(42, 14);
			this.yStatus.TabIndex = 7;
			this.yStatus.Text = "y = 0";
			// 
			// resetButton
			// 
			this.resetButton.Location = new System.Drawing.Point(93, 354);
			this.resetButton.Name = "resetButton";
			this.resetButton.Size = new System.Drawing.Size(75, 23);
			this.resetButton.TabIndex = 8;
			this.resetButton.Text = "Reset";
			this.resetButton.UseVisualStyleBackColor = true;
			this.resetButton.Click += new System.EventHandler(this.ResetHandler);
			// 
			// GUI
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(434, 381);
			this.Controls.Add(this.resetButton);
			this.Controls.Add(this.yStatus);
			this.Controls.Add(this.xStatus);
			this.Controls.Add(this.stepButton);
			this.Controls.Add(this.outputStream);
			this.Controls.Add(this.stackStream);
			this.Controls.Add(this.textArea);
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

		public System.Windows.Forms.TextBox textArea;
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
		private System.Windows.Forms.Button resetButton;
		private System.Windows.Forms.ToolStripMenuItem stepToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripMenuItem1;

	}
}