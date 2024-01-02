import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class WindowTest {
    
    public static void main(String[] args) {
        // Creare una lista di mappe di esempio
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("Nome", "Alice");
        map1.put("Età", 25);
        map1.put("Città", "Roma");

        Map<String, Object> map2 = new LinkedHashMap<>();
        map2.put("Nome", "Bob");
        map2.put("Età", 30);
        map2.put("Città", "Milano");

        Map<String, Object> map3 = new LinkedHashMap<>();
        map3.put("Nome", "Charlie");
        map3.put("Età", 22);
        map3.put("Città", "Napoli");

        resultList.add(map1);
        resultList.add(map2);
        resultList.add(map3);

        // Testare il metodo resultToMatrix
       
        JScrollPane scroll = ActionPanel.getNewScrollTable(resultList);
        
   
        // Stampare la matrice risultante
        JOptionPane.showMessageDialog(null, scroll, null, 0);
    }
}
