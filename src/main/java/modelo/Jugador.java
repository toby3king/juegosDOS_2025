package modelo;

public class Jugador {
    private int id;
    private String nombre;
    private Mesa tablero;
    HandCards mano;

    public Jugador(Mesa tablero, String nombre, int id) {
        this.tablero = tablero;
        this.nombre = nombre;
        this.id = id;
        this.mano = new HandCards();
    }


}
