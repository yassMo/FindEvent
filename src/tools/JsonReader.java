package tools;

import java.util.ArrayList;

import wsSem.model.JsonEvent;

public class JsonReader {
	public static ArrayList<JsonEvent> read(AbstractJsonReader reader, String jFile) {
		return reader.JsonToEvent(jFile);
	}

}
