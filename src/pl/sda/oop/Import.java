package pl.sda.oop;

import java.io.*;
import java.util.*;

public class Import {
    private static Scanner plik;
    private static File fileImport;
    private static ArrayList<Pracownik> pracowniks;

    private static void otwórzPlik(String nazwaPliku) {
        fileImport = new File(nazwaPliku);
    }

    public static ArrayList<Pracownik> pobierzPracownikow(String nazwaPliku) {
        pracowniks = new ArrayList<>();
        otwórzPlik(nazwaPliku);
        try {
            plik = new Scanner(fileImport);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        while(plik.hasNextLine()) {
            String imie = plik.nextLine();
            String nazwisko = plik.nextLine();
            char plec = plik.next().charAt(0);
            int nrDzialu = plik.nextInt();
            float placa = plik.nextFloat();
            int wiek = plik.nextInt();
            int liczbaDzieci = plik.nextInt();
            String s = plik.next();
            boolean stan = s.equals("T");

            pracowniks.add(
                    new Pracownik(
                            imie,
                            nazwisko,
                            plec,
                            nrDzialu,
                            placa,
                            liczbaDzieci,
                            wiek,
                            stan
                    )
            );

            if(plik.hasNext()) plik.nextLine();
        }
        plik.close();
        fileImport = null;
        return pracowniks;
    }

    public static String znajdzNajdluszeNazwisko(String nazwaPliku) {
        pracowniks = null;
        pracowniks = pobierzPracownikow(nazwaPliku);

        Comparator<Pracownik> comp = new Comparator<Pracownik>() {
            @Override
            public int compare(Pracownik o1, Pracownik o2) {
                if(o1.getNazwisko().length() < o2.getNazwisko().length()) return 1;
                else if(o1.getNazwisko().length() == o2.getNazwisko().length()) return 0;
                else return -1;
            }
        };

        ArrayList<Pracownik> pr = pracowniks;

        pracowniks.sort(comp);

        otwórzPlik(nazwaPliku);
        try {
            plik = new Scanner(fileImport);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String line = "Błąd";

        while (plik.hasNextLine()) {
            line = plik.nextLine();
            if(line.equals(pracowniks.get(0).getNazwisko())) {
                break;
            }
        }

        plik.close();
        return line;

    }

}
