import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame{
    
    public static 

    //Dichiarazione mainPanel
    JPanel mainPanel;

    //Bottoni mainPanel
    JButton[] opButton = new JButton[15];
    BtnAction[] buttonActions = new BtnAction[9];

    public MainWindow(String title){ 
        super(title); 
        init(800, 400);
    }

    // inizializzazione finestra con titolo e dimensione
    public MainWindow(String title, int width, int height){ 
        super(title); 
        init(width, height);
    }

    private void init(int width, int height){
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.initMainPanel();
        
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void initMainPanel(){
        mainPanel = new JPanel(new GridLayout(5,3));
        for(int i=0; i<15; i++){
            opButton[i] = new JButton("Op" + (i+1));  
            mainPanel.add(opButton[i]);
        }
        initButtons();
        initActions();
        mainPanel.setVisible(true);
    };

    private void initActions(){

        for (int i = 0; i < 7; i++) {

            final int index = i; 

            opButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonActions[index].showAction();
                }
            });
        }

        for(int i = 7; i<15; i++){
            final int index = i; 

            if(i == 13){
                opButton[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonActions[7].showAction();
                    }
                 });
            }

            else{

                opButton[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonActions[8].showAction(index+1);
                    }
                });
            }
           

        }
        
    }


    private void initButtons(){
        buttonActions[0] = (BtnAction)new Btn1Action(mainPanel);
        buttonActions[1] = new Btn2Action(mainPanel);
        buttonActions[2] = new Btn3Action(mainPanel);
        buttonActions[3] = new Btn4Action(mainPanel);
        buttonActions[4] = new Btn5Action(mainPanel);
        buttonActions[5] = new Btn6Action(mainPanel);
        buttonActions[6] = new Btn7Action(mainPanel);
        buttonActions[7] = new Btn14Action(mainPanel);
        buttonActions[8] = new BtnSelectAction(mainPanel);
    }
}
