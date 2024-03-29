import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Btn6Action extends BtnAction{
    
    ActionPanel garaPanel;

    ActionPanel tipoPanel;

    JPanel mainUpdatePanel;

    JComboBox<String> tipiSelector;

    ActionPanel esitoPanel;
    ActionPanel ritiroPanel;

    public Btn6Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        initEsitoPanel();
        initRitiroPanel();
        initGaraPanel();
        initMainUpdatePanel();
    }

    private void initGaraPanel(){
        garaPanel = new ActionPanel(
                "nome gara ", ActionPanel.getNewTextField()
            );
    }

    private void initEsitoPanel(){
        esitoPanel = new ActionPanel(
            "esito ", ActionPanel.getNewTextField()
        );
    }

    private void initRitiroPanel(){
        ritiroPanel = new ActionPanel(
            "motivo ritiro ", ActionPanel.getNewStringComboBox("INCIDENTE", "GUASTO MECCANICO", "SQUALIFICA", "ALTRO")
        );
    }

    private void initMainUpdatePanel(){
        tipoPanel = new ActionPanel(
            "completamento ", ActionPanel.getNewStringComboBox("COMPLETATO", "RITIRATO")
        );
        tipiSelector = tipoPanel.getListComboBox(0);

        CardLayout changingUpdateFields = new CardLayout();
        JPanel changingPanel = new JPanel(changingUpdateFields);

        changingPanel.add(esitoPanel, "COMPLETATO");
        changingPanel.add(ritiroPanel, "RITIRATO");
        
        tipiSelector.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia il pannello visibile in base alla selezione del tipo
                changingUpdateFields.show(changingPanel, (String)tipiSelector.getSelectedItem());
            }
        });

        mainUpdatePanel = new JPanel(new GridLayout(2, 1));
        mainUpdatePanel.add(tipoPanel);
        mainUpdatePanel.add(changingPanel);
    }


    public void showAction(){
        String gara;
        int count;
        Object[] options = {"annulla", "continua"};
        int optionResult = JOptionPane.showOptionDialog(
            parentPanel,
            garaPanel,
            "Inserisci gara compiuta",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options, 
            options[1]
        );
        if(optionResult == 1){
            gara = garaPanel.getListTextField(0).getText();
            count = operationManager.countVettureGara(gara);
            List<Map<String, Object>> table = operationManager.getVettureGara(gara);
            operationManager.transactionBegin();
            for(Map<String, Object> record : table){
                if(updateSingleIscrizione(((Integer)record.get("vettura")).toString(), gara, count) == false){
                    operationManager.transactionRollback();
                    JOptionPane.showMessageDialog(parentPanel, "Aggiornamenti falliti...");
                    garaPanel.cleanPanel();
                    return;
                }
            }
            operationManager.transactionCommit();
        }
    }

    private boolean updateSingleIscrizione(String vettura, String gara, int esitoMax){
        Object[] options = {"annulla", "continua"};
        boolean result = false;
        int optionResult = JOptionPane.showOptionDialog(
            parentPanel,
            mainUpdatePanel,
            "Inserisci esito vettura " + vettura,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options, 
            options[1]
        );

        if(optionResult == 1){
            String gotTipo = (String)tipiSelector.getSelectedItem();
            if(gotTipo.equals("COMPLETATO")){
                String esito =  esitoPanel.getListTextField(0).getText();

                if(Integer.parseInt(esito) <= 0 || Integer.parseInt(esito) > esitoMax){
                    tipoPanel.cleanPanel();
                    esitoPanel.cleanPanel();
                    ritiroPanel.cleanPanel();

                    return false;
                }

                result = (operationManager.updateEsito(
                    gara, 
                    vettura, 
                    esitoPanel.getListTextField(0).getText(), 
                    null
                ) == 2);
                
            }else{
                result = (operationManager.updateEsito(
                    gara, 
                    vettura, 
                    null, 
                    (String)ritiroPanel.getListComboBox(0).getSelectedItem()
                ) == 1);
            }
            tipoPanel.cleanPanel();
            esitoPanel.cleanPanel();
            ritiroPanel.cleanPanel();
        }
        return result;
    }
}