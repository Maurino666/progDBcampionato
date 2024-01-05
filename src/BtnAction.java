import javax.swing.JPanel;

public abstract class BtnAction {
    
    public static OperationManager operationManager;
    
    JPanel parentPanel;

    public void showAction(){};
    public void showAction(int selectIndex){};

}