import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Btn2Action extends BtnAction{

    
    ActionPanel vetturaPanel;
    JPanel componenteMainPanel;

    ActionPanel componentePanel;

    JComboBox<String> tipiSelector;
    ActionPanel telaioPanel, motorePanel, cambioPanel;
    

    public Btn2Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        initVetturaPanel();
        initComponenteMainPanel();
    }

    private void initVetturaPanel(){
        this.vetturaPanel = new ActionPanel(
            "modello ", ActionPanel.getNewTextField(),
            "scuderia ", ActionPanel.getNewTextField()
        );
    }

    private void initComponenteMainPanel(){
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

        
        CardLayout changingComponentFields = new CardLayout();
        JPanel changingPanel = new JPanel(changingComponentFields);

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

        componenteMainPanel = new JPanel(new GridLayout(2, 1));
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
                String vettura = new String(Integer.toString(operationManager.getLastNumeroGara()));
                for(int i = 0; i<3 && optionResult == 1; i++){
                    optionResult = JOptionPane.showOptionDialog(
                            parentPanel,
                            componenteMainPanel,
                            "Inserisci i dettagli componente",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options2, 
                            options2[1]
                    );

                    if(optionResult != 0){
                        /*TODO questa cosa si può controllare anche a livello applicativo e sarebbe più efficiente */
                        String gotTipo = (String)tipiSelector.getSelectedItem();
                        //provo ad inserire componente 
                        if(gotTipo.equals("TELAIO"))
                            //inserisco tipo telaio
                            result = operationManager.insertComponenteWithCheck(
                                componentePanel.getListTextField(0).getSelectedText(),
                                vettura,
                                componentePanel.getListTextField(1).getSelectedText(),
                                componentePanel.getListTextField(2).getSelectedText(),
                                "TELAIO",
                                null,
                                null,
                                null,
                                null,
                                telaioPanel.getListTextField(1).getSelectedText(),
                                telaioPanel.getListTextField(0).getSelectedText(),
                                componentePanel.getListTextField(3).getSelectedText()
                            );
                        else if(gotTipo.equals("MOTORE"))
                            //inserisco tipo motore
                            result = operationManager.insertComponenteWithCheck(
                                componentePanel.getListTextField(0).getSelectedText(),
                                vettura,
                                componentePanel.getListTextField(1).getSelectedText(),
                                componentePanel.getListTextField(2).getSelectedText(),
                                "MOTORE",
                                motorePanel.getListTextField(1).getSelectedText(),
                                (String)motorePanel.getListComboBox(2).getSelectedItem(),
                                motorePanel.getListTextField(0).getSelectedText(),
                                null,
                                null,
                                null,
                                componentePanel.getListTextField(3).getSelectedText()
                            );
                        else if(gotTipo.equals("CAMBIO"))
                            //inserisco tipo cambio
                            result = operationManager.insertComponenteWithCheck(
                                componentePanel.getListTextField(0).getSelectedText(),
                                vettura,
                                componentePanel.getListTextField(1).getSelectedText(),
                                componentePanel.getListTextField(2).getSelectedText(),
                                "CAMBIO",
                                null,
                                null,
                                null,
                                (String)cambioPanel.getListComboBox(0).getSelectedItem(),
                                null,
                                null,
                                componentePanel.getListTextField(3).getSelectedText()
                            );
                        if(result != 1){
                            JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento componente...");
                            break;
                        }
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento vettura...");
        }
    }
}
