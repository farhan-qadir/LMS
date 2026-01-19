import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login_page extends JFrame implements ActionListener , MouseListener {

    ImageIcon l_icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\L_page1.png");
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
             // Login Panel
    JPanel l_panel = new JPanel();
    JLabel label = new JLabel();
            //Login Page Title
    JLabel title = new JLabel();
    JLabel title1 = new JLabel();
            //Button & TextField Panel
    JPanel b_panel = new JPanel();
    JTextField u_name = new JTextField("enter user name");
    JPasswordField pass1 = new JPasswordField("enter password");
    JLabel msg_label = new JLabel("Welcome");
    JLabel msg_label1 = new JLabel("Please enter Login Details Below");
    JButton submit = new JButton("Submit");

    private String uname = "daani";
    private String pas = "sara";

    Login_page(){
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setTitle("Library Management System");
        this.setBounds(260,140,775,459);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.add(l_panel);
        this.addMouseListener(this);


                    /* BackGround Image*/
        l_panel.setSize(new Dimension(770,459));
        l_panel.setBackground(Color.CYAN);
        l_panel.setLayout(null);
        l_panel.add(title);
        l_panel.add(title1);
        l_panel.add(b_panel);
        l_panel.add(label);


        label.setIcon(l_icon);
        label.setBounds(0,0,770,459);


                /* Title Labels */
        title.setBounds(20,0,500,100);
        title.setText("Library Management");
        title.setFont(new Font("Times New Roman",Font.BOLD,50));
        title.setForeground(Color.white);
        title.addMouseListener(this);


        title1.setBounds(150,50,500,100);
        title1.setText("System");
        title1.setFont(new Font("Times New Roman",Font.BOLD,50));
        title1.setForeground(Color.white);
        title1.addMouseListener(this);


                /* Button Panel */
        b_panel.setBounds(355,160,400,140);
        b_panel.setBackground(Color.white);
        b_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,false));
        b_panel.setLayout(null);
        b_panel.add(msg_label);
        b_panel.add(msg_label1);
        b_panel.add(u_name);
        b_panel.add(pass1);
        b_panel.add(submit);


        msg_label.setBounds(5,0,120,35);
        msg_label.setFont(new Font("Times New Roman",Font.BOLD,30));


        //msg_label1.setBorder(BorderFactory.createLineBorder(Color.BLUE,2,false));
        msg_label1.setBounds(10,21,250,30);
        msg_label1.setFont(new Font("Times New Roman",Font.PLAIN,17));

        u_name.setBounds(80,51,270,25);
        u_name.setBorder(BorderFactory.createLineBorder(Color.black,2,false));
        u_name.setFont(new Font("Times New Roman",Font.PLAIN,17));
        u_name.setBackground(null);
        u_name.addMouseListener(this);

        pass1.setBounds(80,81,270,25);
        pass1.setBorder(BorderFactory.createLineBorder(Color.black,2,false));
        pass1.setFont(new Font("Times New Roman",Font.PLAIN,17));
        pass1.setBackground(null);
        pass1.addMouseListener(this);



        submit.setBounds(145,111,130,22);
        submit.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        submit.setFont(new Font("Times New Roman",Font.BOLD,17));
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.white);
        submit.setFocusable(false);
        submit.addActionListener(this);
        submit.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit) {
            String tem = new String(pass1.getPassword());
            if (tem.equals(pas) && u_name.getText().equals(uname)){
                this.dispose();
                new Main_Menu();
            }
            else
                JOptionPane.showMessageDialog(null,"invalid username or password","Message",JOptionPane.ERROR_MESSAGE);

        }


    }
                /* Mouse Listener Methods */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == u_name){
            u_name.setText(null);
        }
        else if (e.getSource() == pass1){
            pass1.setText(null);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource()== submit){
            submit.setForeground(Color.orange);
            submit.setBackground(Color.BLACK);
            submit.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,false));
            submit.setSize(120,22);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getSource()== submit){
            submit.setBackground(Color.BLUE);
            submit.setForeground(Color.white);
            submit.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            submit.setSize(130,22);
        }

        if (e.getSource() == u_name){
            if (u_name.getText().isEmpty()) {
                u_name.setText("enter user name");
            }
        }

        if (e.getSource() == pass1){
            if (pass1.getPassword().length==0) {
                pass1.setText("enter password");
            }
        }

    }


}
