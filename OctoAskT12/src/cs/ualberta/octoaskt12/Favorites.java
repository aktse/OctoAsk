package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;

public class Favorites implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3063764048456228381L;
	private ArrayList<Question> favorites;
	
	public Favorites()
	{
		favorites = new ArrayList<Question>();
	}

	public void add(Question question)
	{
		favorites.add(question);
	}
	
	public int delete(Question question)
	{
		if (favorites.contains(question))
		{
			favorites.remove(question);
			
			// returns 1 if question successfully removed
			return 1;
		}
		else
		{
			// returns 0 if question does not exist in favorite
			return 0;
		}
	}
	
	public int exist(Question question)
	{
		if (favorites.contains(question))
		{
			return 1;
		}
		
		else
		{
			return 0;
		}
	}
	
	public int count()
	{
		return favorites.size();
	}
	
	public ArrayList<Question> getFavourites()
	{
		return this.favorites;
	}
}
