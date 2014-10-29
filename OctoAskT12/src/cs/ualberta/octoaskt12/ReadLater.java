package cs.ualberta.octoaskt12;

import java.io.Serializable;

public class ReadLater implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2697955593229698772L;
	private QuestionArrayList qal;
	
	public ReadLater()
	{
		this.qal = new QuestionArrayList();
	}
	
	// constructor for when an existing QuestionArrayList is passed
	public ReadLater(QuestionArrayList qal)
	{
		this.qal = qal;
	}

	public void add(Question question)
	{
		qal.addQuestion(question);
	}
	
	public void delete(Question question)
	{
		qal.remove(question);
	}
	
	/*
	public int exist(Question question)
	{
		if (qal.contains(question))
		{
			return 1;
		}
		
		else
		{
			return 0;
		}
	}
	*/
	
	public int count()
	{
		return qal.getSize();
	}
	
	public QuestionArrayList getAll()
	{
		return this.qal;
	}
}
