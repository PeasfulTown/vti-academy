package xyz.peasfultown.utils;

public class OptionPrompt {
	private char separatorChar;
	private int sidepad;
	private int indentspace;

	public OptionPrompt() {
		this.separatorChar = '=';
		this.sidepad = 4;
		this.indentspace = 4;
	}

	public OptionPrompt(char separatorChar) {
		this(separatorChar, 4, 4);
	}

	public OptionPrompt(char separatorChar, int sidepad) {
		this(separatorChar, sidepad, 4);
	}
	
	public OptionPrompt(char separatorChar, int sidepad, int indentspace) {
		this.separatorChar = separatorChar;
		this.sidepad = sidepad;
		this.indentspace = indentspace;
	}

	public void printOptionsMenu(String title, String... options) {
		int length = title.length() + sidepad + indentspace + 2;
		for (int i = 0; i < options.length; i++) {
			if (options[i].length() > length)
				length = options.length + sidepad + indentspace + 2;
		}

		System.out.println(createSeparatorLine(length));
		System.out.println(createLineWithText(title, length));
		System.out.println(createLinesWithTextIndented(options, length, indentspace));
	}

	private String createSeparatorLine(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
			sb.append(separatorChar);
		return sb.toString();
	}

	private String createLineWithText(String text, int maxlength) {
		StringBuilder sb = new StringBuilder();
		boolean textLengthSameAsMaxLength = text.length() == maxlength;

		int lenl = textLengthSameAsMaxLength ? 0 : (maxlength - text.length()) / 2 - 1;

		if (!textLengthSameAsMaxLength) {
			sb.append(createSeparatorLine(lenl));
			sb.append('\s');
		}
		
		sb.append(text);

		int remainingSpace = maxlength - sb.length();
		if (remainingSpace > 0) {
			sb.append('\s');
			sb.append(createSeparatorLine(remainingSpace - 1));			
		}
		return sb.toString();
	}

	private String createLinesWithTextIndented(String[] text, int maxlen, int indentSpace) {
		StringBuilder sb = new StringBuilder();
		
		int lenl = sidepad + indentspace;
		for(String str : text) {
			sb.append(createSeparatorLine(lenl));
			for (int i = 0; i < indentspace; i++) sb.append('\s');
			sb.append(str);
			sb.append('\s');
			sb.append(createSeparatorLine(maxlen - sb.length()));			
			sb.append('\n');
		}
		return sb.toString();
	}
}
