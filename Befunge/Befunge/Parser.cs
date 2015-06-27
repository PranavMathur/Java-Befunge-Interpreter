using System;
using System.Text;

namespace Befunge {
	class Parser {
		private String[,] tokens = new String[MAX_Y, MAX_X];
		private int currentX = 0;
		private int currentY = 0;
		private CardinalDirections currentDir = CardinalDirections.RIGHT;

		public const int MAX_X = 80;
		public const int MAX_Y = 25;

		private bool isRunning = true;
		private bool readingString = false;
		private bool updateNeeded = false;

		private StringBuilder output = new StringBuilder();

		private Interpreter interpreter = new Interpreter();

		public Interpreter getInterpreter() {
			return interpreter;
		}

		public void fillArray(String rawTokens) {
			fillArray(rawTokens.Split(new char[] { '\n' }));
		}

		public void fillArray(String[] rawTokens) {
			for (int currentRow = 0; currentRow < rawTokens.Length && currentRow < MAX_Y; currentRow++) {
				for (int i = 0; i < rawTokens[currentRow].Length && i < MAX_X; i++) {
					tokens[currentRow, i] = rawTokens[currentRow].Substring(i, i + 1);
				}
			}
		}

		public void fillArray(String[,] rawTokens) {
			for (int currentRow = 0; currentRow < rawTokens.GetLength(0) && currentRow < MAX_Y; currentRow++) {
				for (int i = 0; i < rawTokens.GetLength(1) && i < MAX_X; i++) {
					this.tokens[currentRow, i] = rawTokens[currentRow, i];
				}
			}
		}

	}
}
