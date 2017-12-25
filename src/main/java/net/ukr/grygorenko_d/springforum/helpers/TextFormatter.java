package net.ukr.grygorenko_d.springforum.helpers;

public class TextFormatter {
	
	public static String formatString(String input) {
		input = input.replaceAll("\\s+", "");
		return input;
	}

}
