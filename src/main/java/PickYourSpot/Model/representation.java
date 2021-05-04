package PickYourSpot.Model;

public class representation {
    private int ora;
    private int minut;
    private int [][] sala = new int[5][8];

    public int[][] getSala() {
        return sala;
    }

    public void setSala(int[][] sala) {
        this.sala = sala;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }

    public representation(int ora, int minut) {
        this.ora = ora;
        this.minut = minut;
    }

    public void setIndexSala(int row, int column, int val){
        sala[row][column]=val;
    }

    public void forExample(){
        for (int i=0;i<5;i++){
            if(i==1||i==3) {
                for (int j = 0; j < 8; j++) {
                    if(j==2||j==7){
                        sala[i][j]=1;
                    }
                }
            }
        }
    }


}
