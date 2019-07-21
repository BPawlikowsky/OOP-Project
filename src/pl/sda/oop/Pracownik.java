package pl.sda.oop;

public class Pracownik {

    private String imie;
    private String nazwisko;
    private char plec;
    private int nrDzialu;
    private float placa;
    private int wiek;
    private int liczbaDzieci;
    private boolean stanCywilny;

    public Pracownik() {
        imie = "Jan";
        nazwisko = "Kowalski";
        plec = 'M';
        nrDzialu = 0;
        placa = 1000.00f;
        wiek = 40;
        liczbaDzieci = 2;
        stanCywilny = true;
    }

    public Pracownik(String imie, String nazwisko, char plec, int nrDzialu, float placa, int wiek, int liczbaDzieci, boolean stanCywilny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.nrDzialu = nrDzialu;
        this.placa = placa;
        this.wiek = wiek;
        this.liczbaDzieci = liczbaDzieci;
        this.stanCywilny = stanCywilny;
    }

    public String pokaz() {
        return(
            kreseczki(nazwisko) + "\n" +
            "Imię: " + imie + "\n" +
            "Nazwisko: " + nazwisko + "\n" +
            "Płeć: " + plec + "\n" +
            "Numer Działu: " + nrDzialu + "\n" +
            "Płaca: " + placa + "zł" + "\n" +
            "Wiek: " + wiek + "\n" +
            "Liczba dzieci: " + liczbaDzieci + "\n" +
            "Stan cywilny: " + (stanCywilny ? (plec == 'M' ? "żonaty" : "mężatka") :
                                              (plec == 'M' ? "niezamężny" : "niezamężna")) + "\n"
                        );
    }

    public String pokazOkrojone() {
        return (
            kreseczki(nazwisko) + "\n" +
            "Imię: " + imie + "\n" +
            "Nazwisko: " + nazwisko + "\n" +
            "Płaca: " + placa + "zł" + "\n"
        );
    }

    public String pokazSpecjalne() {
        return (
            kreseczki(nazwisko) + "\n" +
            "Imię: " + imie.toUpperCase() + "\n" +
            "Nazwisko: " + nazwisko.toUpperCase() + "\n"
        );
    }

    public boolean czyPlacaWieksza(float f) {
        return f > placa;
    }

    public float obliczaniePodwyzki(float procent) {
        return ((procent + (stanCywilny ? 3 : 0) +
                (2 * liczbaDzieci)) / 100) * placa;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public char getPlec() {
        return plec;
    }

    public int getNrDzialu() {
        return nrDzialu;
    }

    public float getPlaca() {
        return placa;
    }

    public int getWiek() {
        return wiek;
    }

    public int getLiczbaDzieci() {
        return liczbaDzieci;
    }

    public void setNazwisko(String nazwisko) {
        if(this.plec == 'K') this.nazwisko = nazwisko;
    }

    public boolean isStanCywilny() {
        return stanCywilny;
    }

    public void setNrDzialu(int nrDzialu) {
        this.nrDzialu = nrDzialu;
    }

    public void setPlaca(float placa) {
        this.placa = placa;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setLiczbaDzieci(int liczbaDzieci) {
        this.liczbaDzieci = liczbaDzieci;
    }

    public void setStanCywilny(boolean stanCywilny) {
        this.stanCywilny = stanCywilny;
    }

    //--------------------------------------------------
    public static String kreseczki(String n) {
        String s = "";
        for (int i = 0; i < n.length() + 10; i++) {
            s += "-";
        }
        return s;
    }

    public void eksportPracownika(String nazwaPliku) {
        Eksport eksport = new Eksport(this);
        eksport.zapiszPracownika(nazwaPliku);
    }
}
