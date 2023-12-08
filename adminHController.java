package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;





public class adminHController {
	@FXML
	private Label id;
	@FXML
	private Label role;
	@FXML
	private Label email;
	@FXML
	private Label address;
	@FXML
	private Label name;
	
	int userID;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void setUserID(String id) {
		
		this.userID=Integer.parseInt(id);
	}
	// Event Listener on Button.onAction
		@FXML
		public void displayDetails(ActionEvent event) {
			display();
		}
		
		@FXML
		public void enroll(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("enrollStudent.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         enrollStudentController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
		@FXML
		public void addHall(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("addHall.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         addHallController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
		@FXML
		public void enrollFaculty(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("enrollFaculty.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         enrollFacultyController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
		@FXML
		public void report(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("facultyReport.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         studentReportController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
		@FXML
		public void facultyReport(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("facultycourseReport.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         facultycourseReportController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
		@FXML
		public void generateTimetable(ActionEvent event) throws IOException {


			 FXMLLoader loader = new FXMLLoader(getClass().getResource("generateTimetable.fxml"));
	         Parent root = loader.load();

	         // Get the controller instance associated with the FXML
	         generateTimetableController c = loader.getController();

	         // Set the user ID in AdminHomeController
	         
		       // signupController c=new signupController();
		        //c.test();
		        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		        scene=new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		}
		
	void display() {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet5 = null;
        String url = "jdbc:mysql://localhost:3306/task";
        String username1 = "root";
        String password1 = "yourpassword";
        System.out.println("button clicked");

        try {
        	
        	System.out.println("in block");
            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username1, password1);
            if(connection!=null)
            	System.out.println("connection estalished");
            // SQL query to fetch user details based on ID and Role
            String query = "SELECT * FROM user WHERE id = ? AND role = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, "admin");

            // Execute the query
            resultSet5 = preparedStatement.executeQuery();
            

            // Process the result set
            if (resultSet5.next()) {
            	System.out.println("in block1");
                        id.setText(Integer.toString(resultSet5.getInt("id")));
                        name.setText(resultSet5.getString("name"));
                        address.setText(resultSet5.getString("address"));
                        role.setText(resultSet5.getString("role"));
                        name.setText(resultSet5.getString("name"));
                        email.setText(resultSet5.getString("email"));
                      
                
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public adminHController(){
		
		
	}
	
}
