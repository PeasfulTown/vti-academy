package xyz.peasfultown.entity;

import java.time.LocalDate;

public class News implements INews {
	private static int count;
	static {
		count = 0;
	}
	private int id;
	private String title;
	private String publishDate;
	private String author;
	private String content;
	
	private int[] rates;
	private float averageRate;
	
	{
		this.id = count++;
		this.rates = new int[3];
	}
	
	public News() {
		this("New Title", LocalDate.now().toString(), "Undefined", "Undefined");
	}
	
	public News(String title, String publishDate, String author, String content) {
		this.title = title;
		this.publishDate = publishDate;
		this.author = author;
		this.content = content;
		this.averageRate = 0.0f;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setRates(int[] rates) {
		this.rates = rates;
	}
	
	public float getAverageRate() {
		return this.averageRate;
	}

	public void display() {
		System.out.printf("Title: %s\n", this.title);
		System.out.printf("Publish Date: %s\n", this.publishDate);
		System.out.printf("Content: %s\n", this.content);
		System.out.printf("Average rate: %.2f\n", this.averageRate);
	}
	
	public float calculate() {
		this.averageRate = ( rates[0] + rates[1] + rates[2] ) / 3;
		return this.averageRate;
	}
}
