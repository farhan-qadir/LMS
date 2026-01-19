import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DelBook extends JFrame implements ActionListener, KeyListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");

    Books obj = new Books();

    JButton del = new JButton("Delete");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Delete Book");

    JLabel isbn = new JLabel("ISBN");
    JTextField tf_isbn = new JTextField();

    JLabel name = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel author = new JLabel("Author");
    JTextField tf_author = new JTextField();

    JLabel edition = new JLabel("Edition");
    JTextField tf_edi = new JTextField();

    /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    DelBook() {
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
        panel.add(isbn);
        panel.add(tf_isbn);
        panel.add(name);
        panel.add(tf_name);
        panel.add(author);
        panel.add(tf_author);
        panel.add(edition);
        panel.add(tf_edi);
        panel.add(del);

        label.setBounds(80, 10, 200, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 35));

                         /* ISBN */
        isbn.setBounds(7, 70, 70, 30);
        isbn.setFont(new Font("Times New Roman", Font.BOLD, 23));

        tf_isbn.setBounds(140, 70, 170, 25);
        tf_isbn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_isbn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_isbn.addKeyListener(this);

                         /* Name */
        name.setBounds(7, 110, 70, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 23));

        tf_name.setBounds(140, 110, 170, 25);
        tf_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_name.setFocusable(false);

                          /* Author */
        author.setBounds(7, 150, 105, 30);
        author.setFont(new Font("Times New Roman", Font.BOLD, 23));

        tf_author.setBounds(140, 150, 170, 25);
        tf_author.setFocusable(false);
        tf_author.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));

                         /* Edition */
        edition.setBounds(7, 190, 80, 30);
        edition.setFont(new Font("Times New Roman", Font.BOLD, 23));

        tf_edi.setBounds(140, 190, 170, 25);
        tf_edi.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_edi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        tf_edi.setFocusable(false);

                         /* Add Button */
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
                String qry = "DELETE FROM books WHERE isbn = ?";
                PreparedStatement pst = con.prepareStatement(qry);
                pst.setInt(1, Integer.parseInt(tf_isbn.getText()));

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Book Deleted Successfully");
                    this.dispose();
                    obj.dis();
                    new Books();
                } else {
                    JOptionPane.showMessageDialog(null, "No Book Found with ISBN: " + tf_isbn.getText());
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
            if (tf_isbn.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Book ISBN", "Warning", JOptionPane.PLAIN_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "SELECT name, author, edition FROM books WHERE isbn = ?";
                    PreparedStatement pst = con.prepareStatement(qry);
                    pst.setInt(1, Integer.parseInt(tf_isbn.getText()));

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        tf_name.setText(rs.getString("name"));
                        tf_author.setText(rs.getString("author"));
                        tf_edi.setText(rs.getString("edition"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No Book Found with ISBN: " + tf_isbn.getText(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    }

                    rs.close();
                    pst.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException e2) {
                    JOptionPane.showMessageDialog(null, "Something went wrong", "Warning", JOptionPane.ERROR_MESSAGE);
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
