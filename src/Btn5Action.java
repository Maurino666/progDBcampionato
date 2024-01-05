import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Btn5Action extends BtnAction{
    ActionPanel mainPanel;

    public Btn5Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        mainPanel = new ActionPanel(
            "gara ", ActionPanel.getNewTextField(),
            "vettura ", ActionPanel.getNewTextField()
        );
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
            int result = operationManager.insertIscrizione(
                    mainPanel.getListTextField(0).getText(),
                    mainPanel.getListTextField(1).getText()
                );
            if(result == 1){
                JOptionPane.showMessageDialog(parentPanel, "Iscrizione avvenuta con successo!");
            }
            else{
                JOptionPane.showMessageDialog(parentPanel, "Iscrizione fallita...");
            }
        }

        mainPanel.cleanPanel();
    }
}
