package xyz.peasfultown.entity;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Phone {
	private ArrayList<Contact> contacts;
	
	public Phone() {
		this.contacts = new ArrayList<>();
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public abstract void insertContact(String name, String phone);
	public abstract void removeContact(String name);
	public abstract void updateContact(String name, String newPhone);
	public abstract void searchContact(String name);
}
