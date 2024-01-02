import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*[QUESTA CLASSE NON È PARTE DEL PROGETTO]
 * contiene solo del codice di pannelli che ho scritto
 * da cui prendere spunto per la realizzazione della GUI
 */
public class WindowManager extends JFrame{
    
    //variabili per i pannelli
    JPanel mainPanel;
    JPanel[] OpPanel = new JPanel[15];
    JPanel[] AusiliaryPanel = new JPanel[3];


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
        this.initAusOp2();

        Object[] options = {"conferma", "annulla"};
        int result = JOptionPane.showOptionDialog(
                this,
                AusiliaryPanel[0],
                "Inserisci i dettagli",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        
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
        JPanel labelPanel = new JPanel(new GridLayout(3, 1));
        labelPanel.add(lblModello);
        labelPanel.add(lblScuderia);
        labelPanel.add(lblNComp);

        //Pannello dei Fields
        JTextField txtModello = new JTextField();
        txtModello.setPreferredSize(new Dimension(300, 25));
        JTextField txtScuderia = new JTextField();
        txtScuderia.setPreferredSize(new Dimension(300, 25));
        Integer[] options = {0,1,2,3};
        JComboBox<Integer> nComponenti = new JComboBox<>(options);
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1));
        fieldsPanel.add(txtModello);
        fieldsPanel.add(txtScuderia);
        fieldsPanel.add(nComponenti);

        //Prima organizzazione del pannello 
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.add(labelPanel);
        firstPanel.add(fieldsPanel);

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

    private void initAusOp2(){

        /*
        |--------------------------PANNELLO INSERIMENTO MOTORE--------------------------|
        */
        JLabel lblCilindrata = new JLabel("cilindrata ", JLabel.RIGHT);
        JLabel lblNCilindri= new JLabel("numero cilindri ", JLabel.RIGHT);
        JLabel lblTipoMotore = new JLabel("tipo motore ", JLabel.RIGHT);
        JPanel labelPanel2 = new JPanel(new GridLayout(3, 1));
        labelPanel2.add(lblCilindrata);
        labelPanel2.add(lblNCilindri);
        labelPanel2.add(lblTipoMotore);

        //Pannello dei Fields
        JTextField txtCilindrata = new JTextField();
        txtCilindrata.setPreferredSize(new Dimension(300, 25));
        JTextField txtNCilindri = new JTextField();
        txtNCilindri.setPreferredSize(new Dimension(300, 25));
        String[] tipiMotore = {"ASPIRATO", "TURBO"};
        JComboBox<String> motoreSelector = new JComboBox<>(tipiMotore);        
        JPanel fieldsPanel2 = new JPanel(new GridLayout(3, 1));
        fieldsPanel2.add(txtCilindrata);
        fieldsPanel2.add(txtNCilindri);
        fieldsPanel2.add(motoreSelector);

        //Prima organizzazione del pannello 
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
        secondPanel.add(labelPanel2);
        secondPanel.add(fieldsPanel2);

        //Seconda organizzazione del pannello 
        JPanel enclosedSecondPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        enclosedSecondPanel.add(secondPanel);

        /*
        |--------------------------PANNELLO INSERIMENTO TELAIO--------------------------|
        */
        JLabel lblPeso = new JLabel("peso ", JLabel.RIGHT);
        JLabel lblMateriale = new JLabel("materiale ", JLabel.RIGHT);
        JPanel labelPanel3 = new JPanel(new GridLayout(2, 1));
        labelPanel3.add(lblPeso);
        labelPanel3.add(lblMateriale);

        //Pannello dei Fields
        JTextField txtPeso = new JTextField();
        txtPeso.setPreferredSize(new Dimension(300, 25));
        JTextField txtMateriale = new JTextField();
        txtMateriale.setPreferredSize(new Dimension(300, 25));
        JPanel fieldsPanel3 = new JPanel(new GridLayout(2, 1));
        fieldsPanel3.add(txtPeso);
        fieldsPanel3.add(txtMateriale);


        //Prima organizzazione del pannello 
        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new BoxLayout(thirdPanel, BoxLayout.X_AXIS));
        thirdPanel.add(labelPanel3);
        thirdPanel.add(fieldsPanel3);

        //Seconda organizzazione del pannello 
        JPanel enclosedThirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        enclosedThirdPanel.add(thirdPanel);

        /*
        |--------------------------PANNELLO INSERIMENTO CAMBIO--------------------------|
        */
        JLabel lblNMarce = new JLabel("numero marce ", JLabel.RIGHT);
        JPanel labelPanel4 = new JPanel(new GridLayout(1, 1));
        labelPanel4.add(lblNMarce);

        //Pannello dei Fields
        JTextField txtNMarce = new JTextField();
        txtNMarce.setPreferredSize(new Dimension(300, 25));
        JPanel fieldsPanel4 = new JPanel(new GridLayout(1, 1));
        fieldsPanel4.add(txtNMarce);

        //Prima organizzazione del pannello 
        JPanel fourthPanel = new JPanel();
        fourthPanel.setLayout(new BoxLayout(fourthPanel, BoxLayout.X_AXIS));
        fourthPanel.add(labelPanel4);
        fourthPanel.add(fieldsPanel4);

        //Seconda organizzazione del pannello 
        JPanel enclosedFourthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        enclosedFourthPanel.add(fourthPanel);

        /*
        |--------------------------PANNELLO CHE CAMBIA--------------------------|
        */
        CardLayout cardLayout = new CardLayout();
        JPanel changingPanel = new JPanel(cardLayout);
        changingPanel.add(enclosedSecondPanel, "MOTORE");
        changingPanel.add(enclosedThirdPanel, "TELAIO");
        changingPanel.add(enclosedFourthPanel, "CAMBIO");
        cardLayout.show(changingPanel, "MOTORE");

        
        /*
        |--------------------------PANNELLO INSERIMENTO COMPONENTE--------------------------|
        */
        //Pannello delle label
        JLabel lblCodice = new JLabel("codice ", JLabel.RIGHT);
        JLabel lblVettura = new JLabel("vettura ", JLabel.RIGHT);
        JLabel lblDataInstall= new JLabel("data installazione ", JLabel.RIGHT);
        JLabel lblCosto= new JLabel("costo ", JLabel.RIGHT);
        JLabel lblTipo= new JLabel("tipo ", JLabel.RIGHT);

        JPanel labelPanel1 = new JPanel(new GridLayout(5, 1));
        labelPanel1.add(lblCodice);
        labelPanel1.add(lblVettura);
        labelPanel1.add(lblDataInstall);
        labelPanel1.add(lblCosto);
        labelPanel1.add(lblTipo);

        //Pannello dei Fields
        JTextField txtCodice = new JTextField();
        txtCodice.setPreferredSize(new Dimension(300, 25));
        JTextField txtVettura = new JTextField();
        txtVettura.setPreferredSize(new Dimension(300, 25));
        JTextField txtDataInstall = new JTextField();
        txtDataInstall.setPreferredSize(new Dimension(300, 25));
        JTextField txtCosto = new JTextField();
        txtCosto.setPreferredSize(new Dimension(300, 25));
        String[] tipi = {"MOTORE", "TELAIO", "CAMBIO"};
        JComboBox<String> tipiSelector = new JComboBox<>(tipi);

        tipiSelector.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia il pannello visibile in base alla selezione del tipo
                cardLayout.show(changingPanel, (String)tipiSelector.getSelectedItem());
            }
        });

        JPanel fieldsPanel1 = new JPanel(new GridLayout(5, 1));
        fieldsPanel1.add(txtCodice);
        fieldsPanel1.add(txtVettura);
        fieldsPanel1.add(txtDataInstall);
        fieldsPanel1.add(txtCosto);
        fieldsPanel1.add(tipiSelector);

        //Prima organizzazione del pannello 
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.add(labelPanel1);
        firstPanel.add(fieldsPanel1);

        //Seconda organizzazione del pannello 
        JPanel enclosedFirstPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        enclosedFirstPanel.add(firstPanel);
       
        /*
        |---------------------------------------------------------------------------------|
        */

        //Pannello divisore principale
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(enclosedFirstPanel);
        middlePanel.add(changingPanel);

     

        //Pannello di Conferma
        JButton btnConferma = new JButton("conferma");
        JButton btnAnnulla = new JButton("annulla");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(btnAnnulla);
        southPanel.add(btnConferma);

        //Inserimento pannello principale dell'operazione
        AusiliaryPanel[0] = new JPanel(new BorderLayout());
        AusiliaryPanel[0].add(middlePanel, BorderLayout.CENTER);
        AusiliaryPanel[0].add(southPanel, BorderLayout.SOUTH);

        AusiliaryPanel[0].setVisible(true);

    }
}
