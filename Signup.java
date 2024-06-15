package atm.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L)+ 1000L;
    String first = " " + Math.abs(first4);
    JTextField textField,textField1,textField2,textField3,textField4;
    JLabel genderLabel,maritalLabel;
    JButton button4;
    JRadioButton maleRadioButton,femaleRadioButton,marriedRadioButton,unmarriedRadioButton,otherRadioButton;

    Signup(){
        super("Application Form");

        ImageIcon i1 = new ImageIcon("Icons/Atm.png");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        add(image);

        JLabel label1 = new JLabel("Application Form No: "+first);
        label1.setBounds(160,20, 600,40);
        label1.setFont(new Font("Raleway",Font.BOLD,38));
        add(label1);

        JLabel label2 = new JLabel("Personal Details");
        label2.setBounds(160,80, 600,40);
        label2.setFont(new Font("Arial",Font.BOLD,25));
        add(label2);

        JLabel label3 = new JLabel("Name : ");
        label3.setBounds(100,150, 350,20);
        label3.setFont(new Font("Arial",Font.BOLD,20));
        add(label3);

        textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.BOLD,20));
        textField.setBounds(275, 150,230,25);
        add(textField);

        JLabel label4 = new JLabel("Father's Name : ");
        label4.setBounds(100,200, 370,20);
        label4.setFont(new Font("Arial",Font.BOLD,20));
        add(label4);

        textField1 = new JTextField(15);
        textField1.setFont(new Font("Arial", Font.BOLD,20));
        textField1.setBounds(275, 200,230,25);
        add(textField1);

        genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(100,250, 350,20);
        genderLabel.setFont(new Font("Arial",Font.BOLD,20));
        ButtonGroup genderGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(275, 250, 100, 30);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(380, 250, 100, 30);
        add(genderLabel);
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        add(maleRadioButton);
        add(femaleRadioButton);

        JLabel label5 = new JLabel("Email Address:");
        label5.setBounds(100,290, 250,20);
        label5.setFont(new Font("Arial",Font.BOLD,20));
        add(label5);

        textField2 = new JTextField(15);
        textField2.setFont(new Font("Arial", Font.BOLD,20));
        textField2.setBounds(275, 290,230,25);
        add(textField2);

        maritalLabel = new JLabel("Marital Status : ");
        maritalLabel.setBounds(100,350, 350,20);
        maritalLabel.setFont(new Font("Arial",Font.BOLD,20));
        ButtonGroup maritalGroup = new ButtonGroup();
        marriedRadioButton = new JRadioButton("Married");
        marriedRadioButton.setBounds(280, 345, 100, 30);
        unmarriedRadioButton = new JRadioButton("UnMarried");
        unmarriedRadioButton.setBounds(390, 345, 100, 30);
        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(500, 345, 100, 30);
        add(maritalLabel);
        maritalGroup.add(marriedRadioButton);
        maritalGroup.add(unmarriedRadioButton);
        maritalGroup.add(otherRadioButton);
        add(marriedRadioButton);
        add(unmarriedRadioButton);
        add(otherRadioButton);

        JLabel label6 = new JLabel("Address : ");
        label6.setBounds(100,410, 370,20);
        label6.setFont(new Font("Arial",Font.BOLD,20));
        add(label6);

        textField3 = new JTextField(30);
        textField3.setFont(new Font("Arial", Font.BOLD,20));
        textField3.setBounds(275, 410,230,25);
        add(textField3);

        JLabel label7 = new JLabel("PIN : ");
        label7.setBounds(100,480, 370,20);
        label7.setFont(new Font("Arial",Font.BOLD,20));
        add(label7);

        textField4 = new JPasswordField(15);
        textField4.setFont(new Font("Arial", Font.BOLD,20));
        textField4.setBounds(275, 480,230,25);
        add(textField4);

        button4 = new JButton("Next");
        button4.setFont(new Font("Arial", Font.BOLD, 15));
        button4.setForeground(Color.white);
        button4.setBackground(Color.BLACK);
        button4.setBounds(600, 600, 150, 30 );
        button4.addActionListener(this);
        add(button4);

        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(400, 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String formno = first;
        String name = textField.getText();
        String fname = textField1.getText();
        String gender = null;
        if(maleRadioButton.isSelected()){
            gender = "Male";
        } else if (femaleRadioButton.isSelected()) {
            gender = "Female";
        }
        String email = textField2.getText();
        String marital = null;
        if(marriedRadioButton.isSelected()){
            marital = "Married";
        } else if (unmarriedRadioButton.isSelected()) {
            marital = "UnMarried";
        } else if (otherRadioButton.isSelected()) {
            marital = "Other";
        }
        String adress = textField3.getText();
        String pin = textField4.getText();

        try{
            if(textField.getText().equals("") || textField1.getText().equals("") ||
                    textField2.getText().equals("") || textField3.getText().equals("") ||
                    textField4.getText().equals("") || gender == null || marital == null){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
            }else{
                createSignupTable();
                Connectivity c2 = new Connectivity();
                String q = "INSERT INTO Signup9 (Name, FatherName, Gender, Email, MaritalStatus, Address, PIN) " +
                        "VALUES ('"+name+"', '"+fname+"', '"+gender+"', '"+email+"', '"+marital+"', '"+adress+"', '"+pin+"')";
                c2.statement.executeUpdate(q);
                setVisible(false);
                new Login();
            }

        }catch(Exception E){
            E.printStackTrace();
        }


    }
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


    public static void main(String [] args){
        new Signup();
    }
}
