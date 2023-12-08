package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;






public class enrollFacultyController {
	@FXML
	private Button button1;
	@FXML
	private TextField facultyid;
	@FXML
	private TextField courseid;
	@FXML
	private Button button;
	@FXML
	private Label errorLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	

	// Event Listener on Button[#button1].onAction
	@FXML
	public void switchPage(ActionEvent event) throws IOException {


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
	// Event Listener on Button[#button].onAction
	@FXML
	public void enrollFaculty(ActionEvent event) {
		
		if(!facultyid.getText().isEmpty() && ! courseid.getText().isEmpty()) {
		enrollFaculty enrollment= new enrollFaculty();
		int flag=enrollment.enrollFaculty(Integer.parseInt(facultyid.getText()), Integer.parseInt(courseid.getText()));
		if(flag==0) {
			
			errorLabel.setText("Operation Failed");
		}
		
		else
			errorLabel.setText("operation successful!");
		}
		
		else
			errorLabel.setText("Operation Failed");
	}
	
}
