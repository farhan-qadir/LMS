import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class AddMember extends JFrame implements ActionListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    Member obj = new Member();
    JButton add = new JButton("ADD");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Add Member");

    JLabel name = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel city = new JLabel("City");
    JTextField tf_city = new JTextField();

    JLabel house = new JLabel("House No");
    JTextField tf_house = new JTextField();

    JLabel phone = new JLabel("Phone No");
    JTextField tf_phone = new JTextField();

    /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    AddMember() {
        /* Frame */
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setTitle("Library Management System");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setBounds(480, 170, 350, 400);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(panel);

        panel.setBounds(0, 0, 350, 400);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.add(label);
        panel.add(name);
        panel.add(tf_name);
        panel.add(city);
        panel.add(tf_city);
        panel.add(house);
        panel.add(tf_house);
        panel.add(phone);
        panel.add(tf_phone);
        panel.add(add);

        label.setBounds(85, 10, 200, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 35));

        /* Name */
        name.setBounds(7, 70, 70, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_name.setBounds(140, 70, 170, 25);
        tf_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        /* City */
        city.setBounds(7, 110, 70, 30);
        city.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_city.setBounds(140, 110, 170, 25);
        tf_city.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_city.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        /* House No */
        house.setBounds(7, 150, 100, 30);
        house.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_house.setBounds(140, 150, 170, 25);
        tf_house.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_house.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        /* Phone No */
        phone.setBounds(7, 190, 100, 30);
        phone.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_phone.setBounds(140, 190, 170, 25);
        tf_phone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_phone.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        /* Add Button */
        add.setBounds(105, 250, 140, 35);
        add.setFocusable(false);
        add.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        add.setFont(new Font("Times New Roman", Font.BOLD, 27));
        add.setBackground(Color.GRAY);
        add.setForeground(Color.BLACK);
        add.addActionListener(this);
        add.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (tf_name.getText().isEmpty() || tf_city.getText().isEmpty() || tf_house.getText().isEmpty() || tf_phone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Message", JOptionPane.PLAIN_MESSAGE);
            } else {
                /* Database For Addition Of Member */
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "INSERT INTO member(name, city, house_no, phone_no) VALUES (?, ?, ?, ?)";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setString(1, tf_name.getText());
                    pps.setString(2, tf_city.getText());
                    pps.setString(3, tf_house.getText());
                    pps.setString(4, tf_phone.getText());

                    pps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Member Added Successfully", "Message", JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    obj.dispose();
                    new Member();
                } catch (ClassNotFoundException | SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e1.getMessage());
                }
            }
        }
    }


                 /*Methods of mouse Listener*/
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()== add){
            add.setBackground(Color.BLACK);
            add.setForeground(Color.orange);
            add.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            add.setSize(130,30);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == add){
            add.setBackground(Color.GRAY);
            add.setForeground(Color.BLACK);
            add.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            add.setSize(140,35);
        }

    }
}
