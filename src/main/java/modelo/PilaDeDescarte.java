package modelo;

import utilidades.Carta;

import java.util.LinkedList;

public class PilaDeDescarte {

    private LinkedList<Carta> pila;

    public PilaDeDescarte()
    {
        this.pila=new LinkedList<>();
    }

    void descartar(Carta descarte)
    {
        pila.add(descarte);
    }

    LinkedList<Carta> RetornarPila()
    {
        return pila;
    }
    void vaciarPilaDeDescarte()
    {
        pila.clear();
    }



}
