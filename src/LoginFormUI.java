import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFormUI extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JPanel RegisterPanel;

    public LoginFormUI (){
        super();
        setTitle("Register");
        setContentPane(RegisterPanel);
        setSize(450, 475);
        setLocationRelativeTo(null);    /* Localiza a janel ao centro */
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                String email = textField2.getText();

                makeConnection(name, email);
            }
        });
    }

    public void makeConnection(String name, String email) {
        String url = "jdbc:postgresql://localhost:5432/usuario";
        String usuario = "postgres";
        String senha = "123";
        String driver = "org.postgresql.Driver";

        try{
            Class.forName(driver);
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
//            Statement statement = conexao.createStatement();

            String query = "INSERT INTO usuario VALUES(?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            preparedStatement.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
