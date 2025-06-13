package modelo;

import utilidades.Carta;
import utilidades.Colores;

public class ComodinNumeral extends Carta {

    private Colores color;

    public ComodinNumeral(Colores color) {
        this.color = color;
    }

    @Override
    public String descripcion() {
        return "comodin numeral, color: "+this.color.toString();
    }

    @Override
    public String getValue() {
        return "0";
    }

    @Override
    public String getColoro() {
        return color.toString();
    }

    @Override
    public Boolean isNumericComodin() {
        return true;
    }

    @Override
    public Boolean isColorComodin() {
        return  false;
    }

    public Colores getColor() {
        return color;
    }
}
