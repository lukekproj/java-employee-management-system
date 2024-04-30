DROP TABLE Employees;
CREATE TABLE Employees
(
    EmployeeID VARCHAR(10) PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    StartDate VARCHAR(15),
    Salary DECIMAL(10, 2),
    SignedContract BOOLEAN,
    SocialSecurity VARCHAR(15),
    BirthDate VARCHAR(15),
    PhoneNum VARCHAR(15),
    EmergencyName VARCHAR(50),
    EmergencyPhone VARCHAR(15)
);

SELECT * FROM Employees;