package modelo;

public class Jugador {
    private int id;
    private String nombre;
    private Mazo mano;
    private Mesa tablero;

    public Jugador(Mesa tablero, String nombre, int id) {
        this.tablero = tablero;
        this.nombre = nombre;
        this.id = id;
    }

    protected void setMano(Mazo mano) {
        this.mano = mano;
    }

    protected Mazo getMano() {
        return mano;
    }
}
