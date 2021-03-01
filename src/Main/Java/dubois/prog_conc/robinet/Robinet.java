package Main.Java.dubois.prog_conc.robinet;

import Main.Java.dubois.prog_conc.baignoire.Baignoire;

public class Robinet implements Runnable{
    private final int volOutput;
    private Baignoire bath;

    public Robinet(int volOutput){
        this.volOutput = volOutput;
    }

    public Robinet(int volOutput,Baignoire bath){
        this.volOutput = volOutput;
        this.bath = bath;
    }

    public void flow(){
        this.bath.setIsEmpty(false);
        if(this.bath.getVolWater()+this.volOutput <= this.bath.getVolMax()){
            //System.out.println("Z1.a. TAP VOLWATER + VOLOUTPUT <= VOLMAX  " +this.bath.getVolWater()+" + " + this.volOutput + " <= " +this.bath.getVolMax() );
            this.bath.setVolWater(this.bath.getVolWater()+this.volOutput);
        }else{
            this.bath.setVolWater(this.bath.getVolMax());
            //System.out.println("Z1.b. TAP BATH IS FULL");
            this.bath.setIsFull(true);
            //System.out.println("Z1.c. La baignoire est pleine.");
        }
        //this.display();
    }
    public void display(){
        System.out.println("Le volume d'eau actuel dans la baignoire est de : "+this.bath.getVolWater()+" litres.");
    }

    @Override
    public  void run() {

            while(bath.getVolLeak() > 0 && !bath.getIsFull()) {
                synchronized (bath){
                    if( bath.getVolWater() != bath.getVolMax()) {
                        //System.out.println("Z1. TAP VOLWATER != VOLMAX");
                        this.flow();
                        this.bath.checkState();

                        try{
                            bath.notify();
                            bath.wait();
                        }catch (Exception e){
                            Thread.currentThread().interrupt();
                            System.out.println("Thread TAP interrupted ===>" +  e);
                        }
                    }
                }
            }
            if(bath.getVolLeak() == 0 && !bath.getIsFull()){
                //System.out.println("FUITE REPAREE");
                //System.out.println("Z2. TAP VOLEAK = 0");
                while(!bath.getIsFull()){
                    this.flow();
                    this.bath.checkState();
                }
                //System.out.println("BAIGNOIRE PLEINE");
                //this.bath.display("full");
                this.bath.showGif();
            }

    }
}
