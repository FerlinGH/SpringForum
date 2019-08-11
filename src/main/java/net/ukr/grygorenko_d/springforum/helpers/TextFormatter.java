package net.ukr.grygorenko_d.springforum.helpers;

public class TextFormatter {
	
	private TextFormatter() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String formatString(String input) {
		input = input.replaceAll("^\\s+|\\s+$", "");
		return input;
	}

}
