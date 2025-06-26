import controladores.Controller;
import modelo.Mesa;
import vista.*;

public class Prueba {
    public static void main(String[] args) {

        Mesa juego=new Mesa();
        InterfazGraficaDeUsuario vista=new InterfazGraficaDeUsuario();
        Controller c=new Controller(juego,vista);

        InterfazGraficaDeUsuario vista2=new InterfazGraficaDeUsuario();
        Controller c2=new Controller(juego,vista2);

    }

}
