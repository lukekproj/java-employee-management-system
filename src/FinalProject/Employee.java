package FinalProject;

/**
 * Class to represent employee in system
 */
public class Employee {
    private String employeeID, firstName, lastName, startDate, socialSecurity, phoneNum,
            emergencyName, emergencyPhone, birthDate;
    private double salary;
    private boolean signedContract;
    private String originalID;
    
    // Object constructor for adding new employee
    public Employee(String employeeID, String firstName, String lastName, String startDate, 
                    double salary, boolean signedContract, String socialSecurity, 
                    String birthdate, String phoneNum, String emergencyName, String emergencyPhone) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.salary = salary;
        this.signedContract = signedContract;
        this.socialSecurity = socialSecurity;
        this.birthDate = birthdate;
        this.phoneNum = phoneNum;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
    }
    
    // Object constructor for editing existing employee
    public Employee(String employeeID, String firstName, String lastName, String startDate, 
                    double salary, boolean signedContract, String socialSecurity, 
                    String birthdate, String phoneNum, String emergencyName, String emergencyPhone, String originalID) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.salary = salary;
        this.signedContract = signedContract;
        this.socialSecurity = socialSecurity;
        this.birthDate = birthdate;
        this.phoneNum = phoneNum;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        this.originalID = originalID; // stores original ID incase employee ID is changed
    }
    
    // Setter methods
    public void setEmployeeID(String id) {
        this.employeeID = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSignedContract(boolean signedContract) {
        this.signedContract = signedContract;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
    
    public void setOriginalID(String id) {
        this.originalID = id;
    }

    // Getter methods
    public String getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStartDate() {
        return startDate;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isSignedContract() {
        return signedContract;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }
    
    public String getOriginalID() {
        return originalID;
    }

    // Display for debugging
    @Override
    public String toString() {
        return "Employee ID: " + employeeID + "\n" +
               "First Name: " + firstName + "\n" +
               "Last Name: " + lastName + "\n" +
               "Start Date: " + startDate + "\n" +
               "Salary: " + salary + "\n" +
               "Signed Contract: " + signedContract + "\n" +
               "Social Security: " + socialSecurity + "\n" +
               "Birth Date: " + birthDate + "\n" +
               "Phone Number: " + phoneNum + "\n" +
               "Emergency Contact Name: " + emergencyName + "\n" +
               "Emergency Contact Phone: " + emergencyPhone;
    }
}
