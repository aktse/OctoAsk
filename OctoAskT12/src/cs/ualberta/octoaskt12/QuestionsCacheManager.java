package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class QuestionsCacheManager {
	private static final String FILENAME = "OfflineQuestions.sav";
	private User user;
	private Context context;
	public ArrayList<Question> qlist;
	
	public QuestionsCacheManager(Context context)
	{
		this.context = context;
		qlist = new ArrayList<Question>();
	}
	
	public void init()
	{
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qlist = (ArrayList<Question>) ois.readObject();
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "OfflineQuestions.sav");
			try {
				offlineData.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void set(ArrayList<Question> newQuestionsList)
	{
		this.qlist = newQuestionsList;
	}
	
	public ArrayList<Question> get()
	{
		return this.qlist;
	}
	
	public void loadQuestions()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qlist = (ArrayList<Question>) ois.readObject();
			
			fis.close();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addQuestion(Question question)
	{
		this.qlist.add(question);
	}
	
	public void saveQuestions()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.qlist);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear()
	{
		try
		{
			context.deleteFile(FILENAME);
			
			File offlineData = new File(context.getFilesDir(), FILENAME);
			try {
				offlineData.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
