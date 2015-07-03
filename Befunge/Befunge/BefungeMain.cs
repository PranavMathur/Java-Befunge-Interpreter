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
		static void Main() {
			GUI gui = new GUI();
			Application.Run(gui);
			Console.WriteLine("The Program has Ended");
		}
	}
}