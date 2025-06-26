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

       //temporal hasta mejorar interfaz
        mesaDeJuego.setRealizarButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = mesaDeJuego.getComandoField().trim();

                if (aux.startsWith("hj")) {
                    // hj(1,1) o hj(1,1,1)
                    List<Integer> params = extraerParametros(aux);
                    if (params.size() == 2) {
                        controlador.JugarCarta(params.get(0), params.get(1));
                    } else if (params.size() == 3) {
                        controlador.JugarCarta(params.get(0), params.get(1), params.get(2));
                    } else {
                        mesaDeJuego.setMensaje("Formato de hj inválido");
                    }
                } else if (aux.startsWith("tc")) {
                    // tc(1)
                    List<Integer> params = extraerParametros(aux);
                    if (params.size() == 1) {
                        controlador.dejarCarta(params.get(0));
                    } else {
                        mesaDeJuego.setMensaje("Formato de tc inválido");
                    }
                } else if (aux.equals("tt")) {
                    controlador.terminarJurgos();
                } else if (aux.equals("rc")) {
                    controlador.tomarCartaDelMazo();
                } else {
                    mesaDeJuego.setMensaje("Comando no reconocido");
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



    //utilidades
    //temporal hasta mejorar la interfaz:deprecated
    private List<Integer> extraerParametros(String comando) {
        List<Integer> params = new ArrayList<>();
        try {
            int start = comando.indexOf('(');
            int end = comando.indexOf(')');
            if (start != -1 && end != -1 && end > start) {
                String contenido = comando.substring(start + 1, end);
                String[] partes = contenido.split(",");
                for (String parte : partes) {
                    params.add(Integer.parseInt(parte.trim()));
                }
            }
        } catch (NumberFormatException e) {
            // Podés manejar el error si querés: mostrar mensaje, etc.
            mesaDeJuego.setMensaje("Error en parámetros numéricos");
        }
        return params;
    }


}

