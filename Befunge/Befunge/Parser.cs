using System;
using System.Text;

namespace Befunge
{
	class Parser
	{
		//private String[,] tokens = new String[MAX_Y,MAX_X];
		private int currentX = 0;
		private int currentY = 0;
		private CardinalDirections currentDir = CardinalDirections.RIGHT;


		private bool isRunning = true;
		private bool readingString = false;
		private bool updateNeeded = false;
	
		private StringBuilder output = new StringBuilder();

		private Interpreter interpreter = new Interpreter();

		

	}
}
