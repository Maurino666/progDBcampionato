import java.sql.*;
import java.util.*;

public class DBmanager {
    
    private Connection con = null;

    public DBmanager(Connection con){
        this.con = con;
    }

    public List<Map<String, Object>> runQuery(String query){
        
        Statement statement = null;
        ResultSet result = null;
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        try{
            statement = con.createStatement();
            result = statement.executeQuery(query);

            ResultSetMetaData metaData = result.getMetaData();

            int count = metaData.getColumnCount();

            while(result.next()){
                Map<String, Object> record = new LinkedHashMap<>();
                
                for(int i = 1; i <= count; i++){
                    String columName = metaData.getColumnName(i);
                    Object value = result.getObject(i);
                    
                    record.put(columName, value);
                }
                resultList.add(record);
            }
        }catch(Exception e){
            e.printStackTrace();
            resultList = null;
        }finally{
            
            try{
                if(statement != null) statement.close();
                if(result != null) result.close();

            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        return resultList;
    }

    public int runUpdate(String query){
        
        Statement statement = null;
        int result;

        try{
            statement = con.createStatement();
            result = statement.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
            result = -1;
        }finally{
            try{
                if(statement != null) statement.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

}
