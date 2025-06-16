package modelo;

import utilidades.Carta;

import java.util.LinkedList;

public class PilaDeDescarte {

    private LinkedList<Carta> pila;

    void descartar(Carta descarte)
    {
        pila.add(descarte);
    }

    LinkedList<Carta> obtenerMazo()
    {
        return pila;
    }

}
