Final Project Report: ATM Management System

Overview:  
An ATM Management System is a software solution designed to oversee and optimize the operations of Automated Teller Machines (ATMs). It integrates various functionalities to ensure the smooth functioning, security, and efficiency of ATM networks. It monitors ATM status in real-time, handles cash levels, and processes transactions like withdrawals and deposits securely. It facilitates insertion, edition, and deletion of records, as well as searching and traversal operations for efficient data management.  The system provides security features to prevent fraud, generates reports and analytics on ATM usage, and schedules maintenance to keep ATMs running smoothly.

Objectives of ATM Management System:

Efficiency: Streamlining ATM operations to minimize downtime and maximize user convenience.
Security: Implementing robust security measures to safeguard user transactions and prevent fraud.
Maintenance: Managing maintenance schedules and troubleshooting to keep ATMs operational.
User Experience: Enhancing the user experience through seamless transactions and reliable services.
Data Management: Efficiently handling user data, transactions, and ATM statuses for reporting and analysis.
Classes in the ATM Management System:

Signup Class:

Objective: Allows users to create new accounts.
Functionality: Captures user details and generates unique account IDs.
Screenshot
 

Login Class:
Objective: Manages user authentication and access to their accounts.
Functionality: Verifies user credentials and grants access to account functionalities.
Screenshot:
 
Deposit Class:
Objective: Facilitates the deposit of funds into user accounts.
Functionality: Records deposit transactions and updates account balances accordingly.
Screenshot:
 

Withdrawal Class:
Objective: Handles cash withdrawals from user accounts.
Functionality: Verifies available funds and processes withdrawal requests securely.
Screenshot:
 
Pin Change Class:
Objective: Manages PIN-related operations for user accounts.
Functionality: Allows users to change their PINs and verifies PINs during transactions.
Screenshot:
 
Balance Inquiry Class:
Objective: Enables users to check their account balances.
Functionality: Retrieves and displays the current balance for the user's account.
Screenshot:
 
Main Class:
Objective: Acts as the central hub connecting all other classes and functionalities.
Functionality: Coordinates interactions between classes, handles user requests, and ensures smooth execution of ATM operations.
Screenshot:
 
Connectivity Class:
The Connectivity Class links the ATM Management System to the database securely, ensuring data accessibility. Its functions include establishing connections, retrieving and storing data, and managing errors for smooth operation. This class ensures that information flows seamlessly between the system and the database, optimizing performance and security.

By integrating these classes and functionalities, the ATM Management System effectively achieves its objectives of enhancing efficiency, security, maintenance, user experience, and data management within ATM networks.

Database Tables Information:
Signup9 Table
CODE:
public void createSignupTable() {
    try {
        // Get connection
        Connectivity conn = new Connectivity();
        // SQL query to create table if not exists
        // SQL query to create table if not exists
        String sql = "CREATE TABLE IF NOT EXISTS Signup9 (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," + // Auto-incremented primary key
                "Name VARCHAR(255)," +
                "FatherName VARCHAR(255)," +
                "Gender VARCHAR(10)," +
                "Email VARCHAR(255)," +
                "MaritalStatus VARCHAR(20)," +
                "Address VARCHAR(255)," +
                "PIN VARCHAR(20)," +
                "Balance DECIMAL(10, 2) DEFAULT 0.00," + // Add Balance column with default value 0.00
                "INDEX (id)" + // Add index on id column
                ")";

        // Execute query
        conn.statement.executeUpdate(sql);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
Screenshot:
 

Deposit Table
CODE:
public void createDepositTable() {
    try {
        // Get connection
        Connectivity conn = new Connectivity();
        // SQL query to create table if not exists
        String sql = "CREATE TABLE IF NOT EXISTS Deposit (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "UserID INT," +
                "TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "TransactionType VARCHAR(20)," +
                "Amount DECIMAL(10, 2)," +
                "FOREIGN KEY (UserID) REFERENCES Signup9(id)" +  // Updated reference to 'id' column
                ")";

        // Execute query
        conn.statement.executeUpdate(sql);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
Screenshot:
 
Withdraw Table
CODE:
public void createWithdrawalTable() {
    try {
        // Get connection
        Connectivity conn = new Connectivity();
        // SQL query to create table if not exists
        String sql = "CREATE TABLE IF NOT EXISTS withdrawal (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," + // Auto-incremented primary key
                "UserID INT," + // Foreign key referencing the user's table
                "TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "TransactionType VARCHAR(20)," +
                "Amount DECIMAL(10, 2)," +
                "FOREIGN KEY (UserID) REFERENCES Signup9(id)" + // Reference to the user's ID
                ")";

        System.out.println("Withdrawl table created");

        // Execute query
        conn.statement.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

Screenshot:
 

Conclusion: 
The ATM Management System efficiently manages ATMs by handling user accounts, transactions, and maintenance, while securely connecting to a database for smooth data operations. This system enhances ATM reliability, security, and user satisfaction.
