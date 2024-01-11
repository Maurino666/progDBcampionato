import javax.swing.*;

import java.util.*;

public class BtnSelectAction extends BtnAction{
    JPanel mainJPanel;

    public BtnSelectAction(JPanel parentPanel){
        this.parentPanel = parentPanel;
    }

    public void showAction(int selectIndex){
        String title = null;
        List<Map<String, Object>> resultTable = null;
        switch (selectIndex){
            //operazione 8
            case 8: 
                title = "Report scuderie con somma finanziamenti";
                resultTable = operationManager.getFinanziamentiScuderie();
                break;
            //operazione 9
            case 9:
                title = "Report scuderie con numero finanziamenti";
                resultTable = operationManager.getReportScuderie();
                break;
            //operazione 10
            case 10:
                title = "Vincitori in casa";
                resultTable = operationManager.getPilotiCasaVincenti();
                break;
            //operazione 11
            case 11:
                title = "Percentuale GD";
                resultTable = operationManager.getPercentualeGD();
                break;
            //operazione 12
            case 12:
                title = "Report costruttori";
                resultTable = operationManager.getCostruttori();
                break;
            //operazione 13
            case 13:
                title = "Classifica vetture";
                resultTable = operationManager.getClassificaVetture();
                break;
            //operazione 15
            case 15:
                title = "Report scuderie punti/tempo";
                resultTable = operationManager.getReportPuntiMinuti();
                break;
        }
        try{
            JOptionPane.showMessageDialog(parentPanel, ActionPanel.getNewScrollTable(resultTable), title, JOptionPane.PLAIN_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(parentPanel, "L'interrogazione al database non ha dato risultati", title, JOptionPane.PLAIN_MESSAGE);
        }
    }
}
