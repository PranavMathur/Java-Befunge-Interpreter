using System;
using System.Text;
using System.Windows.Forms;
using System.ComponentModel;
using System.Drawing;

namespace Befunge {

	class Parser {
		private string[,] tokens = new string[MAX_Y, MAX_X];
		private int currentX = 0;
		private int currentY = 0;
		private CardinalDirections currentDir = CardinalDirections.RIGHT;

		public const int MAX_X = 80;
		public const int MAX_Y = 25;

		private bool running = true;
		private bool readingString = false;
		private bool updateNeeded = false;

		private StringBuilder output = new StringBuilder("Output: ");

		private Interpreter interpreter = new Interpreter();

		private Random rng = new Random();

		public Interpreter GetInterpreter() {
			return interpreter;
		}

		public Parser(string rawTokens) {
			if (rawTokens == "")
				rawTokens = "  \n  ";
			FillArray(rawTokens);
		}

		public Parser(string[] rawTokens) {
			FillArray(rawTokens);
		}

		public Parser(string[,] rawTokens) {
			FillArray(rawTokens);
		}

		public void FillArray(string rawTokens) {
			FillArray(rawTokens.Split(new char[] { '\n' }));
		}

		public void FillArray(string[] rawTokens) {
			for (int currentRow = 0; currentRow < rawTokens.Length && currentRow < MAX_Y; currentRow++) {
				for (int i = 0; i < rawTokens[currentRow].Length && i < MAX_X; i++) {
					tokens[currentRow, i] = rawTokens[currentRow].Substring(i, 1);
				}
			}
		}

		public void FillArray(string[,] rawTokens) {
			for (int currentRow = 0; currentRow < rawTokens.GetLength(0) && currentRow < MAX_Y; currentRow++) {
				for (int i = 0; i < rawTokens.GetLength(1) && i < MAX_X; i++) {
					this.tokens[currentRow, i] = rawTokens[currentRow, i];
				}
			}
		}

		public String GetToken(int x, int y) {
			return tokens[y,x];
		}

		public void SetToken(int x, int y, String v) {
			tokens[y,x] = v;
		}

		public void Advance() {
			if (currentDir == CardinalDirections.RIGHT) {
				currentX++;
			}
			else if (currentDir == CardinalDirections.LEFT) {
				currentX--;
			}
			else if (currentDir == CardinalDirections.UP) {
				currentY--;
			}
			else {
				currentY++;
			}
			if (currentX < 0) {
				currentX += MAX_X; ;
			}
			if (currentY < 0) {
				currentY += MAX_Y;
			}
			if (currentX >= MAX_X) {
				currentX -= MAX_X;
			}
			if (currentY >= MAX_Y) {
				currentY -= MAX_Y;
			}
			if (!readingString && (GetToken(currentX, currentY) == null || GetToken(currentX, currentY) == " ")) {
				Advance();
			}
		}

		public CardinalDirections NewDirection(int i) {
			return (new CardinalDirections[] {CardinalDirections.RIGHT, CardinalDirections.DOWN, 
					CardinalDirections.LEFT, CardinalDirections.UP})[((int)currentDir + i) % 4];
		}

		public void Interpret() {
			string currentToken = GetToken(currentX, currentY); //retrieves the current token
			if (currentToken == null) //does nothing if the token is null
				return;
			if ("0123456789".IndexOf(currentToken) != -1) { //pushes the integer representation of the token to the stack
				interpreter.Push(Int32.Parse(currentToken));
				return;
			}
			else if ("abcdef".IndexOf(currentToken) != -1) { //pushes the integer representation of the token to the stack
				interpreter.Push(10 + (int)(currentToken[0]) - 97);
				return;
			}
			switch (currentToken) { //performs the correct operation based on the current token
				case "+": //performs the addition operation of the interpreter
					interpreter.Add();
					break;
				case "-": //performs the subtraction operation of the interpreter
					interpreter.Subtract();
					break;
				case "*": //performs the multiplication operation of the interpreter
					interpreter.Multiply();
					break;
				case "/": //performs the division operation of the interpreter
					interpreter.Divide();
					break;
				case "%": //performs the modulus operation of the interpreter
					interpreter.Modulo();
					break;
				case "!": //performs the logical not operation of the interpreter
					interpreter.NOT();
					break;
				case "`": //performs the greater than operation of the interpreter
					interpreter.GreaterThan();
					break;
				case ">": //sets the current direction of the pointer to right
					currentDir = CardinalDirections.RIGHT;
					break;
				case "<": //sets the current direction of the pointer to left
					currentDir = CardinalDirections.LEFT;
					break;
				case "v": //sets the current direction of the pointer to down
					currentDir = CardinalDirections.DOWN;
					break;
				case "^": //sets the current direction of the pointer to up
					currentDir = CardinalDirections.UP;
					break;
				case "?": //sets the current direction of the pointer to a random direction
					int rand = (int)(rng.NextDouble() * 4);
					currentDir = (new CardinalDirections[] {CardinalDirections.RIGHT, CardinalDirections.DOWN, 
					CardinalDirections.LEFT, CardinalDirections.UP})[rand];
					break;
				case "[": //sets the current direction of the pointer to one clockwise rotation of the current direction
					currentDir = NewDirection(-1);
					break;
				case "]": //sets the current direction of the pointer to one counter-clockwise rotation of the current direction
					currentDir = NewDirection(1);
					break;
				case "x": //moves the pointer to the location at <x, y> where x and y are popped from the stack
					int newY = interpreter.Pop();
					int newX = interpreter.Pop();
					SetCurrentX(newX);
					SetCurrentY(newY);
					currentDir = NewDirection(2);
					Advance();
					currentDir = NewDirection(2);
					break;
				case "_": //sets the current direction of the pointer to right if the top element of the stack is 0, left otherwise
					if (interpreter.Pop() == 0) {
						currentDir = CardinalDirections.RIGHT;
					}
					else {
						currentDir = CardinalDirections.LEFT;
					}
					break;
				case "|": //sets the current direction of the pointer to down if the top element of the stack is 0, up otherwise
					if (interpreter.Pop() == 0) {
						currentDir = CardinalDirections.DOWN;
					}
					else {
						currentDir = CardinalDirections.UP;
					}
					break;
				case "w": //performs a clockwise rotation of the pointer if the second element of the stack is larger than the first,
					//counter-clockwise if otherwise
					int b = interpreter.Pop();
					int a = interpreter.Pop();
					if (a < b) {
						currentDir = NewDirection(-1);
					}
					else if (a > b) {
						currentDir = NewDirection(1);
					}
					break;
				case "\"": //pushes the string in the playfield onto the stack
					InterpretString();
					break;
				case ":": //duplicates the value on the top of the stack
					interpreter.Duplicate();
					break;
				case "\\": //swaps the values on the top of the stack
					interpreter.Swap();
					break;
				case "$": //disposes of the top value of the stack
					interpreter.Pop();
					break;
				case "n": //clears the stack
					while (!interpreter.GetStack().IsEmpty()) {
						interpreter.Pop();
					}
					break;
				case ".": //outputs the top element of the stack as an integer
					PushOutput(interpreter.Pop() + " ");
					break;
				case ",": //outputs the top element of the stack as an ASCII character
					PushOutput(((char)interpreter.Pop()).ToString());
					break;
				case "#": //skips the next character
					Jump(1);
					break;
				case "p": //puts a value in the playfield
					Put(interpreter.Pop(), interpreter.Pop(), interpreter.Pop());
					updateNeeded = true;
					break;
				case "g": //retrieves a value from the playfield
					interpreter.Push(Get(interpreter.Pop(), interpreter.Pop()));
					break;
				case "&": //pushes user input the the stack as an integer
					interpreter.Push(Int32.Parse(Prompt(false)));
					break;
				case "~": //pushes user input the the stack as an ASCII character
					interpreter.Push((int)(Prompt(true)[0]));
					break;
				case ";": //jumps over all tokens until the next semicolon
					JumpOver();
					break;
				case "j": //jumps i tokens, where i is the top element of the stack
					Jump(interpreter.Pop());
					break;
				case "k": // interprets the next token n times, where n is the top element of the stack
					int n = interpreter.Pop();
					Advance();
					for (int i = 0; i < n; i++) {
						Interpret();
					}
					break;
				case "@": //stops the program
					running = false;
					break;
				default: //does nothing
					break;
			}
		}

		public bool IsUpdateNeeded() {
			return updateNeeded;
		}

		public void SetUpdateNeeded(bool updateNeeded) {
			this.updateNeeded = updateNeeded;
		}

		private void JumpOver() {
			Advance();
			string currentToken = GetToken(currentX, currentY);
			while (currentToken != ";") {
				Advance();
				currentToken = GetToken(currentX, currentY);
			}
		}

		private void Jump(int num) {
			if (num < 0) {
				currentDir = NewDirection(2);
			}
			for (int i = 0; i < num; i++) {
				Advance();
			}
			if (num < 0) {
				currentDir = NewDirection(2);
			}
		}

		private void Put(int y, int x, int v) {
			SetToken(x, y, ((char)v).ToString());
		}

		private int Get(int y, int x) {
			if (y >= tokens.GetLength(0) || y < 0 || x >= tokens.GetLength(1) || x < 0)
				return 0;
			return (int)(GetToken(x, y)[0]);
		}

		public void InterpretString() {
			readingString = true;
			Advance();
			String currentToken = GetToken(currentX, currentY);
			while (currentToken != "\"") {
				interpreter.Push((int)(currentToken[0]));
				Advance();
				currentToken = GetToken(currentX, currentY);
			}
			readingString = false;
		}

		public void PushOutput(String str) {
			output.Append(str);
		}

		public string GetOutput() {
			return output.ToString();
		}

		public string GetStack() {
			return GetInterpreter().GetStack().ToString();
		}

		public void ResetParser() {
			currentX = 0;
			currentY = 0;
			interpreter = new Interpreter();
		}

		public String GetRawTokens() {
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < tokens.GetLength(0); i++) {
				for (int j = 0; j < tokens.GetLength(1); j++) {
					string stri = tokens[i, j];
					if (stri == null)
						str.Append(" ");
					else
						str.Append(stri);
				}
				str.Append("\n");
			}
			return str.ToString();
		}

		public String[,] GetTokens() {
			return tokens;
		}

		public void SetTokens(String[,] tokens) {
			this.tokens = tokens;
		}

		public int GetCurrentX() {
			return currentX;
		}

		public void SetCurrentX(int currentX) {
			this.currentX = currentX;
		}

		public int GetCurrentY() {
			return currentY;
		}

		public void SetCurrentY(int currentY) {
			this.currentY = currentY;
		}

		public bool IsRunning() {
			return running && tokens.Length != 0;
		}

		public void SetRunning(bool isRunning) {
			this.running = isRunning;
		}

		public string Prompt(bool str) {
			string input = "";
			DialogResult result = OptionPane.ShowInputDialog(ref input, "Enter a " + (str ? "String." : "Integer."));
			return result == DialogResult.OK ? input : "0";
		}

	}

}