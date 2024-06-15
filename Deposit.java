package atm.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String pin;
    JTextField textField;
    JButton b1, b2;

    Deposit(String pin) {
        this.pin = pin;
        createDepositTable();

        ImageIcon i1 = new ImageIcon("./Icons/atm2.png");
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 480, 35);
        image.add(label1);

        textField = new JTextField();
        textField.setBackground(new Color(65, 128, 125));
        textField.setForeground(Color.white);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(65, 128, 125));
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 410, 150, 35);
        b2.setForeground(Color.white);
        b2.setBackground(new Color(65, 128, 125));
        b2.addActionListener(this);
        image.add(b2);

        setLayout(null);
        setSize(1550, 830);
        setLocation(0, 0);
        setVisible(true);
    }

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



    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();

            if (e.getSource() == b1) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter amount you want to deposit");
                } else {
                    Connectivity c1 = new Connectivity();
                    int userID = getUserIdByPIN(pin); // Get the user ID from Signup9 table
                    double depositAmount = Double.parseDouble(amount);

                    // Retrieve the current balance of the user
                    double currentBalance = getUserBalance(userID);

                    // Calculate the new balance after deposit
                    double newBalance = currentBalance + depositAmount;

                    // Update the balance in Signup9 table
                    c1.statement.executeUpdate("UPDATE Signup9 SET Balance = '" + newBalance + "' WHERE id = '" + userID + "'");

                    // Insert the deposit transaction into Deposit table
                    c1.statement.executeUpdate("INSERT INTO Deposit (UserID, TransactionType, Amount) VALUES ('" + userID + "', 'Deposit', '" + amount + "')");

                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    setVisible(false);
                    new Main(pin);
                }
            } else if (e.getSource() == b2) {
                setVisible(false);
                new Main(pin);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    // Method to get the current balance of the user from Signup9 table based on user ID
    private double getUserBalance(int userID) {
        double balance = 0.0;
        try {
            Connectivity conn = new Connectivity();
            String query = "SELECT Balance FROM Signup9 WHERE id = '" + userID + "'";
            ResultSet rs = conn.statement.executeQuery(query);
            if (rs.next()) {
                balance = rs.getDouble("Balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }


    // Method to get the user ID from the Signup9 table based on PIN
    private int getUserIdByPIN(String pin) {
        int userID = 0;
        try {
            Connectivity conn = new Connectivity();
            String query = "SELECT id FROM Signup9 WHERE PIN = '" + pin + "'";
            ResultSet rs = conn.statement.executeQuery(query);
            if (rs.next()) {
                userID = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userID;
    }


}
