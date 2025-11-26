package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.Exercise1Controller;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Exercise1Controller ex1c = new Exercise1Controller();
		Exercise1 ex1 = new Exercise1(scanner, ex1c);
		ex1.run();
		scanner.close();
	}
}
