package xyz.peasfultown.entity;

import java.util.Iterator;

public class VietnamesePhone extends Phone {
	public VietnamesePhone() {
		super();
	}
	
	public void insertContact(String name, String phone) {
		this.getContacts().add(new Contact(name, phone));
	}
	
	public void removeContact(String name) {
		Iterator<Contact> itr = this.getContacts().iterator();
		while (itr.hasNext()) {
			if (itr.next().getName().equals(name)) {
				itr.remove();
				return;
			}
		}
	}
	
	public void updateContact(String name, String newPhone) {
		Contact c = getContactByName(name);
		if (c != null)
			c.setNumber(newPhone);
	}
	
	public void searchContact(String name) {
		Contact c = getContactByName(name);
		if (c != null)
			c.showInfo();
	}
	
	public Contact getContactByName(String name) {
		Iterator<Contact> itr = this.getContacts().iterator();
		while (itr.hasNext()) {
			Contact c = itr.next();
			if (c.getName().equals(name))
				return c;
		}
		return null;
	}
}
