package pl.sda.oop.menu;

import pl.sda.oop.Pracownik;
import pl.sda.oop.Program;
import pl.sda.oop.menu.DodatkoweMenu;

import java.util.Scanner;

public class PodstawoweMenu {
    private static Scanner s = new Scanner(System.in);
    public static void menu() {
        System.out.println(
                "\n\n" + "---------------------------" + "\n" +
                        "MENU GŁÓWNE:\n" +
                        "1. LISTA PRACOWNIKÓW | " +
                        "2. DODAJ PRACOWNIKA  | " +
                        "3. EKSPORT PRACOWNIKA \n" +
                        "4. USUN PRACOWNIKA   | " +
                        "5. EDYTUJ PRACOWNIKA | " +
                        "6. DODATKOWE MENU     \n" +
                        "7. MENU PLIKÓW       | " +
                        "8. INFO O PROGRAMIE  | " +
                        "9. USTAW NAZWĘ PLIKU  \n" +
                        "0. WYJŚCIE           |"
        );

        System.out.print("Jaka opcja: ");

        switch (s.nextInt()) {
            case 1: {
                pokazPracownikowKrotka();
            }
            break;
            case 2: {
                dodajPracownika();
            }
            break;
            case 3: {
                eksportPracownika(s);
            }
            break;
            case 4: {
                usunPracownika();
            }
            break;
            case 5: {
                edytujPracownika(s);
            }
            break;
            case 6: {
                DodatkoweMenu.dodatkoweMenu(s);
            }
            break;
            case 7: {
                PlikMenu.menuPlikow(s);
            }
            break;
            case 8: {
                infoOProgramie();
            }
            break;
            case 9: {
                System.out.print("Podaj nową nazwę pliku .dat: ");
                String nowaNazwa = s.nextLine();
                zmienNazweDat(nowaNazwa);
            }
            break;
            case 0: {
                Program.setKoniecProgramu(true);
            }
            break;
        }
    }

    public static void pokazPracownikowKrotka() {
        System.out.println("LISTA PRACOWNIKÓW: ");
        int i = 0;
        for (Pracownik p : Program.getPracownicy()
        ) {
            System.out.println("Pracownik No." +
                    i++ + ":\n" +
                    p.pokazOkrojone()
            );
        }
    }

    public static void pokazPracownikowPelna() {
        System.out.println("LISTA PRACOWNIKÓW: ");
        int i = 0;
        for (Pracownik p : Program.getPracownicy()
        ) {
            System.out.println("Pracownik No." +
                    i++ + ": \n" +
                    p.pokaz()
            );
        }
    }

    public static void dodajPracownika() {
        Scanner s = new Scanner(System.in);

        System.out.print("Podaj imię pracownika: ");
        String imie = s.next();
        System.out.print("Podaj nazwisko pracownika: ");
        String nazwisko = s.next();
        System.out.print("Podaj płeć pracownika(M | K): ");
        char plec = s.next().toUpperCase().equals("M") ? 'M' : 'K';
        System.out.print("Podaj numer działu: ");
        int nrDzialu = s.nextInt();
        System.out.print("Podaj płacę pracownika: ");
        float placa = s.nextFloat();
        System.out.print("Podaj wiek pracownika: ");
        int wiek = s.nextInt();
        System.out.print("Podaj liczbę dzieci pracownika: ");
        int liczbaDzieci = s.nextInt();
        System.out.print("Czy pracownik jest w związku małżeńskim (T | N): ");
        boolean stanCywilny = s.next().toUpperCase().equals("T");

        Program.getPracownicy().add(new Pracownik(imie, nazwisko, plec, nrDzialu, placa, wiek, liczbaDzieci, stanCywilny));
    }

    public static void usunPracownika() {
        pokazPracownikowKrotka();
        Scanner s = new Scanner(System.in);
        System.out.print("Podaj numer pracownika do usunięcia: ");
        int numerPracownika = s.nextInt();
        Program.getPracownicy().remove(numerPracownika);
    }

    private static void eksportPracownika(Scanner s) {
        pokazPracownikowKrotka();
        System.out.print("Którego pracownika eksportować: ");
        Pracownik p = Program.getPracownicy().get(s.nextInt());
        System.out.print("Podaj nazwę pliku: ");
        Program.setAktualnyPlik(s.next());
        p.eksportPracownika(Program.getAktualnyPlik());
    }

    private static void edytujPracownika(Scanner s) {
        pokazPracownikowPelna();

        System.out.print("Którego pracownika edytować: ");
        int nrPracownika = s.nextInt();
        Pracownik p;

        if (!Program.getPracownicy().isEmpty()) p = Program.getPracownicy().get(nrPracownika);
        else {
            System.out.println("Brak pracowników");
            return;
        }

        p.pokazSpecjalne();
        System.out.print("ZMIANA: 1. Numeru działu 2. Płacy 3. Wieku 4. Liczby dzieci 5. Stanu cywilnego" +
                (p.getPlec() == 'K' ? "6. Nazwisko" : "") + "\nTwój wybór: ");

        switch (s.nextInt()) {
            case 1: {
                System.out.print("Nowy numer działu: ");
                p.setNrDzialu(s.nextInt());
            }
            break;
            case 2: {
                System.out.print("Nowa płaca: ");
                p.setPlaca(s.nextFloat());
            }
            break;
            case 3: {
                System.out.print("Nowy wiek: ");
                p.setWiek(s.nextInt());
            }
            break;
            case 4: {
                System.out.print("Nowa liczba dzieci: ");
                p.setLiczbaDzieci(s.nextInt());
            }
            break;
            case 5: {
                System.out.print("Nowy stan cywilny (T/N): ");
                p.setStanCywilny(s.next().equals("T"));
            }
            break;
            case 6: {
                if (p.getPlec() == 'K') {
                    System.out.print("Nowe nazwisko: ");
                    p.setNazwisko(s.next());
                }
            }
            break;
        }
        Program.getPracownicy().set(nrPracownika, p);
        Program.getPracownicy().get(nrPracownika).pokaz();
    }

    private static void infoOProgramie() {
        System.out.println("--OOP Projekt--\ndla SDA\nAutor: BP");
    }

    private static void zmienNazweDat(String nowaNazwa) {
        PlikMenu.setNazwaPliku(nowaNazwa);
    }
}
