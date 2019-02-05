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
            if (command.equals("start")) {
                System.out.println("Please, create first player with param (example: Name, My ships):");
                //scanner.nextLine().split(", ");
                Player player1 = new Player(scanner.nextLine());
                System.out.println("Please, create second player with param (example: Name, My ships):");
                Player player2 = new Player(scanner.nextLine());
                System.out.println("Player 1 with name " + player1.getName() + " and player 2 " + player2.getName()
                        + " were successful created!");
                System.out.println("So, let's get started...");

                player1.outputMyBoard();
                shipsCreater(player1);
                player1.outputMyBoard();
//                player1.setShip(0,0, 3, true);
                player2.outputMyBoard();
                shipsCreater(player2);
                player2.outputMyBoard();

                gameOn(player1, player2);

                System.out.println(player1.getName());
                player1.outputMyBoard();
                System.out.println();
                System.out.println(player2.getName());
                player2.outputMyBoard();
            } else if (command.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else System.out.println("Wrong command. Please, try again");
        }
    }

    private static void gameOn(Player player1, Player player2) {
        do {


            shooting(player1, player2);
            System.out.println(player2.getName());
            player2.outputMyBoard();

            shooting(player2, player1);
            System.out.println(player1.getName());
            player1.outputMyBoard();
            //break;
        }
        while (player1.hasAnyShip() && player2.hasAnyShip());
        System.out.println("Game Over!");
    }

    private static void shooting(Player shooter, Player shooted) {
        do {
            //shooter shooting
            Scanner scanner = new Scanner(System.in);

            System.out.println("Input coordinates (x, than y; ex: '5 3'):");
            String[] command = scanner.nextLine().split(" ");
            int x = Integer.parseInt(command[0]);
            int y = Integer.parseInt(command[1]);

            shooter.setEnemyCellState(x, y, shooted.isItShip(x, y));
            shooted.hasShooted(x, y);
            break;
        }
        while (/*shooter Get collision && shooted.hasAnyShips*/true);
    }

    private static void shipsCreater(Player player) {
        createShip(player, 4);
        //createShip(player, 3);
        //createShip(player, 2);
        //createShip(player, 1);
    }

    private static void createShip(Player player, int typeOfShip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ship type = " + typeOfShip);
        System.out.println("Example for command: '3 2 true'");
        for (int i = 0; i > typeOfShip - 5; i--) {
            System.out.println("Choise start position for this ship");
            String[] command = scanner.nextLine().split(" ");
            int x = Integer.parseInt(command[0]);
            int y = Integer.parseInt(command[1]);
            boolean isHorisontal = (command[2].equals("true"));
            player.setShip(x, y, typeOfShip, isHorisontal);
        }
    }
}
