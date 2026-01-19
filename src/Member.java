import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Member extends JFrame implements ActionListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    // Left Panel
    JPanel left = new JPanel();
    JButton add = new JButton("Add Member");
    JButton del = new JButton("Delete Member");
    JButton back = new JButton("Back");

            // Right Panel
    JPanel right = new JPanel();
    JLabel r_label = new JLabel("Members");
    JTable table;
    JScrollPane scrollPane;

         /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

    public Member() {
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
        left.add(add);
        left.add(del);
        left.add(back);

                 /* Left Side Buttons */
        add.setBounds(70, 120, 130, 35);
        add.setFocusable(false);
        add.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        add.setBackground(Color.DARK_GRAY);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add.addMouseListener(this);

        del.setBounds(70, 200, 130, 35);
        del.setFocusable(false);
        del.setFont(new Font("Times New Roman", Font.BOLD, 17));
        del.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        del.setBackground(Color.DARK_GRAY);
        del.setForeground(Color.white);
        del.addActionListener(this);
        del.addMouseListener(this);

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
        right.setBackground(Color.lightGray);
        right.setLayout(null);
        right.add(r_label);

        r_label.setBounds(155,18,210,40);
        r_label.setFont(new Font("times new roman",Font.BOLD,50));

                    // Initialize table with data model
        String[] columnNames = {"ID", "Name", "City", "House No","Phone No"};
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

            String qry = "select * from member order by name asc";
            ResultSet rs = stm.executeQuery(qry);
            DefaultTableModel dtb = (DefaultTableModel) table.getModel();

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String city= rs.getString(3);
                String house_no = rs.getString(4);
                String phone_no = rs.getString(5);
                String[] row = {id, name, city, house_no,phone_no};
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
        if (e.getSource() == add){
            this.dispose();
            new AddMember();
        }
        if (e.getSource() == del){
            this.dispose();
            new DelMember();

        }
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
        if (e.getSource() == back){
            back.setBackground(Color.BLACK);
            back.setForeground(Color.orange);
            back.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            back.setSize(80,25);
        }
        else if (e.getSource()== add){
            add.setBackground(Color.BLACK);
            add.setForeground(Color.orange);
            add.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            add.setSize(120,30);
        }
        else if (e.getSource()== del){
            del.setBackground(Color.BLACK);
            del.setForeground(Color.orange);
            del.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            del.setSize(120,30);
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
        else if (e.getSource() == add){
            add.setBackground(Color.DARK_GRAY);
            add.setForeground(Color.WHITE);
            add.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            add.setSize(130,35);
        }
        else if (e.getSource() == del){
            del.setBackground(Color.DARK_GRAY);
            del.setForeground(Color.WHITE);
            del.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            del.setSize(130,35);
        }

    }
}
