package xyz.peasfultown.program;

import java.util.Scanner;

import xyz.peasfultown.entity.Group;
import xyz.peasfultown.utils.Prompt;

public class Exercise4 {
	private Scanner scanner;
	private Data data;

	public Exercise4(Scanner scanner, Data data) {
		System.out.println("Initializing Exercise 4");
		this.scanner = scanner;
		this.data = data;
	}

	public void run() {
		while (true) {
			System.out.println("Exercise 4 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");
			System.out.printf("%4s%s\n", " ", "(6) Question 6");
			System.out.printf("%4s%s\n", " ", "(7) Question 7");
			System.out.printf("%4s%s\n", " ", "(8) Question 8");
			System.out.printf("%4s%s\n", " ", "(9) Question 9");
			
			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 4");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 4\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1();
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2();
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3();
				break;
			case 4:
				System.out.println("Selected Question 4");
				question4();
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5();
				break;
			case 6:
				System.out.println("Selected Question 6");
				question6();
				break;
			case 7:
				System.out.println("Selected Question 7");
				question7();
				break;
			case 8:
				System.out.println("Selected Question 8");
				question8(data.getGroups());
				break;
			case 9:
				System.out.println("Selected Question 9");
				question9(data.getGroups());
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}

	public void question1() {
		String inp = Prompt.getStringUserInput(scanner, "Enter a string: ");
		System.out.printf("\"%s\" word count: %d\n", inp, countWords(inp.toCharArray()));
	}

	public void question2() {
		String inp1 = Prompt.getStringUserInput(scanner, "Enter a string: ");
		String inp2 = Prompt.getStringUserInput(scanner, "Enter next string: ");
		System.out.printf("Joined string: %s\n", inp1.concat(inp2));
	}

	public void question3() {
		String[] inp = Prompt.getStringUserInput(scanner, "Enter a name: ").split("\s+");
		for (int i = 0; i < inp.length; i++) {
			char[] charr = inp[i].toCharArray();
			if (!Character.isUpperCase(charr[0])) {
				charr[0] = Character.toUpperCase(charr[0]);
				inp[i] = String.valueOf(charr);
			}
		}
		System.out.printf("Capitalized name: %s\n", String.join(" ", inp));
	}

	public void question4() {
		char[] inp = trim(Prompt.getStringUserInput(scanner, "Enter a name: ").toCharArray());
		int i = 0;
		while (i < inp.length) {
			if (inp[i] != '\s')
				System.out.printf("Letter #%02d: %c\n", i + 1, Character.toUpperCase(inp[i]));
			++i;
		}
	}

	public void question5() {
		String ln = Prompt.getStringUserInput(scanner, "Enter lastname: ");
		String fn = Prompt.getStringUserInput(scanner, "Enter firstname: ");
		System.out.printf("%s\n", String.join(" ", fn, ln));
	}
	
	public void question6() {
		String[] inp = Prompt.getStringUserInput(scanner, "Enter fullname: ").split("\s+");
		System.out.printf("Lastname: %s\n", inp[0]);
		String[] middlename = new String[inp.length - 2];
		for (int i = 0, j = 1; j < inp.length - 1; i++, j++) {
			middlename[i] = inp[j];
		}
		System.out.printf("Middlename: %s\n", String.join(" ", middlename));
		System.out.printf("Firstname: %s\n", inp[inp.length - 1]);
	}
	
	public void question7() {
		String inp = Prompt.getStringUserInput(scanner, "Enter a name to clean: ");
		System.out.printf("a) \"%s\" = \"%s\"\n", inp, String.valueOf(cleanstr(inp.toCharArray())));
		System.out.printf("b) \"%s\" = \"%s\"\n", inp, String.valueOf(capitalizeFirst(cleanstr(inp.toCharArray()))));
	}

	public void question8(Group[] groups) {
		String keyinp = "Java";
		for (Group grp : groups) {
			if (strcontains(grp.getName().toCharArray(), keyinp.toCharArray()))
				System.out.printf("Group \"%s\" contains \"%s\" = %s\n", grp.getName(), keyinp, strcontains(grp.getName().toCharArray(), keyinp.toCharArray()));			
		}
	}
	
	public void question9(Group[] groups) {
		for (Group grp: groups) {
			if(grp.getName().equals("Java"))
				System.out.printf("Group ID: %d, Group Name: %s, Creator: %s, Creation Date: %4$tF %4$tT\n", grp.getId(), grp.getName(), grp.getCreator().getUsername(), grp.getCreateDate());
		}
	}
	
	private int countWords(char[] charArr) {
		int i = 0, wc = 0;
		boolean prevword = false;
		while (i < charArr.length) {
			switch (charArr[i]) {
			case '\s':
			case '\f':
			case '\t':
			case '\n':
			case '\r':
				if (prevword) {
					wc++;
					prevword = false;
				}
				break;
			default:
				if (i == charArr.length - 1)
					wc++;
				prevword = true;
				break;
			}
			i++;
		}
		return wc;
	}

	private char[] trim(char[] charArr) {
		return trimr(triml(charArr));
	}

	private char[] triml(char[] charr) {
		int il = 0;
		while (charr[il] == '\s')
			il++;
		if (il > 0) {
			char[] ncharr = new char[charr.length - il];
			for (int i = 0; i < ncharr.length; i++, il++)
				ncharr[i] = charr[il];
			return ncharr;
		} else
			return charr;
	}

	private char[] trimr(char[] charr) {
		int ir = charr.length - 1;
		while (charr[ir] == '\s')
			--ir;
		if (ir < charr.length - 1) {
			char[] ncharr = new char[ir + 1];
			for (int i = 0; i < ncharr.length; i++)
				ncharr[i] = charr[i];
			return ncharr;
		} else
			return charr;
	}

	private char[] cleanstr(char[] charr) {
		int i = 0, lc = 0, wc = 0;
		boolean inword = false;
		
		while (i < charr.length) {
			switch (charr[i]) {
			case '\s': case '\t': case '\r': case '\f': case '\n':
				if (inword == true) {
					inword = false;
					wc++;
				}
				break;
			default: 
				lc++;
				if (inword == false) inword = true;
				if (i == charr.length - 1) wc++;
				break;
			}
			++i;
		}
		
		char[] ncharr = new char[lc + (wc - 1)];
		i = 0;
		int j = 0;
		inword = false;
		while (j < ncharr.length) {
			switch (charr[i]) {
			case '\s': case '\t': case '\r': case '\f': case '\n':	
				if (inword == true) {
					inword = false;
					ncharr[j++] = '\s';
				}
				break;
			default:
				ncharr[j++] = charr[i];
				if (inword == false) {
					inword = true;
				}
				break;
			}
			++i;
		}
		return ncharr;
	}
	
	private char[] capitalizeFirst(char[] charr) {
		int i = 0;
		boolean inword = false;
		while (i < charr.length) {
			switch (charr[i]) {
			case '\s': case '\t': case '\r': case '\f': case '\n':
				if (inword == true) inword = false;
				break;
			default: 
				if (inword == false) {
					inword = true;
					if (Character.isLowerCase(charr[i]))
						charr[i] = Character.toUpperCase(charr[i]);
				}
				break;
			}
			++i;
		}
		return charr;
	}
	
	private boolean strcontains(char[] target, char[] key) {
		int i = 0, j = 0;
		
		while (i < target.length) {
			if (target[i] == key[j]) j++;
			else if (j > 0 && target[i] != key[j]) j = 0;
			if (j == key.length) return true;
			++i;
		}
		return false;
	}
}
