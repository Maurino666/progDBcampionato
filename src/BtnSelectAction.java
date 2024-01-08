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
            case 8: 
                resultTable = operationManager.getFinanziamentiScuderie();
                break;
            //operazione 9
            case 9:
                resultTable = operationManager.getReportScuderie();
                break;
            //operazione 10
            case 10:
                resultTable = operationManager.getPilotiCasaVincenti();
                break;
            //operazione 11
            case 11:
                resultTable = operationManager.getPercentualeGD();
                break;
            //operazione 12
            case 12:
                resultTable = operationManager.getCostruttori();
                break;
            //operazione 13
            case 13:
                resultTable = operationManager.getClassificaVetture();
                break;
            //operazione 15
            case 15:
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
