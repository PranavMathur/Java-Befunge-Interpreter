package befunge;

public class Stack {
	
	private StackElement top;

	public Stack() {
		top = null;
	}
	
	public void push(int i) {
		StackElement element = new StackElement(i);
		if (top == null) {
			top = element;
		}
		else {
			element.setNext(top);
			top = element;
		}
	}
	
	public int peek() {
		return top.getData();
	}
	
	public int pop() {
		StackElement ret = top;
		top = top.getNext();
		return ret.getData();
	}
	
	public StackElement getTop() {
		return top;
	}

	public void setTop(StackElement top) {
		this.top = top;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public static class StackElement {
		
		private int data;
		
		private StackElement next;
		
		public StackElement() {
			this(0, null);
		}

		public StackElement(int data) {
			this(data, null);
		}
		
		public StackElement(int data, StackElement next) {
			this.setData(data);
			this.setNext(next);
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public StackElement getNext() {
			return next;
		}

		public void setNext(StackElement next) {
			this.next = next;
		}
		
		public String toString() {
			return this.getData() + "";
		}

	}
	
	public String toString() {
		if (top == null) return "";
		StringBuilder str = new StringBuilder();
		StackElement current = top;
		while (current != null) {
			str.insert(0, current.getData() + " ");
			current = current.getNext();
		}
		return str.toString();
	}

}
