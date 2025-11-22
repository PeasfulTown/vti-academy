package xyz.peasfultown.backend;

import java.util.ArrayList;
import java.util.Iterator;

import xyz.peasfultown.entity.News;

public class NewsController {
	private ArrayList<News> news;
	public NewsController() {
		this.news = new ArrayList<>();
	}
	
	public void insert(News news) {
		this.news.add(news);
	}
	
	public News get(String title) {
		Iterator<News> itr = this.news.iterator();
		while (itr.hasNext()) {
			News n = itr.next();
			if (n.getTitle().equalsIgnoreCase(title)) return n;
		}
		return null;
	}

	public ArrayList<News> getAll() {
		return this.news;
	}
	
	public void delete(News news) {
		this.news.remove(news);
	}
	
	public void delete(String title) {
		Iterator<News> itr = this.news.iterator();
		while(itr.hasNext()) {
			News n = itr.next();
			if (n.getTitle().equals(title))
				itr.remove();
		}
	}
}
