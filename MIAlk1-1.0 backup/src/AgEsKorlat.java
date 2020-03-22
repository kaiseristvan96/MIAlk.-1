import java.util.*;

public class AgEsKorlat {

    static class Csucs {
        Allapot allapot;
        Csucs szulo;
        String eloallito;
        ArrayList<String> nemProbalt = new ArrayList<String>();

        public Csucs(Allapot allapota, Csucs szulo, String eloallito) {
            this.allapot = allapota;
            this.szulo = szulo;
            this.eloallito = eloallito;
            allapot.cubePosition();
            Operator alk = new Operator();

            //ellenorzi, hogy mely operatorok alkalmazhatoak az allapotra
            //ez egy muveleti sorrend is
            if (alk.alkalmazhato(allapot, "fel")) {
                nemProbalt.add("fel");
            }
            if (alk.alkalmazhato(allapot, "bal")) {
                nemProbalt.add("bal");
            }
            if (alk.alkalmazhato(allapot, "le")) {
                nemProbalt.add("le");
            }
            if (alk.alkalmazhato(allapot, "jobb")) {
                nemProbalt.add("jobb");
            }


        }
    }

    public ArrayList<String> megoldas(Csucs terminalis) {
        ArrayList<String> megoldas = new ArrayList<>();
        for (Csucs cs = terminalis; cs.szulo != null; cs = cs.szulo)
            megoldas.add(cs.eloallito);
        return megoldas;
    }

    public ArrayList<String> keres(Integer korlat) {
        ArrayList<String> legjobb = new ArrayList<>();
        Operator o = new Operator();
        Allapot kezdo = new Allapot();
        kezdo = kezdo.kezdoLetrehoz();

        Csucs aktualis = new Csucs(kezdo, null, null);

        int uthossz = 0;


        if (aktualis.allapot.cel()) {
            return megoldas(aktualis);
        }

        System.out.println("Kezdo: \n" + aktualis.allapot);
        while (true) {
            if ((korlat == null || uthossz < korlat) && !aktualis.nemProbalt.isEmpty()) {
                String irany = aktualis.nemProbalt.remove(0);
                Allapot uj = o.alkalmaz(aktualis.allapot, irany);
                uj.cubePosition();

                boolean voltmar = false;
                Csucs dummy = aktualis;
                for (int i = 0; i < uthossz + 1; i++) {
                    if (dummy.allapot.kocka_i == uj.kocka_i
                            && dummy.allapot.kocka_j == uj.kocka_j) {
                        voltmar = true;
                        break;
                    }
                    if (dummy.szulo == null) {
                        break;
                    }
                    dummy = dummy.szulo;

                }

                if (voltmar == false) {
                    aktualis = new Csucs(uj, aktualis, irany);
                    uthossz++;

                    if (aktualis.allapot.cel()) {
                        legjobb = megoldas(aktualis);
                        korlat = uthossz;
                        System.out.println("talalt egy megoldast: " +uthossz + " uthosszal");
                    }
                }

            } else {
                if (aktualis.szulo != null) {
                    aktualis = aktualis.szulo;
                    uthossz--;
                } else {
                    return legjobb;
                }
            }

        }

    }

    public static void main(String[] args) {
        long kezdo = System.currentTimeMillis();

        ArrayList<String> megoldas = new ArrayList<>();
        megoldas = (new AgEsKorlat().keres(25));
        Collections.reverse(megoldas);
        System.out.println(megoldas);
        System.out.println("eltelt ido masodpercben: " + (float)(System.currentTimeMillis() - kezdo)/1000);

    }

}
