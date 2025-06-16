package utilidades;

public enum Colores {
    azul,
    rojo,
    verde,
    amarillo,
    cualquiera;


    // Compara este color con otro, permitiendo que 'cualquiera' sea comodín
    public boolean esCompatibleCon(Colores otro) {
        return this == cualquiera || otro == cualquiera || this == otro;
    }
}
