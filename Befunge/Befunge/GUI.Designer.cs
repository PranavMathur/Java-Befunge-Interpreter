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
			this.menu = new System.Windows.Forms.MenuStrip();
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
			this.statusStream = new System.Windows.Forms.TextBox();
			this.stackStream = new System.Windows.Forms.TextBox();
			this.outputStream = new System.Windows.Forms.TextBox();
			this.button1 = new System.Windows.Forms.Button();
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
			this.inputArea.Size = new System.Drawing.Size(264, 225);
			this.inputArea.TabIndex = 0;
			// 
			// menu
			// 
			this.menu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.runToolStripMenuItem,
            this.helpToolStripMenuItem});
			this.menu.Location = new System.Drawing.Point(0, 0);
			this.menu.Name = "menu";
			this.menu.Size = new System.Drawing.Size(289, 24);
			this.menu.TabIndex = 1;
			this.menu.Text = "menuStrip1";
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
			// 
			// openToolStripMenuItem
			// 
			this.openToolStripMenuItem.Name = "openToolStripMenuItem";
			this.openToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.openToolStripMenuItem.Text = "Open";
			// 
			// saveToolStripMenuItem
			// 
			this.saveToolStripMenuItem.Name = "saveToolStripMenuItem";
			this.saveToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.saveToolStripMenuItem.Text = "Save";
			// 
			// saveAsToolStripMenuItem
			// 
			this.saveAsToolStripMenuItem.Name = "saveAsToolStripMenuItem";
			this.saveAsToolStripMenuItem.Size = new System.Drawing.Size(114, 22);
			this.saveAsToolStripMenuItem.Text = "Save As";
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
			// statusBar
			// 
			this.statusStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.statusStream.Location = new System.Drawing.Point(13, 274);
			this.statusStream.Name = "statusStream";
			this.statusStream.ReadOnly = true;
			this.statusStream.Size = new System.Drawing.Size(264, 20);
			this.statusStream.TabIndex = 2;
			this.statusStream.Text = "Status";
			// 
			// stackBar
			// 
			this.stackStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.stackStream.Location = new System.Drawing.Point(13, 301);
			this.stackStream.Name = "stackStream";
			this.stackStream.ReadOnly = true;
			this.stackStream.Size = new System.Drawing.Size(264, 20);
			this.stackStream.TabIndex = 3;
			this.stackStream.Text = "Stack";
			// 
			// outputBar
			// 
			this.outputStream.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.outputStream.Location = new System.Drawing.Point(13, 328);
			this.outputStream.Name = "outputStream";
			this.outputStream.ReadOnly = true;
			this.outputStream.Size = new System.Drawing.Size(264, 20);
			this.outputStream.TabIndex = 4;
			this.outputStream.Text = "Output";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(12, 354);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(75, 23);
			this.button1.TabIndex = 5;
			this.button1.Text = "Step";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.StepHandler);
			// 
			// GUI
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(289, 382);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.outputStream);
			this.Controls.Add(this.stackStream);
			this.Controls.Add(this.statusStream);
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
		private System.Windows.Forms.MenuStrip menu;
		private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem runToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem newToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem openToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem saveAsToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem closeToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem runToolStripMenuItem1;
		private System.Windows.Forms.ToolStripMenuItem walkToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem crawlToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem resetToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem terminateToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
		private System.Windows.Forms.TextBox statusStream;
		private System.Windows.Forms.TextBox stackStream;
		private System.Windows.Forms.TextBox outputStream;
		private System.Windows.Forms.Button button1;

	}
}