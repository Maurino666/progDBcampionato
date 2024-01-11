import java.sql.*;
public class Main{
    public static void main(String[] args){

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/campionato";
            String username = "root"; 
            String pwd = "root";
           
            con = DriverManager.getConnection(url,username,pwd);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Connessione fallita");
        }

        OperationManager op = new OperationManager(con);

        BtnAction.operationManager = op;

        MainWindow mainWindow = new MainWindow("Scegli operazione");

    
    }
}