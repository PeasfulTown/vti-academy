package xyz.peasfultown.backend;

import java.util.ArrayList;
import java.util.Iterator;

import xyz.peasfultown.entity.Employee;
import xyz.peasfultown.entity.Engineer;
import xyz.peasfultown.entity.Personnel;
import xyz.peasfultown.entity.Worker;

public class PersonnelController {
	private ArrayList<Personnel> personnels;
	
	public PersonnelController() {
		this.personnels = new ArrayList<>();
	}
	
	public void addPersonnel(Personnel p) {
		this.personnels.add(p);
	}
	
	public void deletePersonnel(Personnel p) {
		this.personnels.remove(p);
	}
	
	public Personnel getPersonnelByName(String fullname) {
		Iterator<Personnel> itr = this.personnels.iterator();
		while (itr.hasNext()) {
			Personnel p = itr.next();
			if (p.getFullname().equals(fullname))
				return p;
		}
		return null;
	}
	
	public void deletePersonnelByName(String fullname) {
		Iterator<Personnel> itr = this.personnels.iterator();
		while (itr.hasNext()) {
			String pname = itr.next().getFullname();
			if (pname.equals(fullname)) {
				itr.remove();
				return;
			}
		}
	}
	
	public void showAllPersonnelInfo() {
		Iterator<Personnel> itr = this.personnels.iterator();
		while (itr.hasNext()) {
			itr.next().showInfo();
		}
		showDetailedPersonnelCount();
	}
	
	private void showDetailedPersonnelCount() {
		int workerCount = 0, engineerCount = 0, employeeCount = 0;
		Iterator<Personnel> itr = personnels.iterator();
		while (itr.hasNext()) {
			Personnel p  = itr.next();
			if (p instanceof Worker)
				workerCount++;
			else if (p instanceof Engineer)
				engineerCount++;
			else if (p instanceof Employee)
				employeeCount++;
		}
		
		System.out.printf("Worker count: %d\n", workerCount);
		System.out.printf("Engineer count: %d\n", engineerCount);
		System.out.printf("Employee count: %d\n", employeeCount);
		System.out.printf("Total personnel count: %d\n", this.personnels.size());
	}
}
