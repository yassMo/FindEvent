package tools;

import java.util.ArrayList;

import wsSem.model.JsonEvent;

public class GigatoolsReader extends AbstractJsonReader {

	@Override
	public ArrayList<JsonEvent> ParseJsonFile(String jFile) {
		System.out.println(jFile);
		return null;
	}

}
