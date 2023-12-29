import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame{
    
    //variabili per i pannelli
    JPanel mainPanel;
    JPanel[] OpPanel = new JPanel[15];


    //Bottoni mainPanel
    JButton[] opButton = new JButton[15];

    
    // inizializzazione finestra con titolo
    public WindowManager(String title){ 
        super(title); 
        init(800, 400);
    }

    // inizializzazione finestra con titolo e dimensione
    public WindowManager(String title, int width, int height){ 
        super(title); 
        init(width, height);
    }

    // inizializzazione effettiva del JFrame con inizializzazioane dei panel
    private void init(int width, int height){
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(true);

        this.setVisible(true);
        this.initMainPanel();
        this.initOpPanel1();
        this.initOpPanel2();
        
        this.getContentPane().add(OpPanel[1]);
        this.setVisible(true);
        
    }

       /*TODO: associare ascoltatori ai pulsanti */
   
    //  inizializzazione del mainPanel, riferito al pannello principale
 
    private void initMainPanel(){
        mainPanel = new JPanel(new GridLayout(5,3));
        for(int i=0; i<15; i++){
            opButton[i] = new JButton("Op" + (i+1));  
            mainPanel.add(opButton[i]);
        }
        mainPanel.setVisible(true);
    };

    private void initOpPanel1(){
        
        /*
        |--------------------------PANNELLO INSERIMENTO SCUDERIA--------------------------|
        */
        //Pannello delle label
        JLabel lblNome = new JLabel("nome ", JLabel.RIGHT);
        JLabel lblPaese = new JLabel("paese ", JLabel.RIGHT);
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.add(lblNome);
        labelPanel.add(lblPaese);

        //Pannello dei textField
        JTextField txtNome = new JTextField();
        txtNome.setPreferredSize(new Dimension(300, 25));
        JTextField txtPaese = new JTextField();
        txtPaese.setPreferredSize(new Dimension(300, 25));
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(txtNome);
        textPanel.add(txtPaese);

        //Prima organizzazione del pannello centrale
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(labelPanel);
        middlePanel.add(textPanel);

        //Seconda organizzazione del pannello centrale
        JPanel enclosedMiddlePanel = new JPanel(new FlowLayout());
        enclosedMiddlePanel.add(middlePanel);
        /*
        |---------------------------------------------------------------------------------|
        */

        //Pannello di Conferma
        JButton btnConferma = new JButton("conferma");
        JButton btnAnnulla = new JButton("annulla");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(btnAnnulla);
        southPanel.add(btnConferma);

        //Inserimento pannello principale dell'operazione
        OpPanel[0] = new JPanel(new BorderLayout());
        OpPanel[0].add(enclosedMiddlePanel, BorderLayout.CENTER);
        OpPanel[0].add(southPanel, BorderLayout.SOUTH);

        OpPanel[0].setVisible(true);
    }

    private void initOpPanel2(){
    
        /*
        |--------------------------PANNELLO INSERIMENTO VETTURA--------------------------|
        */
        //Pannello delle label
        JLabel lblModello = new JLabel("modello ", JLabel.RIGHT);
        JLabel lblScuderia = new JLabel("scuderia ", JLabel.RIGHT);
        JLabel lblNComp= new JLabel("numero componenti ", JLabel.RIGHT);
        JPanel labelPanel1 = new JPanel(new GridLayout(3, 1));
        labelPanel1.add(lblModello);
        labelPanel1.add(lblScuderia);
        labelPanel1.add(lblNComp);

        //Pannello dei Fields
        JTextField txtModello = new JTextField();
        txtModello.setPreferredSize(new Dimension(300, 25));
        JTextField txtScuderia = new JTextField();
        txtScuderia.setPreferredSize(new Dimension(300, 25));
        Integer[] options = {0,1,2,3};
        JComboBox<Integer> nComponenti = new JComboBox<>(options);
        JPanel fieldsPanel1 = new JPanel(new GridLayout(3, 1));
        fieldsPanel1.add(txtModello);
        fieldsPanel1.add(txtScuderia);
        fieldsPanel1.add(nComponenti);

        //Prima organizzazione del pannello 
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.add(labelPanel1);
        firstPanel.add(fieldsPanel1);

        //Seconda organizzazione del pannello 
        JPanel enclosedFirstPanel = new JPanel(new FlowLayout());
        enclosedFirstPanel.add(firstPanel);
        /*
        |---------------------------------------------------------------------------------|
        */


        //Pannello Continuazione
        JButton btnContinua = new JButton("continua");
        JButton btnAnnulla = new JButton("annulla");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(btnAnnulla);
        southPanel.add(btnContinua);

        //Inserimento pannello principale dell'operazione
        OpPanel[1] = new JPanel(new BorderLayout());
        OpPanel[1].add(enclosedFirstPanel, BorderLayout.CENTER);
        OpPanel[1].add(southPanel, BorderLayout.SOUTH);

        OpPanel[1].setVisible(true);
    }

    



}
