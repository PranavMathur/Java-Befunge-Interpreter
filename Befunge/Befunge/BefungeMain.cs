using System;
using System.IO;
using System.Text;
using System.Windows.Forms;
using System.ComponentModel;
using System.Drawing;

namespace Befunge {
	static class BefungeMain {

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args) {
			Application.EnableVisualStyles();
			if (args.Length == 0) {
				Application.Run(new GUI());
			}
			else {
				Application.Run(new GUI(args[0]));
			}
		}

	}
}