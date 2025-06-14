package modelo;

import utilidades.Carta;
import utilidades.Colores;

public class Numerada extends Carta {


    public Numerada(Colores color,int numeroDeCarta)
    {
        this.color=color;
        this.numero=(Integer) numeroDeCarta;
    }


    @Override
    public String descripcion() {
        return "carta numero: "+this.getValue()+" color: "+color.toString();
    }

    @Override
    public String getValue() {
        return  this.numero.toString();
    }

    @Override
    public String getColor() {
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
