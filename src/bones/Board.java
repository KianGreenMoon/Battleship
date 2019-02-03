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
        getGrid()[y][x][0] = hasShip;
    }

    void setState(int x, int y, boolean hasShip, boolean isShooted) {
        //set State
        setState(y, x, hasShip);
        getGrid()[y][x][1] = isShooted;
    }
    void visualBoard() {
        for (boolean[][] a : getGrid()) {
            for (boolean[] b : a) {
                System.out.print("|");

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

            System.out.println("\n");
        }

    }
}
