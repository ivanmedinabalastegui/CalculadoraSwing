import javax.swing.*;

public class MenuCalculadora {
    public static void main(String[] args){
        int opcion = 0;
        String menu = "1) Opción básica\n2) Opción programador\n3) Salir\n ";
        try {
            do{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Menú", JOptionPane.INFORMATION_MESSAGE));
                switch(opcion){
                    case 1:
                        new Calculadora();
                        break;

                    case 2:
                        new Calculadora2();
                        break;

                    case 3:
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                        break;
                }
            }while(opcion != 1 && opcion != 2 && opcion != 3);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Debe elegir una opción");
        }
    }
}
