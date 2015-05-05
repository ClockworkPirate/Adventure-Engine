package com.guerrieri.mud.navigation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import com.guerrieri.mud.AbstractObject;
import com.guerrieri.mud.GameObject;
import com.guerrieri.mud.control.Lexicon;
import com.guerrieri.mud.control.Noun;
import com.guerrieri.mud.control.Verb;

/**
 * The <code>Room</code> class represents areas in the game world (literal rooms in a dungeon, maybe map grids outdoors).
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Room extends AbstractObject
{
	private Hashtable<String, GameObject> objects;

	protected static int idCount = 0;
	protected int id;

	public Room(String[] names, String description, String detailedDesc, boolean visible)
	{
		super(names, description, detailedDesc, visible);
		this.objects = new Hashtable<String, GameObject>();

		this.id = idCount ++;
	}

	/**
	 * Returns the <code>Room</code>'s long description.
	 *
	 * @return The <code>Room</code>'s long description.
	 */
	public String getDescription()
	{
		StringBuilder out = new StringBuilder();
		out.append(this.desc + "\n");
		Set<String> keys = this.objects.keySet();
		for(String s : keys)
		{
			GameObject obj = this.objects.get(s);
			if(obj.isVisible() && obj.getDescription().length() > 0) out.append(obj.getDescription() + "\n");
		}
		return out.toString().substring(0, out.length() - 1);
	}

	public void addObject(String name, GameObject object)
	{
		this.objects.put(name, object);
	}

	public void removeObject(String name)
	{
		this.objects.remove(name);
	}

	public Lexicon getLexicon()
	{
		Lexicon out = new Lexicon();
		Set<String> keys = this.objects.keySet();
		for(String s : keys)
		{
			GameObject obj = this.objects.get(s);
			out.addAll(obj.getNouns());
			out.addAll(obj.getVerbs());
		}
		return out;
	}

	/**
	 * Returns the names of the contents of the <code>Room</code> as <code>Noun</code>s.
	 *
	 * @return An array of <code>Noun</code>s containing one generated from each <code>GameObject</code> in the <code>Room</code>.
	 */
	public Noun[] getNouns()
	{
		ArrayList<Noun> out = new ArrayList<Noun>();
		for(String key : this.objects.keySet())
		{
			GameObject obj = this.objects.get(key);
			for(int k = 0; obj.isVisible() && k < obj.getNouns().length; k ++)
			{
				out.add(obj.getNouns()[k]);
			}
		}
		return out.toArray(new Noun[0]);
	}

	/**
	 * Returns the <code>Verb</code>s that the Objects in this <code>Room</code> support.
	 *
	 * @return An array of <code>Verb</code>s that can be performed in this <code>Room</code>.
	 */
	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		for(String key : this.objects.keySet())
		{
			GameObject obj = this.objects.get(key);
			Verb[] objVerbs = obj.getVerbs();
			for(Verb v : objVerbs)
			{
				out.add(v);
			}
		}
		for(Verb v : super.getVerbs()) out.add(v);
		return out.toArray(new Verb[0]);
	}

	public int getID()
	{
		return this.id;
	}
}
