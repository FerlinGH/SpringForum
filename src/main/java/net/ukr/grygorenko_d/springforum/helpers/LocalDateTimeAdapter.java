package net.ukr.grygorenko_d.springforum.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter {

	public static String describeCurrentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String time = currentTime.format(formatter).toString();

		return time;
	}
	
	public static String describeTime(LocalDateTime currentTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String time = currentTime.format(formatter).toString();
		return time;
	}

}
