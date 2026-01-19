import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class AddAuthor extends JFrame implements ActionListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    Author obj = new Author();
    JButton add = new JButton("ADD");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Add Author");

    JLabel name = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel nation = new JLabel("Nationality");
    JTextField tf_nation = new JTextField();

    JLabel lang = new JLabel("Language");
    JTextField tf_lang = new JTextField();

    /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    AddAuthor() {
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
        panel.add(nation);
        panel.add(tf_nation);
        panel.add(lang);
        panel.add(tf_lang);
        panel.add(add);

        label.setBounds(85, 10, 200, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 35));

        /* Name */
        name.setBounds(7, 70, 70, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_name.setBounds(140, 70, 170, 25);
        tf_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                     /* Nationality */
        nation.setBounds(7, 110, 120, 30);
        nation.setFont(new Font("Times New Roman", Font.BOLD, 17));

        tf_nation.setBounds(140, 110, 170, 25);
        tf_nation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_nation.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                    /* Language */
        lang.setBounds(7, 155, 110, 30);
        lang.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_lang.setBounds(140, 155, 170, 25);
        tf_lang.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_lang.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                  /* Add Button */
        add.setBounds(105, 220, 140, 35);
        add.setFocusable(false);
        add.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        add.setFont(new Font("Times new roman", Font.BOLD, 27));
        add.setBackground(Color.GRAY);
        add.setForeground(Color.BLACK);
        add.addActionListener(this);
        add.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (tf_name.getText().isEmpty() || tf_nation.getText().isEmpty() || tf_lang.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Message", JOptionPane.PLAIN_MESSAGE);
            } else {
                /* Database For Addition Of Author */
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "INSERT INTO author( name , nationality , language ) VALUES (?, ?, ?)";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setString(1, tf_name.getText());
                    pps.setString(2, tf_nation.getText());
                    pps.setString(3, tf_lang.getText());

                    pps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Author Added Successfully", "Message", JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    obj.dispose();
                    new Author();
                } catch (ClassNotFoundException | SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e1.getMessage());
                }
            }
        }
    }

            /*Methods Of mouse Listener*/
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
