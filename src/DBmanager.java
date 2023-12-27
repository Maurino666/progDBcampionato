import java.sql.*;

public class DBmanager {
    
    private Connection con = null;

    public DBmanager(Connection con){
        this.con = con;
    }

    public void operazione1(String nome, String paese){
        try {
            Statement ins = con.createStatement();
            ins.executeUpdate("INSERT INTO scuderia(nome, paese) VALUES ('"+ nome +"', '" + paese +"')");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Errore nell'inserimento");
        }   
    }
}
