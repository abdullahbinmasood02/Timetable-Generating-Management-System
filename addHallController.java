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
import java.sql.SQLException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;


public class addHallController {
	@FXML
	private Button button1;
	@FXML
	private TextField hallid;
	@FXML
	private TextField capacity;
	@FXML
	private Button button;
	@FXML
	private Label errorLabel;
	private Stage stage;
	private Scene scene;
	private Parent root;
	Hall hall= new Hall();
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
	public void addHall(ActionEvent event) {
		
		if(!capacity.getText().isEmpty() && ! hallid.getText().isEmpty()) {
		hall.capacity=Integer.parseInt(capacity.getText());
		hall.name=hallid.getText();
		int flag=hall.allocateLectureHall();
		
		if(flag==0) {
			
			errorLabel.setText("operation failed");
			
		}
		else
			errorLabel.setText("HALL ADDED");
	}
		else
			errorLabel.setText("operation failed");
	}
	
}
