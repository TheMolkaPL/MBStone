package pl.mbstone.inder00;

import pl.mbstone.inder00.data.Settings;

public class Data {

	public static long getTicks(String type)
	{
		 if (type.equalsIgnoreCase("regenerate"))
		 {
			 long tick = Settings.getConfig().getLong("regenerate");
			 return tick;
		 }
		 long error = 0L;
		 return error;
	}
}
