package util;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import model.*;

public class Utilities {
	private static Random random = new Random();
	
	private static final int STUDENT_COUNT = 1000;
	private static final int INSTRUCTOR_COUNT = 500;
	private static final int OPEN_SPACE = 1000;
	private static final int TEXTBOOK_COUNT = 38639;
	
	private static String firstNames = "rawData/names/firstNames.txt";
	private static String lastNames = "rawData/names/lastNames.txt";
	private static String majors = "rawData/majors/Majors.txt";
	private static String isbns = "rawData/Textbooks/textbook_isbns.txt";
	private static String titles = "rawData/Textbooks/textbook_titles.txt";

	private static String[] firstNameArr = makeArray(firstNames);
	private static String[] lastNameArr = makeArray(lastNames);
	private static String[] majorArr = makeMajorArray(majors);
	private static String[] ranks = {"Instructor", "Assistant Professor", "Associate Professor", "Professor"};
	private static String[][] titleAndIsbnArr = emitTitleAndIsbn(titles, isbns);

	public static Name emitName() {
		String randFirstName = firstNameArr[random.nextInt(firstNameArr.length)];
		String randLastName = lastNameArr[random.nextInt(lastNameArr.length)];
		return new Name(randFirstName, randLastName);
	}
	
	public static double emitPrice() {
		Double temp = (double) (random.nextInt(20100));
		temp = (temp / 100.0);
		double rounded = Math.round(temp * 100.0) / 100.0;
		return rounded;
	}
	
	public static double emitSalary() {
		Double temp = (double) (random.nextInt(9000000));
		temp = ((temp / 100.0) + 10000);
		double rounded = Math.round(temp * 100.0) / 100.0;
		return rounded;
	}
	
	public static double emitGpa() {
		Double temp = (double) (random.nextInt(41));
		temp /= 10.0;
		double rounded = Math.round(temp * 10.0) / 10.0;
		return rounded;
	}
	
	public static String emitRank() {
		return ranks[(random.nextInt(ranks.length))];
	}
	
	public static String[][] emitTitleAndIsbn(String titles, String isbns) {
		try {
			BufferedReader brTitles = new BufferedReader(new FileReader(titles));
			BufferedReader brIsbns = new BufferedReader(new FileReader(isbns));
			String[][] arr = new String[38639][2]; 
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = brTitles.readLine();
				arr[i][1] = brIsbns.readLine();
			}
			return arr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Textbook[] importBooks() {
		Textbook[] textbooks = new Textbook[TEXTBOOK_COUNT];
		for (int i = 0; i < TEXTBOOK_COUNT; i++) {
			textbooks[i] = new Textbook(titleAndIsbnArr[i][0], titleAndIsbnArr[i][1], emitName(), emitPrice());
		}
		return textbooks;
	}
	
	public static Student[] importStudents() {
		Student[] students = new Student[STUDENT_COUNT];
		for (int i = 0; i < STUDENT_COUNT; i++) {
			students[i] = new Student(emitName(), emitGpa(), emitMajor());
		}
		return students;
	}
	
	public static Instructor[] importInstructors() {
		Instructor[] instructors = new Instructor[INSTRUCTOR_COUNT];
		for (int i = 0; i < INSTRUCTOR_COUNT; i++) {
			instructors[i] = new Instructor(emitName(), emitRank(), emitSalary());
		}
		return instructors;
	}
	
	public static PersonBag loadPerson(Student[] studentArr, Instructor[] instructorArr) {
		PersonBag personBag = new PersonBag(STUDENT_COUNT + INSTRUCTOR_COUNT + OPEN_SPACE);
		for (int i = 0; i < STUDENT_COUNT; i++) {
			personBag.insertStudent(studentArr[i]);
		}
		for (int i = 0; i < INSTRUCTOR_COUNT; i++) {
			personBag.insertInstructor(instructorArr[i]);
		}
		return personBag;
	}
	
	public static TextbookBag loadTextbook(Textbook[] textbookArr) {
		TextbookBag textbookBag = new TextbookBag(TEXTBOOK_COUNT + OPEN_SPACE);
		for (int i = 0; i < TEXTBOOK_COUNT; i++) {
			textbookBag.insert(textbookArr[i]);
		}
		return textbookBag;
	}

	public static String emitMajor() {
		String randMajor = majorArr[random.nextInt(majorArr.length)];
		return randMajor;
	}

	public static String[] makeMajorArray(String fileName) {
		File file = new File(fileName);
		String[] arr;
		try {
			Scanner scanner = new Scanner(file);
			String line = scanner.nextLine();
			arr = line.split(", ");
			scanner.close();
			return arr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] makeArray(String fileName) {
		File file = new File(fileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int lineCount = 0;
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			lineCount++;
		}
		scanner.close();
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] arr = new String[lineCount];
		lineCount = 0;
		while (scanner.hasNextLine()) {
			arr[lineCount++] = scanner.nextLine();
		}
		scanner.close();
		return arr;
	}
}