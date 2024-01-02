import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class ActionPanel extends JPanel{
    
    public List<JComponent> panelInputs = new ArrayList<>(); 



    public static JScrollPane getNewScrollTable(List<Map<String, Object>> list){
        JTable table = getNewTable(list);
        JScrollPane  scroll = new JScrollPane(table);
        return scroll;
    };
    /**modo semplice per ottenere un JTextField personalizzato
     * passando una lista di mappe 
     * @return
     * JTable
     */
    public static JTable getNewTable(List<Map<String, Object>> list){
        Object[][] matrix = resultToMatrix(list);
        List<String> nameList = new ArrayList<>(list.get(0).keySet());
        Object[] nameArr = nameList.toArray();
      
        JTable result = new JTable(new javax.swing.table.DefaultTableModel(matrix, nameArr){@Override public boolean isCellEditable(int rowIndex, int mColIndex) { return false; }});
        return result;
    }

    public static Object[][] resultToMatrix(List<Map<String, Object>> list){
        List<String> keyList = new ArrayList<>(list.get(0).keySet());
        int sizeY = list.size();
        int sizeX = list.get(0).size();
        Object[][] matrix = new Object[sizeY][sizeX];
        for(int i = 0; i < sizeY; i++){
            Map<String, Object> map = list.get(i);
            int j = 0;
            for(String key : keyList){
                matrix[i][j] = map.get(key);
                j++;
            }
        }
        return matrix;
    }

    /**modo semplice per ottenere un JTextField personalizzato
     * @return
     * JTextField
     */
    public static JTextField getNewTextField(){
        JTextField result = new JTextField();
        result.setPreferredSize(new Dimension(300, 25));
        return result;
    }

    /**modo semplice per ottenere un JComboBox di stringhe personalizzato
     * @return
     * JComboBox<String>
     */
    public static JComboBox<String> getNewStringComboBox(String... options){
        JComboBox<String> result = new JComboBox<>(options);
        return result;
    }

    /**Chiede coppie nome-componente, che verranno organizzate
    * automaticamente nel pannello restituito. 
    * Qualora il numero di oggetti passati non sia pari genera un'eccezione.
    */
    public ActionPanel(Object... labelComponentPairs){
        super();

        /**Chiede coppie etichetta-componente.
         * Qualora il numero di oggetti passati non sia pari genera un'eccezione.
         */
        if (labelComponentPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Il numero di parametri deve essere pari");
        }

        //pannello delle label
        JPanel labelPanel = new JPanel(new GridLayout(labelComponentPairs.length /2, 1));
        //pannello dei fields
        JPanel fieldsPanel = new JPanel(new GridLayout(labelComponentPairs.length /2, 1));

        //aggiunta delle label e rispettivi componenti ai due pannelli
        for(int i = 0; i < labelComponentPairs.length; i += 2){
            labelPanel.add(new JLabel((String)labelComponentPairs[i], JLabel.RIGHT));
            fieldsPanel.add((Component) labelComponentPairs[i+1]);
            panelInputs.add((JComponent)labelComponentPairs[i+1]);
        }
        
        //inserimento in un pannello intermedio
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(labelPanel);
        middlePanel.add(fieldsPanel);

        //aggiunta al pannello principale
        this.setLayout(new FlowLayout());
        this.add(middlePanel);
    }
}
