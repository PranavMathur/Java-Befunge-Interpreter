using System;

namespace Befunge {
	class Interpreter {

		private Stack stack;

		public Interpreter() {
			stack = new Stack();
		}

		public Stack GetStack() {
			return stack;
		}

		public void SetStack(Stack stack) {
			this.stack = stack;
		}

		public void Push(int n) {
			stack.Push(n);
		}

		public int Pop() {
			if (stack.IsEmpty())
				return 0;
			return stack.Pop();
		}

		public int Peek() {
			return stack.Peek();
		}

		public void Add() {
			int a = Pop();
			int b = Pop();
			Push(a + b);
		}

		public void Subtract() {
			int a = Pop();
			int b = Pop();
			Push(b - a);
		}

		public void Multiply() {
			int a = Pop();
			int b = Pop();
			Push(a * b);
		}

		public void Divide() {
			int a = Pop();
			int b = Pop();
			Push((int)(b / a));
		}

		public void Modulo() {
			int a = Pop();
			int b = Pop();
			Push(b % a);
		}

		public void NOT() {
			if (Pop() == 0) {
				Push(1);
			}
			else {
				Push(0);
			}
		}

		public void GreaterThan() {
			int a = Pop();
			int b = Pop();
			if (b > a) {
				Push(1);
			}
			else {
				Push(0);
			}
		}

		public void Duplicate() {
			Push(Peek());
		}

		public void Swap() {
			int a = Pop();
			int b = Pop();
			Push(a);
			Push(b);
		}

	}
}
