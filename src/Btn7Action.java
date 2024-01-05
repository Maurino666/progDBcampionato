import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Btn7Action extends BtnAction{

    JPanel mainPanel;

    ActionPanel componentePanel;
    ActionPanel telaioPanel, motorePanel, cambioPanel;

    JComboBox<String> tipiSelector;

    public Btn7Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        initMainPanel();
    }

    private void initMainPanel(){
        this.componentePanel = new ActionPanel(
            "codice ", ActionPanel.getNewTextField(),
            "vettura ", ActionPanel.getNewTextField(),
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

        tipiSelector = componentePanel.getListComboBox(5);
        tipiSelector.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia il pannello visibile in base alla selezione del tipo
                changingComponentFields.show(changingPanel, (String)tipiSelector.getSelectedItem());
            }
        });

        mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(componentePanel);
        mainPanel.add(changingPanel);
    }

    public void showAction(){
        Object[] options = {"annulla", "conferma"};

        int optionResult = JOptionPane.showOptionDialog(
                parentPanel,
                mainPanel,
                "Inserisci i dettagli iscrizione",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options, 
                options[1]
        );

        if(optionResult == 1){
            int result = 0;
            String gotTipo = (String)tipiSelector.getSelectedItem();
            String costruttore = componentePanel.getListTextField(4).getText();
            //provo ad inserire componente 

            if(gotTipo.equals("TELAIO")){
                //inserisco tipo telaio
                result = operationManager.insertComponenteWithCheck(
                    componentePanel.getListTextField(0).getText(),
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    componentePanel.getListTextField(3).getText(),
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
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    componentePanel.getListTextField(3).getText(),
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
                    componentePanel.getListTextField(1).getText(),
                    componentePanel.getListTextField(2).getText(),
                    componentePanel.getListTextField(3).getText(),
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

            if(result == 1){
                operationManager.incrementCostruttore(costruttore);
            }else{
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento componente...");
            }
        }
        componentePanel.cleanPanel();
        telaioPanel.cleanPanel();
        motorePanel.cleanPanel();
        cambioPanel.cleanPanel();
    }
}
