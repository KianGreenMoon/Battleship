/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package bones;

class Board {
    private static int len = 10;
    private boolean grid[][][] = new boolean[len][len][2];

    boolean[][][] getGrid() {
        return grid;
    }

    boolean[] getState(int x, int y) {
        //get State
        try {
            return getGrid()[x][y];
        } catch (Exception e) {
            return new boolean[]{false, false};
        }
    }

    static boolean isBoard(int x, int y) {
        //Is not correct for asymmetric board
        return len > x && len > y;
    }

    void setState(int x, int y, boolean hasShip) {
        //set State
        try {
            getGrid()[x][y][0] = hasShip;
        } catch (Exception e) {
            System.err.println(e + " Error in setState");
        }
    }

    void setState(int x, int y, boolean hasShip, boolean isShooted) {
        //set State
        try {
            setState(x, y, hasShip);
            getGrid()[x][y][1] = isShooted;
        } catch (Exception e) {
            System.err.println(e + " Error in setState");
        }
    }

    void visualBoard() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "|");
        }
        System.out.println();
        int lineNumber = 0;
        for (boolean[][] a : getGrid()) {
            for (boolean[] b : a) {
                //cell0 is ship; cell1 is shooted
                if (!b[0] && !b[1])         //cell0 == false; cell1 == false;
                    System.out.print("~");
                else if (!b[0])             //cell0 == false; cell1 == true;
                    System.out.print("*");
                else if (b[1])              //cell0 == true; cell1 == true;
                    System.out.print("X");
                else System.out.print("#"); //cell0 == true; cell1 == false;

                System.out.print("|");
            }

            System.out.println(lineNumber++);
        }

    }
}
