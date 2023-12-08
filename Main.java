package application;
	
import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.paint.Color;


public class Main extends Application {

	private static Stage stg;
	
	 public void start(Stage primaryStage) throws Exception {
	        Login login = new Login();
	        Registration reg= new Registration();
	        stg=primaryStage;
	        // Load the FXML file to define the structure of the user interface
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
	        
	        // Inject the model into the controller
	      //  signupController controller = new signupController(reg);
	        //loader.setController(controller);

	        Parent root = (Parent) loader.load();
	        Scene scene = new Scene(root);
	      
	        primaryStage.setResizable(false);
	        primaryStage.setScene(scene);
	        primaryStage.setFullScreen(false);
	        primaryStage.show();
	    }
	 
	 public void changeScene(String Fxml) throws IOException{
		 
		 Parent pane= (Parent)FXMLLoader.load(getClass().getResource(Fxml));
		 stg.getScene().setRoot(pane);
	 }

	    public static void main(String[] args) {
	        launch(args);
	    }
}
