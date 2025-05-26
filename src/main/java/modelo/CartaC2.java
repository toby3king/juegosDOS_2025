package modelo;

import utilidades.Carta;

public class CartaC2 implements Carta {

    final Integer numero=2;


    @Override
    public String descripcion() {
        return "comodin 2";
    }

    @Override
    public String getValue() {
        return "2";
    }

    @Override
    public String getColoro() {
        return "null";
    }
}
