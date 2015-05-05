package com.mguerrieri.mud.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Holds a list of words, making up an input command.
 *
 * @author Mario Guerrieri
 *
 */
public class Command implements Iterable<String>
{
	private ArrayList<String> words;

	public Command(String command)
	{
		String newCmd = simplify(command);
		Scanner cmdScan = new Scanner(newCmd);
		this.words = new ArrayList<String>();
		while(cmdScan.hasNext())
		{
			this.words.add(cmdScan.next());
		}
	}

	public String[] split(int partLength)
	{
		if(this.words.size() == 1)
		{
			return new String[]{this.words.get(0)};
		}

		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < partLength; i ++)
		{
			for(int k = 0; i + k * partLength + partLength - 1 < this.words.size(); k ++)
			{
				StringBuilder part = new StringBuilder();
				for(int j = 0; j < partLength - 1; j ++)
				{
					part.append(this.words.get(i + k * partLength + j) + " ");
				}
				if(partLength > 0)
				{
					part.append(this.words.get(i + k * partLength + partLength - 1));
				}
				temp.add(part.toString());
			}
		}
		String[] out = new String[temp.size()];
		for(int i = 0; i < out.length; i ++)
		{
			out[i] = temp.get(i);
		}
		return out;
	}

	private String simplify(String in)
	{
		char[] chars = in.toCharArray();
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < chars.length; i ++)
		{
			if(Character.isLetter(chars[i]) || Character.isSpaceChar(chars[i]))
			{
				out.append(chars[i]);
			}
		}
		return out.toString();
	}

	public Iterator<String> iterator()
	{
		return new CommandIterator(this);
	}

	private class CommandIterator implements Iterator<String>
	{
		private int current;
		private Command iterating;

		public CommandIterator(Command command)
		{
			this.current = 0;
			this.iterating = command;
		}

		public boolean hasNext()
		{
			return this.current < this.iterating.words.size();
		}

		public String next()
		{
			String str = this.iterating.words.get(current);
			this.current ++;
			return str;
		}

		public void remove()
		{
			this.iterating.words.remove(current);
		}
	}

	public int length()
	{
		return this.words.size();
	}
}
