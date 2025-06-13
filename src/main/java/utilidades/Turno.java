package utilidades;

public class Turno {

    private int cantidad_jugadores;
    private int turno;

    public Turno(int c_jugadores)
    {
        this.cantidad_jugadores=c_jugadores;
        turno=0;
    }

    public int getTurno() {
        return this.turno;
    }

    public void siguiente()
    {
        if(turno==cantidad_jugadores-1)
        {
            turno=0;
        }
        turno++;
    }
}
