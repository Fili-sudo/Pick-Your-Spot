package PickYourSpot.Model;

public class representation {
    private int ora;
    private int minut;

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


}
