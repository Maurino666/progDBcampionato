import javax.swing.*;


public class Btn1Action extends BtnAction{

    
    ActionPanel mainPanel;

    public Btn1Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        this.mainPanel = new ActionPanel(
            "nome ", ActionPanel.getNewTextField(),
            "paese ", ActionPanel.getNewTextField()
        );
    }

    public void showAction(){
        Object[] options = {"annulla", "conferma"};
        
        int optionResult = JOptionPane.showOptionDialog(
                parentPanel,
                mainPanel,
                "Inserisci i dettagli",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options, 
                options[1]
        );
         
        if(optionResult == 1){ 
            int result = BtnAction.operationManager.insertScuderia(
                mainPanel.getListTextField(0).getText(),
                mainPanel.getListTextField(1).getText()
            );
            if(result == 1)
                JOptionPane.showMessageDialog(parentPanel, "Inserimento avvenuto con successo!");
            else
                JOptionPane.showMessageDialog(parentPanel, "Errore nell'inserimento...");
        }

        mainPanel.cleanPanel();
    }
}
