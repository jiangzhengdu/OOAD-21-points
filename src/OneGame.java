import java.util.ArrayList;
import java.util.Scanner;

public class OneGame {
    protected ArrayList<Player> players;

    protected OneGame(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * 进行一次游戏
     */
    public void runGame() {
        //洗牌
        Suit suit = new Suit();
        suit.shuffleCard();

        //生成庄家
        Dealer dealer = new Dealer();

        //每个玩家的选项
        String button;

        //赢家列表
        ArrayList<String> Winnername = new ArrayList();


        //生成散家的信息
        for (Player player : players) {
            player.deleteCard();
            if (player.getBet() > 0) {
                Scanner input = new Scanner(System.in);
                System.out.println("请您输入玩家" + player.getName() + "的下注:");
                int tempBet = input.nextInt(); //输入整型
                player.setTempBet(tempBet);
                if (player.getBet() < player.tempBet) {
                    System.out.println("玩家" + player.getName() + "赌资为" + player.getBet() + "，不能下注");
                    System.out.println("请您重新输入玩家" + player.getName() + "的下注:");
                    tempBet = input.nextInt(); //输入整型
                    player.setTempBet(tempBet);
                }
                System.out.println("玩家" + player.getName() + "赌资总数为：" + player.getBet() + "，本轮下注：" + player.getTempBet());
            } else
                System.out.println("玩家" + player.getName() + "赌资为0，不能下注");
        }
        Round round = new Round();
        System.out.println("——————————————————————————————————");
        System.out.println("New round...");

        //庄家先获取两张牌，第一张牌是不可见的
        dealer.getRandomHiddenCard(suit);
        dealer.getRandomCard(suit);
        dealer.caculateCurrentPoints();

        //如果总点数超过17，则庄家再拿一张牌，否则，不拿牌


        //首先每一个玩家都获取两张牌
        for (Player player : players) {
            player.getRandomCard(suit);
            player.getRandomCard(suit);
            player.caculateCurrentPoints();
        }


//        if (dealer.getCurrentPoints() <= 21) {
//            //在每个人分发牌的时候,输入每一个玩家的决定
//            for (Player player : players) {
//                while (true) {
//                    round.printState(player, dealer);
//                    System.out.println("目前玩家" + player.getName() + "的点数: " + player.getCurrentPoints());
//                    System.out.println("目前庄家的点数: " + dealer.getCurrentPoints());
//                    if (player.getCurrentPoints() > 21) {
//                        System.out.println("玩家" + player.getName() + "超过21点，自动跳过！");
//                        break;
//                    }
//                    System.out.println("请您输入玩家 " + player.getName() + " 的决定:");
//                    System.out.println("1.add 2.skip 3.double:");
//                    Scanner input = new Scanner(System.in);
//                    button = input.next();
//
//                    ///choose to add another card or double the temp bets or choose to skip
//
//                    if (button.equals("add"))
//                        round.addRound(player, suit);
//                    else if (button.equals("double")) {
//                        round.doubleRound(player);
//                    } else if (button.equals("skip"))
//                        break;
//                    else System.out.println("add or skip or Double");
//                }
//            }
//            //扣钱或者给钱
//
//        } else {
//
//            for (Player player : players) {
//                round.printState(player, dealer);
//                Winnername.add(player.getName());
//            }
//            System.out.println("Dealer is out 21,round skips");
//
//            round.checkOutMoney(players, Winnername);
//        }

        //发牌阶段,默认没有人在不要牌的list中
        ArrayList<Player> notWantedCardList = new ArrayList<>();
        int index = 0;
        while (notWantedCardList.size() < players.size() || (dealer.getCurrentPoints()) < 17) {
            //庄家拿牌
            button = "none";
            if (dealer.getCurrentPoints() < 17) {
                dealer.getRandomCard(suit);
                dealer.caculateCurrentPoints();
            }
            //还有玩家要牌
            if (notWantedCardList.size() < players.size()) {
                for (Player player : players) {
                    if (notWantedCardList.contains(player)) continue;
                    System.out.println("轮到玩家"+player.getName()+"进行选择------------");
                    round.printState(player, dealer);
                    System.out.println("目前玩家" + player.getName() + "的点数: " + player.getCurrentPoints());
                    while (!(button.equals("1") || button.equals("2") || button.equals("3"))) {
                        System.out.println("1.add 2.skip 3.double:");
                        Scanner input = new Scanner(System.in);
                        button = input.next();
                    }
                    if (button.equals("1")) {
                        round.addRound(player, suit);
                        round.printState(player, dealer);
                        System.out.println("目前玩家" + player.getName() + "的点数: " + player.getCurrentPoints());
                        button = "none";
                    }
                    else if (button.equals("3")) {
                        round.doubleRound(player);
                        round.printState(player, dealer);
                        System.out.println("目前玩家" + player.getName() + "的点数: " + player.getCurrentPoints());
                        button = "none";
                    } else if (button.equals("2"))
                        notWantedCardList.add(player);
                        button = "none";
                }
            }


        }

        //判断输赢
        Winnername=round.DecidedWinner(players,dealer);

        //计算最后大家的结果
        round.checkOutMoney(players,Winnername);

        //最后明牌
        System.out.println("明牌");
        round.printFinnalStates(players,dealer);

        for (Player player : players) {
            System.out.println("本轮游戏结束");
            System.out.println("玩家" + player.getName() + " 的剩余金额为" + player.getBet());
        }
    }
}
