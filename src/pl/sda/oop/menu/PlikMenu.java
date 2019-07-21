package pl.sda.oop.menu;

import pl.sda.oop.Eksport;
import pl.sda.oop.Import;
import pl.sda.oop.Pracownik;
import pl.sda.oop.Program;

import java.util.ArrayList;
import java.util.Scanner;

public class PlikMenu {

    private static String nazwaPliku = "baza.dat";

    public static String getNazwaPliku() {
        return nazwaPliku;
    }

    public static void setNazwaPliku(String nazwaPliku) {
        PlikMenu.nazwaPliku = nazwaPliku;
    }

    public static void menuPlikow(Scanner s) {
        Eksport.ekportujListePracownikow(nazwaPliku, Program.getPracownicy());

        System.out.print(
                "Dodatkowe funkcje dla plików: \n" +
                "a. | b. | c. | d. \n: "
        );

        switch (s.next()) {
            case "a": {
                wyswietlNajdluzszeNazwisko();
            }
            break;
            case "b": {
                sredniaWiekuOsobZDziecmi();
            } 
            break;
            case "c": {
                zakodujNazwiska();
            }
            break;
            case "d": {
                tabelaHTML();
            } break;
        }

    }

    private static void wyswietlNajdluzszeNazwisko() {
        System.out.print("Najdłuższe nazwisko w pliku to: " +
        Import.znajdzNajdluszeNazwisko(nazwaPliku));
    }

    private static void sredniaWiekuOsobZDziecmi() {
        ArrayList<Pracownik> pr;
        pr = Import.pobierzPracownikow(nazwaPliku);
        int sumaWieku = 0;
        int liczbaLudzi = 0;
        for (Pracownik p :
                pr) {
            if (p.getLiczbaDzieci() > 0) {
                sumaWieku += p.getWiek();
                liczbaLudzi++;
            }
        }
        System.out.println("Średnia wieku ludzi z dziećmi wynosi: " + sumaWieku/liczbaLudzi);
    }
    
    private static void zakodujNazwiska() {
        Eksport.ekportujSzyfrowanaListe(nazwaPliku, Program.getPracownicy());
    }
    private static void tabelaHTML() {

        Eksport.tabelaHTML(Program.getPracownicy());

    }
}
