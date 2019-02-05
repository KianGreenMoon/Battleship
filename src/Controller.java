/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

import bones.Player;

import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {
            System.out.println("Welcome to Battleships");
            String command = scanner.nextLine();
            switch (command) {
                case "start":
                    System.out.println("Please, create first player:");
                    Player player1 = new Player(scanner.nextLine());

                    System.out.println("Please, create second player:");
                    Player player2 = new Player(scanner.nextLine());

                    System.out.println("Player 1 with name " + player1.getName() + " and player 2 " + player2.getName()
                            + " were successful created!");
                    System.out.println("So, let's get started...");

                    //shipsCreater(player1);
                    //shipsCreater(player2);

                    //gameOn(player1, player2);

                    player1.createShip(0, 0, 4);
                    player1.outputMyBoard();

                    player2.createShip(0, 0, -4);
                    player2.outputMyBoard();

                    break;
                case "exit":
                    System.out.println("Bye!");
                    break label;
                default:
                    System.out.println("Wrong command. Please, try again");
                    break;
            }
        }
    }

    private static void gameOn(Player player1, Player player2) {
        do {
            shooting(player1, player2);
            shooting(player2, player1);
            break;
        }
        while (player1.hasAnyShip() && player2.hasAnyShip());
        System.out.println("Game Over!");
    }

    private static void shooting(Player shooter, Player shooted) {
        do {
            break;
        }
        while (/*shooter Get collision && shooted.hasAnyShips*/true);
    }

    private static void shipsCreater(Player player) {
        createShip(player, 4, 1);
        //createShip(player, 3, 2);
        //createShip(player, 2, 3);
        //createShip(player, 1, 4);
    }

    private static void createShip(Player player, int size, int count) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, put parameters your ship (x, y, (-/+)size)\nwhere '-' is vertical, '+' horizontal." +
                "\nFor example: '3 2 -4'\nRepeat for" + count + "ship(s)");

        for (int i = 0; i < count; i++) {
            String[] command = scanner.nextLine().trim().split(" ");
            int x = Integer.parseInt(command[0]);
            int y = Integer.parseInt(command[1]);
            player.createShip(x, y, size);
        }
    }
}
