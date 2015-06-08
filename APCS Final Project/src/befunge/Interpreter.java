package befunge;

/**
 * Deals with operations on the Stack
 * @author Pranav
 *
 */
public class Interpreter {
	
	/**
	 * The stack
	 */
	private Stack stack;

	/**
	 * Creates a new Stack
	 */
	public Interpreter() {
		stack = new Stack();
	}
	
	/**
	 * @return The current Stack
	 */
	public Stack getStack() {
		return stack;
	}

	/**
	 * Sets the stack to a new value
	 * @param stack The stack to be copied into this
	 */
	public void setStack(Stack stack) {
		this.stack = stack;
	}

	/**
	 * Pushes an integer to the top of the stack
	 * @param n The integer to be pushed
	 */
	public void push(int n) {
		stack.push(n);
	}

	/**
	 * Pops an integer off the stack and returns it
	 * @return The integer on the top of the stack or 0 if the stack is empty
	 */
	public int pop() {
		if (stack.isEmpty())
			return 0;
		return stack.pop();
	}
	
	/**
	 * @return The top element of the stack without popping the element
	 */
	public int peek() {
		return stack.peek();
	}

	/**
	 * Pushes the sum of the top two elements of the stack onto the stack
	 */
	public void add() {
		int a = pop();
		int b = pop();
		push(a+b);
	}

	/**
	 * Pushes the difference of the top two elements of the stack onto the stack
	 */
	public void subtract() {
		int a = pop();
		int b = pop();
		push(b-a);
	}

	/**
	 * Pushes the product of the top two elements of the stack onto the stack
	 */
	public void multiply() {
		int a = pop();
		int b = pop();
		push(a*b);
	}

	/**
	 * Pushes the quotient of the top two elements of the stack onto the stack
	 */
	public void divide() {
		int a = pop();
		int b = pop();
		push((int)(b/a));
	}

	/**
	 * Pushes the modulus of the top two elements of the stack onto the stack
	 */
	public void modulo() {
		int a = pop();
		int b = pop();
		push(b%a);
	}
	
	/**
	 * Pushes 1 onto the stack if the top element is 0, 0 otherwise
	 */
	public void NOT() {
		if (pop() == 0) {
			push(1);
		}
		else {
			push(0);
		}
	}
	
	/**
	 * pushes 1 onto the stack if the second element on the stack is greater than the first, 0 otherwise
	 */
	public void greaterThan() {
		int a = pop();
		int b = pop();
		if (b > a) {
			push(1);
		}
		else {
			push(0);
		}
	}
	
	/**
	 * Duplicates the item on the top of the stack and pushes it
	 */
	public void duplicate() {
		push(peek());
	}
	
	/**
	 * Swaps the two top elements of the stack
	 */
	public void swap() {
		int a = pop();
		int b = pop();
		push(a);
		push(b);
	}

}
