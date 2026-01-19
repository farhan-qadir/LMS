import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DelAuthor extends JFrame implements ActionListener, KeyListener, MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    Author obj = new Author();
    JButton del = new JButton("Delete");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Delete Author");

    JLabel idLabel = new JLabel("Author ID");
    JTextField tf_id = new JTextField();

    JLabel nameLabel = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel nationLabel = new JLabel("Nationality");
    JTextField tf_nation = new JTextField();

    JLabel langLabel = new JLabel("Language");
    JTextField tf_lang = new JTextField();

    /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    DelAuthor() {
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
        panel.add(nationLabel);
        panel.add(tf_nation);
        panel.add(langLabel);
        panel.add(tf_lang);
        panel.add(del);

        label.setBounds(85, 10, 200, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));

                /* Author ID */
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

                     /* Nationality */
        nationLabel.setBounds(7, 150, 105, 30);
        nationLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_nation.setBounds(140, 150, 170, 25);
        tf_nation.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_nation.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_nation.setFocusable(false);

                 /* Language */
        langLabel.setBounds(7, 190, 105, 30);
        langLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        tf_lang.setBounds(140, 190, 170, 25);
        tf_lang.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_lang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_lang.setFocusable(false);

                /* Delete Button */
        del.setFocusable(false);
        del.setBounds(105, 250, 140, 35);
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
                String qry = "DELETE FROM author WHERE id = ?";
                PreparedStatement pst = con.prepareStatement(qry);
                pst.setInt(1, Integer.parseInt(tf_id.getText()));

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Author Deleted Successfully");
                    this.dispose();
                    obj.dispose();
                    new Author();
                } else {
                    JOptionPane.showMessageDialog(null, "No Author Found with ID: " + tf_id.getText());
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
                JOptionPane.showMessageDialog(null, "Please Enter Author ID", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "SELECT name, nationality, language FROM author WHERE id = ?";
                    PreparedStatement pst = con.prepareStatement(qry);
                    pst.setInt(1, Integer.parseInt(tf_id.getText()));

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        tf_name.setText(rs.getString("name"));
                        tf_nation.setText(rs.getString("nationality"));
                        tf_lang.setText(rs.getString("language"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No Author Found with ID: " + tf_id.getText(), "Info", JOptionPane.INFORMATION_MESSAGE);
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


            /*Methods of Mouse Listener*/
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
