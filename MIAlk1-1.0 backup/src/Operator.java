import java.util.Arrays;
import java.util.Collections;

public class Operator {

    //a metodus eldonti, hogy egy megadott allapotra alkalmazhato-e egy megadott irany
    public boolean alkalmazhato(Allapot allapot, String irany) {
        boolean alk = false;
        switch (irany) {
            case "fel":
                alk = alkalmazhatoFel(allapot);
                break;
            case "le":
                alk = alkalmazhatoLe(allapot);
                break;
            case "jobb":
                alk = alkalmazhatoJobb(allapot);
                break;
            case "bal":
                alk = alkalmazhatoBal(allapot);
                break;
        }
        return alk;
    }

    //FEL
    //annak vizsgalata, hogy a kocka jelenlegi pozicioja kisebb-e mint a tabla szele,
    //illetve, hogy a pozicio felett van-e szurke kocka(szurke kockara nem billenhet a kocka),
    //tovabba megvizsgalja, hogy a kocka kiindulo allapota mi volt,
    //mert ha mar egyszer volt felfele billentes kiindulo helyzetbol,
    //akkor meg egy felfele dontes a piros(6-os) lapot eredmenyezne a legalso lapon
    public boolean alkalmazhatoFel(Allapot allapot) {
        if (allapot.kocka_i < 6 && allapot.tabla[allapot.kocka_i + 1][allapot.kocka_j] != 7) {
            if (!(allapot.kocka_kiindulo_bill.equals("fel"))) {
                return true;
            }
        }
        return false;
    }

    //LE
    public boolean alkalmazhatoLe(Allapot allapot) {
        if (allapot.kocka_i > 0 && allapot.tabla[allapot.kocka_i - 1][allapot.kocka_j] != 7) {
            if (!(allapot.kocka_kiindulo_bill.equals("le"))) {
                return true;
            }
        }
        return false;
    }

    //JOBBRA
    public boolean alkalmazhatoJobb(Allapot allapot) {
        if (allapot.kocka_j < 6 && allapot.tabla[allapot.kocka_i][allapot.kocka_j + 1] != 7) {
            if (!(allapot.kocka_kiindulo_bill.equals("jobb"))) {
                return true;
            }
        }
        return false;
    }

    //BALRA
    public boolean alkalmazhatoBal(Allapot allapot) {
        if (allapot.kocka_j > 0 && allapot.tabla[allapot.kocka_i][allapot.kocka_j - 1] != 7) {
            if (!(allapot.kocka_kiindulo_bill.equals("bal"))) {
                return true;
            }
        }
        return false;
    }

    //egy osszefogo metodus, amellyel a 4 kulonbozo operatort alkalmazzuk egy allapotra megadott irannyal
    public Allapot alkalmaz(Allapot aktualis, String irany) {
        Allapot uj = new Allapot();
        Collections.copy(uj.lapok, aktualis.lapok);
        switch (irany) {
            case "fel":
                uj = alkalmazFel(aktualis);
                break;
            case "le":
                uj = alkalmazLe(aktualis);
                break;
            case "jobb":
                uj = alkalmazJobb(aktualis);
                break;
            case "bal":
                uj = alkalmazBal(aktualis);
                break;
        }
        return uj;
    }

    public Allapot alkalmazFel(Allapot aktualis) {
        Allapot uj = new Allapot();
        uj.kocka_kiindulo_bill = aktualis.kocka_kiindulo_bill;
        Collections.copy(uj.lapok, aktualis.lapok);

        //6-os jelzi, a piros oldal helyét
        //{1,1,1,1,6,2} kezdetben a kocka oldalai
        //{2,6,1,1,1,1} pl.: 1.fellépés után

        //a az elozo elfoglalt helyet "toroljuk"
        uj.tabla[aktualis.kocka_i][aktualis.kocka_j] = 0;

        //megvizsgalja, hogy mi volt a kiindulo allas, aszerint valtoznak majd a kocka oldallapjai
        //ha az alaphelyzetbol valo elso dontes "le" volt, akkor a fel biztosan alaphelyzetbe rakja
        if (aktualis.kocka_kiindulo_bill.equals("le")) {
            uj.tabla[aktualis.kocka_i + 1][aktualis.kocka_j] = 2;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 1, 1, 6, 2));
            uj.kocka_kiindulo_bill = "alap";
        }
        //ha 6-os fent van
        else if (!(aktualis.kocka_kiindulo_bill.equals("fel")) && aktualis.lapok.get(4) == 6) {
            uj.tabla[aktualis.kocka_i + 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(2, 6, 1, 1, 1, 1));
            uj.kocka_kiindulo_bill = "fel";
        }
        //bal odalon van a 6-os
        else if (aktualis.kocka_kiindulo_bill.equals("bal") && aktualis.lapok.get(3) == 6) {
            uj.tabla[aktualis.kocka_i + 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 6, 1, 2, 1, 1));
        }
        //ha jobb oldalon van 6-os(piros)
        else if (aktualis.kocka_kiindulo_bill.equals("jobb") && aktualis.lapok.get(2) == 6) {
            uj.tabla[aktualis.kocka_i + 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 6, 2, 1, 1, 1));
        }

        uj.cubePosition();

        if (uj.lapok.get(5) == 2) {
            uj.kocka_kiindulo_bill = "alap";
        }

        return uj;
    }

    public Allapot alkalmazLe(Allapot aktualis) {
        Allapot uj = new Allapot();
        uj.kocka_kiindulo_bill = aktualis.kocka_kiindulo_bill;
        Collections.copy(uj.lapok, aktualis.lapok);

        uj.tabla[aktualis.kocka_i][aktualis.kocka_j] = 0;

        if (aktualis.kocka_kiindulo_bill.equals("fel")) {
            uj.tabla[aktualis.kocka_i - 1][aktualis.kocka_j] = 2;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 1, 1, 6, 2));
            uj.kocka_kiindulo_bill = "alap";
        }
        //ha 6-os fent van
        else if ((!(aktualis.kocka_kiindulo_bill.equals("le"))) && aktualis.lapok.get(4) == 6) {
            uj.tabla[aktualis.kocka_i - 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(6, 2, 1, 1, 1, 1));
            uj.kocka_kiindulo_bill = "le";
        }
        //bal odalon van a 6-os
        else if (aktualis.kocka_kiindulo_bill.equals("bal") && aktualis.lapok.get(3) == 6) {
            uj.tabla[aktualis.kocka_i - 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 2, 6, 1, 1));
        }
        //ha jobb oldalon van 6-os(piros)
        else if (aktualis.kocka_kiindulo_bill.equals("jobb") && aktualis.lapok.get(2) == 6) {
            uj.tabla[aktualis.kocka_i - 1][aktualis.kocka_j] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 6, 2, 1, 1));
        }

        uj.cubePosition();

        if (uj.lapok.get(5) == 2) {
            uj.kocka_kiindulo_bill = "alap";
        }

        return uj;
    }

    public Allapot alkalmazJobb(Allapot aktualis) {
        Allapot uj = new Allapot();
        uj.kocka_kiindulo_bill = aktualis.kocka_kiindulo_bill;
        Collections.copy(uj.lapok, aktualis.lapok);

        uj.tabla[aktualis.kocka_i][aktualis.kocka_j] = 0;

        //6-os bal oldalon
        if (aktualis.kocka_kiindulo_bill.equals("bal")) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j + 1] = 2;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 2, 6, 1, 1));
            uj.kocka_kiindulo_bill = "alap";
        }
        //ha 6-os fent van
        else if (!(aktualis.kocka_kiindulo_bill.equals("jobb")) && aktualis.lapok.get(4) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j + 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 6, 2, 1, 1));
            uj.kocka_kiindulo_bill = "jobb";
        }
        //6-os a közelebbik oldalon
        else if (aktualis.kocka_kiindulo_bill.equals("fel") && aktualis.lapok.get(1) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j + 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(2, 6, 1, 1, 1, 1));
        }
        //6-os a távolabbik odalon
        else if (aktualis.kocka_kiindulo_bill.equals("le") && aktualis.lapok.get(0) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j + 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(6, 2, 1, 1, 1, 1));
        }

        uj.cubePosition();

        if (uj.lapok.get(5) == 2) {
            uj.kocka_kiindulo_bill = "alap";
        }

        return uj;
    }

    public Allapot alkalmazBal(Allapot aktualis) {
        Allapot uj = new Allapot();
        uj.kocka_kiindulo_bill = aktualis.kocka_kiindulo_bill;
        Collections.copy(uj.lapok, aktualis.lapok);

        //6-os bal oldalon


        uj.tabla[aktualis.kocka_i][aktualis.kocka_j] = 0;

        if (aktualis.kocka_kiindulo_bill.equals("jobb")) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j - 1] = 2;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 1, 1, 6, 2));
            uj.kocka_kiindulo_bill = "alap";
        }
        //ha 6-os fent van
        else if (!(aktualis.kocka_kiindulo_bill.equals("bal")) && aktualis.lapok.get(4) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j - 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(1, 1, 2, 6, 1, 1));
            uj.kocka_kiindulo_bill = "bal";
        }
        //6-os a közelebbik oldalon
        else if (aktualis.kocka_kiindulo_bill.equals("fel") && aktualis.lapok.get(1) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j - 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(2, 6, 1, 1, 1, 1));
        }
        //6-os a távolabbik odalon
        else if (aktualis.kocka_kiindulo_bill.equals("le") && aktualis.lapok.get(0) == 6) {
            uj.tabla[aktualis.kocka_i][aktualis.kocka_j - 1] = 1;
            uj.lapok.clear();
            uj.lapok.addAll(Arrays.asList(6, 2, 1, 1, 1, 1));
        }

        uj.cubePosition();

        if (uj.lapok.get(5) == 2) {
            uj.kocka_kiindulo_bill = "alap";
        }

        return uj;
    }


}
