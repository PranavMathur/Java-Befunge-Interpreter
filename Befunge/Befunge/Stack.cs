using System;
using System.Text;

namespace Befunge
{
	public class Stack
	{

		private StackElement top;

		public Stack()
		{
			top = null;
		}

		public void Push(int i)
		{
			StackElement element = new StackElement(i);
			if (top == null)
			{
				top = element;
			}
			else
			{
				element.Next = top;
				top = element;
			}
		}

		public int Peek()
		{
			if (IsEmpty())
				return 0;
			return top.Data;
		}

		public int Pop()
		{
			StackElement ret = top;
			top = top.Next;
			return ret.Data;
		}

		public StackElement GetTop()
		{
			return top;
		}

		public void SetTop(StackElement top)
		{
			this.top = top;
		}

		public bool IsEmpty()
		{
			return top == null;
		}

		public class StackElement
		{

			private int data;
			public int Data
			{
				get
				{
					return data;
				}
			}

			private StackElement next;
			public StackElement Next
			{
				get
				{
					return next;
				}
				set
				{
					next = value;
				}
			}

			public StackElement(int data, StackElement next)
			{
				this.data = data;
				this.next = next;
			}

			public StackElement(int data)
				: this(data, null)
			{

			}

			public StackElement()
				: this(0, null)
			{

			}

			public override string ToString()
			{
				return "" + data;
			}

		}

		public override string ToString()
		{
			if (top == null) return "Stack: ";
			StringBuilder str = new StringBuilder("Stack: ");
			StackElement current = top;
			while (current != null)
			{
				str.Insert(7, current.Data + " ");
				current = current.Next;
			}
			return str.ToString();
		}

	}
}