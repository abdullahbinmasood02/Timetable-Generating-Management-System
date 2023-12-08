package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import databasePackage.DatabaseManager;


 public class User{
	
	
	 public String name;
	public String Password;
	public String role;
	public String address;
	public String email;
}

class Admin extends User{
	
	Admin(){
		
		super();
		role="admin";
	}
}

class Faculty extends User{
	
	Faculty(){
		
		super();
		role="faculty";
	}
}

class Registration{
	
	Admin admin=new Admin();
	Faculty faculty= new Faculty();
	
	void registerAdmin(String n, String p, String a, String e) {
		
		
		admin.name=n;
		admin.Password=p;
		admin.address=a;
		admin.email=e;
		
		 DatabaseManager d= new DatabaseManager();
		 d.registerUser(admin);
		
	}
	
	
	
void registerFaculty(String n, String p,  String a, String e) {
		
		
		faculty.name=n;
		faculty.Password=p;
		faculty.address=a;
		faculty.email=e;
		
		DatabaseManager d= new DatabaseManager();
		 d.registerUser(faculty);
		
		
	}
}


class Login {

	
	
	public int verifyDetails(int id, String password) {
		
		DatabaseManager d= new DatabaseManager();
		int result=d.verifyLogin(id, password);
		
		return result;
	}
		
		
}


class Hall{
	
	String name;
	int capacity;
	
	public int allocateLectureHall() {
		
		DatabaseManager d= new DatabaseManager();
		return d.allocateHall(this.name, this.capacity);
	}
}
class facultyReport {
	  private SimpleIntegerProperty facultyID;
	    private SimpleStringProperty facultyName;
	    private SimpleIntegerProperty courseID;
	    private SimpleStringProperty courseName;

	    public facultyReport(int facultyID, String name, int courseID, String courseName) {
	        this.facultyID = new SimpleIntegerProperty(facultyID);
	        this.facultyName = new SimpleStringProperty(name);
	        this.courseID = new SimpleIntegerProperty(courseID);
	        this.courseName = new SimpleStringProperty(courseName);
	    }

	    public SimpleIntegerProperty getStudentIDProperty() {
	        return facultyID;
	    }

	    public SimpleStringProperty getStudentNameProperty() {
	        return facultyName;
	    }

	    public SimpleIntegerProperty getCourseIDProperty() {
	        return courseID;
	    }

	    public SimpleStringProperty getCourseNameProperty() {
	        return courseName;
	    }
	    
	    
  
}

class studentReport {
	  private SimpleIntegerProperty studentID;
	    private SimpleStringProperty studentName;
	    private SimpleIntegerProperty courseID;
	    private SimpleStringProperty courseName;

	    public studentReport(int studentID, String name, int courseID, String courseName) {
	        this.studentID = new SimpleIntegerProperty(studentID);
	        this.studentName = new SimpleStringProperty(name);
	        this.courseID = new SimpleIntegerProperty(courseID);
	        this.courseName = new SimpleStringProperty(courseName);
	    }

	    public SimpleIntegerProperty getStudentIDProperty() {
	        return studentID;
	    }

	    public SimpleStringProperty getStudentNameProperty() {
	        return studentName;
	    }

	    public SimpleIntegerProperty getCourseIDProperty() {
	        return courseID;
	    }

	    public SimpleStringProperty getCourseNameProperty() {
	        return courseName;
	    }
	    
	    
   
}

class count{
	
	int courseCount;
	int hallCount;
	
	public int getHallCount() {
		
		DatabaseManager d= new DatabaseManager();
		return d.getHalls();
		
		
	}
	
public int getCourseCount() {
		
	
	DatabaseManager d= new DatabaseManager();
	return d.getCourse();
	
	}
}
class timetable {
	  private SimpleStringProperty id;
	  private SimpleStringProperty courses1;
	  private SimpleStringProperty courses2;
	  private SimpleStringProperty courses3;
	  private SimpleStringProperty courses4;
	  private SimpleStringProperty courses5;
	  private SimpleStringProperty courses6;
	  
	 

	    public timetable(String id,String course) {
	        this.id = new SimpleStringProperty(id);
	        this.courses1=new SimpleStringProperty(course);
	        this.courses2 = new SimpleStringProperty("");
	        this.courses3 = new SimpleStringProperty("");
	        this.courses4 = new SimpleStringProperty("");
	        this.courses5 = new SimpleStringProperty("");
	        this.courses6 = new SimpleStringProperty("");
	 
	    }

	    public SimpleStringProperty getIDProperty() {
	        return id;
	    }
	    
	    public SimpleStringProperty getcourses1Property() {
	        return courses1;
	    }
	    public SimpleStringProperty getcourses2Property() {
	        return courses2;
	    }
	    public SimpleStringProperty getcourses3Property() {
	        return courses3;
	    }
	    public SimpleStringProperty getcourses4Property() {
	        return courses4;
	    }
	    public SimpleStringProperty getcourses5Property() {
	        return courses5;
	    }
	    public SimpleStringProperty getcourses6Property() {
	        return courses6;
	    }

	    public static void setCourseToProperty(timetable rowData, int columnIndex, String courseName) {
		    switch (columnIndex) {
		        case 1:
		            rowData.getcourses1Property().set(courseName);
		            break;
		        case 2:
		            rowData.getcourses2Property().set(courseName);
		            break;
		        case 3:
		            rowData.getcourses3Property().set(courseName);
		            break;
		        case 4:
		            rowData.getcourses4Property().set(courseName);
		            break;
		        case 5:
		            rowData.getcourses5Property().set(courseName);
		            break;
		        case 6:
		            rowData.getcourses6Property().set(courseName);
		            break;
		        default:
		            throw new IllegalArgumentException("Invalid column index");
		    }
		}

		public Map<Integer, Set<String>> getStudentEnrollments() {
		    Map<Integer, Set<String>> studentEnrollments = new HashMap<>();

		    // Connect to the database and fetch data
		    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "yourpassword")) {
		        // Execute SQL query
		        String query = "SELECT studentID, course.courseName FROM enrollments INNER JOIN course ON enrollments.courseID = course.courseId;";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                // Process the result set
		                while (resultSet.next()) {
		                    int studentID = resultSet.getInt("studentID");
		                    String courseName = resultSet.getString("courseName");

		                    // Add the enrollment to the map
		                    studentEnrollments.computeIfAbsent(studentID, k -> new HashSet<>()).add(courseName);
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    // Fetch data from the enrollments table and organize it by student
		    // Return a map where the key is student_id and the value is a set of course_names
		    return studentEnrollments;
		}
	    public static void printEnrollmentMap(Map<Integer, Set<Integer>> studentEnrollments) {
	        for (Map.Entry<Integer, Set<Integer>> entry : studentEnrollments.entrySet()) {
	            int studentID = entry.getKey();
	            Set<Integer> courseIDs = entry.getValue();
	            System.out.println("Student ID: " + studentID + ", Enrolled Courses: " + courseIDs);
	        }
	    }
	    public boolean hasCommonStudents(Map<Integer, Set<String>> studentEnrollments, timetable rowData, String newCourse) {
	        Set<String> newCourses = studentEnrollments.get(newCourse);

	        // Iterate over the properties (columns) of the rowData
	        for (int i = 1; i <= 6; i++) {
	            String existingCourse = getCourseFromProperty(rowData, i);

	            // Check if the column has a course
	            if (!existingCourse.isEmpty()) {
	                Set<String> existingCourses = studentEnrollments.get(existingCourse);

	                System.out.println("Existing Course: " + existingCourse);
	                System.out.println("New Course: " + newCourse);
	                System.out.println("Existing Courses: " + existingCourses);
	                System.out.println("New Courses: " + newCourses);

	                if (existingCourses != null && newCourses != null) {
	                    // Print student IDs for existing course
	                    System.out.println("Existing Students for " + existingCourse + ": " + getStudentIDs(existingCourses));
	                    
	                    // Print student IDs for new course
	                    System.out.println("New Students for " + newCourse + ": " + getStudentIDs(newCourses));

	                    // If there are common courses, return true
	                    if (!Collections.disjoint(existingCourses, newCourses)) {
	                        System.out.println("Common courses found!");
	                        return true;
	                    }
	                }
	            }
	        }

	        return false;
	    }
	    
	    public String getStudentIDs(Set<String> courses) {
	        // Fetch and return the student IDs for the given courses
	        // You may need to implement this based on your database structure
	        // For debugging, you can simply return the courses as a string
	        return courses.toString();
	    }

	    public static String getCourseFromProperty(timetable rowData, int columnIndex) {
	        switch (columnIndex) {
	            case 1:
	                return rowData.getcourses1Property().get();
	            case 2:
	                return rowData.getcourses2Property().get();
	            case 3:
	                return rowData.getcourses3Property().get();
	            case 4:
	                return rowData.getcourses4Property().get();
	            case 5:
	                return rowData.getcourses5Property().get();
	            case 6:
	                return rowData.getcourses6Property().get();
	            default:
	                throw new IllegalArgumentException("Invalid column index");
	        }
	    }
	 
	    
	    
  
}


class enrollments1{
	
	

	
	int validateCourse(int courseID) {
	
		
		DatabaseManager d= new DatabaseManager();
		return d.validateCourse(courseID);
	}
	
	
}

class enrollFaculty extends enrollments1{
	
	DatabaseManager d=new DatabaseManager();
int validateFaculty(int facultyID) {
		
	return d.validateFaculty(facultyID);
		
	}
	int enrollFaculty(int facultyID,int courseID) {
		
		return d.enrollFaculty(facultyID, courseID);
	}
	
}

class enrollStudent extends enrollments1{
	
	DatabaseManager d=new DatabaseManager();
	
int enrollStudent1(int studentID,int courseID) {
		
	return d.enrollStudent1(studentID, courseID);
		
	}
	
int validateStudent(int studentID) {
		
		return d.validateStudent(studentID);
	}
}



