import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Btn2Action extends BtnAction{

    
    ActionPanel vetturaPanel;
    JPanel componenteMainPanel;

    ActionPanel componentePanel;

    JComboBox<String> tipiSelector;
    CardLayout changingComponentFields = new CardLayout();
    JPanel changingPanel = new JPanel(changingComponentFields);
    ActionPanel telaioPanel, motorePanel, cambioPanel;
    

    public Btn2Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        this.vetturaPanel = new ActionPanel(
            "modello ", ActionPanel.getNewTextField(),
            "scuderia ", ActionPanel.getNewTextField()
        );
        this.componentePanel = new ActionPanel(
            "codice ", ActionPanel.getNewTextField(),
            "data istallazione ", ActionPanel.getNewTextField(),
            "costo ", ActionPanel.getNewTextField(),
            "costruttore ", ActionPanel.getNewTextField(),
            "tipo ", ActionPanel.getNewStringComboBox("TELAIO", "MOTORE", "CAMBIO")
        );
        this.telaioPanel = new ActionPanel(
            "materiale ", ActionPanel.getNewTextField(),
            "peso ", ActionPanel.getNewTextField()
        );
        this.motorePanel = new ActionPanel(
            "cilindrata ", ActionPanel.getNewTextField(),
            "numero cilindri ", ActionPanel.getNewTextField(),
            "tipo motore ", ActionPanel.getNewStringComboBox("ASPIRATO", "TURBO")
        );
        this.cambioPanel = new ActionPanel(
            "numero marce ", ActionPanel.getNewStringComboBox("7", "8")
        );
        changingPanel.add(telaioPanel, "TELAIO");
        changingPanel.add(motorePanel, "MOTORE");
        changingPanel.add(cambioPanel, "CAMBIO");

        tipiSelector = componentePanel.getListComboBox(4);
        tipiSelector.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia il pannello visibile in base alla selezione del tipo
                changingComponentFields.show(changingPanel, (String)tipiSelector.getSelectedItem());
            }
        });

        componenteMainPanel.add(componentePanel);
        componenteMainPanel.add(changingPanel);
    }

    public void showAction(){
        Object[] options = {"annulla", "continua"};
        Object[] options2 = {"annulla", "inserisci altro", "conferma"};

        int optionResult = JOptionPane.showOptionDialog(
                parentPanel,
                vetturaPanel,
                "Inserisci i dettagli vettura",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options, 
                options[1]
        );
         
        if(optionResult == 1){
            
            int result = BtnAction.operationManager.insertVettura(
                vetturaPanel.getListTextField(0).getText(),
                vetturaPanel.getListTextField(1).getText()
            );
            if(result == 1){
            
                for(int i = 0; i<3 && optionResult == 1; i++){
                    optionResult = JOptionPane.showOptionDialog(
                            parentPanel,
                            componenteMainPanel,
                            "Inserisci i dettagli componente",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options, 
                            options[1]
                    );

                    if(optionResult != 0){
                        /*TODO questa cosa si può controllare anche a livello applicativo e sarebbe più efficiente */
                        String gotTipi = (String)tipiSelector.getSelectedItem();
                        if(gotTipi.equals("TELAIO"))
                            operationManager.insertComponenteWithCheck(
                                componentePanel.getListTextField(0).getSelectedText(),
                                componentePanel.getListTextField(0).getSelectedText()
                                componentePanel.getListTextField(0).getSelectedText()
                                componentePanel.getListTextField(0).getSelectedText()
                            );
                            /* TODO continuare */
                    }
                }

            }
            else
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento...");
        }
    }
}
