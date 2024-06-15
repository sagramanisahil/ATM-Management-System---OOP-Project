package atm.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceInquiry extends JFrame implements ActionListener {
    JLabel label2;
    JButton b1;
    String pin;

    BalanceInquiry(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon("Icons/atm2.png");
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel label1 = new JLabel("Your Current Balance is : ");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        image.add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.white);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 700, 35);
        image.add(label2);

        b1 = new JButton("Back");
        b1.setBounds(700, 406, 150, 35);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(65, 128, 125));
        b1.addActionListener(this);
        image.add(b1);

        // Fetch balance from Signup9 table
        int balance = getUserBalance(pin);
        label2.setText("" + balance);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        try {
            new Main(pin);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Method to get the current balance of the user from Signup9 table based on PIN
    private int getUserBalance(String pin) {
        int balance = 0;
        try {
            Connectivity conn = new Connectivity();
            String query = "SELECT Balance FROM Signup9 WHERE PIN = '" + pin + "'";
            ResultSet rs = conn.statement.executeQuery(query);
            if (rs.next()) {
                balance = rs.getInt("Balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
