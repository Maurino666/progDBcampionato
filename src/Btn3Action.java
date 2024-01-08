import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Btn3Action extends BtnAction{
    
    JPanel mainPanel;
    ActionPanel pilotaPanel;
    ActionPanel amPanel;
    ActionPanel proPanel;

    JComboBox<String> tipiSelector;

    public Btn3Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        mainPanel = new JPanel(new GridLayout(2, 1));
        initPilotaPanel();
        initTipoPanel();
    }

    private void initPilotaPanel(){
        pilotaPanel = new ActionPanel(
            "vettura ", ActionPanel.getNewTextField(),
            "nome ", ActionPanel.getNewTextField(),
            "cognome ", ActionPanel.getNewTextField(),
            "data di nascita ", ActionPanel.getNewTextField(),
            "nazionalità ", ActionPanel.getNewTextField(),
            "tipo ", ActionPanel.getNewStringComboBox("AM", "PRO")
        );

        tipiSelector = pilotaPanel.getListComboBox(5);

        mainPanel.add(pilotaPanel);
    }

    private void initTipoPanel(){
        amPanel = new ActionPanel(
            "data prima licenza ", ActionPanel.getNewTextField() 
        );

        proPanel = new ActionPanel(
            "numero licenze ", ActionPanel.getNewTextField() 
        );

        CardLayout changingTipiFields = new CardLayout();
        JPanel changingPanel = new JPanel(changingTipiFields);

        changingPanel.add(amPanel, "AM");
        changingPanel.add(proPanel, "PRO");

        tipiSelector.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia il pannello visibile in base alla selezione del tipo
                changingTipiFields.show(changingPanel, (String)tipiSelector.getSelectedItem());
            }
        });

        mainPanel.add(changingPanel);
    }


    public void showAction(){
        Object[] options = {"annulla", "conferma"};

        int optionResult = JOptionPane.showOptionDialog(
                parentPanel,
                mainPanel,
                "Inserisci i dettagli pilota",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options, 
                options[1]
        );

        if(optionResult == 1){
            int result = 0;
            String tipo = (String)tipiSelector.getSelectedItem();
            if(tipo.equals("AM")){
                result = operationManager.insertPilota(
                    pilotaPanel.getListTextField(0).getText(),
                    pilotaPanel.getListTextField(1).getText(),
                    pilotaPanel.getListTextField(2).getText(),
                    pilotaPanel.getListTextField(3).getText(),
                    pilotaPanel.getListTextField(4).getText(),
                    "AM",
                    "1",
                    amPanel.getListTextField(0).getText()
                );
            }else{
                result = operationManager.insertPilota(
                    pilotaPanel.getListTextField(0).getText(),
                    pilotaPanel.getListTextField(1).getText(),
                    pilotaPanel.getListTextField(2).getText(),
                    pilotaPanel.getListTextField(3).getText(),
                    pilotaPanel.getListTextField(4).getText(),
                    "PRO",
                    proPanel.getListTextField(0).getText(),
                    null
                );
            }
            if(result == 1){
                JOptionPane.showMessageDialog(parentPanel, "Pilota inserito!");
            }else{
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento...");
            }
        }

        pilotaPanel.cleanPanel();
        amPanel.cleanPanel();
        proPanel.cleanPanel();
    }
}