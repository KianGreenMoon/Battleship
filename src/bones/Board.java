/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package bones;

class Board {
    private boolean grid[][][] = new boolean[10][10][2];

    boolean[][][] getGrid() {
        return grid;
    }

    boolean[] getState(int x, int y) {
        //get State
        return getGrid()[y][x];
    }

    void setState(int x, int y, boolean hasShip) {
        //set State
        getGrid()[x][y][0] = hasShip;
    }

    void setState(int x, int y, boolean hasShip, boolean isShooted) {
        //set State
        setState(x, y, hasShip);
        getGrid()[y][x][1] = isShooted;
    }
    void visualBoard() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "|");
        }
        System.out.println();
        int lineNumber = 0;
        for (boolean[][] a : getGrid()) {
            for (boolean[] b : a) {

                if(!b[0] && !b[1])
                    System.out.print("~");
                else if(!b[0] && b[1])
                    System.out.print("*");
                else if(b[0] && b[1])
                    System.out.print("X");
                else if(b[0] && !b[1])
                    System.out.print("#");

                System.out.print("|");
            }

            System.out.println(lineNumber++);
        }

    }
}
