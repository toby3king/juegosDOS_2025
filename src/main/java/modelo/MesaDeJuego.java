package modelo;

import utilidades.Carta;

import java.util.LinkedList;

public class MesaDeJuego {

    private final LinkedList<Carta> cardsInGame;
    final Integer MIN_CARTAS=2;

    public MesaDeJuego(Carta c1,Carta c2)
    {
        cardsInGame=new LinkedList<>();
        cardsInGame.add(c1);
        cardsInGame.add(c2);
    }

    public Integer tamanioDeMesa()
    {
       return cardsInGame.size();
    }

    public void agregarCartaALaMesa(Carta c1)
    {
        cardsInGame.add(c1);
    }

    public Carta removerCarta(int indx)
    {
        return cardsInGame.remove(indx);
    }

    public Carta obtenerCarta(int indx)
    {
        return cardsInGame.get(indx);
    }





}
