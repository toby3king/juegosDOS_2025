package modelo;

import utilidades.Carta;

import java.util.LinkedList;

public class HandCards {

    protected LinkedList<Carta> mano;

    public HandCards() {
        this.mano = new LinkedList<>();
    }

    public void addCard(Carta nueva_carta)
    {
        mano.add(nueva_carta);
    }

    public void removeCard(Carta cartaAEliminar)
    {
         mano.remove(cartaAEliminar);
    }

    int cantidadDeCartasEnMano()
    {
        return mano.size();
    }

    LinkedList<Carta> obtenerMano()
    {
        return mano;
    }
}
