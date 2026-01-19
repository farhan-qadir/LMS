import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DelMember extends JFrame implements ActionListener, KeyListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    Member obj = new Member();
    JButton del = new JButton("Delete");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Delete Member");

    JLabel idLabel = new JLabel("Member ID");
    JTextField tf_id = new JTextField();

    JLabel nameLabel = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel cityLabel = new JLabel("City");
    JTextField tf_city = new JTextField();

    JLabel houseLabel = new JLabel("House No");
    JTextField tf_house = new JTextField();

    JLabel phoneLabel = new JLabel("Phone No");
    JTextField tf_phone = new JTextField();

    /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    DelMember() {
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
        panel.add(idLabel);
        panel.add(tf_id);
        panel.add(nameLabel);
        panel.add(tf_name);
        panel.add(cityLabel);
        panel.add(tf_city);
        panel.add(houseLabel);
        panel.add(tf_house);
        panel.add(phoneLabel);
        panel.add(tf_phone);
        panel.add(del);

        label.setBounds(85, 10, 200, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

        /* Member ID */
        idLabel.setBounds(7, 70, 100, 20);
        idLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_id.setBounds(140, 70, 170, 25);
        tf_id.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_id.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_id.addKeyListener(this);

        /* Name */
        nameLabel.setBounds(7, 110, 70, 30);
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));

        tf_name.setBounds(140, 110, 170, 25);
        tf_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_name.setFocusable(false);

        /* City */
        cityLabel.setBounds(7, 150, 70, 30);
        cityLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_city.setBounds(140, 150, 170, 25);
        tf_city.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_city.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_city.setFocusable(false);

        /* House No */
        houseLabel.setBounds(7, 190, 100, 30);
        houseLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_house.setBounds(140, 190, 170, 25);
        tf_house.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_house.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_house.setFocusable(false);

        /* Phone No */
        phoneLabel.setBounds(7, 230, 100, 30);
        phoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_phone.setBounds(140, 230, 170, 25);
        tf_phone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_phone.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_phone.setFocusable(false);

        /* Delete Button */
        del.setFocusable(false);
        del.setBounds(105, 280, 140, 35);
        del.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        del.setFont(new Font("Times New Roman", Font.BOLD, 27));
        del.setBackground(Color.GRAY);
        del.setForeground(Color.BLACK);
        del.addActionListener(this);
        del.addMouseListener(this);
    }

    /* Method Of Action Listener */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == del) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, username, pass);
                String qry = "DELETE FROM member WHERE id = ?";
                PreparedStatement pst = con.prepareStatement(qry);
                pst.setInt(1, Integer.parseInt(tf_id.getText()));

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Member Deleted Successfully");
                    this.dispose();
                    obj.dispose();
                    new Member();
                } else {
                    JOptionPane.showMessageDialog(null, "No Member Found with ID: " + tf_id.getText());
                }

                pst.close();
                con.close();
            } catch (ClassNotFoundException | SQLException e2) {
                JOptionPane.showMessageDialog(null, "Something went wrong", "Warning", JOptionPane.ERROR_MESSAGE);
                System.out.println(e2.getMessage());
            }
        }
    }

    /* Methods Of Key Listener */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tf_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Member ID", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "SELECT name, city, house_no, phone_no FROM member WHERE id = ?";
                    PreparedStatement pst = con.prepareStatement(qry);
                    pst.setInt(1, Integer.parseInt(tf_id.getText()));

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        tf_name.setText(rs.getString("name"));
                        tf_city.setText(rs.getString("city"));
                        tf_house.setText(rs.getString("house_no"));
                        tf_phone.setText(rs.getString("phone_no"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No Member Found with ID: " + tf_id.getText(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    }

                    rs.close();
                    pst.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException e2) {
                    JOptionPane.showMessageDialog(null, "Something went wrong", "Warning", JOptionPane.ERROR_MESSAGE);
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
        if (e.getSource()== del){
            del.setBackground(Color.BLACK);
            del.setForeground(Color.orange);
            del.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            del.setSize(120,30);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == del){
            del.setBackground(Color.GRAY);
            del.setForeground(Color.BLACK);
            del.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            del.setSize(140,35);
        }

    }
}
