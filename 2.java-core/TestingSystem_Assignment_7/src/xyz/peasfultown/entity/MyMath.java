package xyz.peasfultown.entity;

public class MyMath {
	public static int max(int in1, int in2) {
		if (in1 > in2) return in1;
		else return in2;
	}
	
	public static int min(int in1, int in2) {
		if (in1 < in2) return in1;
		else return in2;
	}
	
	public static int sum(int in1, int in2) {
		return in1 + in2;
	}
}
