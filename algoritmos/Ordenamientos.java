package tp.algoritmos;
import java.util.Scanner;
import java.util.Scanner;
import tp.modelo.Expedicion;
public final class Ordenamientos {
    private Ordenamientos(){}
    public static final int PUNTAJE=1,TESOROS=2,TIEMPO=3,CELDAS=4;
    /** Compara según criterio: negativo si a va antes que b, positivo si va después.
     * Empates: id ascendente para obtener orden determinista.
     */
    //public static int comparar(Expedicion a,Expedicion b,int criterio){
        // TODO: puntaje y tesoros descendentes; tiempo y celdas ascendentes.
        //throw new UnsupportedOperationException("TODO comparar");
    // }
    /** Ordenar in-place; elegir un algoritmo de fuerza bruta. */
    public static void ordenarFuerzaBruta(Expedicion[] datos){
        Scanner teclado=new Scanner(System.in);
        int criterio = -1;
        do{  
            System.out.println("\n1. PUNTAJE  2. TESOROS  3. TIEMPO  4. CELDAS  0. Salir");
            System.out.println("Seleccione el criterio: ");
            if(!teclado.hasNextInt()){teclado.nextLine();continue;}
            criterio=teclado.nextInt();
        switch(criterio){
            case 1: 
                for(int i=0; i<datos.length; i++){
                    Expedicion aux = datos[i];
                    int j = i;
                    while (j>0 && aux.getPuntaje() > datos[j-1].getPuntaje()){
                        datos[j] = datos[j-1];
                        j--;
                    }
                    datos[j] = aux;
                }
            break;
            case 2:
                for(int i=0; i<datos.length; i++){
                    Expedicion aux = datos[i];
                    int j = i;
                    while (j>0 && aux.getTesorosRecuperados() > datos[j-1].getTesorosRecuperados()){
                        datos[j] = datos[j-1];
                        j--;
                    }
                    datos[j] = aux;
                }
            break;
            case 3:
                for(int i=0; i<datos.length; i++){
                    Expedicion aux = datos[i];
                    int j = i;
                    while (j>0 && aux.getTiempoNanos() < datos[j-1].getTiempoNanos()){
                        datos[j] = datos[j-1];
                        j--;
                    }
                    datos[j] = aux;
                }
            break;
            case 4: 
            for(int i=0; i<datos.length; i++){
                Expedicion aux = datos[i];
                int j = i;
                while (j>0 && aux.getCeldasVisitadas() < datos[j-1].getCeldasVisitadas()){
                    datos[j] = datos[j-1];
                    j--;
                }
                datos[j] = aux;
            } 
            break;
            default : System.out.println("Criterio invalido"); 
        }
       }while(criterio != 0);    
    }
    /** Ordenar in-place; elegir Heap Sort, Quick Sort o Merge Sort. */
    public static void ordenarDivideVenceras(Expedicion[] datos,int criterio){
        int inicio = 0;
        int fin = datos.length-1;
    
    }

    public static void quickSort (Expedicion[]datos, int inicio, int fin, int criterio){
        if (inicio<fin){
            int k = particion (datos, inicio, fin, criterio);
            quickSort(datos, inicio, k-1, criterio);
            quickSort(datos, k+1, fin, criterio);
        }

    }
    public static int particion (Expedicion[]datos, int inicio, int fin, int criterio){
        Expedicion pivote  = datos[fin];
        int i = inicio -1;

        for(int j=inicio; j<fin;j++){
            boolean cumple = false;
            switch (criterio){
                case 1: 
                    cumple = datos[j].getPuntaje() > pivote.getPuntaje();
                break;
                case 2:
                    cumple = datos[j].getTesorosRecuperados() > pivote.getTesorosRecuperados();
                break;
                case 3: 
                    cumple = datos[j].getTiempoNanos() < pivote.getTiempoNanos();
                break;
                case 4: 
                    cumple = datos[j].getCeldasVisitadas() < pivote.getCeldasVisitadas();
                break;
            }
            if(cumple){
                i++;
                Expedicion aux = datos[i];
                datos[i] = datos[j];
                datos[j] = aux;
            }
        }
        Expedicion aux = datos[i+1];
        datos[i+1] = datos[fin];
        datos[fin]= aux;

        int posPivote = i+1;
        return posPivote;
    }
}
