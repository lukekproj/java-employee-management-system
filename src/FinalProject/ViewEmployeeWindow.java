package FinalProject;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Class to create & customize window for viewing employees
 */
public class ViewEmployeeWindow extends JDialog {
    private final int FRAME_WIDTH = 350;
    private final int FRAME_HEIGHT = 500;
    private JPanel detailsPanel;

    /**
     * Class constructor
     * @param parentFrame - parent JFrame to center window
     * @param selectedEmployees - arrayList of Employee objects of selected employees to display details for
     */
    public ViewEmployeeWindow(JFrame parentFrame, ArrayList<Employee> selectedEmployees) {
        super(parentFrame, "Selected Employee Details", true); // calling parent JDialog
        
        // Customizing JDialog
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        
        // Creating components
        createComponents(selectedEmployees);
    }

    /**
     * Method to create and customize components and sub-panels
     * @param selectedEmployees - arrayList of selected employees
     */
    private void createComponents(ArrayList<Employee> selectedEmployees) {
        // Creating and customizing panels
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);
        
        // If statement to handle if 1 or more than 1 employees need to be displayed
        if (selectedEmployees.size() == 1) {
            displaySingleEmployeeDetails(selectedEmployees.get(0));
        } else {
            displayMultipleEmployeesDetails(selectedEmployees);
        }
    }

    /**
     * Method to display details of single employee
     * @param employee - Employee object to display details for
     */
    private void displaySingleEmployeeDetails(Employee employee) {
        // Creating JLabels of info, adding to JPanel
        JLabel idLabel = new JLabel("Employee ID: " + employee.getEmployeeID());
        JLabel nameLabel = new JLabel("Name: " + employee.getFirstName() + " " + employee.getLastName());
        JLabel startDateLabel = new JLabel("Start Date: " + employee.getStartDate());
        JLabel salaryLabel = new JLabel("Salary: $" + employee.getSalary());
        JLabel contractLabel = new JLabel("Signed Contract: " + (employee.isSignedContract() ? "Yes" : "No"));
        JLabel socialSecurityLabel = new JLabel("Social Security: " + employee.getSocialSecurity());
        JLabel birthDateLabel = new JLabel("Birth Date: " + employee.getBirthDate());
        JLabel phoneNumLabel = new JLabel("Phone Number: " + employee.getPhoneNum());
        JLabel emergencyContactLabel = new JLabel("Emergency Contact: " + employee.getEmergencyName() + " (" + employee.getEmergencyPhone() + ")");

        detailsPanel.add(idLabel);
        detailsPanel.add(nameLabel);
        detailsPanel.add(startDateLabel);
        detailsPanel.add(salaryLabel);
        detailsPanel.add(contractLabel);
        detailsPanel.add(socialSecurityLabel);
        detailsPanel.add(birthDateLabel);
        detailsPanel.add(phoneNumLabel);
        detailsPanel.add(emergencyContactLabel);
    }

    /**
     * Method to display details of multiple employees
     * @param employees - arrayList of Employee objects to display details for
     */
    private void displayMultipleEmployeesDetails(ArrayList<Employee> employees) {
        // Creating new JPanel to hold multiple employee details
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Loop that iterates through arrayList, adds each employee to sub-panel
        int i = 1;
        for (Employee e : employees) {
            JLabel countLabel = new JLabel("#" + i);
            JLabel idLabel = new JLabel("Employee ID: " + e.getEmployeeID());
            JLabel nameLabel = new JLabel("Name: " + e.getFirstName() + " " + e.getLastName());
            JLabel startLabel = new JLabel("Start Date: " + e.getStartDate());
            JLabel salaryLabel = new JLabel("Salary: $" + String.format("%.2f", e.getSalary()));
            JLabel contractLabel = new JLabel("Signed Contract: " + (e.isSignedContract() ? "Yes" : "No"));
            JLabel socialSecurityLabel = new JLabel("Social Security: " + e.getSocialSecurity());
            JLabel birthDateLabel = new JLabel("Birth Date: " + e.getBirthDate());
            JLabel phoneNumLabel = new JLabel("Phone Number: " + e.getPhoneNum());
            JLabel emergencyContactLabel = new JLabel("Emergency Contact: " + e.getEmergencyName() + " (" + e.getEmergencyPhone() + ")");
            JLabel spaceLabel = new JLabel(" ");

            contentPanel.add(countLabel);
            contentPanel.add(idLabel);
            contentPanel.add(nameLabel);
            contentPanel.add(startLabel);
            contentPanel.add(salaryLabel);
            contentPanel.add(contractLabel);
            contentPanel.add(socialSecurityLabel);
            contentPanel.add(birthDateLabel);
            contentPanel.add(phoneNumLabel);
            contentPanel.add(emergencyContactLabel);
            contentPanel.add(spaceLabel);
            
            i++;
          }

        detailsPanel.add(contentPanel);
    }
}
   
