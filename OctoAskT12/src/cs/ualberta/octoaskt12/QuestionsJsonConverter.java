package cs.ualberta.octoaskt12;

import java.lang.reflect.Type;

import android.graphics.Bitmap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class QuestionsJsonConverter implements JsonDeserializer<QuestionArrayList>, JsonSerializer<Bitmap>{

	@Override
	public JsonElement serialize(Bitmap arg0, Type arg1,
			JsonSerializationContext arg2) {
		return null;
	}

	@Override
	public QuestionArrayList deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return null;
	}

}
