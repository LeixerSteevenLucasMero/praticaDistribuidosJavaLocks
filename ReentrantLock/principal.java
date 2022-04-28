import java.util.Random;
public class principal {
    public static void main(String[]args){
        Random rand =new Random (System.nanoTime());
        Thread[] vec = new Thread[30];

        for (int i =0; i<vec.length; i++){
            int intencion =rand.nextInt(5);
            avion avion = new avion(i, intencion);

            Runnable run = new aeropuerto(avion);
            vec[i]= new Thread(run);
            vec[i].start();

        }
        try{
            for(int i=0;i<vec.length;i++){
                vec[i].join();
            }
        }catch(Exception ex){}
    }
}
