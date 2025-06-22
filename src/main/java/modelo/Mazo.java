package modelo;

import utilidades.Carta;
import utilidades.Colores;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Mazo {

    private LinkedList<Carta> cartas;
    private PilaDeDescarte descarte;


    public Mazo(PilaDeDescarte p) {
        this.cartas = new LinkedList<>();
        this.descarte=p;
    }

    public void crearBarajaDeJuego() {

        Colores[] c = Colores.values();

        for (int k = 0; k < 4; k++) {
            for (int i = 1; i <= 10; i++) {
                if (i == 2) continue;

                int repeticiones = (i <= 5) ? 3 : 2;
                for (int j = 0; j < repeticiones; j++) {
                    cartas.push(new Numerada(c[k], i)); // usa pila (push)
                }
            }

            for (int h = 0; h < 3; h++) cartas.push(new ComodinDos());
            for (int h = 0; h < 2; h++) cartas.push(new ComodinNumeral(c[k]));
        }
        mezclarMazo();
    }

    public void mezclarMazo() {
        Collections.shuffle(cartas); // mezcla la pila
    }

    public Carta robarCarta() {
        if (!cartas.isEmpty())
        {
            return cartas.pop(); // como en una pila: remuve y retorna el ultimo elemnto
        } else {

            this.RestaurarMazo();//le da la pila de descarte y la mezcla, tambien vaciando la misma

            return cartas.pop();
        }
    }

    public void agregarCartaAlTope(Carta carta) {
        cartas.push(carta); // apila
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    public void verTopeDelMazo() {
        if (!cartas.isEmpty()) {
            System.out.println("Tope del mazo: " + cartas.peek().descripcion());
        } else {
            System.out.println("El mazo está vacío.");
        }
    }

    void RestaurarMazo()
    {
        this.cartas=new LinkedList<>(this.descarte.RetornarPila());

        this.descarte.vaciarPilaDeDescarte();

        this.mezclarMazo();
    }
}

