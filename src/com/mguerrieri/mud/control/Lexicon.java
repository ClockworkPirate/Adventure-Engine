package com.mguerrieri.mud.control;

import java.util.Hashtable;

/**
 * Holds a list of words that an <code>Engine</code> searches when parsing commands.
 * @author Mario Guerrieri
 *
 */
public class Lexicon
{
	private Hashtable<String, Noun> nouns;
	private Hashtable<String, Verb> verbs;

	public Lexicon()
	{
		this.nouns = new Hashtable<String, Noun>();
		this.verbs = new Hashtable<String, Verb>();
	}

	public Lexicon(Noun[] nouns, Verb[] verbs)
	{
		this.nouns = new Hashtable<String, Noun>();
		for(Noun n : nouns)
		{
			this.nouns.put(n.getName().toLowerCase(), n);
		}

		this.verbs = new Hashtable<String, Verb>();
		for(Verb v : verbs)
		{
			this.verbs.put(v.getName().toLowerCase(), v);
		}
	}

	public void addAll(Lexicon toAdd)
	{
		this.nouns.putAll(toAdd.nouns);
		this.verbs.putAll(toAdd.verbs);
	}

	public void addAll(Noun[] toAdd)
	{
		for(Noun n : toAdd)
		{
			this.nouns.put(n.getName().toLowerCase(), n);
		}
	}

	public void addAll(Verb[] toAdd)
	{
		for(Verb v : toAdd)
		{
			this.verbs.put(v.getName().toLowerCase(), v);
		}
	}

	public void addNoun(Noun toAdd)
	{
		this.nouns.put(toAdd.getName().toLowerCase(), toAdd);
	}

	public void addVerb(Verb toAdd)
	{
		this.verbs.put(toAdd.getName().toLowerCase(), toAdd);
	}

	public boolean containsNoun(String noun)
	{
		return this.nouns.containsKey(noun.toLowerCase());
	}

	public boolean containsVerb(String verb)
	{
		return this.verbs.contains(new Verb(verb.toLowerCase(), ""));
	}

	public Noun getNoun(String noun)
	{
		return this.nouns.get(noun.toLowerCase());
	}

	public Verb getVerb(String verb)
	{
		return this.verbs.get(verb.toLowerCase());
	}

	public String toString()
	{
		if(this.nouns.size() > 0 && this.verbs.size() > 0)
		{
			StringBuilder out = new StringBuilder();
			out.append("Valid words:\n");
			for(String s : this.nouns.keySet())
			{
				out.append(this.nouns.get(s) + "\n");
			}
			for(String s : this.verbs.keySet())
			{
				out.append(this.verbs.get(s) + "\n");
			}
			return out.toString().substring(0, out.length() - 1);
		}
		else return "No valid words.";
	}
}
