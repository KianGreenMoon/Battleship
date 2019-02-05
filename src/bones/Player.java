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

    private Board getMyBoard() {
        return myBoard;
    }

    private Board getEnemyBoard() {
        return enemyBoard;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void create1thSizeShip(int x, int y) {
        getMyBoard().setState(x, y, true);
    }

    public void createShip(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            create1thSizeShip(x + i, y);
        }
        for (int i = 0; i > size; i--) {
            create1thSizeShip(x, y - i);
        }
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
