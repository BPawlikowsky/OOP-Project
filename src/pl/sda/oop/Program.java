package pl.sda.oop;

import pl.sda.oop.menu.PodstawoweMenu;

import java.util.*;

public class Program {
    private static ArrayList<Pracownik> pracownicy;
    private static boolean koniecProgramu = false;
    private static int iloscDzialow = 10;
    private static String aktualnyPlik = "";

    public Program() {
        pracownicy = new ArrayList<>();
    }

    public static ArrayList<Pracownik> getPracownicy() {
        return pracownicy;
    }

    public static void setPracownicy(ArrayList<Pracownik> pracownicy) {
        Program.pracownicy = pracownicy;
    }

    public static boolean isKoniecProgramu() {
        return koniecProgramu;
    }

    public static void setKoniecProgramu(boolean koniecProgramu) {
        Program.koniecProgramu = koniecProgramu;
    }

    public static int getIloscDzialow() {
        return iloscDzialow;
    }

    public static void setIloscDzialow(int iloscDzialow) {
        Program.iloscDzialow = iloscDzialow;
    }

    public static String getAktualnyPlik() {
        return aktualnyPlik;
    }

    public static void setAktualnyPlik(String aktualnyPlik) {
        Program.aktualnyPlik = aktualnyPlik;
    }

    public void program() {
        pracownicy.addAll(new Import().pobierzPracownikow(
                "import.txt"
        ));
        while (!koniecProgramu) PodstawoweMenu.menu();
    }
}
