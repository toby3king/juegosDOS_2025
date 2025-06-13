package modelo;

import utilidades.Carta;

public class ComodinDos extends Carta {

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
        return "";
    }

    @Override
    public Boolean isNumericComodin() {
        return false;
    }

    @Override
    public Boolean isColorComodin() {
        return true;
    }


}
