package befunge;

import javax.swing.JOptionPane;

/**
 * Interacts with tokens, the stack, and the output
 * @author Pranav
 *
 */
public class Parser {
	
	/** 2D array representing the tokens in the playfield */
	private String[][] tokens = new String[MAX_Y][MAX_X];
	/** The current X coordinate of the pointer */
	private int currentX = 0;
	/** The current Y coordinate of the pointer */
	private int currentY = 0;
	/** The current Direction of the pointer */
	private CardinalDirections currentDir = CardinalDirections.RIGHT;


	private boolean isRunning = true;
	private boolean readingString = false;
	private boolean updateNeeded = false;
	
	/** Represents the maximum number of columns of the playfield */
	public static final int MAX_X = 80;
	/** Represents the maximum number of rows of the playfield */
	public static final int MAX_Y = 25;
	
	/** StringBuilder that contains the output of the program */
	private StringBuilder output = new StringBuilder();

	/** Interpreter that manipulates the stack */
	private Interpreter iptr = new Interpreter();
	
	/**
	 * @return The interpreter of this parser
	 */
	public Interpreter getInterpreter() {
		return iptr;
	}

	/**
	 * Sets the interpreter
	 * @param iptr The new Interpreter to be added to this
	 */
	public void setInterpreter(Interpreter iptr) {
		this.iptr = iptr;
	}
	
	/**
	 * Constructs a Parser with the following String as tokens
	 * @param rawTokens The raw String representing the program
	 */
	public Parser(String rawTokens) {
		if (rawTokens.equals(""))
			rawTokens = "  \n  ";
		fillArray(rawTokens);
	}
	
	/**
	 * Constructs a Parser with the following Array of Strings as tokens
	 * @param rawTokens The Array of Strings representing the program
	 */
	public Parser(String[] rawTokens) {
		fillArray(rawTokens);
	}
	
	/**
	 * Constructs a Parser with the following 2D Array of Strings as tokens
	 * @param rawTokens The 2D Array of Strings representing the program
	 */
	public Parser(String[][] rawTokens) {
		fillArray(rawTokens);
	}
	
	public String getToken(int x, int y) {
		return tokens[y][x];
	}
	
	public void setToken(int x, int y, String v) {
		tokens[y][x] = v;
	}
	
	/**
	 * Fills the tokens array with the tokens in the raw String
	 * @param rawTokens The raw String to be used as the tokens
	 */
	public void fillArray(String rawTokens) {
		fillArray(rawTokens.split("\n"));
	}
	
	/**
	 * Fills the tokens array with the tokens in the Array of Strings
	 * @param rawTokens The Array of Strings to be used as the tokens
	 */
	public void fillArray(String[] rawTokens) {
		for (int currentRow = 0; currentRow < rawTokens.length && currentRow < MAX_Y; currentRow++) {
			for (int i = 0; i < rawTokens[currentRow].length() && i < MAX_X; i++) {
				tokens[currentRow][i] = rawTokens[currentRow].substring(i,  i+1);
			}
		}
	}
	
	/**
	 * Fills the tokens array with the tokens in the 2D Array of Strings
	 * @param rawTokens The 2D Array of Strings to be used as the tokens
	 */
	public void fillArray(String[][] rawTokens) {
		for (int currentRow = 0; currentRow < rawTokens.length && currentRow < MAX_Y; currentRow++) {
			for (int i = 0; i < rawTokens[0].length && i < MAX_X; i++) {
				this.tokens[currentRow][i] = rawTokens[currentRow][i];
			}
		}
	}
	
	/**
	 * Advances the pointer to the next token based on the direction and current location of the pointer
	 */
	public void advance() {
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
			currentX += MAX_X;;
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
		if (!readingString && (getToken(currentX, currentY) == null || getToken(currentX, currentY).equals(" "))) {
			advance();
		}
	}
	
	/**
	 * @param i The amount of clockwise 90 degree turns to make
	 * @return The new direction of the pointer after the turn
	 */
	public CardinalDirections newDirection(int i) {
		return CardinalDirections.values()[(currentDir.ordinal() + i)%4];
	}
	
	/**
	 * Interprets the current token
	 */
	public void interpret() {
		String currentToken = getToken(currentX, currentY); //retrieves the current token
		if (currentToken == null) //does nothing if the token is null
			return;
		if ("0123456789".indexOf(currentToken) != -1) { //pushes the integer representation of the token to the stack
			iptr.push(Integer.parseInt(currentToken));
			return;
		}
		else if ("abcdef".indexOf(currentToken) != -1) { //pushes the integer representation of the token to the stack
			iptr.push(10 + (int)(currentToken.charAt(0)) - 97);
			return;
		}
		switch (currentToken) { //performs the correct operation based on the current token
			case "+": //performs the addition operation of the interpreter
				iptr.add();
				break;
			case "-": //performs the subtraction operation of the interpreter
				iptr.subtract();
				break;
			case "*": //performs the multiplication operation of the interpreter
				iptr.multiply();
				break;
			case "/": //performs the division operation of the interpreter
				iptr.divide();
				break;
			case "%": //performs the modulus operation of the interpreter
				iptr.modulo();
				break;
			case "!": //performs the logical not operation of the interpreter
				iptr.NOT();
				break;
			case "`": //performs the greater than operation of the interpreter
				iptr.greaterThan();
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
				int rand = (int)(Math.random()*4);
				currentDir = CardinalDirections.values()[rand];
				break;
			case "[": //sets the current direction of the pointer to one clockwise rotation of the current direction
				currentDir = newDirection(-1);
				break;
			case "]": //sets the current direction of the pointer to one counter-clockwise rotation of the current direction
				currentDir = newDirection(1);
				break;
			case "x": //moves the pointer to the location at <x, y> where x and y are popped from the stack
				int newY = iptr.pop();
				int newX = iptr.pop();
				setCurrentX(newX);
				setCurrentY(newY);
				currentDir = newDirection(2);
				advance();
				currentDir = newDirection(2);
				break;
			case "_": //sets the current direction of the pointer to right if the top element of the stack is 0, left otherwise
				if (iptr.pop() == 0) {
					currentDir = CardinalDirections.RIGHT;
				}
				else {
					currentDir = CardinalDirections.LEFT;
				}
				break;
			case "|": //sets the current direction of the pointer to down if the top element of the stack is 0, up otherwise
				if (iptr.pop() == 0) {
					currentDir = CardinalDirections.DOWN;
				}
				else {
					currentDir = CardinalDirections.UP;
				}
				break;
			case "w": //performs a clockwise rotation of the pointer if the second element of the stack is larger than the first,
				      //counter-clockwise if otherwise
				int b = iptr.pop();
				int a = iptr.pop();
				if (a < b) {
					currentDir = newDirection(-1);
				}
				else if (a > b) {
					currentDir = newDirection(1);
				}
				break;
			case "\"": //pushes the string in the playfield onto the stack
				interpretString();
				break;
			case ":": //duplicates the value on the top of the stack
				iptr.duplicate();
				break;
			case "\\": //swaps the values on the top of the stack
				iptr.swap();
				break;
			case "$": //disposes of the top value of the stack
				iptr.pop();
				break;
			case "n": //clears the stack
				while (!iptr.getStack().isEmpty()) {
					iptr.pop();
				}
				break;
			case ".": //outputs the top element of the stack as an integer
				pushOutput(iptr.pop() + " ");
				break;
			case ",": //outputs the top element of the stack as an ASCII character
				pushOutput(Character.toString((char)iptr.pop()));
				break;
			case "#": //skips the next character
				jump(1);
				break;
			case "p": //puts a value in the playfield
				put(iptr.pop(), iptr.pop(), iptr.pop());
				updateNeeded = true;
				break;
			case "g": //retrieves a value from the playfield
				iptr.push(get(iptr.pop(), iptr.pop()));
				break;
			case "&": //pushes user input the the stack as an integer
				iptr.push(Integer.parseInt(prompt(false)));
				break;
			case "~": //pushes user input the the stack as an ASCII character
				iptr.push((int)(prompt(true).charAt(0)));
				break;
			case ";": //jumps over all tokens until the next semicolon
				jumpOver();
				break;
			case "j": //jumps i tokens, where i is the top element of the stack
				jump(iptr.pop());
				break;
			case "k": // interprets the next token n times, where n is the top element of the stack
				int n = iptr.pop();
				advance();
				for (int i = 0; i < n; i++) {
					interpret();
				}
				break;
			case "@": //stops the program
				isRunning = false;
				break;
			default: //does nothing
				break;
		}
	}
	
	public boolean isUpdateNeeded() {
		return updateNeeded;
	}

	public void setUpdateNeeded(boolean updateNeeded) {
		this.updateNeeded = updateNeeded;
	}

	/**
	 * Jumps until the current token is ';'
	 */
	private void jumpOver() {
		advance();
		String currentToken = getToken(currentX, currentY);
		while (!currentToken.equals(";")) {
			advance();
			currentToken = getToken(currentX, currentY);
		}
	}
	
	/**
	 * Jumps num tokens
	 * @param num The number of tokens to skip
	 */
	private void jump(int num) {
		if (num < 0) {
			currentDir = newDirection(2);
		}
		for (int i = 0; i < num; i++) {
			advance();
		}
		if (num < 0) {
			currentDir = newDirection(2);
		}
	}
	
	/**
	 * places a token in the playfield
	 * @param y The y coordinate of the location 
	 * @param x The x coordinate of the location 
	 * @param v The value to place in given location 
	 */
	private void put(int y, int x, int v) {
		setToken(x, y, Character.toString((char)v));
	}
	
	/**
	 * @param y The y coordinate of the location
	 * @param x The x coordinate of the location
	 * @return The token at the given location
	 */
	private int get(int y, int x) {
		if (y >= tokens.length || y < 0 || x >= tokens[0].length || x < 0)
			return 0;
		return (int)(getToken(x, y).charAt(0));
	}
	
	/**
	 * @param string Whether the input should be proccessed as a string
	 * @return The input from the user
	 */
	public String prompt(boolean string) {
		String s = (String)JOptionPane.showInputDialog(
                null,
                string ? "Enter a String:" : "Enter an Integer:");
		return s;
	}
	
	/**
	 * Push the string form the playfield onto the stack as integers
	 */
	public void interpretString() {
		readingString = true;
		advance();
		String currentToken = getToken(currentX, currentY);
		while (!currentToken.equals("\"")) {
			iptr.push((int)(currentToken.charAt(0)));
			advance();
			currentToken = getToken(currentX, currentY);
		}
		readingString = false;
	}
	
	/**
	 * Adds a string to the current output
	 * @param str The string to be added
	 */
	public void pushOutput(String str) {
		output.append(str);
	}
	
	/**
	 * @return The current output of the program
	 */
	public String getOutput() {
		return output.toString();
	}
	
	/**
	 * Resets the parser to its original state
	 */
	public void resetParser() {
		currentX = 0;
		currentY = 0;
		iptr = new Interpreter();
	}
	
	/**
	 * @return The raw tokens as a String
	 */
	public String getRawTokens() {
		StringBuilder str = new StringBuilder();
		for (String[] arr: tokens) {
			for (String i : arr) {
				if (i == null)
					str.append(" ");
				else
					str.append(i);
			}
			str.append("\n");
		}
		return str.toString();
	}
	
	public String[][] getTokens() {
		return tokens;
	}

	public void setTokens(String[][] tokens) {
		this.tokens = tokens;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
