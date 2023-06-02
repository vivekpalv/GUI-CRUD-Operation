import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Information information=new Information();
        JFrame jFrame=new JFrame();
        JTextField jtn= new JTextField();
        JTextField jtnt= new JTextField();
        JTextField jtq= new JTextField();
        JTextField jta= new JTextField();
        JTextArea jtar=new JTextArea();
        JFileChooser jfc=new JFileChooser("C:\\Users\\VIVEK PAL");
        JButton button=new JButton("OK");
        JButton button1=new JButton("Upload");
        JLabel label=new JLabel("Name");
        JLabel label1=new JLabel("NameType");
        JLabel label2=new JLabel("Quantity");
        JLabel label3=new JLabel("Amount");
        JLabel label4=new JLabel("File");


        jtn.setBounds(150,10,100,30);
        jtnt.setBounds(150,50,100,30);
        jtq.setBounds(150,90,100,30);
        jta.setBounds(150,130,100,30);
        button.setBounds(150,250,100,30);
        label.setBounds(50,10,100,30);
        label1.setBounds(50,50,100,30);
        label2.setBounds(50,90,100,30);
        label3.setBounds(50,130,100,30);
        label4.setBounds(50,170,100,30);
        jtar.setBounds(150,170,290,30);
        button1.setBounds(150,210,100,30);

        final FileInputStream[] fis = new FileInputStream[1];
        final File[] file = {null};

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/projectfetch","root","vivek");
        final PreparedStatement[] statement = {null};

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int vi = jfc.showDialog(null, "Confirm");
                if (vi==JFileChooser.APPROVE_OPTION){
                    File f=jfc.getSelectedFile();
                    file[0] =f;
                    String path = f.getPath();
                    try {
                        FileReader fr = new FileReader(path);
                        fis[0] =new FileInputStream(f);
                        System.out.println(fis[0]);
                        BufferedReader br=new BufferedReader(fr);
                        String str;
                        String str1="";
                        while ((str=br.readLine())!=null){
                            str1+=str;
                        }
                        jtar.setText(path);
                        jtar.setLineWrap(true);
                        br.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(jFrame, "Are you Sure", "ReadMe", JOptionPane.INFORMATION_MESSAGE);
                if (i==JOptionPane.YES_NO_OPTION){
                String name = jtn.getText();
                String nameType = jtnt.getText();
                String quant = jtq.getText();
                String amut = jta.getText();
                information.setQuantity(Integer.valueOf(quant));
                information.setName(name);
                information.setAmount((float) Integer.parseInt(amut));
                System.out.println(information.getAmount());
                information.setNameType(nameType);
                try {
                    statement[0] = connection.prepareStatement("insert into controll(name,nametype,quantity,amount,File) values(?,?,?,?,?)");
                    statement[0].setString(1,information.getName());
                    statement[0].setString(2,information.getNameType());
                    statement[0].setInt(3,information.getQuantity());
                    statement[0].setFloat(4,information.getAmount());
                    statement[0].setBinaryStream(5,fis[0],file[0].length());
                    statement[0].execute();
                    JOptionPane.showMessageDialog(jFrame,"Your records are successfully upload in database");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }}else if (i==JOptionPane.NO_OPTION)System.out.println("clicked on No option");
                else System.out.println("clicked on Cancel/cut from window of confirmdialogbox");
            }
        });
        jFrame.add(label);
        jFrame.add(label1);
        jFrame.add(label2);
        jFrame.add(label3);
        jFrame.add(label4);
        jFrame.add(jtn);
        jFrame.add(jtnt);
        jFrame.add(jtq);
        jFrame.add(jta);
        jFrame.add(button);
        jFrame.add(jtar);
        jFrame.add(button1);
        jFrame.setSize(400,330);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

    }
}