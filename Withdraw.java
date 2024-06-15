package atm.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    String pin;
    TextField textField;
    int uid;
    JButton b1, b2;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    Withdraw(String pin) {
        this.pin = pin;
        createWithdrawalTable();
        ImageIcon i1 = new ImageIcon("Icons/atm2.png");
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel label1 = new JLabel("Maximum Withdrawal Amount is Rs: 10,000");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        image.add(label1);

        JLabel label2 = new JLabel("Please Enter your Amount");
        label2.setForeground(Color.white);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 700, 35);
        image.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65, 128, 125));
        textField.setForeground(Color.white);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(textField);

        b1 = new JButton("Withdrawal");
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
        setSize(1550, 1080);
        setLocation(0, 0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                String formattedDate = dateFormat.format(date);
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                } else {
                    Connectivity c3 = new Connectivity();
                    ResultSet resultSet = c3.statement.executeQuery("select * from Signup9 where pin = '" + pin + "'");
                    if (resultSet.next()) {
                        int balance = resultSet.getInt("Balance");
                        uid = resultSet.getInt("id");
                        if (balance < Integer.parseInt(amount)) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            return;
                        }

                        balance -= Integer.parseInt(amount);
                        c3.statement.executeUpdate("update Signup9 set Balance = " + balance + " where pin = '" + pin + "'");
                        c3.statement.executeUpdate("insert into withdrawal (UserID, TransactionDate, TransactionType, Amount) values ('" + uid + "', '" + formattedDate  + "', 'Withdraw', '" + amount + "')");
                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
                        setVisible(false);
                        new Main(pin);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            try {
                new Main(pin);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

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

}
