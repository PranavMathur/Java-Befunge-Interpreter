package befunge;

public class Interpreter {
	
	private Stack stack;

	public Interpreter() {
		stack = new Stack();
	}
	
	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	public void push(int n) {
		stack.push(n);
	}

	public int pop() {
		return stack.pop();
	}
	
	public int peek() {
		return stack.peek();
	}

	public void add() {
		int a = pop();
		int b = pop();
		push(a+b);
	}
	
	public void subtract() {
		int a = pop();
		int b = pop();
		push(b-a);
	}
	
	public void multiply() {
		int a = pop();
		int b = pop();
		push(a*b);
	}
	
	public void divide() {
		int a = pop();
		int b = pop();
		push((int)(b/a));
	}
	
	public void modulo() {
		int a = pop();
		int b = pop();
		push(b%a);
	}
	
	public void NOT() {
		if (pop() == 0) {
			push(1);
		}
		else {
			push(0);
		}
	}
	
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
	
	public void duplicate() {
		push(peek());
	}
	
	public void swap() {
		int a = pop();
		int b = pop();
		push(a);
		push(b);
	}

}
