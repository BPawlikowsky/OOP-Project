package pl.sda.oop.menu;

import pl.sda.oop.Eksport;
import pl.sda.oop.Pracownik;
import pl.sda.oop.Program;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class DodatkoweMenu {
    public static void dodatkoweMenu(Scanner s) {
        System.out.print("Menu dodatkowe:\n" +
                "A. PRACOWNICY O PENSJI      | " +
                "B. ŚREDNIA PŁACA W DZIALE   | " +
                "C. NAJWYŻSZA PENSJA WG PŁCI | \n" +
                "D. INFORMACJE OD DZIALE     | " +
                "E. STOSUNEK PŁAC WG PŁCI    | " +
                "F. ZWIĘKSZ PŁACE O 10 PROC. | \n" +
                "G. ZWIEKSZ PŁACE O KWOTĘ    | " +
                "H. POSORTUJ WG NAZWISK      | " +
                "I. POSORTUJ WG PŁAC\n" +
                "Twój wybór: ");

        switch (s.next()) {
            case "A": {
                System.out.print("Liczba pracowników o pensji powyżej: ");
                pokazPracownikowOPensji(s.nextFloat());
            }
            break;
            case "B": {
                System.out.print("Podaj dział do obliczenia średniej płacy: ");
                sredniaPlacaWDziale(s.nextInt());
            }
            break;
            case "C": {
                System.out.print("Wybierz płeć (M | K):");
                najwyzszaPlacaWgPlci(s.next());
            }
            break;
            case "D": {
                infoDzial();
            }
            break;
            case "E": {
                stosunekPlacWgPlci();
            }
            break;
            case "F": {
                zwiekszPensje10Procent();
            }
            break;
            case "G": {
                System.out.print("O jaką kwotę dokonać podwyżek dla wszystkich pracowników: ");
                zwiekszPensjeOKwote(s.nextFloat());
            }
            break;
            case "H": {
                System.out.print("Posortuj według nazwisk malejąco lub rosnąco (M | R): ");
                String input = s.next();
                boolean wybor = input == "M";
                sortujWPlikuWgNazwisk(wybor);
            }
            break;
            case "I": {
                System.out.print("Posortuj według pensji malejąco lub rosnąco (M | R): ");
                String input = s.next();
                boolean wybor = input == "M";
                sortujWPlikuWgPensji(wybor);
            }
            break;
        }
    }

    private static void pokazPracownikowOPensji(float pensja) {
        int ilu = 0;
        for (Pracownik p : Program.getPracownicy()) {
            if (p.getPlaca() >= pensja) ilu++;
        }
        System.out.printf("%d pracowników o pensji powyżej %dzł.", ilu, pensja);
    }

    private static void sredniaPlacaWDziale(int nrDzialu) {
        int licznik = 0;
        float sumaPlac = 0;
        for (Pracownik p : Program.getPracownicy()) {
            if (p.getNrDzialu() == nrDzialu) {
                sumaPlac += p.getPlaca();
                licznik++;
            }
        }
        System.out.printf("Średnia płac dla działu %d posiadającego %d pracowników wynosi %4.2fzł", nrDzialu, licznik, (sumaPlac / licznik));
    }

    private static void najwyzszaPlacaWgPlci(String plec) {
        float najwyzsza = 0.0f;
        for (Pracownik p : Program.getPracownicy()) {
            if (p.getPlec() == plec.charAt(0))
                if (najwyzsza <= p.getPlaca()) najwyzsza = p.getPlaca();
        }
        System.out.println("Najwyższa płaca wśród " + (plec.charAt(0) == 'M' ? "mężczyzn" : "kobiet") +
                " wynosi " + najwyzsza);
    }

    private static void infoDzial() {
        LinkedList<InfoDzial> id = new LinkedList<>();
        id.add(new InfoDzial(Program.getPracownicy().get(0).getNrDzialu()));

        for (Pracownik p : Program.getPracownicy()) {
            for (int i = 0; i < id.size(); i++) {
                if (id.get(i).getNrDzialu() == p.getNrDzialu()) {
                    id.get(i).setSumaPensji(id.get(i).getSumaPensji() + p.getPlaca());
                    if (p.getPlec() == 'M') id.get(i).setIloscMezczyzn(id.get(i).getIloscMezczyzn() + 1);
                    else id.get(i).setIloscKobiet(id.get(i).getIloscKobiet() + 1);
                } else {
                    id.add(new InfoDzial(p.getNrDzialu()));
                }
            }
        }

        for (InfoDzial info : id) {
            if (info.getIloscKobiet() > 0 && info.getIloscMezczyzn() > 0) {
                System.out.println(
                        "\n" + "---------------" + " Numer działu " +
                                info.getNrDzialu() +
                                " -----------------\n");
                System.out.println(
                        "W dziale jest większość " +
                                (info.getIloscKobiet() > info.getIloscMezczyzn() ? "kobiet" : "mężczyzn") +
                                ".");
                float stosunek = (float) (info.getIloscMezczyzn()) / (info.getIloscMezczyzn() + info.getIloscKobiet());
                System.out.println("Stosunek mężczyzn do kobiet wynosi: " + stosunek);
                if (stosunek >= 0.4 && stosunek <= 0.6) System.out.println("Ilość kobiet i mężczyzn jest równa");
                else System.out.println(
                        "W dziale jest większość " +
                                (stosunek > 0.6 ? "mężczyzn" : (stosunek < 0.4 ? "kobiet" : ""))
                );
                sredniaPlacaWDziale(info.getNrDzialu());
            }
        }
    }

    private static void stosunekPlacWgPlci() {
        float sumaPlacKobiet = 0;
        int iloscKobiet = 0;
        float sumaPlacMezczyzn = 0;
        int iloscMezczyzn = 0;

        for (Pracownik p : Program.getPracownicy()) {
            if (p.getPlec() == 'M') {
                sumaPlacMezczyzn += p.getPlaca();
                iloscMezczyzn++;
            } else {
                sumaPlacKobiet += p.getPlaca();
                iloscKobiet++;
            }
        }
        System.out.printf("Średnia płaca mężczyzn: %5.2f\nŚrednia płaca kobiet: %5.2f\n"
                , sumaPlacMezczyzn / iloscMezczyzn, sumaPlacKobiet / iloscKobiet);
    }

    private static void zwiekszPensje10Procent() {
        for (Pracownik p : Program.getPracownicy()) {
            System.out.println("Podwyżka: " + p.obliczaniePodwyzki(10f));
            p.setPlaca(p.getPlaca() + p.obliczaniePodwyzki(10f));
        }
        System.out.println("Zwiększono pensje pracowników o 10% uwzględniając" +
                " indywidualne podwyżki ze względu na stan cywilny.");
    }

    private static void zwiekszPensjeOKwote(float kwota) {
        float sumaPodwyzek = 0;
        float sumaPodwyzekKobiety = 0;
        float sumaPodwyzekMezczyzni = 0;

        for (Pracownik p : Program.getPracownicy()) {
            float podwyzka = p.obliczaniePodwyzki(((kwota / p.getPlaca()) * 100));
            sumaPodwyzek += podwyzka;
            if (p.getPlec() == 'M') sumaPodwyzekMezczyzni += podwyzka;
            else sumaPodwyzekKobiety += podwyzka;
        }
        System.out.printf(
                "Suma podwyżek: %6.2fzł\nStosunek podwyżek mężczyzn względem kobiet: %1.1f\n" +
                        "Stusunek podwyżek kobiet do mężczyzn: %1.1f"
                , sumaPodwyzek, sumaPodwyzekMezczyzni / sumaPodwyzek, sumaPodwyzekKobiety / sumaPodwyzek
        );
    }

    private static void sortujWPlikuWgNazwisk(boolean sort) {
        ArrayList<Pracownik> poNazwiskach = Program.getPracownicy();
        if (sort) {
            Comparator<Pracownik> comp = new Comparator<Pracownik>() {
                @Override
                public int compare(Pracownik o1, Pracownik o2) {
                    if (o1.getNazwisko().charAt(0) < o2.getNazwisko().charAt(0)) return -1;
                    else if (o1.getNazwisko().charAt(0) == o2.getNazwisko().charAt(0)) return 0;
                    else return 1;
                }
            };
        } else {
            Comparator<Pracownik> comp = new Comparator<Pracownik>() {
                @Override
                public int compare(Pracownik o1, Pracownik o2) {
                    if (o1.getNazwisko().charAt(0) < o2.getNazwisko().charAt(0)) return 1;
                    else if (o1.getNazwisko().charAt(0) == o2.getNazwisko().charAt(0)) return 0;
                    else return -1;
                }
            };

            poNazwiskach.sort(comp);
            Eksport.eksportujListe("PosortowanaListaPracownikow.txt", poNazwiskach);
        }
    }

    private static void sortujWPlikuWgPensji(boolean sort) {
    ArrayList<Pracownik> poPensji = Program.getPracownicy();
        if (sort) {
            Comparator<Pracownik> comp = new Comparator<Pracownik>() {
                @Override
                public int compare(Pracownik o1, Pracownik o2) {
                    if (o1.getPlaca() < o2.getPlaca()) return -1;
                    else if (o1.getPlaca() == o2.getPlaca()) return 0;
                    else return 1;
                }
            };
        } else {
            Comparator<Pracownik> comp = new Comparator<Pracownik>() {
                @Override
                public int compare(Pracownik o1, Pracownik o2) {
                    if (o1.getPlaca() < o2.getPlaca()) return 1;
                    else if (o1.getPlaca() == o2.getPlaca()) return 0;
                    else return -1;
                }
            };

            poPensji.sort(comp);
            Eksport.eksportujListe("PosortowanaListaPracownikow.txt", poPensji);
        }
    }
}
