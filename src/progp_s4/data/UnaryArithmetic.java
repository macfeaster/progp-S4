package progp_s4.data;

public class UnaryArithmetic extends Arithmetic
{
	private boolean minus;
	private Arithmetic expression;

	public UnaryArithmetic(Arithmetic expression, boolean minus)
	{
		this.minus = minus;
		this.expression = expression;
	}

	@Override
	public double evaluate()
	{
		if (minus)
			return -1 * expression.evaluate();
		else
			return expression.evaluate();
	}

	@Override
	public String toString()
	{
		return "UnaryArithmetic{" +
				"minus=" + minus +
				", expression=" + expression +
				'}';
	}
}
