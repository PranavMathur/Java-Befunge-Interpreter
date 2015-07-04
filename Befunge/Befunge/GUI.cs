using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Befunge {
	public partial class GUI : Form {

		Parser p = null;

		public GUI() {
			InitializeComponent();
		}

		private void RunHandler(object sender, EventArgs e) {
			if (p == null)
				p = new Parser(inputArea.Text);
			while (p.IsRunning()) {
				p.Interpret();
				p.Advance();
			}
			outputStream.Text = p.GetOutput();
			p = null;
		}

		private void StepHandler(object sender, EventArgs e) {
			if (p == null)
				p = new Parser(inputArea.Text);
			if (p.IsRunning()) {
				p.Interpret();
				p.Advance();
			}
			stackStream.Text = p.GetInterpreter().GetStack().ToString();
			outputStream.Text = p.GetOutput();
			if (!p.IsRunning())
				p = null;
		}
	}
}
