import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {


    /**
     * 作用： 执行游戏---n局n个玩家
     *
     * @param args
     */
    public static void main(String args[]) {

        System.out.println("Welcome to Blackjack!");
        //todo:输入玩家人数
        Scanner input = new Scanner(System.in);//创建一个键盘扫描类对象
        System.out.println("输入参与游戏的次数:");
        int gameNum = input.nextInt(); //输入整型
        System.out.println("输入参与游戏的玩家人数:");
        int playerNum = input.nextInt(); //输入整型
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(new Player(1000, String.valueOf(i)));
        }


        //todo:仅玩一局，若想玩多局restart，加循环，图形化界面各种功能未加
//         System.out.println("=====Game"+"1"+"start=====");
        OneGame oneGame = new OneGame(players);
        int gameIndex = 1;
        while (gameNum > 0) {
            gameNum--;
            System.out.println("=====Game " + gameIndex + " start=====");
            oneGame.runGame();
            gameIndex++;
        }

    }
}
