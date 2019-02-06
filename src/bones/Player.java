/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package bones;

public class Player {
    private String name;
    private Board myBoard;
    private Board enemyBoard;

    public Player(String myName) {
        setName(myName);
        myBoard = new Board();
        enemyBoard = new Board();
    }

    public String getName() {
        return name;
    }

    public boolean isBoard(int x, int y) {
        return Board.isBoard(x, y);
    }

    public boolean isBoard(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            if (!isBoard(x + i, y))
                return false;
        }
        for (int i = 0; i > size; i--) {
            if (!isBoard(x, y - i))
                return false;
        }
        return true;
    }

    private Board getMyBoard() {
        return myBoard;
    }

    private Board getEnemyBoard() {
        return enemyBoard;
    }

    public boolean isThatShip(int x, int y) {
        return getMyBoard().getState(x, y)[0];
    }

    public boolean isThatShip(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            if (isThatShip(x + i, y))
                return true;
        }
        for (int i = 0; i > size; i--) {
            if (isThatShip(x, y - i))
                return true;
        }
        return false;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void create1thSizeShip(int x, int y) {
        getMyBoard().setState(x, y, true);
    }

    /**
     * '-' is vertical, '+' horizontal ship.
     * For example: '3 2 -4 for vertical ship in 4 cells'
     */
    public void createShip(int x, int y, int size) {
        if (!isThatShip(x, y, size) && Board.isBoard(x, y)) {
            for (int i = 0; i < size; i++) {
                create1thSizeShip(x + i, y);
            }
            for (int i = 0; i > size; i--) {
                create1thSizeShip(x, y - i);
            }
        }
    }

    public void writeHit(Player player, int x, int y) {
        getEnemyBoard().setState(x, y, player.isThatShip(x, y), true);
    }

    public void writeHitMe(int x, int y) {
        getMyBoard().setState(x, y, getMyBoard().getState(x, y)[0], true);
    }

    public void outputMyBoard() {
        getMyBoard().visualBoard();
    }

    public void outputEnemyBoard() {
        getEnemyBoard().visualBoard();
    }

    public boolean hasAnyShip() {
        //This is not good check, but working. Anyway, it should be change in future
        for (boolean row[][] : getMyBoard().getGrid()) {
            for (boolean cell[] : row) {
                if (cell[0] && !cell[1])
                    return true;
            }
        }
        return false;
    }
}
