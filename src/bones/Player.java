/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package bones;

public class Player {
    private String name;
    private Board myBoard;
    private Board enemyBoard;
    private int shipsCount;

    public Player() {
        this("Default");
    }

    public Player(String myName) {
        setName(myName);
        myBoard = new Board();
        enemyBoard = new Board();
        //shipsCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getShipsCount() {
        return shipsCount;
    }

    private void setName(String name) {
        this.name = name;
    }

    private Board getMyBoard() {
        return myBoard;
    }

    private Board getEnemyBoard() {
        return enemyBoard;
    }

    public void setShip(int x, int y, int lon, boolean isHorisontal) {
        try {
            //Set Ship
            if (isHorisontal) {
                for (int i = 0; i < lon; i++) {
                    getMyBoard().setState(x + i, y, true);
                }
            } else {
                for (int i = 0; i < lon; i++) {
                    getMyBoard().setState(x, y + i, true);
                }
            }
            //shipsCount++;
        }
        catch (Exception e) {System.err.println("Bad variables for this Ship!");}
    }

    public void setEnemyCellState(int x, int y, boolean hasShip) {
        try {
            getEnemyBoard().setState(x, y, hasShip, true);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }

    public boolean isItShip(int x, int y) {
        try {
            return getMyBoard().getState(x, y)[0];
        }
        catch (Exception e) {return false;}
    }
    public void hasShooted(int x, int y) {
        try {
            getMyBoard().setState(x, y, isItShip(x, y), true);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public void outputMyBoard() {
        getMyBoard().visualBoard();
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
        //return getShipsCount() > 0;
    }
}
