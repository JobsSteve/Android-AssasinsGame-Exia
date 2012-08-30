package metier.all_purpose;

import java.util.Date;

public class JSONDateConverter {

	public Date convertJSONDateFormatToJavaDate(String jsonDate){
		jsonDate = jsonDate.split("\\(")[1];
		jsonDate = jsonDate.split("\\)")[0];
		long finalDateMillis = Long.parseLong(jsonDate);
		return new Date(finalDateMillis);
	}
}
