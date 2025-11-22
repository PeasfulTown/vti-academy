package xyz.peasfultown.frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

import xyz.peasfultown.backend.NewsController;
import xyz.peasfultown.entity.News;
import xyz.peasfultown.utils.PromptUtils;

public class MyNews {
	private Scanner scanner;
	private NewsController nc;
	public MyNews(Scanner scanner, NewsController nc) {
		this.scanner = scanner;
		this.nc = nc;
	}
	
	public void run() {
		while (true) {
			System.out.println("News menu");
			System.out.printf("%4s%s\n", "", "(1) Insert News");
			System.out.printf("%4s%s\n", "", "(2) View list news");
			System.out.printf("%4s%s\n", "", "(3) Average rate");
			System.out.printf("%4s%s\n", "", "(4) Exit");
			
			int usrop = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 1:
				insertNews(this.scanner);
				break;
			case 2:
				viewListNews();
				break;
			case 3:
				viewAverageRates(this.scanner);
				break;
			case 4:
				System.out.println("Exitting program");
				return;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
			PromptUtils.continuePrompt(scanner);
		}
	}
	
	private void insertNews(Scanner scanner) {
		String title = PromptUtils.getStringUserInput(scanner, "Enter news title: ");
		String publishDate = getDateInput(scanner);
		String author = PromptUtils.getStringUserInput(scanner, "Enter author name: ");
		String content = PromptUtils.getStringUserInput(scanner, "Enter news content: ");
		int[] rates = new int[3];
		for (int i = 0; i < rates.length; i++) {
			rates[i] = PromptUtils.getIntegerUserInput(scanner, String.format("Enter rating #%d: ", i + 1)); 
		}
		News n = new News(title, publishDate, author, content);
		n.setRates(rates);
		this.nc.insert(n);
		System.out.println("News added");
	}
	
	private void viewListNews() {
		Iterator<News> itr = nc.getAll().iterator();
		while (itr.hasNext()) {
			News n = itr.next();
			n.calculate();
			n.display();
		}
	}
	
	private void viewAverageRates(Scanner scanner) {
		while (true) {
			String title = PromptUtils.getStringUserInput(scanner, "Enter news title (or 'quit' to go back): ");
			
			if (title.equalsIgnoreCase("quit")) return;
			
			News n = nc.get(title);
			if (n == null) {
				System.out.println("No news found by that title");
				continue;
			}
			n.calculate();
			n.display();
			
		}
	}
	
	private String getDateInput(Scanner scanner) {
		while (true) {
			String inp = PromptUtils.getStringUserInput(scanner, "Enter publish date(DD/MM/YYYY): ");
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate publishDate = LocalDate.parse(inp, formatter);
				return publishDate.format(formatter);
			} catch(DateTimeParseException e) {
				System.out.println("Invalid date input, try again");
				continue;
			}
		}
	}
}
