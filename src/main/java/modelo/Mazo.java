package modelo;

import utilidades.Carta;
import utilidades.Colores;

import java.util.*;

public class Mazo {


    private Queue<Carta> baraja;

    public Mazo() {
        this.baraja = new LinkedList<>();
    }


    public void crearBarajaDeJuego() {
        Colores[] c = Colores.values();

        for (int k = 0; k < 4; k++) {
            for (int i = 1; i <= 10; i++) {
                if (i == 2) continue;

                int repeticiones = (i <= 5) ? 3 : 2;
                for (int j = 0; j < repeticiones; j++) {
                    baraja.add(new Numerada(c[k], i));
                }
            }

            for (int h = 0; h < 3; h++) baraja.add(new ComodinDos());
            for (int h = 0; h < 2; h++) baraja.add(new ComodinNumeral(c[k]));
        }
    }

    public void mezclarMazo() {
        List<Carta> temporal = new ArrayList<>(baraja);
        Collections.shuffle(temporal);
        baraja = new LinkedList<>(temporal);
    }

    public void voltearCarta() {
        if (!baraja.isEmpty()) {
            Carta carta = baraja.poll();
            System.out.println(carta.descripcion());
        } else {
            System.out.println("La baraja está vacía.");
        }
    }

    public int largoDeBaraja() {
        return baraja.size();
    }


}
