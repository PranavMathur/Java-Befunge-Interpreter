package befunge;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Parser {
	
	private String[][] tokens;
	private int currentX = 0;
	private int currentY = 0;
	private CardinalDirections currentDir = CardinalDirections.RIGHT;
	
	private boolean isRunning = true;
	private boolean readingString = false;
	
	public static final int MAX_X = 80;
	public static final int MAX_Y = 25;
	
	private StringBuilder output = new StringBuilder();

	private Interpreter iptr;
	
	public Interpreter getInterpreter() {
		return iptr;
	}

	public void setInterpreter(Interpreter iptr) {
		this.iptr = iptr;
	}
	
	public Parser(String rawTokens) {
		this(rawTokens, null);
	}
	
	public Parser(String rawTokens, JFrame frame) {
		if (rawTokens.equals(""))
			rawTokens = "  \n  ";
		fillArray(rawTokens);
	}
	
	public Parser(String[] rawTokens, JFrame frame) {
		fillArray(rawTokens);
	}
	
	public Parser(String[][] rawTokens, JFrame frame) {
		fillArray(rawTokens);
	}
	
	public String getToken(int x, int y) {
		return tokens[y][x];
	}
	
	public void setToken(int x, int y, String v) {
		tokens[y][x] = v;
	}
	
	public void fillArray(String rawTokens) {
		fillArray(rawTokens.split("\n"));
	}
	
	public void fillArray(String[] rawTokens) {
		for (int currentRow = 0; currentRow < rawTokens.length && currentRow < MAX_Y; currentRow++) {
			for (int i = 0; i < rawTokens[currentRow].length() && i < MAX_X; i++) {
				tokens[currentRow][i] = rawTokens[currentRow].substring(i,  i+1);
			}
		}
	}
	
	public void fillArray(String[][] rawTokens) {
		for (int currentRow = 0; currentRow < rawTokens.length && currentRow < MAX_Y; currentRow++) {
			for (int i = 0; i < rawTokens[0].length && i < MAX_X; i++) {
				this.tokens[currentRow][i] = rawTokens[currentRow][i];
			}
		}
	}
	
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
	
	public CardinalDirections newDirection(int i) {
		return CardinalDirections.values()[(currentDir.ordinal() + i)%4];
	}
	
	public void interpret() {
		String currentToken = getToken(currentX, currentY);
		if (currentToken == null)
			return;
		if ("0123456789".indexOf(currentToken) != -1) {
			iptr.push(Integer.parseInt(currentToken));
			return;
		}
		else if ("abcdef".indexOf(currentToken) != -1) {
			iptr.push(10 + (int)(currentToken.charAt(0)) - 97);
			return;
		}
		switch (currentToken) {
			case "+":
				iptr.add();
				break;
			case "-":
				iptr.subtract();
				break;
			case "*":
				iptr.multiply();
				break;
			case "/":
				iptr.divide();
				break;
			case "%":
				iptr.modulo();
				break;
			case "!":
				iptr.NOT();
				break;
			case "`":
				iptr.greaterThan();
				break;
			case ">":
				currentDir = CardinalDirections.RIGHT;
				break;
			case "<":
				currentDir = CardinalDirections.LEFT;
				break;
			case "v":
				currentDir = CardinalDirections.DOWN;
				break;
			case "^":
				currentDir = CardinalDirections.UP;
				break;
			case "?":
				int rand = (int)(Math.random()*4);
				currentDir = CardinalDirections.values()[rand];
				break;
			case "[":
				currentDir = newDirection(-1);
				break;
			case "]":
				currentDir = newDirection(1);
				break;
			case "x":
				int newY = iptr.pop();
				int newX = iptr.pop();
				setCurrentX(newX);
				setCurrentY(newY);
				currentDir = newDirection(2);
				advance();
				currentDir = newDirection(2);
				break;
			case "_":
				if (iptr.pop() == 0) {
					currentDir = CardinalDirections.RIGHT;
				}
				else {
					currentDir = CardinalDirections.LEFT;
				}
				break;
			case "|":
				if (iptr.pop() == 0) {
					currentDir = CardinalDirections.DOWN;
				}
				else {
					currentDir = CardinalDirections.UP;
				}
				break;
			case "w":
				int b = iptr.pop();
				int a = iptr.pop();
				if (a < b) {
					currentDir = newDirection(-1);
				}
				else if (a > b) {
					currentDir = newDirection(1);
				}
				break;
			case "\"":
				interpretString();
				break;
			case ":":
				iptr.duplicate();
				break;
			case "\\":
				iptr.swap();
				break;
			case "$":
				iptr.pop();
				break;
			case "n":
				while (!iptr.getStack().isEmpty()) {
					iptr.pop();
				}
				break;
			case ".":
				pushOutput(iptr.pop() + " ");
				break;
			case ",":
				pushOutput(Character.toString((char)iptr.pop()));
				break;
			case "#":
				jump(1);
				break;
			case "p":
				put(iptr.pop(), iptr.pop(), iptr.pop());
				break;
			case "g":
				iptr.push(get(iptr.pop(), iptr.pop()));
				break;
			case "&":
				iptr.push(Integer.parseInt(prompt(false)));
				break;
			case "~":
				iptr.push((int)(prompt(true).charAt(0)));
				break;
			case ";":
				jumpOver();
				break;
			case "j":
				jump(iptr.pop());
				break;
			case "k":
				int n = iptr.pop();
				advance();
				for (int i = 0; i < n; i++) {
					interpret();
				}
				break;
			case "@":
				isRunning = false;
				System.out.println("done");
				break;
			default:
				break;
		}
	}
	
	private void jumpOver() {
		advance();
		String currentToken = getToken(currentX, currentY);
		while (!currentToken.equals(";")) {
			advance();
			currentToken = getToken(currentX, currentY);
		}
	}
	
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
	
	private void put(int y, int x, int v) {
		setToken(x, y, Character.toString((char)v));
	}
	
	private int get(int y, int x) {
		if (y >= tokens.length || y < 0 || x >= tokens[0].length || x < 0)
			return 0;
		return (int)(getToken(x, y).charAt(0));
	}
	
	public String prompt(boolean string) {
		String s = (String)JOptionPane.showInputDialog(
                null,
                string ? "Enter a String:" : "Enter an Integer:");
		return s;
	}
	
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
	
	public void pushOutput(String str) {
		output.append(str);
	}
	
	public String getOutput() {
		return output.toString();
	}
	
	public void resetParser() {
		currentX = 0;
		currentY = 0;
		iptr = new Interpreter();
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
