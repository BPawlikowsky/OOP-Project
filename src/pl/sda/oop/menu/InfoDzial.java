package pl.sda.oop.menu;

public class InfoDzial {
    private int nrDzialu;
    private float sumaPensji = 0;
    private int iloscKobiet = 0;
    private int iloscMezczyzn = 0;

    public InfoDzial(int nrDzialu) {
        this.nrDzialu = nrDzialu;
    }

    public int getNrDzialu() {
        return nrDzialu;
    }

    public float getSumaPensji() {
        return sumaPensji;
    }

    public void setSumaPensji(float sumaPensji) {
        this.sumaPensji = sumaPensji;
    }

    public int getIloscKobiet() {
        return iloscKobiet;
    }

    public void setIloscKobiet(int iloscKobiet) {
        this.iloscKobiet = iloscKobiet;
    }

    public int getIloscMezczyzn() {
        return iloscMezczyzn;
    }

    public void setIloscMezczyzn(int iloscMezczyzn) {
        this.iloscMezczyzn = iloscMezczyzn;
    }
}
