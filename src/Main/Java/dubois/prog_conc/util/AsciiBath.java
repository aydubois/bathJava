package Main.Java.dubois.prog_conc.util;

import java.util.ArrayList;

public class AsciiBath {
    private String stateFull = "";
    private String stateLeak = "";
    private ArrayList<String> gif = new ArrayList<>();
    private ArrayList<Integer> indexRepare = new ArrayList<>();

    private String man =   ConsoleColors.PURPLE+
                            "      ____\n" +
                            "  ,-/   /)))  \n" +
                            "  \\_\\  ( "+ConsoleColors.BLUE+"e"+ConsoleColors.PURPLE+"(  \n" +
                            "     \\/' _/   ,_ ,  \n" +
                            "     _/ (_   / _/  " +ConsoleColors.YELLOW+
            "___( ^)"+ConsoleColors.PURPLE+">\n"+ConsoleColors.RESET;
        ;
    private String full0Bath =  "\n"+"\n"+"\n"+"\n"+"\n"+
            ",.--------------------------,,\n"+
                                "|%`````````````````````````` |\n"+
                                "|%.                          |\n"+
                                "|%%                          |\n"+
                                " +%%.                      .+'\n";
    private String full1Bath =  "\n"+"\n"+"\n"+"\n"+"\n"+",.--------------------------,,\n"+
                                "|%`````````````````````````` |\n"+
                                "|%.                          |\n"+
                                "|%%                          |\n"+
                                " +%%."+ConsoleColors.BLUE_BACKGROUND+"                      "+ConsoleColors.RESET+".+'\n";
    private String full2Bath =  "\n"+"\n"+"\n"+"\n"+"\n"+",.--------------------------,,\n"+
                                "|%`````````````````````````` |\n"+
                                "|%.                          |\n"+
                                "|%%"+ConsoleColors.BLUE_BACKGROUND+"                          "+ConsoleColors.RESET+"|\n"+
                                " +%%."+ConsoleColors.BLUE_BACKGROUND+"                      "+ConsoleColors.RESET+".+'\n";

    private String full3Bath =  this.man+
                                ",.--"+ConsoleColors.PURPLE+"/    \\\\_/"+ConsoleColors.RESET+"------"+ConsoleColors.YELLOW+"\\ <_. )"+ConsoleColors.RESET+"--,,\n"+
                                "|%"+ConsoleColors.BLUE_BACKGROUND+"`````````````````````````` "+ConsoleColors.RESET+"|\n"+
                                "|%."+ConsoleColors.BLUE_BACKGROUND+"                          "+ConsoleColors.RESET+"|\n"+
                                "|%%"+ConsoleColors.BLUE_BACKGROUND+"                          "+ConsoleColors.RESET+"|\n"+
                                " +%%."+ConsoleColors.BLUE_BACKGROUND+"                      "+ConsoleColors.RESET+".+'\n";
    private String leak0Bath =  " ``--f t--------------f t'°\n"+
                                "    {___}            {___}\n";
    private String leak1BathEmpty = " ``--f t-------- -----f t'°         REPARATION EN COURS\n"+
                                    "    {___}            {___}\n"+
                                    "              "+ConsoleColors.BLUE+"~~~~"+ConsoleColors.RESET+"        ";
    private String leak2BathEmpty =  " ``--f t-------    ---f t'°         REPARATION EN COURS\n"+
                                "    {___}            {___}\n" +
                                "              "+ConsoleColors.BLUE+"~~~~~~"+ConsoleColors.RESET+"        ";

    private String leak3BathEmpty =  " ``--f t-----      ---f t'°         REPARATION EN COURS\n"+
                                "    {___}            {___}\n" +
            "            "+ConsoleColors.BLUE+"~~~~~~~~"+ConsoleColors.RESET+"       ";

    private String leak1Bath =  " ``--f t--------"+ConsoleColors.BLUE_BACKGROUND+" "+ConsoleColors.RESET+"-----f t'°\n"+
                                "    {___}       "+ConsoleColors.BLUE_BACKGROUND+" "+ConsoleColors.RESET+"    {___}\n"+
            "              "+ConsoleColors.BLUE_BACKGROUND+"    "+ConsoleColors.RESET+"        ";

    private String leak2Bath =  " ``--f t-------"+ConsoleColors.BLUE_BACKGROUND+"    "+ConsoleColors.RESET+"---f t'°\n"+
                                "    {___}      "+ConsoleColors.BLUE_BACKGROUND+"    "+ConsoleColors.RESET+"  {___}\n"+
            "              "+ConsoleColors.BLUE_BACKGROUND+"      "+ConsoleColors.RESET+"        ";

    private String leak3Bath =  " ``--f t-----"+ConsoleColors.BLUE_BACKGROUND+"      "+ConsoleColors.RESET+"---f t'°\n"+
                                "    {___}    "+ConsoleColors.BLUE_BACKGROUND+"      "+ConsoleColors.RESET+"  {___}\n"+
            "            "+ConsoleColors.BLUE_BACKGROUND+"        "+ConsoleColors.RESET+"       ";




    public void fillGif(String stateFull,String stateLeak ){
        if(this.stateLeak != stateLeak || this.stateFull != stateFull){
            this.stateLeak = stateLeak;
            this.stateFull = stateFull;
            this.createImage();
        }
    }

    private void createImage(){
        String text = "";
        Boolean checkRepare = false;
        switch (this.stateFull){
            case "empty":
                text = this.full0Bath;
                break;
            case "full-":
                text = this.full1Bath;
                break;
            case "full+":
                text = this.full2Bath;
                break;
            case "full++":
                text = this.full3Bath;
                break;
            default:
                break;
        }
        if(this.stateFull == "empty"){
            switch (this.stateLeak){
                case "leak0":
                    text += this.leak0Bath;
                    break;
                case "leak-":
                    text += this.leak1BathEmpty;
                    checkRepare =true;
                    break;
                case "leak+":
                    text += this.leak2BathEmpty;
                    checkRepare =true;
                    break;
                case "leak++":
                    text += this.leak3BathEmpty;
                    checkRepare =true;
                    break;
                default:
                    break;
            }
        }else{
            switch (this.stateLeak){
                case "leak0":
                    text += this.leak0Bath;
                    break;
                case "leak-":
                    text += this.leak1Bath;
                    break;
                case "leak+":
                    text += this.leak2Bath;
                    break;
                case "leak++":
                    text += this.leak3Bath;
                    break;
                default:
                    break;
            }
        }
        gif.add(text);
        if(checkRepare){
            indexRepare.add(gif.size()-1);
        }
    }

    public void animateGif(){

        for(int i = 0; i < gif.size();i++){
            this.clearConsole();

            System.out.println(gif.get(i));
            try {
                if(indexRepare.contains(i))
                    Thread.sleep(800);
                else
                Thread.sleep(400);
            } catch (InterruptedException e) {e.printStackTrace(); }

        }

    }

    private void clearConsole(){
        String modeRun = "dev";
        if(modeRun == "dev"){
            /* !! ATTENTION !! Ne fonctionne que si GREP CONSOLE est installé
                Dans les paramètres =====> Ajouter une ligne 'filtering', mettre en expression ' .*CLEAR.*  '
                tout cocher, y compris 'clear console' et enregistrer
            */
            System.out.println("CLEAR");
        }

        if(modeRun == "prod"){
            // Ne fonctionne pas dans la console de l'IDE
            try{
                String os = System.getProperty("os.name");
                if (os.contains("Windows")){
                    Runtime.getRuntime().exec("cls");
                }else{
                    Runtime.getRuntime().exec("clear");
                }
            }
            catch (Exception e){
                System.out.println("Oops");
            }
        }
    }

}
