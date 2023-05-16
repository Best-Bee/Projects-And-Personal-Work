package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

public class PersonBag implements Serializable {
	private static final long serialVersionUID = 1L;
	private Person[] arr;
	private int nElems;
	
	public PersonBag(int maxSize) {
		arr = new Person[maxSize];
	}
	
	public void insertStudent(Student student) {
		arr[nElems++] = student;
	}
	public void insertInstructor(Instructor instructor) {
		arr[nElems++] = instructor;
	}
	
	public Student[] searchStudents(Predicate<Person> predicate) {
		Student[] temp = new Student[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if(predicate.test(arr[i]) && arr[i] instanceof Student) {
				temp[count++] = (Student) arr[i];
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public Instructor[] searchInstructors(Predicate<Person> predicate) {
		Instructor[] temp = new Instructor[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if(predicate.test(arr[i]) && arr[i] instanceof Instructor) {
				temp[count++] = (Instructor) arr[i];
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public Person[] deleteStudents(int id, String firstName, String lastName) {
		Person[] temp = new Person[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if ((((arr[i]).getName().getFirstName().equalsIgnoreCase(firstName) && (arr[i]).getName().getLastName().equalsIgnoreCase(lastName)) || String.valueOf(id) == arr[i].getId()) && arr[i] instanceof Student) {
				temp[count++] = arr[i];
				for(int j = i; j < nElems-1; j++) {
					arr[j] = arr[j+1];
				}
				nElems--;
				i--;
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public Person[] deleteInstructors(int id, String firstName, String lastName) {
		Person[] temp = new Person[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if ((((arr[i]).getName().getFirstName().equalsIgnoreCase(firstName) && (arr[i]).getName().getLastName().equalsIgnoreCase(lastName)) || String.valueOf(id) == arr[i].getId()) && arr[i] instanceof Instructor) {
				temp[count++] = arr[i];
				for(int j = i; j < nElems-1; j++) {
					arr[j] = arr[j+1];
				}
				nElems--;
				i--;
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public void updateStudent(int id, String firstName, String lastName, double gpa, String major) {
		for (int i = 0; i < nElems; i++) {
			if (id == Integer.parseInt(arr[i].getId())) {
				if (arr[i] instanceof Student) {
					int tempIdCount = nElems;
					Person.setIdCount(id);
					arr[i] = new Student(new Name(firstName, lastName), gpa, major);
					Person.setIdCount(tempIdCount);
				}
			}
		}
	}
	
	public void updateInstructor(int id, String firstName, String lastName, double salary, String rank) {
		for (int i = 0; i < nElems; i++) {
			if (id == Integer.parseInt(arr[i].getId())) {
				if (arr[i] instanceof Instructor) {
					int tempIdCount = nElems;
					Person.setIdCount(id);
					arr[i] = new Instructor(new Name(firstName, lastName), rank, salary);
					Person.setIdCount(tempIdCount);
				}
			}
		}
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public int getnElems() {
		return nElems;
	}
}