import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main_Menu extends JFrame implements ActionListener , MouseListener {
    ImageIcon icon = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\library-icon-png-5.jpg");
    ImageIcon img = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\m_page1.png");
    ImageIcon l_img = new ImageIcon("C:\\Users\\faru\\IdeaProjects\\LMS\\src\\m_left1.png");
            //Panel For Buttons
    JPanel panel = new JPanel();
    JButton books = new JButton("Books");
    JButton author = new JButton("Author");
    JButton member = new JButton("Members");
    JButton issue_book = new JButton("Issue Book");
    JButton return_book = new JButton("Return Book");
    JButton dues = new JButton("Dues");
    JButton logout = new JButton("Logout");
                //Center Image PAnel
    JPanel img_panel = new JPanel();
    JLabel label = new JLabel();
                //Left Side Panel
    JPanel left = new JPanel();
    JLabel l_label = new JLabel();

                //Right Side PAnel
    JPanel right = new JPanel();


    Main_Menu(){
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setTitle("Library Management System");
        this.setBounds(260,140,775,459);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.add(panel);


                    /*Main Panel*/
        panel.setSize(new Dimension(775,459));
        panel.setBackground(Color.black);
        panel.setLayout(null);
        panel.add(img_panel);
        panel.add(left);
        panel.add(right);


                /* Center Image Panel */
        img_panel.setBounds(225,0,300,459);
        img_panel.setBackground(Color.WHITE);
        img_panel.setLayout(null);
        img_panel.add(label);


        label.setBounds(0,0,300,459);
        label.setIcon(img);


                     /* Left Side Panel */
        left.setBounds(0,0,222,454);
        left.setBackground(Color.white);
        left.setLayout(null);
        left.add(l_label);


        l_label.setBounds(0,0,222,454);
        l_label.setIcon(l_img);
        left.add(l_label);
        l_label.add(books);
        l_label.add(author);
        l_label.add(member);


                     /* Right Side Panel */
        right.setBounds(529,0,252,454);
        right.setBackground(Color.white);
        right.setLayout(null);
        right.add(issue_book);
        right.add(return_book);
        right.add(dues);
        right.add(logout);

                    /* Left Side Buttons */
        books.setBounds(55,80,120,30);
        books.setFocusable(false);
        books.setFont(new Font("Times New Roman",Font.BOLD,20));
        books.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        books.setBackground(Color.gray);
        books.setForeground(Color.cyan);
        books.addActionListener(this);
        books.addMouseListener(this);

        author.setBounds(55,140,120,30);
        author.setFocusable(false);
        author.setFont(new Font("Times New Roman",Font.BOLD,20));
        author.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        author.setBackground(Color.gray);
        author.setForeground(Color.cyan);
        author.addActionListener(this);
        author.addMouseListener(this);

        member.setBounds(55,200,120,30);
        member.setFocusable(false);
        member.setFont(new Font("Times New Roman",Font.BOLD,20));
        member.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        member.setBackground(Color.gray);
        member.setForeground(Color.cyan);
        member.addActionListener(this);
        member.addMouseListener(this);



                     /* Right Side Buttons */
        issue_book.setBounds(55,80,120,30);
        issue_book.setFocusable(false);
        issue_book.setFont(new Font("Times New Roman",Font.BOLD,16));
        issue_book.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        issue_book.setBackground(Color.lightGray);
        issue_book.setForeground(Color.black);
        issue_book.addActionListener(this);
        issue_book.addMouseListener(this);

        return_book.setBounds(55,140,120,30);
        return_book.setFocusable(false);
        return_book.setFont(new Font("Times New Roman",Font.BOLD,15));
        return_book.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        return_book.setBackground(Color.LIGHT_GRAY);
        return_book.setForeground(Color.black);
        return_book.addActionListener(this);
        return_book.addMouseListener(this);

        dues.setBounds(55,200,120,30);
        dues.setFocusable(false);
        dues.setFont(new Font("Times New Roman",Font.BOLD,22));
        dues.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        dues.setBackground(Color.LIGHT_GRAY);
        dues.setForeground(Color.black);
        dues.addActionListener(this);
        dues.addMouseListener(this);


        logout.setBounds(145,385,80,27);
        logout.setFocusable(false);
        logout.setFont(new Font("Times New Roman",Font.BOLD,20));
        logout.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        logout.setBackground(Color.lightGray);
        logout.setForeground(Color.BLACK);
        logout.addActionListener(this);
        logout.addMouseListener(this);

    }

                /*ActionListener Methods*/
    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource()==logout){
                this.dispose();
                new Login_page();
            }
            else if (e.getSource() == books){
                this.dispose();
                new Books();
            }
            else if (e.getSource() == author){
                this.dispose();
                new Author();

            }
            else if (e.getSource() == member){
                this.dispose();
                new Member();
            }
        else if (e.getSource() == issue_book){
            this.dispose();
            new IssueBook();
        }
        else if (e.getSource() == return_book){
            this.dispose();
            new ReturnBook();
        }
        else if (e.getSource() == dues) {
                this.dispose();
                new Dues();
        }

    }

                    /*Mouse Listener Methods*/
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

        if (e.getSource() == logout){
            logout.setBackground(Color.BLACK);
            logout.setForeground(Color.orange);
            logout.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            logout.setSize(70,22);
        }
        else if (e.getSource() == dues){
            dues.setBackground(Color.BLACK);
            dues.setForeground(Color.ORANGE);
            dues.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            dues.setSize(115,27);
        }
        else if (e.getSource() == issue_book){
            issue_book.setBackground(Color.BLACK);
            issue_book.setForeground(Color.orange);
            issue_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            issue_book.setSize(115,27);
        }
        else if (e.getSource() == return_book){
            return_book.setBackground(Color.BLACK);
            return_book.setForeground(Color.ORANGE);
            return_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,false));
            return_book.setSize(115,27);
        }
        else if (e.getSource() == member){
            member.setBackground(Color.BLACK);
            member.setForeground(Color.ORANGE);
            member.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            member.setSize(115,27);
        }
        else if (e.getSource() == author){
            author.setBackground(Color.BLACK);
            author.setForeground(Color.ORANGE);
            author.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            author.setSize(115,27);
        }
        else if (e.getSource() == books){
            books.setBackground(Color.BLACK);
            books.setForeground(Color.ORANGE);
            books.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            books.setSize(115,27);
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == logout){
            logout.setBackground(Color.LIGHT_GRAY);
            logout.setForeground(Color.BLACK);
            logout.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            logout.setSize(80,27);
        }
        else if (e.getSource() == dues){
            dues.setBackground(Color.LIGHT_GRAY);
            dues.setForeground(Color.BLACK);
            dues.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            dues.setSize(120,30);
        }
        else if (e.getSource() == issue_book){
            issue_book.setBackground(Color.LIGHT_GRAY);
            issue_book.setForeground(Color.BLACK);
            issue_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            issue_book.setSize(120,30);
        }
        else if (e.getSource() == return_book){
            return_book.setBackground(Color.LIGHT_GRAY);
            return_book.setForeground(Color.BLACK);
            return_book.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            return_book.setSize(120,30);
        }
        else if (e.getSource() == member){
            member.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
            member.setBackground(Color.gray);
            member.setForeground(Color.cyan);
            member.setSize(120,30);
        }
        else if (e.getSource() == author){
            author.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
            author.setBackground(Color.gray);
            author.setForeground(Color.cyan);
            author.setSize(120,30);
        }
        else if (e.getSource() == books){
            books.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
            books.setBackground(Color.gray);
            books.setForeground(Color.cyan);
            books.setSize(120,30);
        }

    }
}
