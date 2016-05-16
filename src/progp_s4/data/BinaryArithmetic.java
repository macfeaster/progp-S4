package progp_s4.data;

public class BinaryArithmetic extends Arithmetic
{
	Arithmetic left;
	Arithmetic right;
	Type type;

	public BinaryArithmetic(Arithmetic left, Arithmetic right, Type type)
	{
		this.left = left;
		this.right = right;
		this.type = type;
	}

	@Override
	public double evaluate()
	{
		switch (type)
		{
			case PLUS:
				return left.evaluate() + right.evaluate();

			case MINUS:
				return left.evaluate() - right.evaluate();

			case TIMES:
				return left.evaluate() * right.evaluate();

			case DIV:
				return left.evaluate() / right.evaluate();

			default:
				return -9999;
		}
	}

	@Override
	public String toString()
	{
		return "BinaryArithmetic{" +
				"left=" + left +
				", right=" + right +
				", type=" + type +
				'}';
	}
}