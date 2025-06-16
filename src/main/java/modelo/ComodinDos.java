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
    public Integer getValue() {
        return 2;
    }

    @Override
    public Colores getColor() {
        return this.color;
    }

    @Override
    public Boolean isNumericComodin() {
        return false;
    }

    @Override
    public Boolean isColorComodin() {
        return true;
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
        return true;
    }

    @Override
    public boolean efectoColorDoble(Carta mesa, Carta dupla) {
        return dupla.efectoColorSimple(mesa);
    }
}
