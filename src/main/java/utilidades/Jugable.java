package utilidades;

public interface Jugable {

    boolean jugadaSimplePosible(Carta mesa);
    boolean juagadaDoblePosible(Carta mesa,Carta dupla);
    boolean efectoColorSimple(Carta mesa);
    boolean efectoColorDoble(Carta mesa, Carta dupla);
}
