package io.altar.jseproject.utils;



import java.util.Arrays;
import java.util.Scanner;

public class ScannerUtils {
	private Scanner sc = new Scanner(System.in);

	
	public int getInt(String msg) {
		System.out.println(msg);

		while (sc.hasNextInt() != true) {
			System.out.println("Input tem de ser um numero inteiro \n"+msg);
			sc.next();
		}

		int input = sc.nextInt();
		sc.nextLine();
		return input;
	}
	public int getInt(String msg, int min, int max) {
		int input = 0;
		boolean valid = false;
		while (!valid) {
			input = getInt(msg);
			if (input >= min && input <= max) {
				valid = true;
			} else {
				System.out.println("Numero inserido tem de ser entre " + min + " e " + max + ".");
			}
		}
		return input;
	}


	public int getInt(String msg, int[] value) {
		int input = getInt(msg);
		while (Arrays.binarySearch(value, input) < 0) {
			msg = "Escolha uma das opções fornecidas "+ Arrays.toString(value);
			input = getInt(msg);
		}
		return input;
	}

	public String getString(String msg) {
		System.out.println(msg);
		String output = "";
		output = sc.nextLine();
		return output;
	}
}