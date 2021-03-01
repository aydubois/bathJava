package Main.Java.dubois.prog_conc.baignoire;

import Main.Java.dubois.prog_conc.util.AsciiBath;

import java.util.ArrayList;

public class Baignoire implements Runnable{
    private final int volMax;
    private int volLeak;
    private int volWater;
    private boolean isEmpty;
    private boolean isFull;
    private int volLeakInit;
    private String stateLeak;
    private String stateFull;
    private ArrayList<String>  gif = new ArrayList();
    private AsciiBath ascii = new AsciiBath();
    /**
     *
     * @param volMax maximum bath capacity (water volume in Litre)
     * @param volLeak leak volume (in Litre)
     * @param volWater water volume (in Litre) into the bath
     */
    public Baignoire (int volMax, int volLeak,int volWater){
        this.volMax = volMax;
        this.volWater = volWater;
        this.volLeak = volLeak;
        this.volLeakInit = volLeak;
        this.isEmpty = volWater == 0 ? true : false;
        this.isFull = volWater == volMax ? true : false;
    }
    /**
     * @param volMax maximum bath capacity (water volume in Litre)
     * @param volLeak leak volume (in Litre)
     * volWater is initialized to 0
     */
    public Baignoire(int volMax, int volLeak){
        this.volMax = volMax;
        this.volWater = 0;
        this.volLeak = volLeak;
        this.volLeakInit = volLeak;
        this.isEmpty = true;
        this.isFull = false;
    }

    public void setVolWater(int volWater) {
        this.volWater = volWater;
    }
    public void setVolLeak(int volLeak){
        this.volLeak = volLeak;
    }
    public int getVolMax() {
        return volMax;
    }
    public boolean getIsFull() {
        return isFull;
    }
    public void setIsFull(boolean isFull){
        this.isFull = isFull;
    }
    public void setIsEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
    }
    public int getVolWater() {
        return volWater;
    }
    public int getVolLeak(){
        return volLeak;
    }
    public void leak(){
            //System.out.println("1.a. BATH LEAK ===> "+this.volWater +" - "+this.volLeak+" = "+ (this.volWater - this.volLeak));
        if(this.volWater - this.volLeak > 0){
            this.volWater -=  this.volLeak;
            this.isEmpty = false;
            //System.out.println("1.b. BATH VOLWATER = "+this.volWater);
        }else{
            //System.out.println("1.c. BATH VOLWATER == 0");
            this.volWater = 0;
            this.isEmpty = true;
        }
    }


    @Override
    public void run() {

        while(this.getVolLeak() > 0) {
            synchronized (this){
                checkState();
                if(!this.isEmpty){
                    //System.out.println("1.BATH NOT EMPTY");
                    this.leak();
                }else{
                    //System.out.println("2.a BATH EMPTY");
                    this.volLeak = this.volLeak -5;
                    //System.out.println("2.b BATH leak - 5 = "+this.volLeak);
                    try {
                        this.notify();
                        this.wait();
                    }catch (Exception e){
                        Thread.currentThread().interrupt();
                        System.out.println("Thread interrupted BATH ===>" +  e);
                    }
                }
            }
        }

    }
    public void checkState(){
        String stateFull = "";
        String stateLeak = "";
        int percentageFull = this.getVolWater() * 100 / this.getVolMax();
        if(this.isFull == true){
            stateFull = "full++";
        }else if(percentageFull >= 50){
            stateFull = "full+";
        }else if(!this.isEmpty){
            stateFull = "full-";
        }else{
            stateFull = "empty";
        }
        int percentageLeak = this.volLeak * 100 /this.volLeakInit ;
        if(percentageLeak >= 75){
            stateLeak = "leak++";
        }else if(percentageLeak >= 50){
            stateLeak = "leak+";
        }else if(percentageLeak > 0){
            stateLeak = "leak-";
        }else{
            stateLeak = "leak0";
        }
        if(this.stateLeak != stateLeak || this.stateFull != stateFull){
            this.stateLeak = stateLeak;
            this.stateFull = stateFull;
        }
        this.ascii.fillGif(stateFull,stateLeak);

    }
    public void showGif(){
        this.ascii.animateGif();
    }
    public void display(String stateFull, String stateLeak){

        /*String RESET =  "\033[0m";
        String BACK_YELLOW =  "\033[1;33m";
        String ORANGE = "\033[1;35m";;
        //System.out.println(stateFull+ " "+ stateLeak);

           System.out.println("|                     |");
        if(stateFull == "full+") {
            System.out.println( "\033[0;94m"+"|_____________________|");
        }
            System.out.println("|                     |");
        if(stateFull == "full-") {
            System.out.println( "\033[44m"+"|_____________________|");
        }
            System.out.println("|                     |");
        if(stateLeak == "leak++") {
            System.out.println("|_     _______________|"+"\033[0m");
        }
        if(stateLeak == "leak+") {
            System.out.println("|_   _________________|"+"\033[0m");
        }
        if(stateLeak == "leak-") {
            System.out.println("|_ ___________________|"+"\033[0m");
        }
        if(stateLeak == "leak0") {
            System.out.println("|_____________________|"+"\033[0m");
        }

        System.out.println("");*/
    }
}
