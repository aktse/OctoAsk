package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class Favourites {
	private ArrayList<Question> favorites;
	
	public Favourites()
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
