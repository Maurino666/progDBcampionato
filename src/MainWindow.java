import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame{
    
    //Dichiarazione mainPanel
    JPanel mainPanel;

    //Bottoni mainPanel
    JButton[] opButton = new JButton[15];

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
        mainPanel.setVisible(true);
    };
}
