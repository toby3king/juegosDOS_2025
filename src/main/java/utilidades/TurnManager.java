package utilidades;

import modelo.Jugador;

import java.util.LinkedList;
import java.util.List;

public class TurnManager {


    List<Jugador> jugadores;
    Jugador jugadorEnTurno;
    int cantidad_jugadores;

    public TurnManager(List<Jugador> jugadores)
    {
        this.jugadores=new LinkedList<>();
        this.cantidad_jugadores= jugadores.size();
        this.jugadorEnTurno=jugadores.getFirst();

    }

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void CambioDeTurno()
    {
        if (jugadorEnTurno.equals(jugadores.getLast()))
        {
            jugadorEnTurno=jugadores.getFirst();
        }
        jugadorEnTurno=jugadores.get(jugadores.indexOf(jugadorEnTurno)+1);//el siguiente del actual
    }
}
