package xyz.peasfultown.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import xyz.peasfultown.entity.Student;

public class StudentController {
	private ArrayList<Student> students;
	
	public StudentController() {
		this.students = new ArrayList<>();
	}
	
	public List<Student> getAll() {
		return this.students;
	}
	
	public Student get(String name) {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			Student s = itr.next();
			if (s.getName().equals(name)) return s;
		}
		return null;
	}
	
	public void insert(String name, int group) {
		this.students.add(new Student(name, group));
	}
	
	public void insert(Student... students) {
		this.students.addAll(Arrays.asList(students));
	}
	
	public void setStudentGroup(String name, int group) {
		this.get(name).setGroup(group);
	}
	
	public void setStudentGroup(int group, String... names) {
		for (String n : names) {
			this.setStudentGroup(n, group);
		}
	}
	
	public void listStudents() {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().toString());
		}
		System.out.printf("Total students: %d", this.students.size());
	}
	
	public void takeAllAttendance() {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			itr.next().attendance();
		}
	}

	public void makeGroupStudy(int group) {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			Student s = itr.next();
			if (s.getGroup() == group)
				s.study();
		}
	}
	
	public void makeGroupClean(int group) {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			Student s = itr.next();
			if (s.getGroup() == group)
				s.clean();
		}
	}
}
