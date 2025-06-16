package modelo;

import utilidades.Carta;
import utilidades.Turno;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private final List<Jugador> jugadores;
    private Turno turno;
    private final PilaDeDescarte descarte;


    private List<Carta> cartasEnJuego;
    private Mazo masoDeJuego;

    private Boolean colorEfect;
    private Boolean colorEfectPlus;

    public Mesa(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        descarte=new PilaDeDescarte();
        colorEfect=false;
        colorEfectPlus=false;

    }

    Boolean hacer_jugada(Jugador player, int idxCartaDeMesa, Carta cartaACombinar)
    {
        if (!(turno.getTurno()==jugadores.indexOf(player)))// si no es su turno rechaza la jugada
        {return false;}


        if ( !cartaACombinar.jugadaSimplePosible(cartasEnJuego.get(idxCartaDeMesa)) ) //si los valores no coinciden se rechaza la jugada
        {return false;}

        Carta aux= cartasEnJuego.remove(idxCartaDeMesa);
        this.descarte.descartar(aux);
        this.descarte.descartar(cartaACombinar);

        player.mano.removeCard(cartaACombinar);

        if( cartasEnJuego.get(idxCartaDeMesa).getColor().contains(cartaACombinar.getColor()) )
        {
            colorEfect=true;
        }


        return true;
    }

    Boolean hacer_jugada(Jugador player,int idxCartaDeMesa,Carta cartaACombinar,Carta cartaACombinar2)
    {

        if (!(turno.getTurno()==jugadores.indexOf(player)))// si no es su turno rechaza la jugada
        {return false;}





        return true;
    }

    Boolean finDeTurno(Jugador player)
    {
        if (!(turno.getTurno()==jugadores.indexOf(player)))// si no es su turno rechaza la jugada
        {return false;}

        if (colorEfect)
        {//aca va la penalizacion a cada cujador
        }
        colorEfect=false;//setea el descarte a false
        colorEfectPlus=false;//setea el penalizador a false
        turno.siguiente();// avanza el turno al siguiente


        return true;
    }




}
