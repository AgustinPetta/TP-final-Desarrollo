package tp.modelo;
public class Ciudad {
    private  Sector[][] sectores;
    private int filas;
    private int columnas;
    public Ciudad(int filas,int columnas){
        if(filas<=0||columnas<=0)throw new IllegalArgumentException("Dimensiones inválidas");
        this.filas= filas;
        this.columnas = columnas;
        Sector[][] sectores;
        this.sectores = new Sector[filas][columnas];
        //TO DO: Crear la matriz de sectores y poblarla con los sectores
        //sectores=new Sector[filas][columnas];
    }
    public int getFilas(){
        return this.filas;
    }
    public int getColumnas(){
        return this.columnas;
    }
    public boolean posicionValida(int fila,int columna){
       //Devuelve si el elemento en la poscion de la fila y la columna es una posicion
       // válida dentro de la matriz;
        boolean verifPos = false;
        if((fila>=0 && fila<filas) && (columna>=0 && columna<columnas)){
            verifPos = true;
        }
        return verifPos;
    }

    public Sector getSector(int fila,int columna){
        if(!posicionValida(fila,columna))throw new IndexOutOfBoundsException();
        return sectores[fila][columna];
    }

    public void modificar(int fila,int columna,Sector sector){
        // TO DO: validar posición y asignar nuevo tipo.
        // Recordar que null representa muro.
        
    }
    public boolean esTransitable(int fila,int columna){
        boolean transitable = true;
        if(!posicionValida(fila,columna) || this.sectores[fila][columna] == null){transitable=false;}
        return transitable; 
        // TO DO: fuera de rango o muro devuelve false.
        
    }

    public int contarTesoros(){
        //TO DO contar todos los tesoros;
        int tesorosEncontrados =0;
        for (int i=0;i<this.sectores.length;i++){
            for (int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null && sectores[i][j].esTesoro()){
                    tesorosEncontrados++;
                }
            }
        }
        return tesorosEncontrados;
    }

    public int contarTrampas(){
        //TO DO contar todas las Trampas
        int trampasEncontradas = 0;
        for (int i=0;i<this.sectores.length;i++){
            for (int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null&&sectores[i][j].esTrampa()){
                    trampasEncontradas++;
                }
            }
        }
        return trampasEncontradas;
    }

    public int contarTransitables(){
       //TO DO contar todos los lugares Transitables
       int cantTransitable= 0;
       for(int i=0;i<this.sectores.length;i++){
            for(int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j] != null){
                    cantTransitable++;
                }
            }
        }
        return cantTransitable;
    }

    public int[] tesorosPorFila(){
        //devuelve un arreglo con la cantidad de tesoros en cada fila
        int[] arrTesorosFila;
        arrTesorosFila = new int[filas];
        for(int i=0;i<this.sectores.length;i++){
            for (int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null && (sectores[i][j].esTesoro())){
                    arrTesorosFila[i]++;
                }
            }
        }
        return arrTesorosFila;
    }

    public int[] tesorosPorColumna(){
        //Devuelve un arreglo con la cantidad de tesoros en cada columna
        int[] arrTesorosColumna;
        arrTesorosColumna = new int[columnas];
        for(int i=0;i<this.sectores.length;i++){
            for(int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null && (sectores[i][j].esTesoro())){
                    arrTesorosColumna[j]++;
                }
            }
        }
        return arrTesorosColumna;
    }

    public int[] trampasPorFila(){
         //Devuelve un arreglo con la cantidad de trampas por fila
         int[] arrTrampasFila;
         arrTrampasFila = new int[filas];
         for(int i=0;i<this.sectores.length;i++){
            for(int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null && (sectores[i][j].esTrampa())){
                    arrTrampasFila[i]++;
                }
            }
        }
        return arrTrampasFila;
    }

    public int[] trampasPorColumna(){
         //Devuelve un arreglo con la cantidad de trampas por columna
         int[] arrTrampasColumna;
         arrTrampasColumna = new int[columnas];
         for(int i=0;i<this.sectores.length;i++){
            for(int j=0;j<this.sectores[i].length;j++){
                if(sectores[i][j]!=null && (sectores[i][j].esTrampa())){
                    arrTrampasColumna[j]++;
                }
            }
        }
        return arrTrampasColumna;
    }

    /** En caso de empate devolver el índice menor; si no hay tesoros, devolver 0. */
    public int filaConMasTesoros(){
        //Devuelve el indice de la fila con mas tesoros
        int[] arrTesorosFila = this.tesorosPorFila();
        int filaMaxTesoros=0;
        for(int i=0;i<arrTesorosFila.length;i++){
            if(arrTesorosFila[i]>arrTesorosFila[filaMaxTesoros]){
                filaMaxTesoros=i;
            }
        }
        return filaMaxTesoros;
    }

    public int columnaConMasTesoros(){
        //Devuelve el índice de la columna con mas tesoros
        int[] arrTesorosColumna = this.tesorosPorColumna();
        int columnaMaxTesoros=0;
        for(int i=0;i<arrTesorosColumna.length;i++){
            if(arrTesorosColumna[i]>arrTesorosColumna[columnaMaxTesoros]){
                columnaMaxTesoros=i;
            }
        }
        return columnaMaxTesoros;
    }
    
//PREGUNTAR ACERCA DE ESTOS METODOS.
    public void  mostrarMapa(){
        //Imprime por pantalla la matriz de la ciudad
        //Ver que métodos ya existentes pueden utilizar y completar lo que falte.
        //Por si lo necesitan: un emoji para los muros: "⬛" 
        for (int i=0;i<this.sectores.length;i++){
            for (int j=0;j<this.sectores[i].length;j++){
                if(this.sectores[i][j] == null ){
                    System.out.print("⬛");
                }else{
                    System.out.print(this.sectores[i][j].getEmoji());
                }
            }
        }
    }

    public Ciudad clonar(){
        //Retorna una ciudad que es una copia de la ciudad actual.
        Ciudad ciudadClon = new Ciudad(this.filas,this.columnas);
        for(int i=0;i<this.sectores.length;i++){
            for(int j=0;j<this.sectores[i].length;j++){
                ciudadClon.sectores[i][j] = this.sectores[i][j];
            }
        }
        return ciudadClon;
    }

    public void informeGeneral(){
        System.out.println("Cantidad de tesoros: "+this.contarTesoros());
        System.out.println("Cantidad de trampas: "+this.contarTrampas());
        System.out.println("Cantidad de lugares transitables: "+this.contarTransitables());
        System.out.println("Fila con más tesoros: "+this.filaConMasTesoros());
        System.out.println("Columna con más tesoros: "+this.columnaConMasTesoros());
    }
}
