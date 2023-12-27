import java.sql.*;

public class DBmanager {
    
    private Connection con = null;

    public DBmanager(Connection con){
        this.con = con;
    }

    public void operazione1(String nome, String paese){
        try {
            Statement ins = con.createStatement();

            if(ins.executeUpdate("INSERT INTO scuderia(nome, paese) VALUES ('"+ nome +"', '" + paese +"')") != 1)
                throw new Exception("Errore inserimento");
        }
        catch (Exception e){
            System.out.println("Errore nell'inserimento");
        }   
    }
}
