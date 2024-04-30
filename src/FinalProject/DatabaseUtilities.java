package FinalProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


/**
 * Class to interact with mySQL database
 */
public class DatabaseUtilities {
    private static final String DB_JDBC = "jdbc:mysql://localhost:3306/finalproject";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";
    
    /**
     * Method to add an employee to database
     * @param employee - Employee object containing all necessary variables
     * with setter and getter methods
     */
    public static void addEmployee(Employee employee) {
        // Query to insert provided values into specified columns in the Employees table
        String insertQuery = "INSERT INTO Employees (EmployeeID, FirstName, LastName, StartDate, Salary, SignedContract,"
                + " SocialSecurity, BirthDate, PhoneNum, EmergencyName, EmergencyPhone)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Prepared statement object to insert employee into database
            PreparedStatement insert = conn.prepareStatement(insertQuery);
            
            // Replacing the "?" placeholder with values from Employee object
            insert.setString(1, employee.getEmployeeID());
            insert.setString(2, employee.getFirstName());
            insert.setString(3, employee.getLastName());
            insert.setString(4, employee.getStartDate());
            insert.setDouble(5, employee.getSalary());
            insert.setBoolean(6, employee.isSignedContract());
            insert.setString(7, employee.getSocialSecurity());
            insert.setString(8, employee.getBirthDate());
            insert.setString(9, employee.getPhoneNum());
            insert.setString(10, employee.getEmergencyName());
            insert.setString(11, employee.getEmergencyPhone());
            
            // Executing prepared statement
            int count = insert.executeUpdate();

            System.out.println("Count from executed update: " + count); // debugging
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Method to remove an employee from the database
     * @param id - int value of employee ID that is being removed
     */
    public static void deleteEmployee(String id) {
        // Query to delete rows containing provided employeeID
        String deleteQuery = "DELETE FROM Employees WHERE EmployeeID = ?";
        
        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Prepared statement object to delete employee from database
            PreparedStatement delete = conn.prepareStatement(deleteQuery);
            
            // Replacing the "?" placeholder with provided ID
            delete.setString(1, id);
            
            // Executing prepared statement
            int count = delete.executeUpdate();
            
            System.out.println("count from executed update: " + count); // debugging
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to check if an employee already exists in database
     * @param id - String value that is being compared
     * @return - true/false depending if ID is already found in Employees table
     */
    public static boolean employeeExists(String id) {
        // Query to select all columns from Employees table where ID matches provided ID
        String selectQuery = "SELECT * FROM Employees WHERE EmployeeID = ?";
        
        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Prepared statement object to select columns from table
            PreparedStatement select = conn.prepareStatement(selectQuery);
            
            // Replacing the "?" placeholder with provided ID value
            select.setString(1, id);
            
            // Executing query, returning true if found, false if not found
            try (ResultSet resultSet = select.executeQuery()) {
                return resultSet.isBeforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Method to update employee information in database
     * @param employee - Employee object holding updated info
     */
    public static void updateEmployee(Employee employee) {
        // Query to update column information for row with specified employeeID
        String updateQuery = "UPDATE Employees SET EmployeeID=?, FirstName=?, "
                + "LastName=?, StartDate=?, Salary=?, SignedContract=?, SocialSecurity=?, "
                + "BirthDate=?, PhoneNum=?, EmergencyName=?, EmergencyPhone=? WHERE EmployeeID=?";

        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Prepared statement object to update columns from table
            PreparedStatement update = conn.prepareStatement(updateQuery);
            
            // Debugging
            System.out.println(employee.getEmployeeID());
            System.out.println(employee.getOriginalID());

            // Replacing the "?" placeholder with updated values from Employee object
            update.setString(1, employee.getEmployeeID());
            update.setString(2, employee.getFirstName());
            update.setString(3, employee.getLastName());
            update.setString(4, employee.getStartDate());
            update.setDouble(5, employee.getSalary());
            update.setBoolean(6, employee.isSignedContract());
            update.setString(7, employee.getSocialSecurity());
            update.setString(8, employee.getBirthDate());
            update.setString(9, employee.getPhoneNum());
            update.setString(10, employee.getEmergencyName());
            update.setString(11, employee.getEmergencyPhone());
            update.setString(12, employee.getOriginalID());

            // Executing prepared statement
            int count = update.executeUpdate();
            
            System.out.println("Count from executed update: " + count); // debugging
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to retrieve individual employee information from database
     * @param employeeID - String employeeID to search for
     * @return - returns Employee object containing selected employee's info
     */
    public static Employee getEmployeeById(String employeeID) {
        Employee employee = null;
        // Query to select all columnswhere ID matches employeeID
        String selectQuery = "SELECT * FROM Employees WHERE EmployeeID = ?";
        
        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Prepared statement object to select columns from table
            PreparedStatement select = conn.prepareStatement(selectQuery);
            
            // Replacing "?" placeholder with provided ID value
            select.setString(1, employeeID);
            
            // Executing query and storing result
            ResultSet resultSet = select.executeQuery();
            
            // If statement to check if result exists
            if (resultSet.next()) {
                // Creating new Employee object with retrieved data from column names
                employee = new Employee(
                        resultSet.getString("EmployeeID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("StartDate"),
                        resultSet.getDouble("Salary"),
                        resultSet.getBoolean("SignedContract"),
                        resultSet.getString("SocialSecurity"),
                        resultSet.getString("BirthDate"),
                        resultSet.getString("PhoneNum"),
                        resultSet.getString("EmergencyName"),
                        resultSet.getString("EmergencyPhone")
                    );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employee;
    }
   
    /**
     * Method to retrieve multiple employee information from database for table on dashboard
     * @return - returns a 2D Object array that contains employee data (for JTable)
     */
    public static Object[][] getEmployeeData() {
        // Query to select specified columns from table
        String selectQuery = "SELECT EmployeeID, FirstName, LastName, StartDate, SignedContract FROM Employees";
        
        // Try catch block to connect to database
        try (Connection conn = DriverManager.getConnection(DB_JDBC, DB_USER, DB_PASS)) {
            // Statement object to select columns form table
            Statement select = conn.createStatement();
            // Executing query & storing results using ResultSet
            ResultSet result = select.executeQuery(selectQuery);
            
            // Storing ResultSet meta data in variable
            ResultSetMetaData metaData = result.getMetaData();
            // Storing # of columns from result
            int columnCount = metaData.getColumnCount();

            // Creating ArrayList to temporarily store rows of data from the result set
            // each index contains data from a row
            ArrayList<Object[]> dataList = new ArrayList<>();
            // Looping through the rows in result set
            while (result.next()) {
              // Object array that holds data from a single row
              Object[] rowData = new Object[columnCount];
              // Loop that iterates through each column of current row
              // each index contains data from an individual column in current row
              for (int i = 1; i <= columnCount; i++) {
                  rowData[i - 1] = result.getObject(i);
              }
              // Adding data from row to the ArrayList
              dataList.add(rowData);
            }

            // Creating a 2D Object array to store finalized data to return
            Object[][] data = new Object[dataList.size()][columnCount];
            // Loop that populates the data array with references to rows from dataList
            for (int i = 0; i < dataList.size(); i++) {
              data[i] = dataList.get(i);
            }
            conn.close();
            
            return data;  
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
