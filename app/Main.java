package tp.app;
import tp.modelo.*;
import tp.datos.DatosIniciales;
import tp.algoritmos.*;

import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
    
    //codigo auxiliar para mostrar emojis en la consola 
        try {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
    } catch (java.io.UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    
        Ciudad ciudad=crearCiudad();
        Explorador[] exploradores=crearExploradores();
        Scanner teclado=new Scanner(System.in);
        int opcion=-1;
        do{
            System.out.println("\n1. Mostrar Mapa  2. Informe  3. Explorar  4. Ordenar  0. Salir");
            System.out.print("Opcion: ");
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
                    System.out.println("Tesoros encontrados: " + explorar(ciudad,exploradores[0],1).getTesorosRecuperados());
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
    //utilizo el metodo de la clase DatosIniciales
    Ciudad ciudad = DatosIniciales.crearCiudad();
    return ciudad;
}

public static Explorador[] crearExploradores()
 {
    //utilizo el metodo de la clase DatosIniciales
    Explorador [] exploradores = DatosIniciales.crearExploradores();
    return exploradores;
 }

 public static Expedicion explorar(Ciudad ciudad,Explorador explorador,int idExpedicion){
        //Se crea la matriz de booleanos, de las misma dimensiones que la ciudad y ayuda a tener registro de que casillas fueron visitadas y cuales no
        boolean[][] visitadas;
        visitadas = new boolean[ciudad.getFilas()][ciudad.getColumnas()];
        //se reinician los resultados del explorador por si ya participo en otra expedicion
        explorador.reiniciarResultados();
        //se obtienen la fila y columna iniciales del explorador para asi devolverlo a esta una vez termine la exploracion, evitando errores si vuelve a realizar otra epxloracion
        int filaInicial = explorador.getFila();
        int columnaInicial = explorador.getColumna();
        // se crea un arreglo de un solo elemento para poder pasar por referencia la cantidad de casillas visitadas y asi poder modificarlo dentro del metodo recursivo explorarDesde
        int[] contadorVisitadas = new int[1];
        
        //inicializo la exploracion a retornar.
        Expedicion expedicionMedida;
        //comienzo a medir el tiempo de ejecucion de la exploracion
        long inicio = System.nanoTime();
        //realizo la exploracion con el metodo recursivo, devolviendo la cantidad de tesoros encontrados y guardandola en la variable tesoros
        int tesoros = explorarDesde(ciudad, explorador, visitadas, explorador.getFila(),explorador.getColumna() ,contadorVisitadas);
        
        long fin = System.nanoTime();
        
        explorador.cambiarPosicion(filaInicial, columnaInicial);
        long tiempo = fin-inicio;
        expedicionMedida = new Expedicion(idExpedicion, explorador.getId(), tesoros, contadorVisitadas[0], explorador.getPuntaje(), tiempo);
        return expedicionMedida;
        
        
        
}
/** El auxiliar debe incluir casos base y cuatro llamadas recursivas. */
private static int explorarDesde(Ciudad ciudad,Explorador explorador,boolean[][] visitadas,int fila,int columna,int[] contadorVisitadas){
   // este modulo devuelve la cantidad de tesoros alcanzables por el explorador desde la posicion que se le pasa como parametro, ademas de ir registrando la cantidad de casillas visitadas y el puntaje del explorador.
    int tesorosAlcanzables = 0;
    
    if(!ciudad.esTransitable(fila, columna) || visitadas[fila][columna]){ // si la casilla no es transitable O ya fue visitada, detiene el metodo recursivo devolviendo 0 tesoros.
        tesorosAlcanzables = 0;
    }else{
        //sino, marca la casilla visitada, aumenta la cantidad de casillas visitadas y mueve al explorador a la casilla que paso el caso base.
        visitadas[fila][columna]=true;//marco la casilla como visitada
        contadorVisitadas[0]++;//aumento la cantidad de casillas visitadas
        explorador.cambiarPosicion(fila, columna);//muevo al explorador a la casilla analizada
        
        // se establece la casilla actual, verificando su tipo y el valor que le agregara (o restara) al puntaje del explorador. 
        // Ademas dependiendo del tipo se sumara la cantidad de tesoros encontrados o las trampas encontradas
        Sector casilla = ciudad.getSector(fila, columna);
        if(casilla.esTesoro()){
            tesorosAlcanzables++;
            explorador.registrarTesoro(casilla.getValor());;
        }else if(casilla.esTrampa()){
            explorador.registrarTrampa(casilla.getValor());
        }

        // luego se verifica mediante los modulos de direccion en que camino puede moverse el explorador, siguiendo el orden de: 
        // abajo-> derecha-> arriba -> izquierda
        // si puede moverse en esa direccion, se hace la llamada recursiva a explorarDesde con la nueva posicion del explorador y se suma a la cantidad de tesoros encontrados.
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
   
}
//verifica si la casilla de x direccion a la del explorador es transitable y devuelve un true si es el caso o un false si no.
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


