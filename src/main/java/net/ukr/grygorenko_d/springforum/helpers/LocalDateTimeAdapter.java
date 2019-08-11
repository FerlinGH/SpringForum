package net.ukr.grygorenko_d.springforum.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter {

	private LocalDateTimeAdapter() {
		throw new IllegalStateException("Utility class");
	}

	public static String describeCurrentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		return currentTime.format(formatter);
	}

	public static String describeTime(LocalDateTime currentTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return currentTime.format(formatter);
	}

}
