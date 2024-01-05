import javax.swing.*;

import java.util.*;

public class BtnSelectAction extends BtnAction{
    JPanel mainJPanel;

    public BtnSelectAction(JPanel parentPanel){
        this.parentPanel = parentPanel;
    }

    public void showAction(int selectIndex){
        List<Map<String, Object>> resultTable = null;
        switch (selectIndex){
            //operazione 8
            case 0: 
                resultTable = operationManager.getFinanziamentiScuderie();
                break;
            //operazione 9
            case 1:
                resultTable = operationManager.getReportScuderie();
                break;
            //operazione 10
            case 2:
                resultTable = operationManager.getPilotiCasaVincenti();
                break;
            //operazione 11
            case 3:
                resultTable = operationManager.getPercentualeGD();
                break;
            //operazione 12
            case 4:
                resultTable = operationManager.getCostruttori();
                break;
            //operazione 13
            case 5:
                resultTable = operationManager.getClassificaVetture();
                break;
            //operazione 15
            case 6:
                resultTable = operationManager.getReportPuntiMinuti();
                break;
        }
        try{
            JOptionPane.showMessageDialog(parentPanel, ActionPanel.getNewScrollTable(resultTable), "Tabella risultati", JOptionPane.PLAIN_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(parentPanel, "L'interrogazione al database non ha dato risultati", "Tabella risultati", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
