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

                    shipsCreater(player1);
                    shipsCreater(player2);

                    gameOn(player1, player2);

                    System.out.println(player1.getName());
                    player1.outputMyBoard();
                    System.out.println();
                    System.out.println(player2.getName());
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
        while (player1.hasAnyShip() && player2.hasAnyShip()) {
            shooting(player1, player2);
            if (player2.hasAnyShip())
                shooting(player2, player1);
            else break;
        }
        System.out.println("Game Over!");
    }

    private static void shooting(Player shooter, Player shooted) {
        Scanner scanner = new Scanner(System.in);
        String[] command;
        int x = 0;
        int y = 0;
        do {
            System.out.println(shooter.getName() + ", select cell for shoot (Ex: '0 5'):");
            command = scanner.nextLine().trim().split(" ");
            x = Integer.parseInt(command[0]);
            y = Integer.parseInt(command[1]);

            shooter.writeHit(shooted, x, y);
            shooted.writeHitMe(x, y);
            System.out.println("Enemy's Board:");
            shooter.outputEnemyBoard();
            if (!shooted.isThatShip(x, y)) {
                break;
            }
        }
        while (shooted.hasAnyShip());
    }

    private static void shipsCreater(Player player) {
        createShip(player, 4, 1);
        System.out.println(player.getName());
        player.outputMyBoard();
        System.out.println();
        //createShip(player, 3, 2);
        //player.outputMyBoard();
        //createShip(player, 2, 3);
        //player.outputMyBoard();
        //createShip(player, 1, 4);
        //player.outputMyBoard();
    }

    private static void createShip(Player player, int size, int count) {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;
        String[] command;

        for (int i = 0; i < count; i++) {
            System.out.println("Please, put parameters your ship (x, y, (-/+)size)\nwhere '-' is vertical, '+' horizontal." +
                    "\nFor example: '3 2 -4'\nRepeat for " + count + " ship(s)");
            while (true) {
                command = scanner.nextLine().trim().split(" ");
                x = Integer.parseInt(command[0]);
                y = Integer.parseInt(command[1]);
                if (command.length > 2)
                    size = -size;
                if (!player.isThatShip(x, y, size) && player.isBoard(x, y))
                    break;
                else System.out.println("Here is one ship or beyond the Board. Try again");
            }
            player.createShip(x, y, size);
        }
    }
}
