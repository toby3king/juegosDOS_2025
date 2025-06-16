package utilidades;

public abstract class Carta implements Jugable {

    public Integer numero;
    public Colores color;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Carta otra = (Carta) obj;
        return this.getValue().equals(otra.getValue()) && this.getColor().equals(otra.getColor());
    }

    public abstract String descripcion();
    public abstract Integer getValue();
    public abstract Colores getColor();  // o getColor(), si lo vas a renombrar
    public abstract Boolean isNumericComodin();
    public abstract Boolean isColorComodin();
}

