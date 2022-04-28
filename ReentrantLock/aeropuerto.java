import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class aeropuerto implements Runnable{
    private avion avion;
    private static int contDespegar = 0;


    private static ReentrantLock lock = new ReentrantLock();
    private static Condition despegar = lock.newCondition();
    private static Condition aparcamiento = lock.newCondition();

    public aeropuerto(avion avion){
        this.avion=avion;

    }
    public void run(){
        int intencion = avion.getIntencion();
        while (true){
            lock.lock();
            switch(intencion){
                case 0:
                    intencion=avion.nextIntencion();//intencion 1
                    System.out.println("El avion "+avion.getId()+" esta volando");

                    contDespegar--;

                    despegar.signal();
                    aparcamiento.signalAll();
                    break;
                case 2:
                    intencion = avion.nextIntencion();//intencion 2
                    System.out.println("El avion "+avion.getId()+" va a aterrizar");
                    await(aparcamiento);
                    break;
                case 3:
                    intencion = avion.nextIntencion();//intencion 0

                    while(contDespegar>2){
                        await(aparcamiento);
                        System.out.println("El avion "+ avion.getId()+" ha intentado despegar pero esta llena la cola");
                    }
                    System.out.println("El avion "+ avion.getId()+" va a pasar a la cola de despegue");
                    contDespegar++;
                    await(despegar);
                    break;
                default:
                intencion =avion.nextIntencion();//intencion 3 o 4
                System.out.println("El avion "+avion.getId()+" esta en el parking");

            }
            //generado t Threa sleep de 1000 seg para ejecucion de cada hilo
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
    public void await(Condition condition){
        try{
            condition.await();
        }catch(InterruptedException e){
            e.printStackTrace();;
        }
    }
    
}