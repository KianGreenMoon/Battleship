/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

import bones.Player;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Battleships");
            String command = scanner.nextLine();
            if (command.equals("StartGame")) {
                System.out.println("Please, create first player with param (example: Name, My ships):");
                //scanner.nextLine().split(", ");
                Player player1 = new Player(scanner.nextLine());
                System.out.println("Please, create second player with param (example: Name, My ships):");
                Player player2 = new Player(scanner.nextLine());
                System.out.println("Player 1 with name " + player1.getName() + " and player 2 " + player2.getName()
                        + " were successful created!");
                System.out.println("So, let's get started...");

                player1.setShip(0,0, 3, true);
                //player1.outputMyBoard();

                gameOn(player1, player2);

                player1.outputMyBoard();
            } else if (command.equals("Exit")) {
                System.out.println("Bye!");
                break;
            }
        }
    }

    private static void gameOn(Player player1, Player player2) {
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Input coordinates (x, than y):");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            shooting(player1, player2, x, y);

            System.out.println("Input coordinates (x, than y):");
            x = scanner.nextInt();
            y = scanner.nextInt();
            shooting(player2, player1, x ,y);
            break;
        }
        while (player1.hasAnyShip() && player2.hasAnyShip());
        System.out.println("Game Over!");
    }

    private static void shooting(Player shooter, Player shooted, int x, int y) {
        do {
            //shooter shooting

            shooter.setEnemyCellState(x, y, shooted.isItShip(x, y));
            shooted.hasShooted(x, y);
            break;
        }
        while (/*shooter Get collision && shooted.hasAnyShips*/true);
    }
}
