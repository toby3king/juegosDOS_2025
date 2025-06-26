package observer;

public interface ISubject {

    void agregarSubscriptor(IObserver observador);
    void notificar(EventoEsperable e,String descripcion);

}
