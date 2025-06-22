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
        return "|| "+this.getValue().toString()+"."+color.toString()+" ||";
    }

    @Override
    public Integer getValue() {
        return  this.numero;
    }

    @Override
    public Colores getColor() {
        return color;
    }

    @Override
    public Boolean isNumericComodin() {
        return false;
    }

    @Override
    public Boolean isColorComodin() {
        return false;
    }

    @Override
    public boolean jugadaSimplePosible(Carta mesa) {
        return mesa.isNumericComodin() || this.numero.equals(mesa.numero);//si en la mesa hay un comodin o coincide el numero es valida
    }

    @Override
    public boolean juagadaDoblePosible(Carta mesa, Carta dupla) {
        if (mesa.numero.equals(1))
        {
            return false;
        }
        if (mesa.isNumericComodin())
        {return true;}

        if(!dupla.isNumericComodin())
        {
            if (dupla.numero + this.numero != mesa.numero)//si la dupla no es un comodin y la suma de numeros no es igual.
            {return false;}
        }

        return true;
    }

    @Override
    public boolean efectoColorSimple(Carta mesa) {

        return this.getColor().esCompatibleCon(mesa.getColor());
    }

    @Override
    public boolean efectoColorDoble(Carta mesa, Carta dupla) {

        return  (this.getColor().esCompatibleCon(mesa.getColor()) && this.getColor().esCompatibleCon(dupla.getColor()));//si es compatible con las dos cartas

    }

}
