package modelo;

import utilidades.Carta;
import utilidades.Colores;

public class Numerada extends Carta {

    Integer numeroDeCarta;

    Colores color;

    public Numerada(Colores color,int numeroDeCarta)
    {
        this.color=color;
        this.numeroDeCarta=(Integer) numeroDeCarta;
    }


    @Override
    public String descripcion() {
        return "carta numero: "+this.numeroDeCarta.toString()+" color: "+color.toString();
    }

    @Override
    public String getValue() {
        return  numeroDeCarta.toString();
    }

    @Override
    public String getColoro() {
        return color.toString();
    }

    @Override
    public Boolean isNumericComodin() {
        return false;
    }

    @Override
    public Boolean isColorComodin() {
        return false;
    }
}
