package FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * Class to manage functions of JButtons from EmployeeManager
 */
public class ButtonListener implements ActionListener {
    private final JTable table;
    private final EmployeeManager employeeManager;
    
    /**
     * Class constructor
     * @param table - JTable displaying employee data
     * @param employeeManager - instance of EmployeeManager (to refresh table)
     */
    public ButtonListener(JTable table, EmployeeManager employeeManager) {
        this.table = table;
        this.employeeManager = employeeManager;
    }
    
    /**
     * Method to handle button clicks
     * https://docs.oracle.com/javase/8/docs/api/java/util/EventObject.html#getSource--
     * @param event 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Gets button that triggered actiobevent
        JButton buttonClicked = (JButton) event.getSource();
        // Gets # of rows in table
        int rows = table.getSelectedRowCount();
        
        // Switch statement to call appropriate method depending on button pressed
        switch (buttonClicked.getText()) {
            case "Add Employee":
                handleAddEmployee();
                break;
            case "Edit Employee":
                handleEditEmployee(rows);
                break;
            case "Delete Employee":
                handleDeleteEmployee(rows);
                break;
            case "View Details":
                handleViewDetails(rows);
                break;
            case "Select All":
                table.selectAll();
            default:
                break;
        }
    }
    
    /**
     * Method to handle add employee button press
     */
    private void handleAddEmployee() {
        System.out.println("Add Button pressed"); // debugging
        
        // Creates and displays add employee window, passes parent JFrame as parameter
        AddEmployeeWindow employeeWindow = new AddEmployeeWindow((JFrame) SwingUtilities.getWindowAncestor(table));
        employeeWindow.setVisible(true);
        
        // Refreshes table after adding employee
        employeeManager.refreshJTable();
    }
    
    /**
     * Method to handle edit employee button press
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingUtilities.html
     * @param rows - int of # of rows in table
     */
    private void handleEditEmployee(int rows) {
        System.out.println("Edit Button pressed"); // debugging
        
        // If-else to make sure only one employee can be edited at once
        if (rows == 1) {
            System.out.println("One row selected!"); // debugging
            
            // Gets selected row & String of employee ID from selected row
            int rowSelected = table.getSelectedRow();
            String id = (String) table.getValueAt(rowSelected, 0);
            
            // Creates employee object from data in database
            Employee employee = DatabaseUtilities.getEmployeeById(id);
            
            // Creates and displays edit employee window, passes parent Jframe and employee object as parameter
            EditEmployeeWindow editEmployeeWindow = new EditEmployeeWindow((JFrame) SwingUtilities.getWindowAncestor(table), employee);
            editEmployeeWindow.setVisible(true);
            
            // Refreshes table if changes are saved
            if (editEmployeeWindow.isChangesSaved()) {
                employeeManager.refreshJTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select exactly one row to edit.", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Method to handle delete employee button press
     * @param rows - int of # of rows in table
     */
    private void handleDeleteEmployee(int rows) {
        System.out.println("Delete Button pressed"); // debugging
        
        // If-else to check if one or more employees are being deleted at once
        if (rows == 1) {
            System.out.println("One row selected!"); // debugging
            
            // Gets selected row & String of employee ID from selected row
            int rowSelected = table.getSelectedRow();
            String id = (String) table.getValueAt(rowSelected, 0);
            
            
            // JOptionPane to confirm deletion, deletes employee and refreshes if confirmed
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete employee with ID \"" + id + "\"?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);  
            if (option == JOptionPane.YES_OPTION) {
                DatabaseUtilities.deleteEmployee(id);
                employeeManager.refreshJTable();
            }
        } else if (rows > 1) {
            System.out.println("Multiple rows selected!"); // debugging
            
            // JOptionPane to confirm deletion, deletes employee and refreshes if confirmed
            int option = JOptionPane.showConfirmDialog(null, "You are about to delete " + rows + " employee records, are you sure? This cannot be undone.",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Loop iterates and deletes rows stored in array of selected rows
                int[] selectedRows = table.getSelectedRows();
                for (int row : selectedRows) {
                    String id = (String) table.getValueAt(row, 0);
                    DatabaseUtilities.deleteEmployee(id);
                }
                employeeManager.refreshJTable();
            }
        }
    }
    
    /**
     * Method to handle view employee button press
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingUtilities.html
     * @param rows - int of # of rows in table
     */
    private void handleViewDetails(int rows) {
        System.out.println("View button pressed"); // debugging
        
        // If-else to check to make sure atleast one employee being selected to view
        if (rows > 0) {
            System.out.println("Rows selected!"); // debugging
            
            // Creates array of selectedRows, arrayList to hold Employee objects
            int[] selectedRows = table.getSelectedRows();
            ArrayList<Employee> selectedEmployees = new ArrayList<>();
            
            // Loop to iterate through each row,
            // create employee objects from data in each row and add objects to arraylist
            for (int row : selectedRows) {
                String employeeID = (String) table.getValueAt(row, 0);
                Employee employee = DatabaseUtilities.getEmployeeById(employeeID);
                selectedEmployees.add(employee);
            }
            
            // Creates and displays view employee window, passes parent JFrame and ArrayList of employee objects in parameter
            ViewEmployeeWindow detailsWindow = new ViewEmployeeWindow((JFrame) SwingUtilities.getWindowAncestor(table), selectedEmployees);
            detailsWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select at least one row to view.", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
}
