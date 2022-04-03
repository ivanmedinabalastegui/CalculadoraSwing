import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

public class Calculadora2 extends JFrame{
    //Para crear los paneles
    private JLabel lblResultado = new JLabel(" ");
    private JPanel pnlBotones = new JPanel(new GridLayout(6,6));
    private JPanel pnlIgual = new JPanel(new GridLayout(6,6));
    private JPanel pnlTraduc = new JPanel(new GridLayout(1, 1));
    private JPanel pnlRadio = new JPanel(new GridLayout(4, 1));

    //Creamos los botones
    JButton btn1 = new JButton("1");
    JButton btn2 = new JButton("2");
    JButton btn3 = new JButton("3");
    JButton btnSum = new JButton("+");
    JButton btn4 = new JButton("4");
    JButton btn5 = new JButton("5");
    JButton btn6 = new JButton("6");
    JButton btnRes = new JButton("-");
    JButton btn7 = new JButton("7");
    JButton btn8 = new JButton("8");
    JButton btn9 = new JButton("9");
    JButton btnMult = new JButton("*");
    JButton btn0 = new JButton("0");
    JButton btnComa = new JButton(",");
    JButton btnIg = new JButton("=");
    JButton btnDiv = new JButton("/");
    JButton btnA = new JButton("A");
    JButton btnB = new JButton("B");
    JButton btnC = new JButton("C");
    JButton btnD = new JButton("D");
    JButton btnE = new JButton("E");
    JButton btnF = new JButton("F");
    JButton btnBor = new JButton("BORRAR");
    JButton btnTr = new JButton("TRADUCIR");

    //Creamos donde iría el número traducido
    JTextField binario = new JTextField("Binario");
    JTextField octal = new JTextField("Octal");
    JTextField hexadecimal = new JTextField("Hexadecimal");
    JTextField decimal = new JTextField("Decimal");

    //Creamos los RadioButton
    JRadioButton binarioR = new JRadioButton("Binario", false);
    JRadioButton octalR = new JRadioButton("Octal", false);
    JRadioButton hexadecimalR = new JRadioButton("Hexadecimal", false);
    JRadioButton decimalR = new JRadioButton("Decimal", true);

    //Tamaño de la ventana
    private Dimension dmVentana = new Dimension(500,400);

    private double resultado = 0;
    private double numero;
    private static final int SUMA = 1;
    private static final int RESTA = 2;
    private static final int MULTIPLICACION = 3;
    private static final int DIVISION = 4;
    private static final int NINGUNO = 0;
    private int operador = Calculadora2.NINGUNO;
    private boolean hayPunto = false;
    private boolean nuevoNumero = true;
    private NumberFormat nf = NumberFormat.getInstance();

    //Creamos la interfaz ara la calculadora
    public Calculadora2(){
        //Posición de la interfaz dependiendo de la pantalla
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

        Click pr = new Click();

        //Para poner los botones en el panel y en el ActionListener
        pnlIgual.add(btn1);
        btn1.addActionListener(pr);
        pnlIgual.add(btn2);
        btn2.addActionListener(pr);
        pnlIgual.add(btn3);
        btn3.addActionListener(pr);
        pnlIgual.add(btnSum);
        btnSum.addActionListener(pr);
        pnlIgual.add(btn4);
        btn4.addActionListener(pr);
        pnlIgual.add(btn5);
        btn5.addActionListener(pr);
        pnlIgual.add(btn6);
        btn6.addActionListener(pr);
        pnlIgual.add(btnRes);
        btnRes.addActionListener(pr);
        pnlIgual.add(btn7);
        btn7.addActionListener(pr);
        pnlIgual.add(btn8);
        btn8.addActionListener(pr);
        pnlIgual.add(btn9);
        btn9.addActionListener(pr);
        pnlIgual.add(btnMult);
        btnMult.addActionListener(pr);
        pnlIgual.add(btn0);
        btn0.addActionListener(pr);
        pnlIgual.add(btnComa);
        btnComa.addActionListener(pr);
        pnlIgual.add(btnIg);
        btnIg.addActionListener(pr);
        pnlIgual.add(btnDiv);
        btnDiv.addActionListener(pr);
        pnlIgual.add(btnA);
        btnA.addActionListener(pr);
        pnlIgual.add(btnB);
        btnB.addActionListener(pr);
        pnlIgual.add(btnC);
        btnC.addActionListener(pr);
        pnlIgual.add(btnD);
        btnD.addActionListener(pr);
        pnlIgual.add(btnE);
        btnE.addActionListener(pr);
        pnlIgual.add(btnF);
        btnF.addActionListener(pr);
        pnlIgual.add(btnBor);
        btnBor.addActionListener(pr);
        pnlIgual.add(btnTr);
        btnTr.addActionListener(pr);

        //Para poner los campos de texto en el panel
        pnlTraduc.add(decimal);
        pnlTraduc.add(binario);
        pnlTraduc.add(octal);
        pnlTraduc.add(hexadecimal);

        //Para poner los RadioButton en el panel y en el ActionListener
        pnlRadio.add(decimalR);
        decimalR.addActionListener(pr);
        pnlRadio.add(binarioR);
        binarioR.addActionListener(pr);
        pnlRadio.add(octalR);
        octalR.addActionListener(pr);
        pnlRadio.add(hexadecimalR);
        hexadecimalR.addActionListener(pr);

        //Añadimos cada panel a la ventana
        pnlIgual.setPreferredSize(new Dimension(0,50));
        this.add(lblResultado, BorderLayout.NORTH);
        this.add(pnlBotones);
        this.add(pnlIgual, BorderLayout.CENTER);
        this.add(pnlTraduc, BorderLayout.SOUTH);
        this.add(pnlRadio, BorderLayout.EAST);

        //Otros detalles de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        lblResultado.setText("0");
        lblResultado.setHorizontalAlignment(JLabel.RIGHT);
    }

    //Para cambiar de Decimal a Binario
    public void DecimalBinario(){
        String numero1;
        int numero;
        int exp;
        int digito;
        double binario1;

        numero1 = decimal.getText();
        numero = Integer.parseInt(numero1);

        exp=0;
        binario1=0;
        while(numero!=0){
            digito = numero % 2;
            binario1 = binario1 + digito * Math.pow(10, exp);
            exp++;
            numero = numero/2;
        }
        binario.setText(String.valueOf(binario1));
    }

    //Para cambiar de Decimal a Octal
    public void DecimalOctal(){
        int decimal2 = Integer.parseInt(decimal.getText());
        String octal1 = "";
        String caracteresOctales = "01234567";
        while (decimal2 > 0) {
            int residuo = decimal2 % 8;
            octal1 = (caracteresOctales.charAt(residuo) + octal1);
            decimal2 /= 8;
        }
        octal.setText(octal1);
    }

    //Para cambiar de Decimal a Hexadecimal
    public void DecimalHexadecimal(){
        int decimal1 = Integer.parseInt(decimal.getText());
        String hexadecimal1 = "";
        String caracteresHexadecimales = "0123456789abcdef";
        while (decimal1 > 0) {
            int residuo = decimal1 % 16;
            hexadecimal1 = caracteresHexadecimales.charAt(residuo) + hexadecimal1;
            decimal1 /= 16;
        }
        hexadecimal.setText(hexadecimal1);
    }

    //Para cambiar de Binario a Decimal
    public void BinarioDecimal(){
        long numero, aux, digito, decimal1;
        int exponente;
        boolean esBinario;

        numero = Long.parseLong(lblResultado.getText());
        esBinario = true;
        aux = numero;
        while (aux != 0) {
            digito = aux % 10;
            if (digito != 0 && digito != 1) {
                esBinario = false;
            }
            aux = aux / 10;
        }

        exponente = 0;
        decimal1 = 0;
        while (numero != 0) {
            digito = numero % 10;
            decimal1 = decimal1 + digito * (int) Math.pow(2, exponente);
            exponente++;
            numero = numero / 10;
        }
        decimal.setText(String.valueOf(decimal1));
    }

    //Para cambiar de Octal a Decimal
    public void OctalDecimal(){
        String onum = lblResultado.getText();
        int num = Integer.parseInt(onum, 8);
        decimal.setText(String.valueOf(num));
    }

    //Para cambiar de Hexadecimal a Decimal
    public void HexadecimalDecimal(){
        String hexnum = lblResultado.getText();
        int num = Integer.parseInt(hexnum,16);
        decimal.setText(String.valueOf(num));
    }

    //Para recoger los clicks
    class Click implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton origen = (JButton) e.getSource();
            String texto = origen.getText();
            String rdo;

            //Si hay seleccionado un RadioButton se desactivan los botones que no sirvan
            if(decimalR.isSelected()){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btn0.setEnabled(true);
                btnSum.setEnabled(true);
                btnRes.setEnabled(true);
                btnMult.setEnabled(true);
                btnDiv.setEnabled(true);
                btnIg.setEnabled(true);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                btnComa.setEnabled(true);

            } else if (binarioR.isSelected()){
                btn1.setEnabled(true);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                btn5.setEnabled(false);
                btn6.setEnabled(false);
                btn7.setEnabled(false);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btn0.setEnabled(true);
                btnSum.setEnabled(false);
                btnRes.setEnabled(false);
                btnMult.setEnabled(false);
                btnDiv.setEnabled(false);
                btnIg.setEnabled(false);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                btnComa.setEnabled(false);

            } else if (octalR.isSelected()){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btn0.setEnabled(true);
                btnSum.setEnabled(false);
                btnRes.setEnabled(false);
                btnMult.setEnabled(false);
                btnDiv.setEnabled(false);
                btnIg.setEnabled(false);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                btnComa.setEnabled(false);

            } else if (hexadecimalR.isSelected()){
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btn0.setEnabled(true);
                btnSum.setEnabled(false);
                btnRes.setEnabled(false);
                btnMult.setEnabled(false);
                btnDiv.setEnabled(false);
                btnIg.setEnabled(false);
                btnA.setEnabled(true);
                btnB.setEnabled(true);
                btnC.setEnabled(true);
                btnD.setEnabled(true);
                btnE.setEnabled(true);
                btnF.setEnabled(true);
                btnComa.setEnabled(false);

            }

            // Lo que ocurre cuando pulsas un botón
            switch (texto) {
                case "+":
                    operar(Calculadora2.SUMA);
                    break;
                case "-":
                    if(!nuevoNumero){
                        operar(Calculadora2.RESTA);
                    } else {
                        lblResultado.setText("-");
                        nuevoNumero = false;
                    }
                    break;
                case "*":
                    operar(Calculadora2.MULTIPLICACION);
                    break;
                case "/":
                    operar(Calculadora2.DIVISION);
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
                case "BORRAR":
                    lblResultado.setText("0");
                    nuevoNumero = true;
                    hayPunto = false;
                case "TRADUCIR":
                    if(binarioR.isSelected()){
                        binario.setText(lblResultado.getText());
                        BinarioDecimal();
                        DecimalOctal();
                        DecimalHexadecimal();
                    } else if(octalR.isSelected()){
                        octal.setText(lblResultado.getText());
                        OctalDecimal();
                        DecimalBinario();
                        DecimalHexadecimal();
                    } else if(hexadecimalR.isSelected()){
                        hexadecimal.setText(lblResultado.getText());
                        HexadecimalDecimal();
                        DecimalBinario();
                        DecimalOctal();
                    } else if(decimalR.isSelected()){
                        decimal.setText(lblResultado.getText());
                        DecimalBinario();
                        DecimalOctal();
                        DecimalHexadecimal();
                    }

                case "=":
                    if (operador != Calculadora2.NINGUNO){
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
                                case Calculadora2.SUMA:
                                    resultado += numero;
                                    break;
                                case Calculadora2.RESTA:
                                    resultado -= numero;
                                    break;
                                case Calculadora2.MULTIPLICACION:
                                    resultado *= numero;
                                    break;
                                case Calculadora2.DIVISION:
                                    resultado /= numero;
                                    break;
                                default:
                                    resultado = numero;
                                    break;
                            }
                            operador = Calculadora2.NINGUNO;
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

    //Para los cálculos si lo haces por decimal
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
                    case Calculadora2.SUMA:
                        resultado += numero;
                        break;
                    case Calculadora2.RESTA:
                        resultado -= numero;
                        break;
                    case Calculadora2.MULTIPLICACION:
                        resultado *= numero;
                        break;
                    case Calculadora2.DIVISION:
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