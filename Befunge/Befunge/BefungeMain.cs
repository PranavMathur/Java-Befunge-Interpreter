using System;
using System.IO;

namespace Befunge {
	static class BefungeMain {
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() {
			int[,] a = new int[,] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
			Console.WriteLine(a[3, 1]); //11
			Console.WriteLine(a.Length); //12
			Console.WriteLine(a.GetLength(0));
			Console.WriteLine(a.GetLength(1));
		}
	}
}