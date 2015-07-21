package io.github.pranavmathur.befunge;

/**
 * The stack is the main data structure in Befunge.
 * Each element in the stack is an integer.
 * 
 * @author Pranav
 *
 */
public class Stack {
	
	/**
	 * The top-most element of the stack
	 */
	private StackElement top;

	/**
	 * Initializes the top-most element to be null
	 */
	public Stack() {
		top = null;
	}
	
	/**
	 * Pushes an Integer to the top of the stack
	 * @param i The integer to be pushed
	 */
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
	
	/**
	 * @return The top element of the Stack without popping the element
	 */
	public int peek() {
		if (isEmpty())
			return 0;
		return top.getData();
	}
	
	/**
	 * Takes the top element off the stack and returns its value
	 * @return The value of the top element of the stack
	 */
	public int pop() {
		StackElement ret = top;
		top = top.getNext();
		return ret.getData();
	}
	
	/**
	 * 
	 * @return True if the Stack is Empty
	 */
	public boolean isEmpty() {
		return top == null;
	}
	
	/**
	 * The StackElement class is used to store Integers to be placed on the Stack
	 */
	public static class StackElement {
		
		/**
		 * The Integer value stored in the Element
		 */
		private int data;
		
		/**
		 * The StackElement that is directly below this
		 */
		private StackElement next;
		
		/**
		 * Empty Constructor, sets data to 0 and next to null
		 */
		public StackElement() {
			this(0, null);
		}

		/**
		 * sets data to param and next to null
		 * @param data The value to set as the data
		 */
		public StackElement(int data) {
			this(data, null);
		}
		
		/**
		 * Sets the instance variables of the Object
		 * @param data The value to set as the data
		 * @param next The StackElement to set as the next element
		 */
		public StackElement(int data, StackElement next) {
			this.setData(data);
			this.setNext(next);
		}

		/**
		 * @return The data in this element
		 */
		public int getData() {
			return data;
		}

		/**
		 * Changes the data of this element
		 * @param data The value to set as the data
		 */
		public void setData(int data) {
			this.data = data;
		}

		/**
		 * @return The element below this
		 */
		public StackElement getNext() {
			return next;
		}

		/**
		 * Changes the element below this
		 * @param next The element to be added below this
		 */
		public void setNext(StackElement next) {
			this.next = next;
		}
		
		/**
		 * @return A String representation of this
		 */
		public String toString() {
			return this.getData() + "";
		}

	}
	
	/**
	 * @return A String representation of this
	 */
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
