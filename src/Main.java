import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void home(String usrname){
        String arr[]={"Images/IMG1.jpeg","Images/IMG2.jpeg","Images/IMG3.jpeg","Images/IMG4.jpeg"};
        JFrame f2 = new JFrame();
        JLabel l = new JLabel("Welcome to Storybook Corner");
        JButton probtn=new JButton(usrname);
        probtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        probtn.setBounds(1405,50,130,50);
        probtn.setBackground(Color.lightGray);
        probtn.setFont(new Font("TimesRoman",Font.BOLD,20));
        probtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame f=new JFrame();
                f.setLayout(null);
                JLabel profile=new JLabel("Edit Profile");
                profile.setBounds(155,50,100,20);
                profile.setFont(new Font("Courier",Font.BOLD,15));
                JLabel fname = new JLabel("FullName:");
                fname.setBounds(90, 100, 80, 20);
                JTextField tf1 = new JTextField();
                tf1.setBounds(160,100,120,20);
                JLabel age=new JLabel("Age:");
                age.setBounds(90,130,80,20);
                JTextField tf2=new JTextField();
                tf2.setBounds(160,130,120,20);
                JLabel gender=new JLabel("Gender:");
                gender.setBounds(90,160,120,20);
                JTextField tf3=new JTextField();
                tf3.setBounds(160,160,120,20);
                JLabel phno=new JLabel("Phone No.:");
                phno.setBounds(90,190,120,20);
                JTextField tf4=new JTextField();
                tf4.setBounds(160,190,120,20);
                JLabel address=new JLabel("Address:");
                address.setBounds(90,220,120,20);
                JTextArea ta=new JTextArea();
                ta.setBounds(160,220,120,35);
                f.add(address);
                f.add(ta);
                f.add(tf4);
                f.add(phno);
                f.add(tf3);
                f.add(gender);
                f.add(tf2);
                f.add(age);
                JButton update=new JButton("Update");
                update.setBounds(145,280,120,25);
                update.setCursor(new Cursor(Cursor.HAND_CURSOR));
                f.add(update);
                JButton logout=new JButton("LogOut");
                logout.setBounds(270,320,100,25);
                logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
                logout.setBackground(Color.YELLOW);
                logout.setForeground(Color.BLUE);
                logout.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        login();
                        f.dispose();
                        f2.dispose();
                    }
                });
                f.add(logout);
                f.setBounds(600,200,400,400);
                update.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                            PreparedStatement ps=con.prepareStatement("update registerusr set fullname=?,age=?,gender=?,phoneno=?,address=? where username=?");
                            ps.setString(1,tf1.getText());
                            ps.setString(2,tf2.getText());
                            ps.setString(3,tf3.getText());
                            ps.setString(4,tf4.getText());
                            ps.setString(5,ta.getText());
                            ps.setString(6,usrname);
                            ps.executeUpdate();
                            JFrame f1=new JFrame();
                            JOptionPane.showMessageDialog(f1,"Details updated successfully");
                            f.dispose();

                        }
                        catch (Exception e){

                        }
                    }
                });
                try {

                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                    Statement stm=con.createStatement();
                    ResultSet rs=stm.executeQuery("select * from registerusr where username='" +usrname+"'");
                    if (rs.next()) {
                        String username = rs.getString(2);
                        int  agee=rs.getInt(3);
                        String gen=rs.getString(6);
                        String phn=rs.getString(7);
                        String add=rs.getString(5);
                        tf2.setText(String.valueOf(agee));
                        tf3.setText(gen);
                        tf4.setText(phn);
                        ta.setText(add);
                        tf1.setText(username);
                    }

                }
                catch (Exception exx){

                }

                f.add(tf1);
                f.add(profile);
                f.setSize(400,400);
                f.add(fname);
                f.setVisible(true);

            }
        });
        f2.add(probtn);
        l.setBounds(520,50,520,45);
        l.setFont(new Font("TimesRoman",Font.BOLD,35));
        l.setForeground(Color.RED);
        f2.add(l);
        try{
            f2.setLayout(null);
            BufferedImage image = ImageIO.read(new File(arr[0]));
            JLabel label=new JLabel(new ImageIcon(image));
            label.setBounds(60,200,300,380);
            JLabel price=new JLabel("Price: Rs 300");
            price.setBounds(125,600,300,20);
            price.setFont(new Font("Verdana",Font.BOLD,20));
            JLabel items=new JLabel("No of items:");
            items.setBounds(125,630,110,25);
            items.setFont(new Font("Verdana",Font.BOLD,15));
            JTextField tf=new JTextField();
            tf.setBounds(245,630,20,25);
            JButton b=new JButton("Buy Now");
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setBounds(145,680,100,35);
            b.setBackground(Color.GREEN);
            f2.add(b);
            f2.add(items);
            f2.add(tf);
            f2.add(price);
            f2.add(label);
            f2.pack();
            f2.setVisible(true);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                        PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
                        Statement stm=con.createStatement();
                        ResultSet rs=stm.executeQuery("select id from book");
                        int lastid=0;
                        int q=Integer.parseInt(tf.getText());

                        if(q>0 && q<=4) {


                            while (rs.next()) {
                                lastid = rs.getInt("id");
                            }
                            lastid++;
                            ps.setInt(1, lastid);
                            ps.setString(2, usrname);
                            ps.setString(3, "Believe in yourself");
                            ps.setInt(4, 2023);
                            ps.setString(5, arr[0]);
                            ps.setInt(6, 300);
                            ps.setInt(7, q);
                            ps.setInt(8, q * 300);
                            Date date = new Date();
                            String d=date.toString();
                            ps.setString(9,d);
                            ps.executeUpdate();
                            JFrame det=new JFrame();
                            JOptionPane.showMessageDialog(det,"Order Placed Successfully");




                        }
                        else if(q==0){
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Please enter atleast 1 quantity");
                        }
                        else{
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Maximum 4 quantity allowed");
                        }
                    }
                    catch (Exception ex){

                    }
                }
            });



        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            f2.setLayout(null);
            BufferedImage image = ImageIO.read(new File(arr[1]));
            JLabel label=new JLabel(new ImageIcon(image));
            label.setBounds(420,200,300,380);
            JLabel price=new JLabel("Price: Rs 350");
            price.setBounds(500,600,300,20);
            price.setFont(new Font("Verdana",Font.BOLD,20));
            JLabel items=new JLabel("No of items:");
            items.setBounds(500,630,110,25);
            items.setFont(new Font("Verdana",Font.BOLD,15));
            JTextField tf=new JTextField();
            tf.setBounds(615,630,20,25);
            JButton b=new JButton("Buy Now");
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setBounds(520,680,100,35);
            b.setBackground(Color.GREEN);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                        PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
                        Statement stm=con.createStatement();
                        ResultSet rs=stm.executeQuery("select id from book");
                        int lastid=0;
                        int q=Integer.parseInt(tf.getText());
                        if(q>0 && q<=4) {


                            while (rs.next()) {
                                lastid = rs.getInt("id");
                            }
                            lastid++;
                            ps.setInt(1, lastid);
                            ps.setString(2, usrname);
                            ps.setString(3, "Wings of Fire");
                            ps.setInt(4, 2021);
                            ps.setString(5, arr[1]);
                            ps.setInt(6, 350);
                            ps.setInt(7, q);
                            ps.setInt(8, q * 350);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();
                            String d=date.toString();
                            ps.setString(9,d);
                            ps.executeUpdate();
                            JFrame det=new JFrame();
                            JOptionPane.showMessageDialog(det,"Order Placed Successfully");

                        }
                        else if(q==0){
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Please enter atleast 1 quantity");
                        }
                        else{
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Maximum 4 quantity allowed");
                        }
                    }
                    catch (Exception ex){

                    }
                }
            });
            f2.add(b);
            f2.add(items);
            f2.add(tf);
            f2.add(price);
            f2.add(label);
            f2.pack();
            f2.setVisible(true);



        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            f2.setLayout(null);
            BufferedImage image = ImageIO.read(new File(arr[2]));
            JLabel label=new JLabel(new ImageIcon(image));
            label.setBounds(790,190,290,400);
            JLabel price=new JLabel("Price: Rs 99 (Special Offer)");
            price.setBounds(798,590,300,25);
            price.setFont(new Font("Verdana",Font.BOLD,18));
            JLabel items=new JLabel("No of items:");
            items.setBounds(865,630,110,25);
            items.setFont(new Font("Verdana",Font.BOLD,15));
            JTextField tf=new JTextField();
            tf.setBounds(985,630,20,25);
            JButton b=new JButton("Buy Now");
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setBounds(880,680,100,35);
            b.setBackground(Color.GREEN);
            f2.add(b);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                        PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
                        Statement stm=con.createStatement();
                        ResultSet rs=stm.executeQuery("select id from book");
                        int lastid=0;
                        int q=Integer.parseInt(tf.getText());
                        if(q>0 && q<=4) {


                            while (rs.next()) {
                                lastid = rs.getInt("id");
                            }
                            lastid++;
                            ps.setInt(1, lastid);
                            ps.setString(2, usrname);
                            ps.setString(3, "You Can");
                            ps.setInt(4, 2020);
                            ps.setString(5, arr[2]);
                            ps.setInt(6, 99);
                            ps.setInt(7, q);
                            ps.setInt(8, q * 99);
                            Date date = new Date();
                            String d=date.toString();
                            ps.setString(9,d);
                            ps.executeUpdate();
                            JFrame det=new JFrame();
                            JOptionPane.showMessageDialog(det,"Order Placed Successfully");

                        }
                        else if(q==0){
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Please enter atleast 1 quantity");
                        }
                        else{
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Maximum 4 quantity allowed");
                        }
                    }
                    catch (Exception ex){

                    }
                }
            });
            f2.add(items);
            f2.add(tf);
            f2.add(price);
            f2.add(label);
            f2.pack();
            f2.setVisible(true);



        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            f2.setLayout(null);
            BufferedImage image = ImageIO.read(new File(arr[3]));
            JLabel label=new JLabel(new ImageIcon(image));
            label.setBounds(1160,215,320,354);
            JLabel price=new JLabel("Price: Rs 450");
            price.setBounds(1240,600,300,20);
            price.setFont(new Font("Verdana",Font.BOLD,20));
            JLabel items=new JLabel("No of items:");
            items.setBounds(1250,630,110,25);
            items.setFont(new Font("Verdana",Font.BOLD,15));
            JTextField tf=new JTextField();
            tf.setBounds(1360,630,20,25);
            JButton b=new JButton("Buy Now");
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setBounds(1263,680,100,35);
            b.setBackground(Color.GREEN);
            f2.add(b);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                        PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
                        Statement stm=con.createStatement();
                        ResultSet rs=stm.executeQuery("select id from book");
                        int lastid=0;
                        int q=Integer.parseInt(tf.getText());
                        if(q>0 && q<=4) {


                            while (rs.next()) {
                                lastid = rs.getInt("id");
                            }
                            lastid++;
                            ps.setInt(1, lastid);
                            ps.setString(2, usrname);
                            ps.setString(3, "The Blue Umbrella");
                            ps.setInt(4, 2023);
                            ps.setString(5, arr[3]);
                            ps.setInt(6, 450);
                            ps.setInt(7, q);
                            ps.setInt(8, q * 450);
                            Date date = new Date();
                            String d=date.toString();
                            ps.setString(9,d);
                            ps.executeUpdate();
                            JFrame det=new JFrame();
                            JOptionPane.showMessageDialog(det,"Order Placed Successfully");
                        }
                        else if(q==0){
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Please enter atleast 1 quantity");
                        }
                        else{
                            JFrame f=new JFrame();
                            JOptionPane.showMessageDialog(f,"Maximum 4 quantity allowed");
                        }
                    }
                    catch (Exception ex){

                    }
                }
            });
            f2.add(items);
            f2.add(tf);
            f2.add(price);
            f2.add(label);
            f2.pack();
            f2.setVisible(true);



        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        f2.setSize(1700, 1000);
        f2.setLayout(null);
        f2.setVisible(true);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the frame properly

    }

    public static void register(){
        JFrame f=new JFrame();
        f.setLayout(null);
        JLabel prdet=new JLabel("Personal Details");
        prdet.setBounds(135,30,150,25);
        prdet.setFont(new Font("TimesRoman",Font.BOLD,18));
        JLabel username=new JLabel("UserName:");
        JTextField tf1=new JTextField();
        username.setBounds(90,80,70,20);
        tf1.setBounds(160,80,120,20);
        f.add(username);
        f.add(tf1);
        JLabel fname=new JLabel("FullName:");
        JTextField tf2=new JTextField();
        fname.setBounds(90,130,70,20);
        tf2.setBounds(160,130,120,20);
        f.add(fname);
        f.add(tf2);
        JLabel age=new JLabel("Age:");
        age.setBounds(90,180,30,20);
        JTextField tf5=new JTextField();
        tf5.setBounds(160,180,65,20);
        f.add(tf5);
        f.add(age);
        JLabel gender=new JLabel("Gender:");
        gender.setBounds(90,230,70,20);
        JTextField tf6=new JTextField("M/F");
        tf6.setBounds(160,230,28,20);
        f.add(tf6);
        f.add(gender);
        JLabel passwd=new JLabel("Password:");
        JPasswordField tf3=new JPasswordField();
        passwd.setBounds(90,280,70,20);
        tf3.setBounds(160,280,120,20);
        f.add(passwd);
        f.add(tf3);
        JLabel confpass=new JLabel("CfmPwd:");
        JPasswordField tf4=new JPasswordField();
        confpass.setBounds(90,330,100,20);
        tf4.setBounds(160,330,120,20);
        JLabel address=new JLabel("Address:");
        JTextArea ta=new JTextArea();
        address.setBounds(90,380,120,20);
        ta.setBounds(160,380,120,30);
        f.add(address);
        f.add(ta);
        JLabel phone=new JLabel("Phone No:");
        phone.setBounds(90,430,120,20);
        JTextField tf7=new JTextField("+91");
        tf7.setBounds(160,430,120,30);
        f.add(phone);
        f.add(tf7);
        JButton register=new JButton("REGISTER");
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.setBounds(150,480,100,30);
        register.setBackground(Color.GREEN);
        JButton login=new JButton("Login");
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setBounds(260,530,100,30);
        f.add(login);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
                f.dispose();
            }
        });

        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String user=tf1.getText();
                String fullname=tf2.getText();
                char[] pwdd=tf3.getPassword();
                char[] cpwd=tf4.getPassword();
                String gdr=tf6.getText();
                String ads=ta.getText();
                String agee=tf5.getText();
                String phoneno=tf7.getText();

//                if (user.length()==0 || fullname.length()==0 || pwdd.length==0 || cpwd.length==0 || gdr.length()==0 || ads.length()==0 || agee.length()==0 || phoneno.length()==0){
//                    JFrame f=new JFrame();
//                    JOptionPane.showMessageDialog(f,"Enter all details");
//                    return;
//                }
                try{
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/storybook_corner","root","root123");
                    Statement stm=con.createStatement();
//                    ResultSet rs=stm.executeQuery("select * from registerusr");
                    ResultSet rs1=stm.executeQuery("select * from loginusr");
                    boolean flg=false;
                    boolean chk=false;
                    while(rs1.next()){
                        if(rs1.getString(2).equals(tf1.getText())){
                            flg=true;
                            break;
                        }
                    }
                    if(flg){
                        JFrame f1=new JFrame();
                        JOptionPane.showMessageDialog(f1,"This user name already exists");
                        tf1.setText(null);

                    }
                    else {
                        boolean smalllet = false;
                        boolean capitallet = false;
                        boolean digit = false;
                        boolean speciallet = false;
                        char[] pwd = tf3.getPassword();
                        if (pwd.length != 0) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < pwd.length; i++) {
                                sb.append(pwd[i]);
                            }
                            String password = sb.toString();
                            for (int j = 0; j < password.length(); j++) {
                                if (password.charAt(j) >= 'a' && password.charAt(j) <= 'z') {
                                    smalllet = true;
                                } else if (password.charAt(j) >= 'A' && password.charAt(j) <= 'Z') {
                                    capitallet = true;
                                } else if (password.charAt(j) - 48 >= 0 && password.charAt(j) - 48 <= 9) {
                                    digit = true;
                                } else {
                                    speciallet = true;
                                }
                            }
                            if (smalllet == false) {
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Atleast one small letter should be included");
                                tf3.setText(null);
                                tf4.setText(null);
                            }
                            if (capitallet == false) {
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Atleast one capital letter should be included");
                                tf3.setText(null);
                                tf4.setText(null);
                            }
                            if (digit == false) {
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Atleast one digit letter should be included");
                                tf3.setText(null);
                                tf4.setText(null);
                            }
                            if (speciallet == false) {
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f, "Atleast one special letter should be included");
                                tf3.setText(null);
                                tf4.setText(null);
                            }

                            if (tf3.getPassword().length != 0 && tf4.getPassword().length != 0 && Arrays.equals(tf3.getPassword(), tf4.getPassword())) {
                                PreparedStatement stmt = con.prepareStatement("insert into registerusr values(?,?,?,?,?,?,?)");
                                stmt.setString(1, tf1.getText());
                                stmt.setString(2, tf2.getText());
                                stmt.setString(3, tf5.getText());
                                stmt.setString(4, password);
                                stmt.setString(5, ta.getText());
                                stmt.setString(6,tf6.getText());
                                stmt.setString(7,tf7.getText());
                                stmt.executeUpdate();
                                PreparedStatement stment = con.prepareStatement("insert into loginusr values(?,?,?)");
                                Statement stmm = con.createStatement();
                                ResultSet rss = stmm.executeQuery("select id from loginusr");
                                int lastID = 0;
                                while (rss.next()) {
                                    lastID = rss.getInt("id");
                                }
                                lastID++;
                                stment.setInt(1, lastID);
                                stment.setString(2, tf1.getText());
                                stment.setString(3, password);
                                stment.executeUpdate();
                                login();
                                chk = true;
                                f.dispose();

                            }
                            if (chk == false) {
                                JFrame f2 = new JFrame();
                                JOptionPane.showMessageDialog(f2, "Check password");
                                tf3.setText(null);
                                tf4.setText(null);

                            }
                        }
                    }
                }
                catch (Exception ex){

                }
            }
        });
        f.setBounds(300,300,400,480);
        f.add(register);
        f.add(confpass);
        f.add(tf4);
        f.add(prdet);
        f.setLocationRelativeTo(null);
        f.setSize(400,610);
        f.setVisible(true);





    }
    public static void login(){
        JFrame f=new JFrame();
        JLabel heading=new JLabel("LOGIN PAGE");
        heading.setBounds(100,10,100,40);
        heading.setFont(new Font("Verdana", Font.BOLD, 12));
        f.add(heading);
        JLabel l1=new JLabel("Username:");
        l1.setBounds(40,50,100,30);
        JLabel l2=new JLabel("Password:");
        l2.setBounds(40,100,100,30);
        f.add(l1);f.add(l2);
        f.setSize(300,300);
        JTextField tf=new JTextField();
        tf.setBounds(120,55,130,25);
        f.add(tf);
        JPasswordField pf=new JPasswordField();
        pf.setBounds(120,105,130,25);
        f.add(pf);
        JButton b1=new JButton("LOGIN");
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFont(new Font("Font.TimesRoman", Font.BOLD,12));
        b1.setBounds(40,155,100,30);
        b1.setBackground(Color.GREEN);
        String db_url="jdbc:mysql://localhost:3306/storybook_corner";
        String db_name="root";
        String db_pass="root123";
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Connection con=DriverManager.getConnection(db_url,db_name,db_pass);
                    Statement stm=con.createStatement();
                    ResultSet rs=stm.executeQuery("select * from loginusr");
                    String usrname_entered=tf.getText();
                    char[] pass=pf.getPassword();
                    boolean flg=false;
                    while(rs.next()){
                        if(usrname_entered.equals(rs.getString(2)) && Arrays.equals(pass,rs.getString(3).toCharArray())){
                            flg=true;
                            break;
                        }

                    }
                    if(flg){
                        home(usrname_entered);
                        f.dispose();
                    }
                    else{
                        JFrame f1=new JFrame();
                        JOptionPane.showMessageDialog(f1,"Incorrect username or password");

                    }

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        JButton b2=new JButton("RESET");
        b2.setFont(new Font("Font.TimesRoman",Font.BOLD,12));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setBounds(150,155,100,30);
        b2.setBackground(Color.YELLOW);

        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf.setText(null);
                pf.setText(null);
            }
        });
        JButton register=new JButton("Register Now");
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.setBounds(170,200,111,30);
        f.add(register);
        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                register();
                f.dispose();

            }
        });
        f.add(b1);
        f.add(b2);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);

    }
    public static void main(String[] args) {
//        home("chirag1");
//          register();
//        home("chirag1");
        register();


    }
}
