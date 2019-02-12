/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

import api.GameAPI;
import api.TelegramAPI;
import bones.Player;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Check proxy: " + System.getProperty("java.net.useSystemProxies"));
        TelegramAPI api = new TelegramAPI("token", "chat_id");
        System.out.println("Check proxy: " + System.getProperty("java.net.useSystemProxies"));
        api.sendMessage("Test");

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

                    GameAPI.shipsCreater(player1, true);
                    GameAPI.shipsCreater(player2, true);

                    System.out.println(player1.outputMyBoard());
                    api.sendMessage(player1.outputMyBoard());

                    //gameOn(player1, player2);

                    System.out.println(player1.getName());

                    System.out.println();
                    System.out.println(player2.getName());

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
}
