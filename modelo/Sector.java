package tp.modelo;
/** null en la matriz significa muro; un Sector nunca es muro. */
public class Sector {
    public static final int INICIO=0, LIBRE=1, TESORO=2, TRAMPA=3; //final significa que es una constante
    private int tipo;
    private int valor; // Recompensa positiva del tesoro o penalización positiva de la trampa.
    
    public Sector(int xtipo,int xvalor) {
        this.tipo = xtipo;
        this.valor = xvalor;
    }

    public int getTipo(){
        return this.tipo;
    }
    public int getValor(){
        return this.valor;
    }

    private String getEmoji(){
        //Devuelve un simbolo para cada sector. Ver donde lo pueden utilizar
        String simbolo = 
                 (this.getTipo() == 0) ? "🚩" :
                 (this.getTipo() == 1) ? "⬜" :
                 (this.getTipo() == 2) ? "💎" : "⚠️";
        return simbolo;
    }
    public boolean esTesoro(){
        boolean verifTesoro = 
                    (this.getTipo()==2)? true : false;
        return verifTesoro;
    }
    public boolean esTrampa(){
        boolean verifTrampa =
                    (this.getTipo()==3)? true : false;
        return verifTrampa;
    }
}
