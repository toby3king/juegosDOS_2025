import modelo.Mazo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Mazo baraja=new Mazo();

        baraja.crearBarajaDeJuego();

        System.out.println(baraja.largoDeBaraja());

        baraja.mezclarMazo();

        baraja.voltearCarta();

        baraja.voltearCarta();

        baraja.voltearCarta();


    }
}