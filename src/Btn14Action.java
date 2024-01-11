import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Btn14Action extends BtnAction{

    public Btn14Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
    }

    public void showAction(){
        List<Map<String, Object>> resultTable = null;
        resultTable = operationManager.getClassificaMotoreAsp();

        try{
            JOptionPane.showMessageDialog(parentPanel, ActionPanel.getNewScrollTable(resultTable), "Classifica motori aspirato", JOptionPane.PLAIN_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(parentPanel, "L'interrogazione al database non ha dato risultati", "Classifica motori aspirato", JOptionPane.PLAIN_MESSAGE);
        }

        resultTable = operationManager.getClassificaMotoreTurbo();

        try{
            JOptionPane.showMessageDialog(parentPanel, ActionPanel.getNewScrollTable(resultTable), "Classifica motori turbo", JOptionPane.PLAIN_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(parentPanel, "L'interrogazione al database non ha dato risultati", "Classifica motori turbo", JOptionPane.PLAIN_MESSAGE);
        }
    }

}