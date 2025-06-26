package controladores;

import modelo.Jugador;
import modelo.Mesa;
import observer.EventoEsperable;
import observer.IObserver;
import utilidades.Carta;
import vista.InterfazGraficaDeUsuario;

import java.util.LinkedList;

public class Controller implements IObserver
{
    InterfazGraficaDeUsuario vista;
    Mesa modelo;
    Jugador player;

    public Controller(Mesa modelo, InterfazGraficaDeUsuario vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador(this);
        modelo.agregarSubscriptor(this);
    }


        public void loguearJugador(String nombre)
        {
        this.player = modelo.addJugador(nombre);

        if (modelo.cantidadActualDeJugadores() == modelo.getCantidadEsperadaDeJugadores()) {
            modelo.iniciarJuego();
        }
    }


    public boolean establecerNDeJugadores(int cantJugadores){
        return modelo.setCantidadEsperadaDeJugadores(cantJugadores);
    }

    public int getNJugadores()
    {
        return modelo.getCantidadEsperadaDeJugadores();
    }

    public void JugarCarta(int posCartaMea,int posCartaMano)
    {
       LinkedList<Carta> cJugador= modelo.getPlayerCards(player);
       LinkedList<Carta> cMesa=modelo.getTableCards();

       if(!modelo.hacer_jugada(player,posCartaMano-1,cJugador.get(posCartaMano-1)))
       {
           vista.enviarMsjAJugador("la accion no esta permitida");
       }
    }

    public void JugarCarta(int posCartaMea,int posCartaMano,int posCartaMano2)
    {
        LinkedList<Carta> cJugador= modelo.getPlayerCards(player);
        LinkedList<Carta> cMesa=modelo.getTableCards();

        if(!modelo.hacer_jugada(player,posCartaMano-1,cJugador.get(posCartaMano-1),cJugador.get(posCartaMano2-1)))
        {
            vista.enviarMsjAJugador("la accion no esta permitida");
        }

    }

    public void tomarCartaDelMazo()
    {
        if(modelo.tomarCarta(player))
        {
            vista.enviarMsjAJugador("la accion no esta permitida");
        }
    }

    public void dejarCarta(int posCartaEnMano)
    {
        LinkedList<Carta> cJugador=modelo.getPlayerCards(player);

        if (modelo.darCartaALaMesa(player,cJugador.get(posCartaEnMano-1)))
        {
            vista.enviarMsjAJugador("la accion no esta permitida,ya jugaste o levantaste");
        }
    }

    public void terminarJurgos()
    {
        if(modelo.finalDeJugada(player))
        {
            vista.enviarMsjAJugador("la accion no esta permitida,todavia no jugaste");
        }
    }

    @Override
    public void Update(EventoEsperable event, String descripcion) {
            switch (event) {
                case CAMBIO_EN_MESA:
                    vista.actualizarMesaDeJuego(modelo.getTableCards());
                    vista.enviarMsjAJugador(descripcion);
                    break;

                case CAMBIO_EN_MANO:
                    vista.actualizarManoDeJugador(modelo.getPlayerCards(player));
                    break;

                case GANADOR:
                    vista.terminarJuego(descripcion);
                    break;

                case UNION_DE_JUGADOR:
                    vista.enviarMsjAJugador(descripcion);
                    break;

                case ESPERA_DE_JUGADOR:
                    vista.enviarMsjAJugador(descripcion);
                    break;

                case CAMBIO_EN_TURNO:
                    vista.enviarMsjAJugador(descripcion);
                    break;

                default:
                    // Por si en el futuro aparece un evento no manejado
                    System.out.println("Evento no esperado: " + event);
                    break;
            }
        }

    }



