package databasePackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import application.User;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/task";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yourpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);

                    row.put(columnName, value);
                }

                result.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static int verifyLogin(int id, String password) {
    	
    	 
        
       Connection con=null;
       try {
           //2.Load the MySQL JDBC driver
    	   
          
           //3.Establish a connection to the database
           con=(Connection)DriverManager.getConnection(URL, USERNAME, PASSWORD);
           if(con!=null)
           {
               System.out.println("database is connected successfully");
           }
           
           String sql = "SELECT * FROM user WHERE id = ? and password=?";
           
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           preparedStatement.setInt(1, id);
           preparedStatement.setString(2, password);
           

           // Execute the query
           ResultSet resultSet = preparedStatement.executeQuery();
           
           while(resultSet.next()) {
        	   
        	   if ("admin".equals(resultSet.getString("role")))
        		   return 1;
        	   
        	   else if ("faculty".equals(resultSet.getString("role")))
        		   return 2;
           }
           
           
           
          
        	   
		
    }
       
       catch (SQLException e) {
           e.printStackTrace();
           
           
       }
       
       return 0;
	}
   
public static int enrollStudent1(int studentID,int courseID) {
		
		if(validateStudent(studentID)==0   || validateCourse(courseID)==0)
			return 0;
		
		  //1. Database connection parameters
        String url = "jdbc:mysql://localhost:3306/task";
        String username1 = "root";
        String password1 = "yourpassword";
        
       Connection con=null;
       try {
           //2.Load the MySQL JDBC driver
    	   
          
           //3.Establish a connection to the database
           con=(Connection)DriverManager.getConnection(url, username1, password1);
           if(con!=null)
           {
               System.out.println("database is connected successfully");
           }
           
           String sql = "insert into enrollments(studentID,courseID) values (?,?)";
           
           try (PreparedStatement statement = con.prepareStatement(sql)) {
             
               statement.setInt(1, studentID);
               statement.setInt(2, courseID);
             
               
              

               // Execute the query
               int rowsInserted = statement.executeUpdate();

               if (rowsInserted > 0) {
                   System.out.println("A new row has been inserted!");
               }
           }
		
    }
       
       catch (SQLException e1) {
           e1.printStackTrace();
           
           
       }
       
       return 1;
	}
	
public static int validateStudent(int studentID) {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/task";
        String username1 = "root";
        String password1 = "yourpassword";

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username1, password1);

            // SQL query to fetch user details based on ID and Role
            String query = "SELECT * FROM student WHERE idStudent = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentID);
           

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                
                    return 1;
            }
            
            
            	
            	
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
	}

 	public static int allocateHall(String name, int capacity) {
 		
 		
        
       Connection con=null;
       try {
           //2.Load the MySQL JDBC driver
    	   
          
           //3.Establish a connection to the database
           con=(Connection)DriverManager.getConnection(URL,USERNAME,PASSWORD);
           if(con!=null)
           {
               System.out.println("database is connected successfully");
           }
           
           String sql = "insert into halls(name,capacity) values (?,?)";
           
           try (PreparedStatement statement = con.prepareStatement(sql)) {
             
               statement.setString(1, name);
               statement.setInt(2, capacity);
             
               
              

               // Execute the query
               int rowsInserted = statement.executeUpdate();

               if (rowsInserted > 0) {
                   System.out.println("A new row has been inserted!");
                   return 1;
               }
           }
		
    }
       
       catch (SQLException e1) {
           e1.printStackTrace();
           
           
       }
		
		
		
		return 0;
	}
 	
public static int validateCourse(int courseID) {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/task";
        String username1 = "root";
        String password1 = "yourpassword";

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username1, password1);

            // SQL query to fetch user details based on ID and Role
            String query = "SELECT * FROM course WHERE courseId = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
           

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                
                    return 1;
            }
            
            
            	
            	
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
	}
 	

    public static void registerUser(User user) {
        String sql = "INSERT INTO user(name, password, address, role, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, user.name);
            statement.setString(2, user.Password);
            statement.setString(3, user.address);
            statement.setString(4, user.role);
            statement.setString(5, user.email);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public static int getCourse() {
		
		int courseCount=0;
	try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "yourpassword");
        Statement statement = (Statement) connection.createStatement();
        ResultSet resultSet = ((java.sql.Statement) statement).executeQuery("SELECT count(*) as count from course;");

        if (resultSet.next()) 
            courseCount = resultSet.getInt("count");
          
       

        resultSet.close();
        
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }


return courseCount;

	}

public static int validateFaculty(int facultyID) {
	
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String url = "jdbc:mysql://localhost:3306/task";
    String username1 = "root";
    String password1 = "yourpassword";

    try {
        // Establish a connection to the database
        connection = DriverManager.getConnection(url, username1, password1);

        // SQL query to fetch user details based on ID and Role
        String query = "SELECT * FROM user WHERE id = ? ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, facultyID);
       

        // Execute the query
        resultSet = preparedStatement.executeQuery();

        // Process the result set
        if (resultSet.next()) {
            
                return 1;
        }
        
        
        	
        	
        

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return 0;
}
public static int enrollFaculty(int facultyID,int courseID) {
	
	
	
	if(validateFaculty(facultyID)==0   || validateCourse(courseID)==0)
		return 0;
	
	  //1. Database connection parameters
    String url = "jdbc:mysql://localhost:3306/task";
    String username1 = "root";
    String password1 = "yourpassword";
    
   Connection con=null;
   try {
       //2.Load the MySQL JDBC driver
	   
      
       //3.Establish a connection to the database
       con=(Connection)DriverManager.getConnection(url, username1, password1);
       if(con!=null)
       {
           System.out.println("database is connected successfully");
       }
       
       String sql = "insert into facultyEnrollments(facultyID,courseID) values (?,?)";
       
       try (PreparedStatement statement = con.prepareStatement(sql)) {
         
           statement.setInt(1,facultyID);
           statement.setInt(2, courseID);
         
           
          

           // Execute the query
           int rowsInserted = statement.executeUpdate();

           if (rowsInserted > 0) {
               System.out.println("A new row has been inserted!");
           }
       }
	
}
   
   catch (SQLException e1) {
       e1.printStackTrace();
       
       
   }
   
   return 1;
}
    
    public static int getHalls() {
    	
    	int hallCount = 0;
    	
    	try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task", "root", "yourpassword");
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = ((java.sql.Statement) statement).executeQuery("SELECT count(*) as count from halls;");

            if (resultSet.next()) 
                hallCount = resultSet.getInt("count");
              
           

            resultSet.close();
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
	
    	return hallCount;
    }
}

