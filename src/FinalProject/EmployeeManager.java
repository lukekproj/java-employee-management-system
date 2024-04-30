package FinalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Controller class, initializes and displays table with employee info on launch
 * Creates JButtons needed to manipulate employee information
 */
public class EmployeeManager extends JFrame {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private JTable employeeTable;
    private JPanel buttonPanel, tablePanel, statusPanel;
    private JButton addButton, editButton, deleteButton, viewButton, selectAllButton;
    private JLabel statusLabel;
    private JScrollPane scrollPane;
    
    /**
     * Class constructor, creates and sets up layout and components
     */
    public EmployeeManager() {
        super("Employee Manager"); // calling parent JFrame constructor
        
        // Sets layout of the JFrame
        setLayout(new BorderLayout());
         
        // Creates and adds sub panels to parent JFrame
        tablePanel = createTablePanel();  
        buttonPanel = createButtonPanel();
        statusPanel = createStatusPanel();
        add(buttonPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        
        // Customizing JFrame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Method to create dashboard panel (buttons)
     * @return - returns buttonPanel JPanel
     */
    private JPanel createButtonPanel() {
        Color color = new Color(112, 229, 252);
        
        // Creating and customizing JButtons
        buttonPanel = new JPanel();
        addButton = new JButton("Add Employee");
        editButton = new JButton("Edit Employee");
        deleteButton = new JButton("Delete Employee");
        viewButton = new JButton("View Details");
        selectAllButton = new JButton("Select All");
        
        addButton.setBackground(color);
        editButton.setBackground(color);
        deleteButton.setBackground(color);
        viewButton.setBackground(color);
        selectAllButton.setBackground(color);
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(selectAllButton);
        buttonPanel.setBackground(Color.WHITE);
       
        // Adding actionlistener to each button
        ButtonListener listener = new ButtonListener(employeeTable, this);
        addButton.addActionListener(listener);
        editButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        viewButton.addActionListener(listener);
        selectAllButton.addActionListener(listener);
        
        return buttonPanel;
    }
    
    /**
     * Method to create the JTable front end to display employee info
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/table/DefaultTableModel.html
     * https://docs.oracle.com/javase/7/docs/api///javax/swing/JTable.html
     * @return - returns JPanel including the table
     */
    private JPanel createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        
        // Initializing table with data from database
        String[] columnNames = {"Employee ID", "First Name", "Last Name", "Start Date", "Signed Contract?"};
        Object[][] data = DatabaseUtilities.getEmployeeData();
        
        // Creating a table model, policy change to disallow editing
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Creates and customizes JTable, populates with data from table model
        employeeTable = new JTable(model);
        employeeTable.setBackground(Color.WHITE);
        employeeTable.getTableHeader().setReorderingAllowed(false);
        employeeTable.getTableHeader().setBackground(new Color(112, 229, 252));
        
        // Adds table to JScrollPane incase large amounts of rows
        scrollPane = new JScrollPane(employeeTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    /**
     * Method to create small info panel at bottom of screen
     * @return - returns status JPanel
     */
    private JPanel createStatusPanel() {
        statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Retrieves table row count to determine # of employees
        statusLabel = new JLabel("Total Employees: " + employeeTable.getRowCount());
        statusPanel.add(statusLabel);
        statusPanel.setBackground(Color.WHITE);
        
        return statusPanel;
    }
    
    /**
     * Method to refresh JTable with updated data from SQL database
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/table/DefaultTableModel.html
     */
    public void refreshJTable() {
        // Updates data in current referenced table model to data from database
        String[] columnNames = {"Employee ID", "First Name", "Last Name", "Start Date", "Signed Contract?"};
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        model.setDataVector(DatabaseUtilities.getEmployeeData(), columnNames);
        statusLabel.setText("Total Employees: " + employeeTable.getRowCount());
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
    } 
}
