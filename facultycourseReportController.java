package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;



import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.x.protobuf.MysqlxCrud.DataModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;



public class facultycourseReportController {

		@FXML
	    private TableView<facultyReport> tableView;
	    @FXML
	    private TableColumn<facultyReport, Integer> colFacultyID;
	    @FXML
	    private TableColumn<facultyReport, String> colFacultyName;
	    @FXML
	    private TableColumn<facultyReport, Integer> colCourseID;
	    @FXML
	    private TableColumn<facultyReport, String> colCourseName;
	    private Stage stage;
		private Scene scene;
		private Parent root;

	    public void initialize() {
	        colFacultyID.setCellValueFactory(cellData -> cellData.getValue().getStudentIDProperty().asObject());
	        colFacultyName.setCellValueFactory(cellData -> cellData.getValue().getStudentNameProperty());
	        colCourseID.setCellValueFactory(cellData -> cellData.getValue().getCourseIDProperty().asObject());
	        colCourseName.setCellValueFactory(cellData -> cellData.getValue().getCourseNameProperty());

	        fetchDataFromDatabase();
	    }
	    
	    private void fetchDataFromDatabase() {
	        try {
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "Yourpassword");
	            Statement statement = (Statement) connection.createStatement();
	            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery("SELECT facultyenrollments.facultyID,user.name,facultyenrollments.courseID,course.courseName FROM facultyenrollments inner join user inner join course on facultyenrollments.facultyID=user.id and facultyenrollments.courseID=course.courseId " );

	            while (resultSet.next()) {
	                facultyReport data = new facultyReport(resultSet.getInt("facultyID"), resultSet.getString("name"), resultSet.getInt("courseID"),resultSet.getString("courseName"));
	                tableView.getItems().add(data);
	            }

	            resultSet.close();
	            
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
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
