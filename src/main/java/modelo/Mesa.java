package modelo;

import observer.EventoEsperable;
import observer.IObserver;
import observer.ISubject;
import utilidades.Carta;
import utilidades.TurnManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mesa implements ISubject {

    List<IObserver> observadores;
    private final List<Jugador> jugadores;
    private  TurnManager controlDeTurno;
    private final PilaDeDescarte descarte;
    private final Mazo masoDeJuego;
    private final MesaDeJuego cartasEnMesa;

    //flags

    private boolean tomoDeCartaPermitido;
    private Boolean colorEfectPlus;
    private boolean penalizacionDos;
    private boolean esPosbJugar;
    private boolean esPosbFinDeJugada;
    private boolean faseDeDescarte;
    /*-----------------------------------------------*/

    private Integer colorEfect;
    private Jugador jugadorActual;
    private int cantidadEsperadaDeJugadores;

    public Mesa() {
        this.observadores=new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.descarte = new PilaDeDescarte();
        this.masoDeJuego = new Mazo(descarte);
        this.cartasEnMesa = new MesaDeJuego(masoDeJuego.robarCarta(), masoDeJuego.robarCarta());
        this.colorEfect = 0;

        //inicializacion de los flags
        this.colorEfectPlus = false;
        this.tomoDeCartaPermitido = true;
        this.penalizacionDos = false;
        this.esPosbJugar=true;
        this.faseDeDescarte=false;
        this.cantidadEsperadaDeJugadores=0;
    }

    public boolean setCantidadEsperadaDeJugadores(int cantidad) {
        if (cantidadEsperadaDeJugadores!=0)
        {
            return false;
        }
        this.cantidadEsperadaDeJugadores = cantidad;
        return true;
    }

    public Jugador addJugador(String nombre) {
        Jugador nuevoJugador = new Jugador(this, nombre);
        jugadores.add(nuevoJugador);

        notificar(EventoEsperable.UNION_DE_JUGADOR, "El jugador " + nombre + " se ha unido");

        if (jugadores.size() < cantidadEsperadaDeJugadores) {
            notificar(EventoEsperable.ESPERA_DE_JUGADOR, "Estamos esperando a que los jugadores se unan");
        }

        return nuevoJugador;
    }

    public void iniciarJuego() {
        System.out.println("¡Todos los jugadores conectados! Iniciando el juego...");// obs
        this.controlDeTurno = new TurnManager(jugadores);
        this.jugadorActual = controlDeTurno.getJugadorEnTurno();

        repartirMano();



        notificar(EventoEsperable.CAMBIO_EN_MANO,"se ah repartido la mano");
        notificar(EventoEsperable.CAMBIO_EN_MESA,"se repartio la mesa");

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
    public Boolean hacer_jugada(Jugador player, int idxCartaDeMesa, Carta cartaACombinar)
    {
        if (jugadorActual==null||!jugadorActual.equals(player))// si no es su turno rechaza la jugada
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
        esPosbFinDeJugada=true;

        notificar(EventoEsperable.CAMBIO_EN_MANO,"");
        notificar(EventoEsperable.CAMBIO_EN_MESA,"el jugador: "+jugadorActual.getName()+ "ah hecho juego");

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
    public Boolean hacer_jugada(Jugador player,int idxCartaDeMesa,Carta cartaACombinar,Carta dupla)
    {

        if (jugadorActual==null||!jugadorActual.equals(player))// si no es su turno rechaza la jugada
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


        //aplica penalizaciones del dos,si es el caso
        controlarDos();

        //impide que se robe carta despues de realizar jugada
        tomoDeCartaPermitido=false;

        esPosbFinDeJugada=true;//habilita pasar el turno

        notificar(EventoEsperable.CAMBIO_EN_MANO,"");
        notificar(EventoEsperable.CAMBIO_EN_MESA,"el jugador: "+jugadorActual.getName()+ "ah hecho juego");

        return true;
    }



    void doColorEfectPlus()
    {
        for (Jugador p:jugadores)
        {
            if ( !p.equals(jugadorActual) )
            {p.darCarta(masoDeJuego.robarCarta());}
        }
        notificar(EventoEsperable.CAMBIO_EN_MANO,"el jugador "+jugadorActual.getName()+" ah logrado un color doble");
    }

    public boolean tomarCarta(Jugador player)
    {
        if (jugadorActual==null|| !jugadorActual.equals(player) || !tomoDeCartaPermitido)//si no es el turno o no puede tomar carta ret false
        {
            return false;
        }



        player.darCarta(masoDeJuego.robarCarta());

        //controla el estado de dos despues de tomar la carta
        controlarDos();

        //impide tomar carta mas de una vez
        tomoDeCartaPermitido=false;

        esPosbFinDeJugada=true;//valida que puedas pasar el turno

        notificar(EventoEsperable.CAMBIO_EN_MANO,"");

        return true;
    }


    public Boolean finalDeJugada(Jugador player)
    {
        if (jugadorActual==null|| !jugadorActual.equals(player) || !esPosbFinDeJugada)// si no es su turno rechaza la jugada
        {return false;}

        doPenalizacionDos();//aca chequeo penalizacion antes de aplicar los efectos de color

        if (colorEfectPlus)
        {
            doColorEfectPlus();
        }
        colorEfectPlus=false;//lo seteo despues de aplicar penalizacion

        if (player.cartasRestantes()==0)
        {
            finDelJuego();//ganaste
        }



        esPosbJugar=false;
        penalizacionDos=false;
        colorEfectPlus=false;//setea el penalizador a false

        esPosbFinDeJugada=false;
        this.faseDeDescarte=true;
        return true;
        //avisale que tiene que tirar las cartubis el nenubi
    }

    public boolean darCartaALaMesa(Jugador player,Carta carta)
    {
        if(jugadorActual==null|| !jugadorActual.equals(player))
        {return false;}
        if (!faseDeDescarte)
        {
            return false;
        }

        player.quitarCarta(carta);
        cartasEnMesa.agregarCartaALaMesa(carta);
        colorEfect--;

        if (player.cartasRestantes()==0)
        {
            return finDelJuego();
        }
        if (colorEfect==0)
        {
            return finDeTurno();
        }
        return false;
    }
    Boolean finDeTurno()
    {

        while (cartasEnMesa.tamanioDeMesa() < cartasEnMesa.MIN_CARTAS)
        {
            cartasEnMesa.agregarCartaALaMesa(masoDeJuego.robarCarta());
        }




        tomoDeCartaPermitido=true;
        penalizacionDos=false;




        controlDeTurno.CambioDeTurno();// avanza el turno al siguiente
        jugadorActual=controlDeTurno.getJugadorEnTurno();//actualizo el jugador actual

        notificar(EventoEsperable.CAMBIO_EN_TURNO,"es el turno del jugador " +jugadorActual.getName() );
        tomoDeCartaPermitido=true;
        penalizacionDos=false;

        return true;
    }

    boolean finDelJuego()
    {

        //aca dar aviso por obs
        notificar(EventoEsperable.GANADOR,"el jugador "+jugadorActual.getName() + " ah ganado");
        return true;
    }


    @Override
    public void agregarSubscriptor(IObserver observador) {

        observadores.add(observador);

    }

    @Override
    public void notificar(EventoEsperable e, String descripcion) {
        for (IObserver o: observadores)
        {
            o.Update(e,descripcion);
        }
    }

    public LinkedList<Carta> getTableCards()
    {
        return cartasEnMesa.obtenerMesa();

    }
    public LinkedList<Carta> getPlayerCards(Jugador player)
    {
        return player.cartasDelJugador();
    }

    public int getCantidadEsperadaDeJugadores() {
        return cantidadEsperadaDeJugadores;
    }

    public int cantidadActualDeJugadores()
    {
        return jugadores.size();
    }
}
