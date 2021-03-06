/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package api;

import bones.Player;

import java.util.Scanner;

public class GameAPI {

    private static void gameOn(Player player1, Player player2) {
        while (player1.hasAnyShip() && player2.hasAnyShip()) {
            shooting(player1, player2, false);
            if (player2.hasAnyShip()) {
                shooting(player2, player1, true);
                System.out.println(player1.getName());
                //visualStrings(player1.outputMyBoard());
            } else break;
        }
        System.out.println("Game Over!");
    }

    private static void shooting(Player shooter, Player shooted, boolean Auto) {
        if (Auto) {
            int x = 0;
            int y = 0;
            do {
                //System.out.println(shooter.getName() + ", select cell for shoot (Ex: '0 5'):");
                x = (int) Math.round(Math.random() * 9.0);
                //System.out.println("X is " + x);
                y = (int) Math.round(Math.random() * 9.0);
                //System.out.println("Y is " + y);

                if (!shooted.isHit(x, y)) {
                    shooter.writeHit(shooted, x, y);
                    shooted.writeHitMe(x, y);
                    //System.out.println("Enemy's Board:");
                    //shooter.outputEnemyBoard();
                    if (!shooted.isThatShip(x, y)) break;
                }
            }
            while (shooted.hasAnyShip());
        } else {
            shooting(shooter, shooted);
        }
    }

    private static void shooting(Player shooter, Player shooted) {
        String[] command;
        int x = 0;
        int y = 0;
        do {
            System.out.println(shooter.getName() + ", select cell for shoot (Ex: '0 5'):");
            command = scanner();
            x = Integer.parseInt(command[0]);
            y = Integer.parseInt(command[1]);

            shooter.writeHit(shooted, x, y);
            shooted.writeHitMe(x, y);
            System.out.println("Enemy's Board:");
            //visualStrings(shooter.outputEnemyBoard());
            if (!shooted.isThatShip(x, y)) break;
        }
        while (shooted.hasAnyShip());
    }

    public static void shipsCreater(Player player, boolean Auto) {
        if (Auto) {
            createShipAuto(player, 4, 1);
            createShipAuto(player, 3, 2);
            createShipAuto(player, 2, 3);
            createShipAuto(player, 1, 4);

            System.out.println(player.getName());
            //visualStrings(player.outputMyBoard()); // This line is spoiler for debug
            System.out.println();
        } else {
            shipsCreater(player);
        }
    }

    private static void shipsCreater(Player player) {
        createShip(player, 4, 1);
        System.out.println(player.getName());
        //visualStrings(player.outputMyBoard());
        System.out.println();
        createShip(player, 3, 2);
        //visualStrings(player.outputMyBoard());
        createShip(player, 2, 3);
        //visualStrings(player.outputMyBoard());
        createShip(player, 1, 4);
        //visualStrings(player.outputMyBoard());
    }

    private static void createShip(Player player, int size, int count) {
        int x = 0;
        int y = 0;
        String[] command;

        for (int i = 0; i < count; i++) {
            System.out.println("Please, put parameters your ship (x, y, (-/+)size)\nwhere '-' is vertical, '+' horizontal." +
                    "\nFor example: '3 2 -4'\nRepeat for " + count + " ship(s)");
            while (true) {
                command = scanner();
                x = Integer.parseInt(command[0]);
                y = Integer.parseInt(command[1]);
                if (command.length > 2)
                    size = -size;
                if (!player.isThatShip(x, y, size) && player.isBoard(x, y, size))
                    break;
                else System.out.println("Here is one ship or beyond the Board. Try again");
            }
            player.createShip(x, y, size);
        }
    }

    private static void createShipAuto(Player player, int size, int count) {
        int x = 0;
        int y = 0;
        if (Math.random() > 0.5)
            size = -size;
        for (int i = 0; i < count; i++) {
            do {
                x = (int) Math.round(Math.random() * 9.0);
                //System.out.println("X is " + x);
                y = (int) Math.round(Math.random() * 9.0);
                //System.out.println("Y is " + y);
            } while (player.isThatShip(x, y, size) || !player.isBoard(x, y, size));
            player.createShip(x, y, size);
        }
    }

    private static String[] scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().split(" ");
    }
}
