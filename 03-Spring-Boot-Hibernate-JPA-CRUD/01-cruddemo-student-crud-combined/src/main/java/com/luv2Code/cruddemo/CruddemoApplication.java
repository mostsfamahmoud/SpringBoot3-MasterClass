package com.luv2Code.cruddemo;

// Import necessary packages and classes
import com.luv2Code.cruddemo.dao.StudentDAO;
import com.luv2Code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

// Main class annotated with @SpringBootApplication to indicate it is a Spring Boot application
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		// Launch the Spring Boot application
		SpringApplication.run(CruddemoApplication.class, args);
	}

	/**
	 * CommandLineRunner bean that executes custom code after the Spring context is loaded.
	 * This bean is used to run specific methods when the application starts.
	 *
	 * @param studentDAO An instance of StudentDAO for database operations.
	 * @return CommandLineRunner instance to run custom code.
	 */
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		// Lambda expression to execute specific code
		return runner -> {
			// Uncomment the desired method to run specific functionality
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);

			// Call the deleteAllStudents method to remove all students from the database
			deleteAllStudents(studentDAO);
		};
	}

	/**
	 * Delete all students from the database and display the number of rows deleted.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting All Students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted Row Count: " + numRowsDeleted);
	}

	/**
	 * Delete a specific student by ID.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting Student Id: " + studentId);
		studentDAO.delete(studentId);
	}

	/**
	 * Update the details of an existing student and display the updated information.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);

		// Get the Student with the specified ID
		Student myStudent = studentDAO.findById(studentId);

		// Display update message and change the first name
		System.out.println("Updating Student...");
		myStudent.setFirstName("Mostafa");

		// Update the student in the database
		studentDAO.update(myStudent);

		// Display the updated student details
		System.out.println("Updated Student: " + myStudent);
	}

	/**
	 * Query and display students with a specific last name.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// Retrieve a list of students by last name
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		// Print each student to the console
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	/**
	 * Query and display all students from the database.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void queryForStudents(StudentDAO studentDAO) {
		// Retrieve all students
		List<Student> theStudents = studentDAO.findAll();

		// Print each student to the console
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	/**
	 * Create and save a new student, then read and display it using its ID.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void readStudent(StudentDAO studentDAO) {
		// Create a new student
		System.out.println("Creating new Student Object");
		Student tempStudent = new Student("Mostafa", "Mahmoud", "Mostafa@luv2Code.com");

		// Save the student to the database
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display the generated ID
		int theId = tempStudent.getId();
		System.out.println("Saved Student. Generated Id: " + theId);

		// Retrieve the student using the ID and print it
		System.out.println("Retrieving Student with Id: " + theId);
		Student myStudent = studentDAO.findById(theId);
		System.out.println("Found the student: " + myStudent);
	}

	/**
	 * Create and save multiple students to the database.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void createMultipleStudents(StudentDAO studentDAO) {
		// Create several student objects
		System.out.println("Creating 3 Student Objects...");
		Student tempStudent1 = new Student("Mostafa", "Mahmoud", "Mostafa@luv2Code.com");
		Student tempStudent2 = new Student("John", "Doe", "John@luv2Code.com");
		Student tempStudent3 = new Student("Mary", "David", "Mary@luv2Code.com");

		// Save each student
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	/**
	 * Create and save a single student to the database.
	 *
	 * @param studentDAO The data access object for Student.
	 */
	private void createStudent(StudentDAO studentDAO) {
		// Create a new student
		System.out.println("Creating new Student Object");
		Student tempStudent = new Student("Mostafa", "Mahmoud", "Mostafa@luv2Code.com");

		// Save the student to the database
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display the generated ID
		System.out.println("Saved Student. Generated Id: " + tempStudent.getId());
	}
}
