package progp_s4.data;

import progp_s4.Main;

public class Value extends Arithmetic
{
	String variable;
	double number;

	public Value(String variable)
	{
		this.variable = variable;
	}

	public Value(int number)
	{
		this.number = number;
	}

	@Override
	public double evaluate()
	{
		if (variable == null)
			return number;
		else
			return Main.variables.get(variable);
	}

	@Override
	public String toString()
	{
		if (variable == null)
			return Double.toString(number);
		else
			return variable;
	}
}
