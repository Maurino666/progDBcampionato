import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Btn4Action extends BtnAction{
    
    ActionPanel mainPanel;

    public Btn4Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        mainPanel = new ActionPanel(
            "codice pilota ", ActionPanel.getNewTextField(),
            "quota ", ActionPanel.getNewTextField()
        );
    }

    public void showAction(){
        Object[] options = {"annulla", "conferma"};

        int optionResult = JOptionPane.showOptionDialog(
                parentPanel,
                mainPanel,
                "Inserisci i dettagli finanziamento",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options, 
                options[1]
        );

        if(optionResult == 1){
            int result = operationManager.updateFinanziamento(
                    mainPanel.getListTextField(0).getText(),
                    mainPanel.getListTextField(1).getText()
                );
            if(result == 1){
                JOptionPane.showMessageDialog(parentPanel, "Finanziamento avvenuto con successo!");
            }
            else{
                JOptionPane.showMessageDialog(parentPanel, "Finanziamento fallito...");
            }
        }

        mainPanel.cleanPanel();
    }
}