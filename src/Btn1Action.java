
import javax.swing.*;
import java.awt.*;

public class Btn1Action{

    JPanel parentPanel;
    JPanel mainPanel;

    public Btn1Action(JPanel parentPanel){
        this.parentPanel = parentPanel;
        this.mainPanel = new ActionPanel(
            "nome ", ActionPanel.getNewTextField(),
            "paese ", ActionPanel.getNewTextField()
        );
    }

    public void showAction(){
        Object[] options = {"annulla", "conferma"};
        int result = JOptionPane.showOptionDialog(
                parentPanel,
                mainPanel,
                "Inserisci i dettagli",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]
        );
    }


}
