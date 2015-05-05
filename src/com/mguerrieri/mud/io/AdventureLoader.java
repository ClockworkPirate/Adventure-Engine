package com.mguerrieri.mud.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.mguerrieri.mud.inventory.Item;
import com.mguerrieri.mud.inventory.Key;
import com.mguerrieri.mud.inventory.Weapon;
import com.mguerrieri.mud.navigation.Path;
import com.mguerrieri.mud.navigation.Room;

/**
 * Saves and loads <code>Room</code>s. Current implementation is fairly barebones, just saves anything that might be contained within a room.
 * @author Mario Guerrieri
 *
 */
public class AdventureLoader
{
	private static XStream coder;

	private static String topPath;

	public static void init(String path)
	{
		topPath = path;
		coder = new XStream();
		coder.alias("path", Path.class);
		coder.alias("room", Room.class);
		coder.alias("weapon", Weapon.class);
		coder.alias("character", Character.class);
		coder.alias("key", Key.class);
		coder.alias("item", Item.class);
	}

	private int id;

	public AdventureLoader(int id)
	{
		this.id = id;
	}

	public Room loadWorld()
	{
		return (Room)coder.fromXML(this.readFile(topPath + this.id));
	}

	public String loadInitText()
	{
		return this.readFile(topPath + this.id + "InitText");
	}

	public void saveWorld(Room toSave)
	{
		this.writeFile(coder.toXML(toSave), topPath + this.id);
	}

	public void saveInitText(String initText)
	{
		this.writeFile(initText, topPath + this.id + "InitText");
	}

	private String readFile(String path)
	{
		File file = new File(path);
		Scanner fileScan = new Scanner("");
		try
		{
			fileScan = new Scanner(file, "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}

		StringBuffer out = new StringBuffer();
		if(fileScan.hasNextLine())
		{
			out.append(fileScan.nextLine());
		}
		while(fileScan.hasNextLine())
		{
			out.append("\n" + fileScan.nextLine());
		}
		return out.toString();
	}

	private void writeFile(String data, String path)
	{
		File file = new File(path);
		FileWriter writer;
		try
		{
			writer = new FileWriter(file);
			writer.write(data);
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Write error.");
		}
	}
}
