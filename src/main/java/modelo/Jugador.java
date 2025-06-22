package modelo;

import jdk.jshell.execution.JdiExecutionControl;
import utilidades.Carta;

public class Jugador {
    private int id;
    private String nombre;
    private Mesa tablero;
    private HandCards mano;
    Boolean dosState;


    String getName()
    {
        return nombre;
    }

    int getId()
    {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // es el mismo objeto
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // null o clase distinta
        }
        if ( ((Jugador) obj).id==this.id )
        {
            return true;
        }
        return false;
    }

    public Jugador(Mesa tablero, String nombre, int id) {
        this.tablero = tablero;
        this.nombre = nombre;
        this.id = id;
        this.mano = new HandCards();
        this.dosState=false;
    }

    void decirDos()
    {
        dosState=true;
    }

    public void darCarta(Carta cartaNueva)
    {
        mano.addCard(cartaNueva);
    }

    public void quitarCarta(Carta cartaARemover) {
         mano.removeCard(cartaARemover);
    }



    int cartasRestantes()
    {
        return mano.cantidadDeCartasEnMano();
    }



}
