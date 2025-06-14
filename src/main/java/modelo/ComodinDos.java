package modelo;

import utilidades.Carta;
import utilidades.Colores;

public class ComodinDos extends Carta {

    public ComodinDos() {
        this.color= Colores.cualquiera;
        this.numero=2;
    }

    @Override
    public String descripcion() {
        return "comodin 2";
    }

    @Override
    public String getValue() {
        return "2";
    }

    @Override
    public String getColor() {
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
