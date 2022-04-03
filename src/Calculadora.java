import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.*;

public class Calculadora extends JFrame{
    //Creamos los paneles
    private JLabel lblResultado = new JLabel(" ");
    private JPanel pnlBotones = new JPanel(new GridLayout(4,4));
    private JPanel pnlIgual = new JPanel(new GridLayout(1,1));

    //Creamos los botones
    private JButton[] botones = {
            new JButton("1"),  new JButton("2"), new JButton("3"), new JButton("+"),
            new JButton("4"),  new JButton("5"), new JButton("6"), new JButton("-"),
            new JButton("7"),  new JButton("8"), new JButton("9"), new JButton("*"),
            new JButton("C"),  new JButton("0"), new JButton(","), new JButton("/"),
            new JButton("=")
    };

    //Tamaño de la ventana
    private Dimension dmVentana = new Dimension(300,440);

    //Para las operaciones
    private double resultado = 0;
    private double numero;
    private static final int SUMA = 1;
    private static final int RESTA = 2;
    private static final int MULTIPLICACION = 3;
    private static final int DIVISION = 4;
    private static final int NINGUNO = 0;
    private int operador = Calculadora.NINGUNO;
    private boolean hayPunto = false;
    private boolean nuevoNumero = true;
    private NumberFormat nf = NumberFormat.getInstance();

    //Para crear la interfaz de la calculadora
    public Calculadora(){
        //Tamaño de la interfaz dependiendo de la pantalla
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x,y);
        this.setSize(dmVentana);
        this.setTitle("Calculadora");

        //Estilo de la ventana
        lblResultado.setBackground(Color.white);
        lblResultado.setOpaque(true);
        lblResultado.setFont(new Font("Arial", Font.PLAIN,32));

        //Llamamos al método que recoje los clicks
        Click pr = new Click();

        //Añadimos los botones al panel y al ActionListener
        for (int i=0; i<botones.length-1;i++){
            pnlBotones.add(botones[i]);
            botones[i].addActionListener(pr);
        }

        pnlIgual.add(botones[botones.length-1]);
        botones[botones.length-1].addActionListener(pr);

        //Ponemos los paneles en el JFrame
        pnlIgual.setPreferredSize(new Dimension(0,50));
        this.add(lblResultado, BorderLayout.NORTH);
        this.add(pnlBotones);
        this.add(pnlIgual,BorderLayout.SOUTH);

        //Otros detalles de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        lblResultado.setText("0");
        lblResultado.setHorizontalAlignment(JLabel.RIGHT);
    }

    //Para recoger los clicks
    class Click implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton origen = (JButton) e.getSource();
            String texto = origen.getText();
            String rdo;

            //Lo que sucede cuando pulsas el botón corrspondiente
            switch (texto) {
                case "+":
                    operar(Calculadora.SUMA);
                    break;
                case "-":
                    if(!nuevoNumero){
                        operar(Calculadora.RESTA);
                    } else {
                        lblResultado.setText("-");
                        nuevoNumero = false;
                    }
                    break;
                case "*":
                    operar(Calculadora.MULTIPLICACION);
                    break;
                case "/":
                    operar(Calculadora.DIVISION);
                    break;
                case ",":
                    if (!nuevoNumero){
                        if (!hayPunto){
                            rdo = lblResultado.getText();
                            lblResultado.setText(rdo+",");
                        }
                    } else {
                        lblResultado.setText("0,");
                        nuevoNumero = false;
                    }
                    hayPunto = true;
                    break;
                case "C":
                    lblResultado.setText("0");
                    nuevoNumero = true;
                    hayPunto = false;
                case "=":
                    if (operador != Calculadora.NINGUNO){
                        rdo = lblResultado.getText();
                        if (!rdo.isEmpty()){
                            Number n = null;
                            try {
                                n = (Number) nf.parse(rdo);
                                numero = n.doubleValue();
                            } catch (ParseException ex) {
                                numero = 0;
                            }
                            switch (operador) {
                                case Calculadora.SUMA:
                                    resultado += numero;
                                    break;
                                case Calculadora.RESTA:
                                    resultado -= numero;
                                    break;
                                case Calculadora.MULTIPLICACION:
                                    resultado *= numero;
                                    break;
                                case Calculadora.DIVISION:
                                    resultado /= numero;
                                    break;
                                default:
                                    resultado = numero;
                                    break;
                            }
                            operador = Calculadora.NINGUNO;
                            lblResultado.setText(nf.format(resultado));
                        }
                    }
                    break;
                default:
                    rdo = lblResultado.getText();
                    if (nuevoNumero){
                        lblResultado.setText(texto);
                    } else {
                        lblResultado.setText(rdo + texto);
                    }
                    nuevoNumero = false;
                    break;
            }
        }
    }

    //Para hacer las operaciones
    public void operar(int operacion){
        if (!nuevoNumero){
            String rdo = lblResultado.getText();
            if (!rdo.isEmpty()){
                Number n = null;
                try {
                    n = (Number) nf.parse(rdo);
                    numero = n.doubleValue();
                } catch (ParseException ex) {
                }
                switch (operador) {
                    case Calculadora.SUMA:
                        resultado += numero;
                        break;
                    case Calculadora.RESTA:
                        resultado -= numero;
                        break;
                    case Calculadora.MULTIPLICACION:
                        resultado *= numero;
                        break;
                    case Calculadora.DIVISION:
                        resultado /= numero;
                        break;
                    default:
                        resultado = numero;
                }
                operador = operacion;
                lblResultado.setText(nf.format(resultado));
                nuevoNumero = true;
                hayPunto = false;
            }
        }
    }
}