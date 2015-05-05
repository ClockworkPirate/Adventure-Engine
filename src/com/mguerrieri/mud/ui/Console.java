package com.wedageeks_at_gmail.mud.ui;

import java.util.Scanner;

/**
 * Accepts input from and displays output to the command line.
 * @author Mario Guerrieri;
 *
 */
public class Console implements UserInterface
{
	Scanner consoleScan;
	
	public Console()
	{
		this.consoleScan = new Scanner(System.in);
	}

	public void display(String displayText)
	{
		if(displayText != null) System.out.print(displayText);
	}

	public String getInput()
	{
		System.out.print("\n> ");
		return this.consoleScan.nextLine();
	}
}