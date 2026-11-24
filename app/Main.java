package tp.app;
import tp.modelo.*;
import tp.datos.DatosIniciales;
import tp.algoritmos.*;

import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Ciudad ciudad=crearCiudad();
        Explorador[] exploradores=crearExploradores();
        Scanner teclado=new Scanner(System.in);
        int opcion=-1;
        do{
            System.out.println("\n1. Mostrar Mapa  2. Informe  3. Explorar  4. Ordenar  0. Salir");
            System.out.print("Opción: ");
            if(!teclado.hasNextInt()){teclado.nextLine();continue;}
            opcion=teclado.nextInt();
            switch(opcion){
                case 1 :
                     ciudad.mostrarMapa();
                     break;
                case 2 :
                     ciudad.informeGeneral();
                     break;
                case 3 :
                    //Ejemplo de llamada, podria cambiar
                    System.out.println("Tesoros: " + explorar(ciudad,exploradores[0],1).getTesorosRecuperados());
                    break;
                case 4 : 
                    //COMPLETAR
                    break;
                
                case 0 : System.out.println("Fin");
                break;
                default: System.out.println("Opción inválida");
            }
        }while(opcion!=0);
    }


public static Ciudad crearCiudad(){
    /* COMPLETAR */
    Ciudad ciudad = DatosIniciales.crearCiudad();
    return ciudad;
}

public static Explorador[] crearExploradores()
 {
    Explorador [] exploradores = DatosIniciales.crearExploradores();
    return exploradores;
    //COMPLETAR 
 }

 public static Expedicion explorar(Ciudad ciudad,Explorador explorador,int idExpedicion){
        boolean[][] visitadas;
        visitadas = new boolean[ciudad.getFilas()][ciudad.getColumnas()];
        
        explorador.reiniciarResultados();
        int filaInicial = explorador.getFila();
        int columnaInicial = explorador.getColumna();
        int[] contadorVisitadas = new int[1];
        
        Expedicion expedicionMedida;
        
        long inicio = System.nanoTime();
        
        int tesoros = explorarDesde(ciudad, explorador, visitadas, explorador.getFila(),explorador.getColumna() ,contadorVisitadas);
        
        long fin = System.nanoTime();
        
        explorador.cambiarPosicion(filaInicial, columnaInicial);
        long tiempo = fin-inicio;
        expedicionMedida = new Expedicion(idExpedicion, explorador.getId(), tesoros, contadorVisitadas[0], explorador.getPuntaje(), tiempo);
        return expedicionMedida;
        // TODO: crear boolean[][] visitadas, medir con System.nanoTime(),
        // llamar al auxiliar recursivo y construir Expedicion.
        //throw new UnsupportedOperationException("TODO explorar");
}
/** El auxiliar debe incluir casos base y cuatro llamadas recursivas. */
private static int explorarDesde(Ciudad ciudad,Explorador explorador,boolean[][] visitadas,int fila,int columna,int[] contadorVisitadas){
    // TODO: contar tesoros, registrar trampas y contar celdas visitadas.
    int tesorosAlcanzables = 0;
    
    if(!ciudad.esTransitable(fila, columna) || visitadas[fila][columna]){
        tesorosAlcanzables = 0;
    }else{
        visitadas[fila][columna]=true;//marco la casilla como visitada
        contadorVisitadas[0]++;//aumento la cantidad de casillas visitadas
        explorador.cambiarPosicion(fila, columna);//muevo al explorador a la casilla analizada

        Sector casilla = ciudad.getSector(fila, columna);
        if(casilla.esTesoro()){
            tesorosAlcanzables++;
            explorador.registrarTesoro(casilla.getValor());;
        }else if(casilla.esTrampa()){
            explorador.registrarTrampa(casilla.getValor());
        }
        if (Abajo(ciudad, explorador, visitadas, fila, columna, contadorVisitadas)) {
            tesorosAlcanzables += explorarDesde(ciudad, explorador, visitadas, fila+1, columna, contadorVisitadas);
        }
        if(Derecha(ciudad, explorador, visitadas, fila, columna, contadorVisitadas)){
            tesorosAlcanzables += explorarDesde(ciudad, explorador, visitadas, fila, columna+1, contadorVisitadas);
        }
        if(Arriba(ciudad, explorador, visitadas, fila, columna, contadorVisitadas)){
            tesorosAlcanzables += explorarDesde(ciudad, explorador, visitadas, fila-1, columna, contadorVisitadas);
        }
        if (Izquierda(ciudad, explorador, visitadas, fila, columna, contadorVisitadas)){
            tesorosAlcanzables += explorarDesde(ciudad, explorador, visitadas, fila, columna-1, contadorVisitadas);
        }

    }
    return tesorosAlcanzables;
    //throw new UnsupportedOperationException("TODO explorarDesde");
}
public static boolean Abajo(Ciudad ciudad, Explorador explorador, boolean[][] visitadas, int fila, int columna, int[] contadorVisitadas){
    boolean movimiento = false;
    if(ciudad.esTransitable(fila+1, columna)){
        movimiento=true;
    }
    return movimiento;
}
public static boolean Arriba(Ciudad ciudad, Explorador explorador, boolean[][] visitadas, int fila, int columna, int[] contadorVisitadas){
    boolean movimiento = false;
    if(ciudad.esTransitable(fila-1, columna)){
        movimiento=true;
    }
    return movimiento;
}public static boolean Izquierda(Ciudad ciudad, Explorador explorador, boolean[][] visitadas, int fila, int columna, int[] contadorVisitadas){
    boolean movimiento = false;
    if(ciudad.esTransitable(fila, columna-1)){
        movimiento=true;
    }
    return movimiento;
}public static boolean Derecha(Ciudad ciudad, Explorador explorador, boolean[][] visitadas, int fila, int columna, int[] contadorVisitadas){
    boolean movimiento = false;
    if(ciudad.esTransitable(fila, columna+1)){
        movimiento=true;
    }
    return movimiento;
}
}




