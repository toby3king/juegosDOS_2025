package modelo;

import utilidades.Carta;
import utilidades.Colores;

public class CartaCnumeral implements Carta {

    private Colores color;

    public CartaCnumeral(Colores color) {
        this.color = color;
    }

    @Override
    public String descripcion() {
        return "comodin numeral, color: "+this.color.toString();
    }

    @Override
    public String getValue() {
        return "null";
    }

    @Override
    public String getColoro() {
        return color.toString();
    }

    public Colores getColor() {
        return color;
    }
}
