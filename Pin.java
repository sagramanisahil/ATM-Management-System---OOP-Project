package atm.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {

    String pin;
    JButton b1, b2;
    JPasswordField p1, p2;

    Pin(String pin) {

        this.pin = pin;
        ImageIcon i1 = new ImageIcon("Icons/atm2.png");
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 830);
        add(image);

        JLabel label1 = new JLabel("Change your Pin");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(430, 180, 480, 35);
        image.add(label1);

        JLabel label2 = new JLabel("New Pin");
        label2.setForeground(Color.white);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(430, 220, 150, 35);
        image.add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65, 128, 125));
        p1.setForeground(Color.white);
        p1.setBounds(600, 220, 180, 25);
        p1.setFont(new Font("Rale way", Font.BOLD, 22));
        image.add(p1);

        JLabel label3 = new JLabel("Re-Enter New Pin");
        label3.setForeground(Color.white);
        label3.setFont(new Font("System", Font.BOLD, 16));
        label3.setBounds(430, 250, 200, 35);
        image.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65, 128, 125));
        p2.setForeground(Color.white);
        p2.setBounds(600, 260, 180, 25);
        p2.setFont(new Font("Rale way", Font.BOLD, 22));
        image.add(p2);

        b1 = new JButton("Change");
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

        try {

            String pin1 = p1.getText();
            String pin2 = p2.getText();

            if (!pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            if (e.getSource() == b1) {
                if (pin1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (pin2.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter New PIN");
                    return;
                }

                Connectivity c6 = new Connectivity();
                String q1 = "update Signup9 set PIN = '" + pin1 + "' where PIN = '" + pin + "'";

                c6.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Main(pin);

            } else if (e.getSource() == b2) {
                new Main(pin);
                setVisible(false);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
