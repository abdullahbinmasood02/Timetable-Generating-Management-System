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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;



public class loginController {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private  Login log=new Login();
	@FXML
    private TextField username;
	@FXML
    private PasswordField password;
	@FXML
    private Label errorLabel;
	int userID;
    
    
	public loginController(Login login) {
	    log = login;
	}
	
	public loginController() {
        // Empty constructor
    }
    
	
	
	 
    // Event Listener on Button.onAction
    public void login(ActionEvent event) throws IOException {
    	
    	
    	
    	
    	int u;
    	
    		
    	double result=0;
    	String p;
    	if(!password.getText().isEmpty() &&  !username.getText().isEmpty()) {
    		p=password.getText();
    		u = Integer.parseInt(username.getText());
    		   result  = log.verifyDetails(u,p);
    	}

        // Call the add method in the CalculatorModel
     

        // Update the view (resultLabel)
        
        if(result==0 || username.getText().isEmpty() || password.getText().isEmpty())
        	errorLabel.setText("Invalid Username or Password");
        else if (result==1 && !username.getText().isEmpty() && !password.getText().isEmpty()) {
        	
        	System.out.println(username.getText());
        	 FXMLLoader loader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
             Parent root = loader.load();

             // Get the controller instance associated with the FXML
             adminHController adminHome = loader.getController();

             // Set the user ID in AdminHomeController
             adminHome.setUserID(username.getText());
   	       // signupController c=new signupController();
   	        //c.test();
   	        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
   	        scene=new Scene(root);
   	        stage.setScene(scene);
   	        stage.show();
   	        // Set the user ID in AdminHomeController
   	      
        }
    }
    
   
    public void switchPage() throws IOException {
    	
    	Main m= new Main();
    	m.changeScene("signup.fxml");
    }
}