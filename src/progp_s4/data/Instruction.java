package progp_s4.data;

import progp_s4.sym;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single instruction, with or without parameters.
 */
public class Instruction
{
	public int type;
	public Arithmetic num;
	public String str;
	public List<Instruction> subInstr;

	/**
	 * Container for short instruction types (Up, Down)
	 */
	public Instruction(int type)
	{
		this.type = type;
	}

	/**
	 * Container for regular instruction types (Forw 2, Left 90, etc.)
	 */
	public Instruction(int type, Arithmetic num)
	{
		this.type = type;
		this.num = num;
	}

	/**
	 * Container for Color instruction.
	 */
	public Instruction(int type, String str)
	{
		this.type = type;
		this.str = str;
	}

	/**
	 * Container for Variable instruction, which contains both a string
	 * (variable name) and a number (its calculated value).
	 */
	public Instruction(int type, String str, Arithmetic num)
	{
		this.type = type;
		this.str = str;
		this.num = num;
		System.err.println("VARIABLE INSTRUCTION: " + str + " :: " + num);
	}

	/**
	 * Container for Rep instruction, with sub instruction set.
	 */
	public Instruction(int type, Arithmetic num, List<Instruction> subInstr)
	{
		this.type = type;
		this.num = num;
		this.subInstr = subInstr;
	}

	/**
	 * Container for single Rep instruction.
	 */
	public Instruction(int type, Arithmetic num, Object o)
	{
		List<Instruction> subInstr = new ArrayList<>();
		Instruction instr = (Instruction) o;
		subInstr.add(instr);

		this.type = type;
		this.num = num;
		this.subInstr = subInstr;
	}

	@Override
	public String toString()
	{
		return "Instruction{" +
				"type=" + sym.terminalNames[type] +
				", num=" + num +
				", str='" + str + '\'' +
				", subInstr=" + subInstr +
				'}';
	}
}