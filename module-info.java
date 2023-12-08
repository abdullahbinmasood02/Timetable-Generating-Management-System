module sdaproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires mysql.connector.j;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
