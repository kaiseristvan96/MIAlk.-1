import java.util.ArrayList;
import java.util.Arrays;

public class Allapot {
    int[][] tabla;
    //kocka pozíciója a táblán
    short kocka_i;
    short kocka_j;

    //kocka lapja lent
    int kocka_lap;
    //kocka lapjai:tavolabbik,kozelebbik,jobb,bal,fent,lent
    ArrayList<Integer> lapok = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10));

    String kocka_kiindulo_bill = "alap"; //alaphelyzet:1-es alul, 6-os felül.

    //look for the colored cube's position
    public void cubePosition() {
        for (short i = 0; i < 7; i++) {
            for (short j = 0; j < 7; j++) {
                if (this.tabla[i][j] >= 1 && this.tabla[i][j] <= 5) {
                    this.kocka_i = i;
                    this.kocka_j = j;
                    this.kocka_lap = this.tabla[i][j];
                }
            }
        }
    }

    public Allapot() {
        this.tabla = new int[][]
                {{0, 0, 0, 0, 7, 0, 0},
                        {7, 0, 0, 0, 0, 0, 7},
                        {0, 7, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 7, 0, 0},
                        {0, 7, 0, 0, 0, 0, 0},
                        {0, 0, 7, 0, 7, 0, 7},
                        {0, 0, 0, 0, 0, 0, 0}};
    }

    @Override
    public String toString() {
        return tabla[0][0] + " " + tabla[0][1] + " " + tabla[0][2] + " " + tabla[0][3] + " " + tabla[0][4] + " " + tabla[0][5] + " " + tabla[0][6] + "\n" +
                tabla[1][0] + " " + tabla[1][1] + " " + tabla[1][2] + " " + tabla[1][3] + " " + tabla[1][4] + " " + tabla[1][5] + " " + tabla[1][6] + "\n" +
                tabla[2][0] + " " + tabla[2][1] + " " + tabla[2][2] + " " + tabla[2][3] + " " + tabla[2][4] + " " + tabla[2][5] + " " + tabla[2][6] + "\n" +
                tabla[3][0] + " " + tabla[3][1] + " " + tabla[3][2] + " " + tabla[3][3] + " " + tabla[3][4] + " " + tabla[3][5] + " " + tabla[3][6] + "\n" +
                tabla[4][0] + " " + tabla[4][1] + " " + tabla[4][2] + " " + tabla[4][3] + " " + tabla[4][4] + " " + tabla[4][5] + " " + tabla[4][6] + "\n" +
                tabla[5][0] + " " + tabla[5][1] + " " + tabla[5][2] + " " + tabla[5][3] + " " + tabla[5][4] + " " + tabla[5][5] + " " + tabla[5][6] + "\n" +
                tabla[6][0] + " " + tabla[6][1] + " " + tabla[6][2] + " " + tabla[6][3] + " " + tabla[6][4] + " " + tabla[6][5] + " " + tabla[6][6] + "\n";
    }

    public boolean cel() {
        if (tabla[2][0] >= 1 && tabla[2][0] <= 5) {
            return true;
        } else
            return false;
    }

    public Allapot kezdoLetrehoz() {
        Allapot kezdo = new Allapot();
        kezdo.tabla[5][5] = 2;
        kezdo.lapok.clear();
        kezdo.lapok.add(1);
        kezdo.lapok.add(1);
        kezdo.lapok.add(1);
        kezdo.lapok.add(1);
        kezdo.lapok.add(6);// 6-os jelolt oldal fent, 2-essel jelolt lent
        kezdo.lapok.add(2);
        return kezdo;
    }
}
