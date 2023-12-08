package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class generateTimetableController {
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn<timetable, String>  colhallID;
	@FXML
	private TableColumn<timetable, String> col8;
	@FXML
	private TableColumn<timetable, String> col10;
	@FXML
	private TableColumn<timetable, String> col11;
	@FXML
	private TableColumn<timetable, String> col1;
	@FXML
	private TableColumn<timetable, String> col2;
	@FXML
	private TableColumn<timetable, String> col4;
	private Stage stage;
	private Scene scene;
	private Parent root;
	count c=new count();
	timetable t=new timetable(null, null);
	
	public void initialize() {
		colhallID.setCellValueFactory(cellData -> cellData.getValue().getIDProperty());
		col8.setCellValueFactory(cellData -> cellData.getValue().getcourses1Property());
		
		col10.setCellValueFactory(cellData -> cellData.getValue().getcourses2Property());
		col11.setCellValueFactory(cellData -> cellData.getValue().getcourses3Property());
		
		col1.setCellValueFactory(cellData -> cellData.getValue().getcourses4Property());
		col2.setCellValueFactory(cellData -> cellData.getValue().getcourses5Property());
		col4.setCellValueFactory(cellData -> cellData.getValue().getcourses6Property());
        

        fetchDataFromDatabase();
    }
	
	private void fetchDataFromDatabase() {
	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "Yourpassword");
	        Statement hallsStatement = connection.createStatement();
	        Statement coursesStatement = connection.createStatement();

	        ResultSet hallsResultSet = hallsStatement.executeQuery("SELECT name FROM halls;");
	        ResultSet coursesResultSet = coursesStatement.executeQuery("SELECT courseName FROM course;");

	        // Fetch hall names
	        while (hallsResultSet.next()) {
	            String hallName = hallsResultSet.getString("name");
	            boolean hallExists = false;

	            // Check if the hall is already in the table
	            for (Object row : tableView.getItems()) {
	                if (row instanceof timetable) {
	                   timetable rowData = (timetable) row;
	                    if (rowData.getIDProperty().get().equals(hallName)) {
	                        hallExists = true;
	                        break;
	                    }
	                }
	            }

	            // If the hall is not in the table, add it
	            if (!hallExists) {
	                timetable data = new timetable(hallName, "");
	                tableView.getItems().add(data);
	            }
	        }
	        
	    

	        // Fetch course names and assign them to the respective columns
	        while (coursesResultSet.next()) {
	            String courseName = coursesResultSet.getString("courseName");

	            // Iterate over rows
	            for (Object row : tableView.getItems()) {
	                if (row instanceof timetable) {
	                    timetable rowData = (timetable) row;

	                    // Check if the courses have common students
	                    if (!t.hasCommonStudents(t.getStudentEnrollments(), rowData, courseName)) {
	                        // Check if the course is already assigned to any column
	                        boolean courseAssigned = false;

	                        // Iterate over columns
	                        for (int i = 1; i <= 6; i++) {
	                            String existingCourse = timetable.getCourseFromProperty(rowData, i);

	                            if (existingCourse.isEmpty()) {
	                                // Found an empty column, assign the course
	                                timetable.setCourseToProperty(rowData, i, courseName);
	                                courseAssigned = true;
	                                break;  // Break from the inner loop (columns)
	                            }
	                        }

	                        if (courseAssigned) {
	                            // Course was successfully assigned, break from the outer loop (rows)
	                            break;
	                        } else {
	                            // No empty column found, handle as needed (e.g., log a message)
	                            System.out.println("No empty column found for course: " + courseName);
	                        }
	                    } else {
	                        System.out.println("Common students found for course " + courseName + " and existing courses.");
	                        // You can add additional actions or logging here
	                    }
	                }
	            }
	        }
	        hallsResultSet.close();
	        coursesResultSet.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	   
	}



	@FXML
	public void homeScreen(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
        Parent root = loader.load();

        // Get the controller instance associated with the FXML
        adminHController adminHome = loader.getController();

        // Set the user ID in AdminHomeController
        
	       // signupController c=new signupController();
	        //c.test();
	        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	        scene=new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	}
}
