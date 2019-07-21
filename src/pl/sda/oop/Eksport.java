package pl.sda.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

public class Eksport {
    private static PrintWriter printWriter;
    private static File file;
    private Pracownik pracownik;

    public Eksport(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    private static void utworzPlik(String nazwaPliku) {
        file = new File(nazwaPliku);

        try { printWriter = new PrintWriter(file.getName());
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public void zapiszPracownika(String nazwaPliku) {
        utworzPlik(nazwaPliku);
        printWriter.println(pracownik.getNazwisko());
        printWriter.println(pracownik.getImie());
        printWriter.println(pracownik.getPlec());
        printWriter.println(pracownik.getNrDzialu());
        printWriter.println(pracownik.getPlaca());
        printWriter.println(pracownik.getWiek());
        printWriter.println(pracownik.getLiczbaDzieci());
        printWriter.close();
        file = null;
    }

    public static void eksportujListe(String nazwaPliku, ArrayList<Pracownik> lista) {
        utworzPlik(nazwaPliku);
        if(!lista.isEmpty()) for (Pracownik p : lista) {
            printWriter.println(p.getNazwisko());
            printWriter.println(p.getImie());
            printWriter.println(p.getPlec());
            printWriter.println(p.getNrDzialu());
            printWriter.println(p.getPlaca());
            printWriter.println(p.getWiek());
            printWriter.println(p.getLiczbaDzieci());
        } else System.out.println("Brak pracowników");

        printWriter.close();
    }

    public static void ekportujListePracownikow(String nazwaPliku, ArrayList<Pracownik> pracowniks) {
        utworzPlik(nazwaPliku);

        for (Pracownik p :
                pracowniks) {
            printWriter.println(p.getImie());
            printWriter.println(p.getNazwisko());
            printWriter.println(p.getPlec());
            printWriter.println(p.getNrDzialu());
            printWriter.println(p.getPlaca());
            printWriter.println(p.getLiczbaDzieci());
            printWriter.println(p.getWiek());
            printWriter.println(p.isStanCywilny());
        }
        printWriter.close();
        printWriter = null;
        file = null;
    }

    public static void ekportujSzyfrowanaListe(String nazwaPliku, ArrayList<Pracownik> pracowniks) {
        utworzPlik(nazwaPliku);
        float sumaPlac = 0;
        for (Pracownik p :
                pracowniks) {
            sumaPlac += p.getPlaca();
        }

        float srednia = sumaPlac / pracowniks.size();
        for (Pracownik p :
                pracowniks) {
            printWriter.println(p.getImie());
            if (p.getPlaca() < srednia) {
                String gwiazdki = "";
                for (int i = 0; i < p.getNazwisko().length() - 2; i++) {
                    gwiazdki += "*";
                }
                printWriter.println(p.getNazwisko().replace(
                        p.getNazwisko().subSequence(1, p.getNazwisko().length() - 1),
                        gwiazdki.subSequence(0, gwiazdki.length())
                ));
            } else printWriter.println(p.getNazwisko());
            printWriter.println(p.getPlec());
            printWriter.println(p.getNrDzialu());
            printWriter.println(p.getPlaca());
            printWriter.println(p.getLiczbaDzieci());
            printWriter.println(p.getWiek());
            printWriter.println(p.isStanCywilny());
        }
        printWriter.close();
        printWriter = null;
        file = null;
    }

    public static void tabelaHTML(ArrayList<Pracownik> pracowniks) {
        String html =
                "<!doctype html>\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "\n" +
                "    <title>The HTML5 Herald</title>\n" +
                "    <meta name=\"description\" content=\"The HTML5 Herald\">\n" +
                "    <meta name=\"author\" content=\"SitePoint\">\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"css/styles.css?v=1.0\">\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>" +
                "<table style=\"width:100%\">\n" +
                "    <tr>\n" +
                "        <th>LP.</th>\n" +
                "        <th>Nazwisko</th>\n" +
                "        <th>Imię</th>\n" +
                "        <th>Płeć</th>\n" +
                "        <th>Numer Działu</th>\n" +
                "        <th>Płaca</th>\n" +
                "        <th>Wiek</th>\n" +
                "    </tr>\n";

        for (int i = 0; i < pracowniks.size(); i++) {
            html +=
                    "    <tr>\n" +
                    "        <th>" + (i + 1) + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getNazwisko() + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getImie() + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getPlec() + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getNrDzialu() + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getPlaca() + "</th>\n" +
                    "        <th>" + pracowniks.get(i).getWiek() + "</th>\n" +
                    "    </tr>\n";
        }
        html +=
                "</table>\n" +
                "</body>\n" +
                "</html>";

        utworzPlik("tabela.html");
        printWriter.println(html);
        printWriter.close();
    }
}

