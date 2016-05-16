// Main.java
// Part of the KTH course DD1361 Programming Paradigms lab S4
// Authors: Johan Callvik and Mauritz Zachrisson

package progp_s4;

import java_cup.runtime.Symbol;
import progp_s4.data.Instruction;
import progp_s4.data.Line;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("unchecked")
public class Main
{
	public static Map<String, Double> variables = new HashMap<>();

	public static void main(String args[]) throws Exception
	{
		// Read file input
		String input = String.join("\n", Files.readAllLines(Paths.get(args[0])));

		// Run lexer and construct parser
		Lexer lexer = new Lexer(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		Parser parser = new Parser(lexer);

		// Parse and cast to List<Instruction>
		Symbol parse_result = parser.debug_parse();
		List<Instruction> result = (List<Instruction>) parse_result.value;

		// Use Runner to convert to Line objects
		Set<Line> lines = new HashSet<>();
		new Runner(result, lines);

		// Get dimensions
		int xMin = (int) Math.floor(lines.stream().map(Line::getXMin).min(Double::compareTo).get());
		int yMin = (int) Math.floor(lines.stream().map(Line::getYMin).min(Double::compareTo).get());
		int width = (int) Math.ceil(lines.stream().map(Line::getXMax).max(Double::compareTo).get()) + Math.abs(xMin);
		int height = (int) Math.ceil(lines.stream().map(Line::getYMax).max(Double::compareTo).get()) + Math.abs(yMin);

		// Determine stroke width
		double strokeWidth;
		if (width < 10)
			strokeWidth = 0.015;
		else if (width < 50)
			strokeWidth = 0.1;
		else if (width < 200)
			strokeWidth = 0.25;
		else
			strokeWidth = 1;

		// Render SVG
		StringBuilder out = new StringBuilder();
		out.append("<?xml version=\"1.0\" standalone=\"no\"?>");
		out.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
		out.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" " +
				"viewBox=\"%d %d %d %d\" preserveAspectRatio=\"xMidYMid\" width=\"1500\" height=\"1500\" version=\"1.1\">", xMin, yMin, width, height));
		lines.stream().forEach(l -> out.append(l.render(strokeWidth)));
		out.append("</svg>");

		// Write SVG
		Path file = Paths.get("out.svg");
		Files.write(file, out.toString().getBytes());
		System.out.println("Wrote to out.svg");
	}
}
