package modelo;

import utilidades.Carta;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class ConjuntoDeCartas {

    private LinkedList<Carta> cartas;


    public ConjuntoDeCartas() {
        this.cartas = new LinkedList<>();

    }

    protected Integer getSize()
    {return cartas.size();}

    protected void reOrder()
    {
        Collections.shuffle(this.cartas);
    }

    protected void addCard(Carta card)
    {
        cartas.add(card);
    }

    protected boolean removeCard(Carta card)
    {
       return cartas.remove(card);
    }

    protected LinkedList<Carta> seeCards()
    {
        LinkedList<Carta> aux=new LinkedList<>();
        for (Carta c:cartas){
            aux.add(c);
        }
        return aux;
    }

    protected Carta getCard(){
        if (cartas.isEmpty())
        {return null;}
        return cartas.removeLast();
    }

    protected Carta getCard(int index)
    {
        if (index>cartas.size()-1||index<0)
        {return null;}

        return cartas.remove(index);

    }
}
