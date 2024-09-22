package com.spring.orm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.zip.InflaterInputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
//		Student student1 = new Student(2, "Kunal Gupta", "Birgunj");
//		Student student2 = new Student(3, "Megha Gupta", "Ghadiarwa");
//		List<Student> students = Arrays.asList(student1, student2);
//		studentDao.insertStudent(student);
//		studentDao.saveAllStudents(students);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean go = true;
		while (go) {
			System.out.println("PRESS 1 for adding new student");
			System.out.println("PRESS 2 for displaying all student");
			System.out.println("PRESS 3 for getting details of a student");
			System.out.println("PRESS 4 for deleting a student");
			System.out.println("PRESS 5 for updating a student");
			System.out.println("PRESS 6 for exit");
			try {

				int input = Integer.parseInt(br.readLine());

				switch (input) {
				case 1:
					System.out.println("Enter student id:");
					int id = Integer.parseInt(br.readLine());
					System.out.println("Enter student name:");
					String name = br.readLine();
					System.out.println("Enter student city:");
					String city = br.readLine();
					Student st = new Student(id, name, city);
					studentDao.insertStudent(st);
					System.out.println("New Student Added !!!");
					System.out.println("***********************************");
					break;
				case 2:
					List<Student> allStudents = studentDao.getAllStudents();
					System.out.println("All Students");
					for (Student s : allStudents)
						System.out.println(s);
					System.out.println("***********************************");
					break;
				case 3:
					System.out.println("Enter the student id");
					int sId = Integer.parseInt(br.readLine());
					Student student = studentDao.getStudentById(sId);
					System.out.println("Requested student details");
					System.out.println(student);
					System.out.println("***********************************");

					break;
				case 4:
					System.out.println("Enter the student id");
					int studentId = Integer.parseInt(br.readLine());
					Student std = studentDao.getStudentById(studentId);
					studentDao.deleteStudent(std);
					System.out.println("Student deleted");
					System.out.println("***********************************");
					break;
				case 5:
					System.out.println("Enter the student id you wnat to update");
					int stdId = Integer.parseInt(br.readLine());
					Student stud = studentDao.getStudentById(stdId);
					if (stud != null) {
						System.out.println("Enter 1 to update name");
						System.out.println("Enter 2 to update city");
						System.out.println("Enter 3 to update name & city both");
						int inputValue = Integer.parseInt(br.readLine());
						if (inputValue == 1) {
							System.out.println("Please enter updated name");
							String sName = br.readLine();
							stud.setStudentName(sName);
							studentDao.updateStudent(stud);
							System.out.println("Student name updated");

						} else if (inputValue == 2) {
							System.out.println("Please enter updated city");
							String sCity = br.readLine();
							stud.setStudentCity(sCity);
							studentDao.updateStudent(stud);
							System.out.println("Student city updated");

						} else if (inputValue == 3) {
							System.out.println("Please enter updated name");
							String sName = br.readLine();
							stud.setStudentName(sName);
							System.out.println("Please enter updated city");
							String sCity = br.readLine();
							stud.setStudentCity(sCity);
							studentDao.updateStudent(stud);
							System.out.println("Student details updated");

						} else {
							System.out.println("Invalid input taking you to main menu");
							System.out.println("***********************************");
							break;
						}

					} else {
						System.out.println("Requested student is not presnt in our database");

					}
					System.out.println("***********************************");
					break;

				default:
					go = false;
					break;
				}

			} catch (Exception e) {
				System.err.println("Invalid Input !! \n Please try with available options");

			}
		}
		System.err.println("Thanks For Using My Application");

	}
}
