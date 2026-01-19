import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class IssueBook extends JFrame implements ActionListener , KeyListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");

            // Left Panel
     JButton issue_book = new JButton("Issue");

    JPanel left = new JPanel();
    JButton back = new JButton("Back");

    JLabel issue = new JLabel("Issue Book");

    JLabel book_id = new JLabel("Book ID");
    JTextField tf_book_id = new JTextField();

    JLabel book_name = new JLabel("B Name");
    JTextField tf_book_name = new JTextField();


    JLabel m_id = new JLabel("Member ID");
    JTextField tf_m_id = new JTextField();

    JLabel m_name = new JLabel("M Name");
    JTextField tf_m_name= new JTextField();

    JLabel n_days = new JLabel("No Days");
    JTextField tf_n_days = new JTextField();

            // Right Panel
    JPanel right = new JPanel();
    JLabel r_label = new JLabel("Issued Books");
    JTable table;
    JScrollPane scrollPane;

            /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    public IssueBook() {
        initializeUI();
        table_database();
    }

    public void initializeUI() {
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setTitle("Library Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setBounds(260, 140, 775, 459);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(left);
        this.add(right);

                /* Left Side Panel */
        left.setBounds(0, 2, 280, 459);
        left.setBackground(Color.WHITE);
        left.setLayout(null);
        left.add(back);
        left.add(issue);
        left.add(book_id);
        left.add(tf_book_id);
        left.add(m_id);
        left.add(tf_m_id);
        left.add(tf_book_name);
        left.add(book_name);
        left.add(m_name);
        left.add(tf_m_name);
        left.add(issue_book);
        left.add(n_days);
        left.add(tf_n_days);

        issue.setBounds(50,50,200,30);
        issue.setFont(new Font("Times New Roman",Font.BOLD,40));

                //Label For Book Id
        book_id.setBounds(7,110,70,30);
        book_id.setFont(new Font("Times New Roman",Font.BOLD,20));
        book_id.setForeground(Color.DARK_GRAY);

                //Text Field For Book Id
        tf_book_id.setBounds(100,110,170,25);
        tf_book_id.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_book_id.setFont(new Font("Times New Roman",Font.PLAIN,16));
        tf_book_id.setBackground(Color.LIGHT_GRAY);
        tf_book_id.addKeyListener(this);

                  //Label For Book Name
        book_name.setBounds(7,190,70,30);
        book_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        book_name.setForeground(Color.DARK_GRAY);

                //Text Field For Book Name
        tf_book_name.setBounds(100,190,170,25);
        tf_book_name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_book_name.setFont(new Font("Times New Roman",Font.PLAIN,16));
        tf_book_name.setBackground(Color.LIGHT_GRAY);
        tf_book_name.setFocusable(false);

                 //Label Field For Member Id
        m_id.setBounds(7,150,90,30);
        m_id.setFont(new Font("Times New Roman",Font.BOLD,17));
        m_id.setForeground(Color.DARK_GRAY);

                //Text Field For Member Id
        tf_m_id.setBounds(100,150,170,25);
        tf_m_id.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_m_id.setFont(new Font("Times New Roman",Font.PLAIN,16));
        tf_m_id.setBackground(Color.LIGHT_GRAY);
        tf_m_id.addKeyListener(this);

                //Label For Member Name
        m_name.setBounds(7,230,90,30);
        m_name.setFont(new Font("Times New Roman",Font.BOLD,17));
        m_name.setForeground(Color.DARK_GRAY);

                    //Text Field For Member Name
        tf_m_name.setBounds(100,230,170,25);
        tf_m_name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_m_name.setFont(new Font("Times New Roman",Font.PLAIN,16));
        tf_m_name.setBackground(Color.LIGHT_GRAY);
        tf_m_name.setFocusable(false);

                //Label For Days
        n_days.setBounds(7,270,90,30);
        n_days.setFont(new Font("Times New Roman",Font.BOLD,17));
        n_days.setForeground(Color.DARK_GRAY);

                //Text Field For Days
        tf_n_days.setBounds(100,270,170,25);
        tf_n_days.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_n_days.setFont(new Font("Times New Roman",Font.PLAIN,16));
        tf_n_days.setBackground(Color.LIGHT_GRAY);


                    //Issue Button
        issue_book.setBounds(70,320,120,35);
        issue_book.setFocusable(false);
        issue_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        issue_book.setFont(new Font("times new roman",Font.BOLD,29));
        issue_book.setBackground(Color.GRAY);
        issue_book.setForeground(Color.BLACK);
        issue_book.addActionListener(this);
        issue_book.addMouseListener(this);

                //Back Button
        back.setBounds(2, 2, 90, 30);
        back.setFocusable(false);
        back.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.addMouseListener(this);


             /* Right Side Panel */
        right.setBounds(282, 0, 495, 459);
        right.setBackground(Color.LIGHT_GRAY);
        right.setLayout(null);
        right.add(r_label);

        r_label.setBounds(145,18,250,40);
        r_label.setFont(new Font("times new roman",Font.BOLD,40));

                 // Initialize table with data model
        String[] columnNames = {"ID", "Member ID", "M Name", "Book ID", "Book Name", "Return Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.BOLD, 12));
        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 450, 320);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        right.add(scrollPane);
    }

    public void table_database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pass);
            Statement stm = con.createStatement();

            String qry = "select * from issue_book";
            ResultSet rs = stm.executeQuery(qry);
            DefaultTableModel dtb = (DefaultTableModel) table.getModel();

            while (rs.next()) {
                String id = rs.getString(1);
                String m_id = rs.getString(2);
                String m_name = rs.getString(3);
                String b_id = rs.getString(4);
                String b_name = rs.getString(5);
                String i_date = rs.getString(6);
                String[] row = {id, m_id, m_name, b_id, b_name, i_date};
                dtb.addRow(row);
            }
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e2) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e2.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
            new Main_Menu();
        }
        if (e.getSource() == issue_book) {
            if (tf_book_id.getText().isEmpty()||tf_m_id.getText().isEmpty()||tf_n_days.getText().isEmpty()
            ||tf_m_name.getText().isEmpty()||tf_book_name.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Pls Fill All the Fields","Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "INSERT INTO issue_book (m_id, m_name, b_id, b_name, i_date) VALUES (?, ?, ?, ?,DATE_ADD(CURRENT_DATE, INTERVAL ? DAY))";
                    PreparedStatement pps = con.prepareStatement(qry);

                    pps.setString(1, tf_m_id.getText());
                    pps.setString(2, tf_m_name.getText());
                    pps.setString(3, tf_book_id.getText());
                    pps.setString(4, tf_book_name.getText());
                    pps.setInt(5, Integer.parseInt(tf_n_days.getText()));

                    int rowsInserted = pps.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Book issued successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new IssueBook();
                    }

                    pps.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tf_book_id.getText().isEmpty() || tf_m_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Both IDs", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "SELECT m.name AS member_name, b.name AS book_name FROM books b JOIN member m ON m.id = ? AND b.isbn = ?";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setInt(1, Integer.parseInt(tf_m_id.getText()));
                    pps.setInt(2, Integer.parseInt(tf_book_id.getText()));

                    ResultSet rs = pps.executeQuery();
                    if (rs.next()) {
                        tf_m_name.setText(rs.getString("member_name"));
                        tf_book_name.setText(rs.getString("book_name"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Please Enter Correct IDs", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    rs.close();
                    pps.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

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
        if (e.getSource() == back){
            back.setBackground(Color.BLACK);
            back.setForeground(Color.orange);
            back.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            back.setSize(80,25);
        }
        else if (e.getSource() == issue_book){
            issue_book.setBackground(Color.BLACK);
            issue_book.setForeground(Color.orange);
            issue_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            issue_book.setSize(110,25);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getSource() == back){
            back.setBackground(Color.DARK_GRAY);
            back.setForeground(Color.WHITE);
            back.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            back.setSize(90,30);
        }
        else if (e.getSource() == issue_book){
            issue_book.setBackground(Color.DARK_GRAY);
            issue_book.setForeground(Color.white);
            issue_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            issue_book.setSize(120,30);
        }

    }
}
