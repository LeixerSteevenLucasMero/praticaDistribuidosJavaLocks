public class avion {

    private int id;
    private int intencion;      //intencion = 0 quiere despega el avion
                                //intencion = 1 esta volando 
                                //intencion !=0 o 1, esta aparcado
                                //ultima intencion posible =4

    public avion (int id, int intencion){
        this.id = id;
        this.intencion= intencion;
    }
    public int getIntencion(){
        return intencion;
    
    }
    public int getId(){
        return id;
    
    }
    public int nextIntencion(){
        intencion= ++intencion % 5;
        return intencion;
    }
    
}
