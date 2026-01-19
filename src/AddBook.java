import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


public class AddBook extends JFrame  implements ActionListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    Books obj = new Books();
    JButton add = new JButton("ADD");

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Add Book");

    JLabel name = new JLabel("Name");
    JTextField tf_name = new JTextField();

    JLabel author = new JLabel("Select Author");
    JComboBox  cb_author = new JComboBox();

    JLabel edition = new JLabel("Edition");
    JTextField tf_edi = new JTextField();

    JLabel pub = new JLabel("Published");
    JTextField tf_pub = new JTextField();


                /* Database Details */
    String url = "jdbc:mysql://127.0.0.1:3306/lms";
    String username = "root";
    String pass = "F@rhan123";

                /* Method for Database Entries in Combo Box*/
    public  void far(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,username,pass);
            Statement stm = con.createStatement();

            String qry = "select name from author order by name asc";
            ResultSet rs = stm.executeQuery(qry);
            String name;
            while (rs.next()){
                name = rs.getString("name");
                cb_author.addItem(name);
            }

        }
        catch (ClassNotFoundException | SQLException e ){
            System.out.println(e.getMessage());
        }

    }


    AddBook(){
        far();
                        /* Frame  */
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setTitle("Library Management System");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setBounds(480,170,350,400);
        this.getContentPane().setBackground(Color.BLACK);
        this.add(panel);

        panel.setBounds(0,0,350,400);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        panel.add(label);
        panel.add(name);
        panel.add(tf_name);
        panel.add(author);
        panel.add(cb_author);
        panel.add(edition);
        panel.add(tf_edi);
        panel.add(pub);
        panel.add(tf_pub);
        panel.add(add);

        label.setBounds(85,10,200,30);
        label.setFont(new Font("Times New Roman",Font.BOLD,40));

                            /* Name */
        name.setBounds(7,70,70,30);
        name.setFont(new Font("Times New Roman",Font.BOLD,20));

        tf_name.setBounds(140,70,170,25);
        tf_name.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_name.setFont(new Font("Times New Roman",Font.PLAIN,14));

                         /* Author */
        author.setBounds(7,110,105,30);
        author.setFont(new Font("Times New Roman",Font.BOLD,17));

        cb_author.setBounds(140,110,170,25);
        cb_author.setFocusable(false);
        cb_author.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));

                            /*Edition*/
        edition.setBounds(7,155,80,30);
        //edition.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        edition.setFont(new Font("Times New Roman",Font.BOLD,20));

        tf_edi.setBounds(140,155,170,25);
        tf_edi.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_edi.setFont(new Font("Times New Roman",Font.PLAIN,14));

                    /* Published */
        pub.setBounds(7,200,80,30);
       // pub.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        pub.setFont(new Font("Times New Roman",Font.BOLD,20));

        tf_pub.setBounds(140,200,170,25);
        tf_pub.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,false));
        tf_pub.setFont(new Font("Times New Roman",Font.PLAIN,14));

                        /* Add Button */
        add.setBounds(105,270,140,35);
        add.setFocusable(false);
        add.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        add.setFont(new Font("times new roman",Font.BOLD,27));
        add.setBackground(Color.GRAY);
        add.setForeground(Color.BLACK);
        add.addActionListener(this);
        add.addMouseListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add){
            if (tf_name.getText().isEmpty()||tf_pub.getText().isEmpty()||tf_edi.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Plz Fill All the Fields","Message",JOptionPane.PLAIN_MESSAGE);
            }
            else {
                            /* DataBase For Addition Of Book */
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection con = DriverManager.getConnection(url,username,pass);
                    String qry = "insert into  books(name , author , edition , publish) values (?,?,?,?)";
                    PreparedStatement pps = con.prepareStatement(qry);
                    pps.setString(1,tf_name.getText());
                    pps.setString(2, (String) cb_author.getSelectedItem());
                    pps.setString(3,tf_edi.getText());
                    pps.setString(4,tf_pub.getText());

                    pps.executeUpdate();


                }
                catch (ClassNotFoundException | SQLException e1 ){
                    System.out.println(e1.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Book Added Successfully", "Message", JOptionPane.PLAIN_MESSAGE);
                this.dispose();
                obj.dis();
                new Books();

            }


        }

    }

                /*Methods of MouseListener*/
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
