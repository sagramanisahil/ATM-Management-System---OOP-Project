package atm.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends Frame implements ActionListener {
    JButton b1, b2, b3, b4, b6, b7;
    String pin;
    int id = 0;
    Main(String pin) throws SQLException {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon("Icons/atm2.png");
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        Connectivity con = new Connectivity();

        String query = "Select id from signup9 where PIN = "+pin;
        ResultSet set = con.statement.executeQuery(query);
        if(set.next())
        id = set.getInt("ID");
        else System.out.println("Record not found");

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(430, 180, 700, 35);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        image.add(label);

        b1 = new JButton("Deposit");
        b1.setBounds(410, 270, 130, 35);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(65, 128, 125));
        b1.addActionListener(this);
        image.add(b1);

        b2 = new JButton("Cash WithDrawl");
        b2.setBounds(410, 317, 130, 35);
        b2.setForeground(Color.white);
        b2.setBackground(new Color(65, 128, 125));
        b2.addActionListener(this);
        image.add(b2);

        b3 = new JButton("Balance Inquiry");
        b3.setBounds(410, 363, 130, 35);
        b3.setForeground(Color.white);
        b3.setBackground(new Color(65, 128, 125));
        b3.addActionListener(this);
        image.add(b3);



        b4 = new JButton("Pin Change");
        b4.setBounds(715, 270, 130, 35);
        b4.setForeground(Color.white);
        b4.setBackground(new Color(65, 128, 125));
        b4.addActionListener(this);
        image.add(b4);

        b7 = new JButton("Delete account");
        b7.setBounds(715, 317, 130, 35);
        b7.setForeground(Color.white);
        b7.setBackground(new Color(65, 128, 125));
        b7.addActionListener(this);
        image.add(b7);

        b6 = new JButton("Exit");
        b6.setBounds(715, 363, 130, 35);
        b6.setForeground(Color.white);
        b6.setBackground(new Color(65, 128, 125));
        b6.addActionListener(this);
        image.add(b6);


        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Deposit(pin);
            setVisible(false);
        }
       else if (e.getSource() == b2) {
            new Withdraw(pin);
            setVisible(false);
        } else if (e.getSource() == b6) {
            System.exit(0);
        } else if (e.getSource() == b3) {
            new BalanceInquiry(pin);
            setVisible(false);
        } else if (e.getSource() == b4) {
            new Pin(pin);
            setVisible(false);
        }
        else if (e.getSource() == b7) {
            try {
                deleteAccout(id);
                JOptionPane.showMessageDialog(this, "User is successfully deleted");
                setVisible(false);
                new Login();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }
    public void deleteAccout(int id) throws SQLException{
        String query = "Delete from signup9 where id = "+id;
        Connectivity con = new Connectivity();
        con.statement.executeUpdate(query);


    }

}
