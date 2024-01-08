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
        vetturaPanel = new ActionPanel(
            "modello ", ActionPanel.getNewTextField(),
            "scuderia ", ActionPanel.getNewTextField()
        );
    }

    private void initComponenteMainPanel(){
        componentePanel = new ActionPanel(
            "codice ", ActionPanel.getNewTextField(),
            "data istallazione ", ActionPanel.getNewTextField(),
            "costo ", ActionPanel.getNewTextField(),
            "costruttore ", ActionPanel.getNewTextField(),
            "tipo ", ActionPanel.getNewStringComboBox("TELAIO", "MOTORE", "CAMBIO")
        );
        telaioPanel = new ActionPanel(
            "materiale ", ActionPanel.getNewTextField(),
            "peso ", ActionPanel.getNewTextField()
        );
        motorePanel = new ActionPanel(
            "cilindrata ", ActionPanel.getNewTextField(),
            "numero cilindri ", ActionPanel.getNewTextField(),
            "tipo motore ", ActionPanel.getNewStringComboBox("ASPIRATO", "TURBO")
        );
        cambioPanel = new ActionPanel(
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

            if(result == 1)
                insertAllComponenti();
            else
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento vettura...");
        }
        vetturaPanel.cleanPanel();
    }


    private void insertAllComponenti(){
        String vettura = Integer.toString(operationManager.getLastNumeroGara());
        int optionResult = 1;
        int result = 0;
        Object[] options = {"annulla", "inserisci altro", "conferma"};
       
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
            /*questa cosa si può controllare anche a livello applicativo e sarebbe più efficiente */
            String gotTipo = (String)tipiSelector.getSelectedItem();
            String costruttore = componentePanel.getListTextField(3).getText();
            //provo ad inserire componente 

            if(gotTipo.equals("TELAIO")){
                //inserisco tipo telaio
                result = operationManager.insertComponenteWithCheck(
                    componentePanel.getListTextField(0).getText(),
                    vettura,
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    "TELAIO",
                    null,
                    null,
                    null,
                    null,
                    telaioPanel.getListTextField(1).getText(),
                    telaioPanel.getListTextField(0).getText(),
                    costruttore
                );
                
            }
            else if(gotTipo.equals("MOTORE")){
                //inserisco tipo motore
                result = operationManager.insertComponenteWithCheck(
                    componentePanel.getListTextField(0).getText(),
                    vettura,
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    "MOTORE",
                    motorePanel.getListTextField(1).getText(),
                    (String)motorePanel.getListComboBox(2).getSelectedItem(),
                    motorePanel.getListTextField(0).getText(),
                    null,
                    null,
                    null,
                    costruttore
                );
            }
            else if(gotTipo.equals("CAMBIO")){
                //inserisco tipo cambio
                result = operationManager.insertComponenteWithCheck(
                    componentePanel.getListTextField(0).getText(),
                    vettura,
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    "CAMBIO",
                    null,
                    null,
                    null,
                    (String)cambioPanel.getListComboBox(0).getSelectedItem(),
                    null,
                    null,
                    costruttore
                );
            }
            componentePanel.cleanPanel();
            telaioPanel.cleanPanel();
            motorePanel.cleanPanel();
            cambioPanel.cleanPanel();
            if(result != 1){
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento componente...");
                break;
            }
            operationManager.incrementCostruttore(costruttore);
        }
    }
}
