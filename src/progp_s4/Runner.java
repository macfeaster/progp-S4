// Runner.java
// Part of the KTH course DD1361 Programming Paradigms lab S4
// Authors: Johan Callvik and Mauritz Zachrisson

package progp_s4;

import progp_s4.data.Instruction;
import progp_s4.data.Line;

import java.util.List;
import java.util.Set;

/**
 * Runs the Instructions supplied in a syntax tree produced by the parser.
 */
public class Runner
{
	private boolean penDown;
	private String penColor = "#0000FF";
	private double x;
	private double y;
	private int angle;
	private Set<Line> set;

	public Runner(List<Instruction> tree, Set<Line> set)
	{
		this.set = set;
		Run(tree);
	}

	/**
	 * Run the instructions in the tree.
	 * 
	 * @param tree  Parse tree from parser.
	 */
	private void Run(List<Instruction> tree)
	{
		// Traverse the instruction set
		for (Instruction i : tree)
		{
			switch (i.type)
			{
				// The global pen state is changed by UP and DOWN commands
				case sym.UP:
					System.out.println("Pen is now Up");
					penDown = false;
					break;
				case sym.DOWN:
					System.out.println("Pen is now Down");
					penDown = true;
					break;

				// FORW and BACK moves the pointer, drawing a line if the pen is down
				case sym.FORW:
				case sym.BACK:
					System.out.println("Calling DrawLine()");
					DrawLine(i);
					break;

				// LEFT and RIGHT commands move the direction the pointer is facing
				case sym.LEFT:
					angle += i.num.evaluate();
					System.out.println("Turned left " + i.num.evaluate() + " degrees, angle is now " + angle);
					break;
				case sym.RIGHT:
					angle -= i.num.evaluate();
					System.out.println("Turned right " + i.num + " degrees, angle is now " + angle);
					break;

				// Change the pen color
				case sym.COLOR:
					System.out.println("Changed pen color to " + i.str);
					penColor = i.str;
					break;

				// For a REP instruction, branch out and repeat its sub-instruction set Num times
				case sym.REP:
					int runs = (int) i.num.evaluate();
					System.out.println("Evaluated REP " + i.num + " to " + runs);
					System.out.println((int) i.num.evaluate());
					for (int j = 0; j < runs; j++)
					{
						System.err.println("REP RUN " + j);
						Run(i.subInstr);
					}
					break;

				case sym.VAR:
					Main.variables.put(i.str, i.num.evaluate());
					System.out.println("Variable " + i.str + " has calculated value " + i.num);
					break;

				// This should never be reached
				default:
					System.err.println("Unknown instruction encountered");
			}
		}
	}

	/// <summary>
	/// Move the pointer forwards or backwards, drawing a line if the pen is down
	/// </summary>
	private void DrawLine(Instruction i)
	{
		double x1 = x;
		double y1 = y;
		double x2, y2;
		double d = i.num.evaluate();

		// Add to the coordinates for forward instruction
		if (i.type == sym.FORW)
		{
			x2 = x1 + d * Math.cos(Math.PI * angle / 180);
			y2 = y1 + d * Math.sin(Math.PI * angle / 180);
		}
		// And subtract for back command
		else if (i.type == sym.BACK)
		{
			x2 = x1 - d * Math.cos(Math.PI * angle / 180);
			y2 = y1 - d * Math.sin(Math.PI * angle / 180);
		}
		// This should never be reached
		else
			x2 = y2 = 0;

		// Update the global position
		x = x2;
		y = y2;

		// If the pen is down, "draw" a line by printing coordinates
		if (penDown)
			set.add(new Line(x1, y1, x2, y2, penColor));
	}
}