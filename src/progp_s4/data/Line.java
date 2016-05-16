package progp_s4.data;

/**
 * Represents a SVG line
 */
public class Line
{
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private String color;

	public Line(double x1, double y1, double x2, double y2, String color)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	public double getXMin()
	{
		return Math.min(x1, x2);
	}

	public double getXMax()
	{
		return Math.max(x1, x2);
	}

	public double getYMin()
	{
		return Math.min(y1, y2);
	}

	public double getYMax()
	{
		return Math.max(y1, y2);
	}

	public String render(double strokeWidth)
	{
		return String.format(
				"<line style=\"stroke-width: %f; stroke: %s\" x1=\"%.5f\" y1=\"%.5f\" x2=\"%.5f\" y2=\"%.5f\" />",
				strokeWidth, color, x1, y1, x2, y2);
	}
}
