import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame{
    
    
    JPanel mainPanel;
    JPanel[] OpPanel = new JPanel[15];
    
    //init
    public WindowManager(String title){ 
        super(title); 
        init(800, 400);
    }

    public WindowManager(String title, int width, int height){ 
        super(title); 
        init(width, height);
    }

    private void init(int width, int height){
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(true);

        this.setVisible(true);
        this.initMainPanel();
        this.getContentPane().add(mainPanel);
    }


    JButton[] opButton = new JButton[15];

    private void initMainPanel(){
        mainPanel = new JPanel(new GridLayout(5,3));
        for(int i=0; i<15; i++){
            opButton[i] = new JButton("Op" + (i+1));  
            mainPanel.add(opButton[i]);
        }
    };


}
