import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReturnBook extends JFrame implements ActionListener , KeyListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
                 // Left Pane
    JButton return_book = new JButton("Return");

    JPanel left = new JPanel();
    JButton back = new JButton("Back");

    JLabel rtn = new JLabel("Return Book");

    JLabel issue_id = new JLabel("Issue ID");
    JTextField tf_issue_id = new JTextField();

    JLabel book_name = new JLabel("B Name");
    JTextField tf_book_name = new JTextField();

    JLabel m_name = new JLabel("M Name");
    JTextField tf_m_name = new JTextField();

            // Right Panel
    JPanel right = new JPanel();
    JLabel r_label = new JLabel("Returned Books");
    JTable table;
    JScrollPane scrollPane;

            /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    public ReturnBook() {
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
        left.add(rtn);
        left.add(issue_id);
        left.add(tf_issue_id);
        left.add(book_name);
        left.add(tf_book_name);
        left.add(m_name);
        left.add(tf_m_name);
        left.add(return_book);

        rtn.setBounds(50, 50, 200, 30);
        rtn.setFont(new Font("Times New Roman", Font.BOLD, 35));

                // Back Button
        back.setBounds(2, 2, 90, 30);
        back.setFocusable(false);
        back.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.addMouseListener(this);

                 // Label For Issue Id
        issue_id.setBounds(7, 110, 70, 30);
        issue_id.setFont(new Font("Times New Roman", Font.BOLD, 20));
        issue_id.setForeground(Color.DARK_GRAY);

                 // Text Field For Issue Id
        tf_issue_id.setBounds(100, 110, 170, 25);
        tf_issue_id.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_issue_id.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tf_issue_id.setBackground(Color.LIGHT_GRAY);
        tf_issue_id.addKeyListener(this);

                    // Label For Book Name
        book_name.setBounds(7, 150, 70, 30);
        book_name.setFont(new Font("Times New Roman", Font.BOLD, 20));
        book_name.setForeground(Color.DARK_GRAY);

                 // Text Field For Book Name
        tf_book_name.setBounds(100, 150, 170, 25);
        tf_book_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_book_name.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tf_book_name.setBackground(Color.LIGHT_GRAY);
        tf_book_name.setFocusable(false);

                    // Label For Member Name
        m_name.setBounds(7, 190, 90, 30);
        m_name.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m_name.setForeground(Color.DARK_GRAY);

                     // Text Field For Member Name
        tf_m_name.setBounds(100, 190, 170, 25);
        tf_m_name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_m_name.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tf_m_name.setBackground(Color.LIGHT_GRAY);
        tf_m_name.setFocusable(false);

                 // Return Button
        return_book.setBounds(70, 250, 120, 35);
        return_book.setFocusable(false);
        return_book.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        return_book.setFont(new Font("Times New Roman", Font.BOLD, 29));
        return_book.setBackground(Color.GRAY);
        return_book.setForeground(Color.BLACK);
        return_book.addActionListener(this);
        return_book.addMouseListener(this);

                    /* Right Side Panel */
        right.setBounds(282, 0, 495, 459);
        right.setBackground(Color.LIGHT_GRAY);
        right.setLayout(null);
        right.add(r_label);

        r_label.setBounds(120,18,290,40);
        r_label.setFont(new Font("times new roman",Font.BOLD,40));

                    // Initialize table with data model
        String[] columnNames = {"ID", "Issue ID", "M Name", "B Name", "Late Days"};
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

            String qry = "select * from return_book;";
            ResultSet rs = stm.executeQuery(qry);
            DefaultTableModel dtb = (DefaultTableModel) table.getModel();

            while (rs.next()) {
                String id = rs.getString(1);
                String issueId = rs.getString(2);
                String mName = rs.getString(3);
                String bName = rs.getString(4);
                String late = rs.getString(5);
                String[] row = {id, issueId, mName, bName, late};
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
        } else if (e.getSource() == return_book) {
            handleReturnBook();
        }
    }

    private void handleReturnBook() {
        if (tf_issue_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Issue ID", "Message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pass);
            String qry = "SELECT m_name, b_name , (current_date() - i_date ) as days FROM issue_book WHERE IB_id = ?";
            PreparedStatement pps = con.prepareStatement(qry);
            pps.setString(1, tf_issue_id.getText());

            ResultSet rs = pps.executeQuery();


            if (rs.next()) {
                int days = rs.getInt("days");
                if (days < 0) {
                    days = 0;
                }
            if (days>0){
                tf_m_name.setText(rs.getString("m_name"));
                tf_book_name.setText(rs.getString("b_name"));

                String returnQry = "INSERT INTO return_book (i_id, m_name, b_name, late_days) VALUES (?, ?, ?, ?)";
                PreparedStatement returnPps = con.prepareStatement(returnQry);
                returnPps.setString(1, tf_issue_id.getText());
                returnPps.setString(2, tf_m_name.getText());
                returnPps.setString(3, tf_book_name.getText());
                returnPps.setInt(4, days);

                int rowsInserted = returnPps.executeUpdate();


                    if (rowsInserted > 0) {
                        String deleteQry = "DELETE FROM issue_book WHERE IB_id = ?";
                        PreparedStatement deletePps = con.prepareStatement(deleteQry);
                        deletePps.setString(1, tf_issue_id.getText());
                        deletePps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Book returned successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new ReturnBook();
                    }
                else{
                        JOptionPane.showMessageDialog(null, "Please enter a valid Issue ID", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                returnPps.close();
                }
            else {

                String deleteQry = "DELETE FROM issue_book WHERE IB_id = ?";
                PreparedStatement deletePps = con.prepareStatement(deleteQry);
                deletePps.setString(1, tf_issue_id.getText());
                deletePps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Book returned successfully On Time", "Message", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new ReturnBook();
                }
            }
            rs.close();
            pps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e2) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e2.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tf_issue_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Both IDs", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "select  m_name , b_name  from issue_book where IB_id = ?";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setInt(1, Integer.parseInt(tf_issue_id.getText()));


                    ResultSet rs = pps.executeQuery();
                    if (rs.next()) {
                        tf_m_name.setText(rs.getString("m_name"));
                        tf_book_name.setText(rs.getString("b_name"));
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
        else if (e.getSource() == return_book){
            return_book.setBackground(Color.BLACK);
            return_book.setForeground(Color.orange);
            return_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            return_book.setSize(110,25);
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
        else if (e.getSource() == return_book){
            return_book.setBackground(Color.DARK_GRAY);
            return_book.setForeground(Color.white);
            return_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            return_book.setSize(120,30);
        }

    }
}
