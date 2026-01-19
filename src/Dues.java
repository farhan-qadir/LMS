import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Dues extends JFrame implements ActionListener , KeyListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    // Left Pane
    JButton clear = new JButton("Clear");

    JPanel left = new JPanel();
    JButton back = new JButton("Back");

    JLabel dues = new JLabel("Clear Dues");

    JLabel return_id = new JLabel("Return ID");
    JTextField tf_return_id = new JTextField();

    JLabel book_name = new JLabel("B Name");
    JTextField tf_book_name = new JTextField();

    JLabel m_name = new JLabel("M Name");
    JTextField tf_m_name = new JTextField();

    JLabel charges = new JLabel("Charges");
    JTextField tf_charges = new JTextField();

                 // Right Panel
    JPanel right = new JPanel();
    JLabel r_label = new JLabel("Remaining Charges");
    JTable table;
    JScrollPane scrollPane;

             /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    public Dues() {
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
        left.add(dues);
        left.add(return_id);
        left.add(tf_return_id);
        left.add(book_name);
        left.add(tf_book_name);
        left.add(m_name);
        left.add(tf_m_name);
        left.add(charges);
        left.add(tf_charges);
        left.add(clear);

        dues.setBounds(50, 50, 200, 30);
        dues.setFont(new Font("Times New Roman", Font.BOLD, 35));

                    // Back Button
        back.setBounds(2, 2, 90, 30);
        back.setFocusable(false);
        back.setFont(new Font("Times New Roman", Font.BOLD, 20));
        back.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.addMouseListener(this);

                    // Label For Return Id
        return_id.setBounds(7, 110, 100, 30);
        return_id.setFont(new Font("Times New Roman", Font.BOLD, 18));
        return_id.setForeground(Color.DARK_GRAY);

                 // Text Field For Return Id
        tf_return_id.setBounds(100, 110, 170, 25);
        tf_return_id.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_return_id.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tf_return_id.setBackground(Color.LIGHT_GRAY);
        tf_return_id.addKeyListener(this);

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

                // Label For Charges
        charges.setBounds(7, 230, 90, 30);
        charges.setFont(new Font("Times New Roman", Font.BOLD, 20));
        charges.setForeground(Color.DARK_GRAY);

                // Text Field For Charges
        tf_charges.setBounds(100, 230, 170, 25);
        tf_charges.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
        tf_charges.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tf_charges.setBackground(Color.LIGHT_GRAY);
        tf_charges.setFocusable(false);

                 // Clear Button
        clear.setBounds(80, 290, 120, 35);
        clear.setFocusable(false);
        clear.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        clear.setFont(new Font("Times New Roman", Font.BOLD, 29));
        clear.setBackground(Color.GRAY);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        clear.addMouseListener(this);

                /* Right Side Panel */
        right.setBounds(282, 0, 495, 459);
        right.setBackground(Color.LIGHT_GRAY);
        right.setLayout(null);
        right.add(r_label);

        r_label.setBounds(110,18,310,50);
        r_label.setFont(new Font("times new roman",Font.BOLD,35));

                 // Initialize table with data model
        String[] columnNames = {"ID", "Member Name", "Book Name", "Late Days","Late Charges"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.BOLD, 12));
        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 450, 310);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        scrollPane.setAlignmentX(Font.CENTER_BASELINE);

        right.add(scrollPane);
    }


            /*Method For Table Database*/
    public void table_database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pass);
            Statement stm = con.createStatement();

            String qry = "select id , m_name,b_name,late_days from return_book";
            ResultSet rs = stm.executeQuery(qry);
            DefaultTableModel dtb = (DefaultTableModel) table.getModel();

            while (rs.next()) {
                String id = rs.getString(1);
                String mName = rs.getString(2);
                String bName = rs.getString(3);
                String late = rs.getString(4);
                int tem = (Integer.parseInt(late) * 50);
                String tem1 = Integer.toString(tem);
                String[] row = {id,mName, bName, late,tem1};
                dtb.addRow(row);
            }
            stm.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e2) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e2.getMessage());
        }
    }

                /*Method For Clear Dues*/
    private void handleReturnBook() {
        if (tf_return_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Return ID", "Message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pass);
            String qry = "select * from return_book where id = ?";
            PreparedStatement pps = con.prepareStatement(qry);
            pps.setString(1, tf_return_id.getText());

            ResultSet rs = pps.executeQuery();

            if(rs.next()){
                String deleteQry = "DELETE FROM return_book WHERE id = ?";
                PreparedStatement deletePps = con.prepareStatement(deleteQry);
                deletePps.setString(1, tf_return_id.getText());
                deletePps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dues Cleared Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Dues();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter a valid Issue ID", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
            pps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e2) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e2.getMessage());
        }
    }


            /*Action Listener Method*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
            new Main_Menu();
        } else if (e.getSource() == clear) {
            handleReturnBook();
        }
    }


            /*Key Listener Methods*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tf_return_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Both IDs", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, pass);
                    String qry = "select  m_name , b_name , late_days from return_book where id = ?";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setInt(1, Integer.parseInt(tf_return_id.getText()));


                    ResultSet rs = pps.executeQuery();
                    if (rs.next()) {
                        tf_m_name.setText(rs.getString("m_name"));
                        tf_book_name.setText(rs.getString("b_name"));
                        int tem = (rs.getInt("late_days") * 50);
                        tf_charges.setText(Integer.toString(tem));
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

            /*Mouse Listener Method*/
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
        else if (e.getSource() == clear){
            clear.setBackground(Color.BLACK);
            clear.setForeground(Color.orange);
            clear.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            clear.setSize(110,25);
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
        else if (e.getSource() == clear){
            clear.setBackground(Color.DARK_GRAY);
            clear.setForeground(Color.white);
            clear.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            clear.setSize(120,30);
        }

    }
}
