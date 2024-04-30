package FinalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to create & customize window for adding an employee
 */
public class AddEmployeeWindow extends JDialog {
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 500;
    private JTextField idField, firstNameField, lastNameField, startDateField, salaryField,
            socialSecurityField, birthDateField, phoneNumField, emergencyNameField, emergencyPhoneField;
    private JCheckBox signedContractCheckBox;
    private JButton submitButton, clearButton;
    
    /**
     * Class constructor
     * @param parentFrame - parent JFrame to center the window
     */
    public AddEmployeeWindow(JFrame parentFrame) {
        super(parentFrame, "Add Employee", true); // calling parent JDialog
        
        // Customizing JDialog
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);
        
        // Creating components
        createComponents();
    }

    /**
     * Method to create and customize components and sub-panels
     */
    private void createComponents() {
        // Creating text fields
        idField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        startDateField = new JTextField(10);
        salaryField = new JTextField(10);
        signedContractCheckBox = new JCheckBox("Signed Contract");
        signedContractCheckBox.setBackground(Color.WHITE);
        socialSecurityField = new JTextField(10);
        birthDateField = new JTextField(10);
        phoneNumField = new JTextField(10);
        emergencyNameField = new JTextField(10);
        emergencyPhoneField = new JTextField(10);

        // Creating submit button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(112, 229, 252));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Reading input from text fields
                String employeeID = idField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String startDate = startDateField.getText();
                String salaryText = salaryField.getText();
                boolean signedContract = signedContractCheckBox.isSelected();
                String socialSecurity = socialSecurityField.getText();
                String birthDate = birthDateField.getText();
                String phoneNum = phoneNumField.getText();
                String emergencyName = emergencyNameField.getText();
                String emergencyPhone = emergencyPhoneField.getText();
                
                // Input validation to make sure no field is empty
                if (employeeID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || startDate.isEmpty()
                    || salaryText.isEmpty() || socialSecurity.isEmpty() || birthDate.isEmpty()
                    || phoneNum.isEmpty() || emergencyName.isEmpty() || emergencyPhone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Input validation to make sure salary is a number
                double salary;
                try {
                    salary = Double.parseDouble(salaryField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Salary must be a valid number.", "Warning", JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                // Input validation to make sure dates are formatted properly
                if (!startDate.matches("\\d{2}/\\d{2}/\\d{4}") || !birthDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Must use mm/dd/yyyy format with integers only.", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Input validation to make sure SSN is formatted properly
                if (!socialSecurity.matches("\\d{3}-\\d{2}-\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Invalid SSN format. Must use ###-##-#### format with integers only.", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Input validation to make sure phone number is formatted properly
                if (!phoneNum.matches("\\d{3}-\\d{3}-\\d{4}") || !emergencyPhone.matches("\\d{3}-\\d{3}-\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number format. Must use XXX-XXX-XXXX format with integers only.", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Input validation to make sure employee doesn't already exist
                if (DatabaseUtilities.employeeExists(employeeID)) {
                    JOptionPane.showMessageDialog(null, "Employee with ID \"" + employeeID + "\" already exists", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Creating employee object, adding to database
                Employee employee = new Employee(employeeID, firstName, lastName, startDate, salary,
                        signedContract, socialSecurity, birthDate, phoneNum, emergencyName, emergencyPhone);
                DatabaseUtilities.addEmployee(employee);
                
                dispose();
            }
        });
        
        // Creating clear fields button
        clearButton = new JButton("Clear Fields");
        clearButton.setBackground(new Color(255, 180, 162));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Confirming before clearing
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you wish to clear all fields? This cannot be undone.",
                    "Confirm Clear", JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    // Clearing any input in text fields
                    idField.setText("");
                    firstNameField.setText("");
                    lastNameField.setText("");
                    startDateField.setText("");
                    salaryField.setText("");
                    signedContractCheckBox.setSelected(false);
                    socialSecurityField.setText("");
                    birthDateField.setText("");
                    phoneNumField.setText("");
                    emergencyNameField.setText("");
                    emergencyPhoneField.setText("");
                    
                    System.out.println("Fields cleared"); // debugging
                }     
            }
        });
        
        // Creating & customizing JPanel for components
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.add(new JLabel("Employee ID:"));
        panel.add(idField);
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Start Date: mm/dd/yyyy"));
        panel.add(startDateField);
        panel.add(new JLabel("Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Signed Contract:"));
        panel.add(signedContractCheckBox);
        panel.add(new JLabel("Social Security: ###-##-####"));
        panel.add(socialSecurityField);
        panel.add(new JLabel("Birth Date: mm/dd/yyyy"));
        panel.add(birthDateField);
        panel.add(new JLabel("Phone Number: XXX-XXX-XXXX"));
        panel.add(phoneNumField);
        panel.add(new JLabel("Emergency Contact Name:"));
        panel.add(emergencyNameField);
        panel.add(new JLabel("Emergency Contact Phone: XXX-XXX-XXXX"));
        panel.add(emergencyPhoneField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);

        add(mainPanel);
    }
}


