package de.supercode.jsonreader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class JsonreaderApplication {
	private static final String FILE_PATH = "students.json";
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Max", 20));
		students.add(new Student("Anna", 22));

		saveToJsonFile(students);
		List<Student> loadedStudents = loadFromJsonFile();
		loadedStudents.forEach(System.out::println);

		students.add(new Student("Yan", 28));
		saveToJsonFile(students);
		 loadedStudents = loadFromJsonFile();
		loadedStudents.forEach(System.out::println);
	}

	public static void saveToJsonFile(List<Student> students) {
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			objectMapper.writeValue(new File(FILE_PATH), students);
			System.out.println("Daten wurden erfolgreich gespeichert");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Student> loadFromJsonFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			return objectMapper.readValue(new File(FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(List.class,Student.class));

		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
