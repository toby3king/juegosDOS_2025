package vista;

import controladores.Controller;
import utilidades.Carta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InterfazGraficaDeUsuario {

    //atributos
    VentanaEOFG finDeJuego;
    VentanaJuego mesaDeJuego;
    VentanaLoguin login;
    VentanaInicio config;
    Controller controlador;


    //constructor
    public InterfazGraficaDeUsuario() {
        this.config = new VentanaInicio();
        this.login =new VentanaLoguin();
        this.mesaDeJuego = new VentanaJuego();
        config.setVisible(true);

        //crear los manejadores de eventos
        config.setInitButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux=config.getNJugadores();
                if (!controlador.establecerNDeJugadores(Integer.parseInt(aux)))
                {
                    mesaDeJuego.setMensaje("la mesa ya habia selecionado tamanio");

                    esconderConfig();

                    login.setVisible(true);

                }
                esconderConfig();
                login.setVisible(true);

            }
        });

        login.clickIniciar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux=login.getNombreUsuario();
                controlador.loguearJugador(aux);
                login.setVisible(false);
                mesaDeJuego.setVisible(true);
            }
        });

       mesaDeJuego.setRealizarButton(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               String aux=mesaDeJuego.getComandoField();

               if (aux.equals("tt"))
               {
                   controlador.terminarJurgos();
               }
               if (aux.equals("rc"))
               {
                   controlador.tomarCartaDelMazo();
               }
               if (aux.startsWith("hj"))
               {
                   String[] partes = aux.split(" ");//poner separados por espacios
                   if (partes.length==3)
                   {
                       controlador.JugarCarta(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]));//mesa,mano
                   }
                   else if(partes.length==4)
                   {
                       controlador.JugarCarta(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Integer.parseInt(partes[3]));//mesa,mano,mano
                   }
                   System.out.println("no funciono");
               }
               if (aux.startsWith("tc"))
               {
                   String[] partes = aux.split(" ");

                   controlador.dejarCarta(Integer.parseInt(partes[1]));
               }
           }
       });

        //fin constructor
    }


    public void actualizarManoDeJugador(List<Carta> cards){

        List<String> casteo=new LinkedList<>();

        for (Carta c:cards)
        {
            casteo.add(c.descripcion());
        }

        mesaDeJuego.actualizarMano(casteo);

    }

    public void actualizarMesaDeJuego(List<Carta> cards){

        List<String> casteo=new LinkedList<>();

        for (Carta c:cards)
        {
            casteo.add(c.descripcion());
        }

        mesaDeJuego.actualizarMesa(casteo);

    }
    public void enviarMsjAJugador(String txt)
    {
        mesaDeJuego.setMensaje(txt);

    }

    public void setControlador(Controller controlador) {
        this.controlador = controlador;
    }

    public void terminarJuego(String ganador){
        mesaDeJuego.setVisible(false);
        finDeJuego=new VentanaEOFG(ganador);
        finDeJuego.setVisible(true);
    }
    public void esconderConfig(){
        config.setVisible(false);
    }





}

