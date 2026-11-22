package tp.modelo;
public class Expedicion {
    private final int id,exploradorId,tesorosRecuperados,celdasVisitadas,puntaje;
    private final long tiempoNanos;
    public Expedicion(int id,int exploradorId,int tesorosRecuperados,int celdasVisitadas,int puntaje,long tiempoNanos){
        this.id=id;this.exploradorId=exploradorId;this.tesorosRecuperados=tesorosRecuperados;
        this.celdasVisitadas=celdasVisitadas;this.puntaje=puntaje;this.tiempoNanos=tiempoNanos;
    }
    public int getId(){
    //TO DO
        return id;
    }
    public int getExploradorId(){
        //TO DO
        return exploradorId;
    }
    public int getTesorosRecuperados(){
        //TO DO
        return tesorosRecuperados;
    }
    public int getCeldasVisitadas(){
        //TO DO
        return celdasVisitadas;
    }
    public int getPuntaje(){
        //TO DO
        return puntaje;
    }
    public long getTiempoNanos(){
        //TO DO
        return tiempoNanos;
    }
}
