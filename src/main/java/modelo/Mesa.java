package modelo;

import utilidades.Carta;
import utilidades.TurnManager;
import java.util.List;


/**
* es la clase principal del juego, encargada de controlar las jugadas y las penalizaciones aplicadas a los jugadores
 * */
public class Mesa {

    private final List<Jugador> jugadores;
    private final TurnManager controlDeTurno;
    private final PilaDeDescarte descarte;
    private final Mazo masoDeJuego;
    private final MesaDeJuego cartasEnMesa;
    private Integer colorEfect;
    private Boolean colorEfectPlus;
    private boolean tomoDeCartaPermitido;
    private Jugador jugadorActual;
    private boolean penalizacionDos;

    public Mesa(List<Jugador> jugadores,TurnManager controlDeTurno) {

        this.controlDeTurno=controlDeTurno;
        this.jugadores = jugadores;
        this.colorEfect=0;
        this.colorEfectPlus=false;
        this.descarte=new PilaDeDescarte();
        this.masoDeJuego=new Mazo(descarte);
        this.cartasEnMesa=new MesaDeJuego(masoDeJuego.robarCarta(), masoDeJuego.robarCarta());
        this.jugadorActual=controlDeTurno.getJugadorEnTurno();
        this.tomoDeCartaPermitido=true;
        this.penalizacionDos=false;

        repartirMano();


    }

    /**
     * reparte la mano inicial a cada jugador, iterando sobre la lista de jugadores, añadiendo a su mano cartas que saca del mazo
     */
    void repartirMano()
    {
        for(Jugador p:jugadores)
        {
            for(int i=0;i<7;i++)
            {
                p.darCarta(masoDeJuego.robarCarta());
            }
        }
    }

    void doPenalizacionDos()
    {
        for (int i=0;i<=1;i++)
        {
            jugadorActual.darCarta(masoDeJuego.robarCarta());
        }
    }

    void controlarDos()
    {
        if (jugadorActual.dosState)
        {
            if (jugadorActual.cartasRestantes()!=2)
            {penalizacionDos=true;}
        }
        if (jugadorActual.cartasRestantes()==2)
        {penalizacionDos=true;}
    }

    /**
     * el metodo verifica si la jugada que quiere realizar el jugador es valida
     * @param player
     * @param idxCartaDeMesa
     * @param cartaACombinar
     * @return retorna un booleano que determina si es posible realizar la jugada
     */
    Boolean hacer_jugada(Jugador player, int idxCartaDeMesa, Carta cartaACombinar)
    {
        if (!jugadorActual.equals(player))// si no es su turno rechaza la jugada
        {return false;}


        if ( !cartaACombinar.jugadaSimplePosible(cartasEnMesa.obtenerCarta(idxCartaDeMesa)) ) //si los valores no coinciden se rechaza la jugada
        {return false;}


        if(cartaACombinar.efectoColorSimple(cartasEnMesa.obtenerCarta(idxCartaDeMesa)) )
        {
            colorEfect+=1;
        }

        Carta aux=cartasEnMesa.removerCarta(idxCartaDeMesa);
        this.descarte.descartar(aux);//saco la de la mesa
        this.descarte.descartar(cartaACombinar);//descarto la del jugador
        jugadorActual.quitarCarta(cartaACombinar);



        controlarDos();
        tomoDeCartaPermitido=false;
        return true;
    }

    /**
     *
     * @param player
     * @param idxCartaDeMesa
     * @param cartaACombinar
     * @param dupla
     * @return retorna un booleano que determina si es posible realizar la jugada
     */
    Boolean hacer_jugada(Jugador player,int idxCartaDeMesa,Carta cartaACombinar,Carta dupla)
    {

        if (!jugadorActual.equals(player))// si no es su turno rechaza la jugada
        {
            return false;
        }

        if (!cartaACombinar.juagadaDoblePosible(cartasEnMesa.obtenerCarta(idxCartaDeMesa),dupla))
        {
            return false;
        }


       if( cartaACombinar.efectoColorDoble(cartasEnMesa.obtenerCarta(idxCartaDeMesa),dupla) )
       {
           colorEfect+=1;
           colorEfectPlus=true;
       }

        Carta aux=cartasEnMesa.obtenerCarta(idxCartaDeMesa);

       //muevelas cartas a la pila de descarte
        descarte.descartar(aux);
        descarte.descartar(cartaACombinar);
        descarte.descartar(dupla);

        jugadorActual.quitarCarta(cartaACombinar); //remueve las cartas de la mano del jugador
        jugadorActual.quitarCarta(dupla);//remueve las cartas de la mano del jugador

        //de retirar de la mano se encarga el jugador



        //aplica penalizaciones del dos,si es el caso
        controlarDos();
        //impide que se robe carta despues de realizar jugada
        tomoDeCartaPermitido=false;
        return true;
    }

    void doColorEfect(Carta paraLaMesa)
    {
        if (jugadorActual.cartasRestantes()<=colorEfect)
        {
            //aca ganas
        }
        for (int i=0;i<colorEfect;i++)
        {
            cartasEnMesa.agregarCartaALaMesa(paraLaMesa);
        }
    }

    void doColorEfectPlus()
    {
        for (Jugador p:jugadores)
        {
            if ( !p.equals(jugadorActual) )
            {p.darCarta(masoDeJuego.robarCarta());}
        }
    }

    boolean tomarCarta(Jugador player)
    {
        if (!jugadorActual.equals(player))
        {
            return false;
        }

        if (!tomoDeCartaPermitido)
        {
            return false;
        }

        player.darCarta(masoDeJuego.robarCarta());

        //controla el estado de dos despues de tomar la carta
        controlarDos();

        //impide tomar carta mas de una vez
        tomoDeCartaPermitido=false;

        return true;
    }


    Boolean finDeTurno(Jugador player)
    {
        if (!jugadorActual.equals(player))// si no es su turno rechaza la jugada
        {return false;}

        doPenalizacionDos();//aca chequeo penalizacion antes de aplicar los efectos de color

        if (colorEfectPlus)
        {
            doColorEfectPlus();
        }


        while (cartasEnMesa.MIN_CARTAS< cartasEnMesa.tamanioDeMesa())
        {
            cartasEnMesa.agregarCartaALaMesa(masoDeJuego.robarCarta());
        }

        colorEfect=0;//setea el descarte a false
        colorEfectPlus=false;//setea el penalizador a false
        controlDeTurno.CambioDeTurno();// avanza el turno al siguiente
        jugadorActual=controlDeTurno.getJugadorEnTurno();//actualizo el jugador actual
        tomoDeCartaPermitido=true;
        penalizacionDos=false;

        return true;
    }

    void FinDelJuego()
    {
        System.out.println("el jugador "+jugadorActual.getName() +"ah ganado!!!!!");
    }

        //programar decir dos.




}
