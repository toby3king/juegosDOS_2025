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
        return "|| #,"+this.color.toString()+" ||";
    }

    @Override
    public Integer getValue() {
        return 0;
    }

    @Override
    public Colores getColor() {
        return color;
    }

    @Override
    public Boolean isNumericComodin() {
        return true;
    }

    @Override
    public Boolean isColorComodin() {
        return  false;
    }

    @Override
    public boolean jugadaSimplePosible(Carta mesa) {
        return true;
    }

    @Override
    public boolean juagadaDoblePosible(Carta mesa, Carta dupla) {
        if (mesa.isNumericComodin())
        {return true;}

        if (dupla.numero>=mesa.numero)
        {return false;}

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
