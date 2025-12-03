package xyz.peasfultown.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import xyz.peasfultown.entity.Circle;
import xyz.peasfultown.entity.MyMath;
import xyz.peasfultown.entity.PrimaryStudent;
import xyz.peasfultown.entity.Rectangle;
import xyz.peasfultown.entity.SecondaryStudent;
import xyz.peasfultown.entity.Shape;
import xyz.peasfultown.entity.Student;

public class Exercise1Controller {
	private ArrayList<Student> students;
	private ArrayList<Shape> shapes;
	public Exercise1Controller() {
		this.students = new ArrayList<>();
		this.shapes = new ArrayList<>();
	
		this.addStudent(new PrimaryStudent("Primary Student 1"), new PrimaryStudent("Primary Student 2"),
				new SecondaryStudent("Secondary Student 1"), new SecondaryStudent("Secondary Student 2"),
				new SecondaryStudent("Secondary Student 3"), new SecondaryStudent("Secondary Student 4"));			
	
	}
	
	private void addStudent(Student student) {
		this.students.add(student);
	}

	private void addStudent(List<Student> student) {
		this.students.addAll(students);
	}

	private void addStudent(Student... student) {
		this.addStudent(Arrays.asList(student));
	}

	private void showStudentsInfo() {
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext())
			itr.next().showInfo();
	}

	private void printStudentGroupMoney() {
		System.out.printf("Total group money: %,dVND\n", Student.getMoney());
	}
	
	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}
	
	public void showShapesInfo() {
		Iterator<Shape> itr = this.shapes.iterator();
		while (itr.hasNext())
			itr.next().showInfo();
		System.out.printf("Total Shape count: %d\n", Shape.getCount());
	}

	public void question1() {
		Student.setCollege("Dai Hoc Bach Khoa");

		System.out.println("First college value:");
		this.showStudentsInfo();

		Student.setCollege("Dai Hoc Cong Nghe");
		
		System.out.println("Second college value:");
		this.showStudentsInfo();
	}

	public void question2() {
		System.out.println("All students contribute 100,000VND");
		Iterator<Student> itr = this.students.iterator();
		while (itr.hasNext()) {
			itr.next().contribute(100000);
		}

		printStudentGroupMoney();
		this.students.get(0).spend(50000, "chips, candies");
		printStudentGroupMoney();
		this.students.get(1).spend(20000, "banh mi");
		printStudentGroupMoney();
		this.students.get(2).spend(150000, "stationery");
		printStudentGroupMoney();
	}

	public void question3_max(int in1, int in2) {
		System.out.printf("MyMath.max of %d and %d is: %d\n", in1, in2, MyMath.max(in1, in2));
	}

	public void question3_min(int in1, int in2) {
		System.out.printf("MyMath.min of %d and %d is: %d\n", in1, in2, MyMath.min(in1, in2));
	}

	public void question3_sum(int in1, int in2) {
		System.out.printf("MyMath.sum of %d and %d is: %d\n", in1, in2, MyMath.sum(in1, in2));
	}

	public void question6() {
		System.out.printf("Number of Students: %d\n", Student.getCount());
		System.out.printf("Number of PrimaryStudents: %d\n", PrimaryStudent.getCount());
		System.out.printf("Number of SecondaryStudents: %d\n", SecondaryStudent.getCount());
	}
	
	
}
