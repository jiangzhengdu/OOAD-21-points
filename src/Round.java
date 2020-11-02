import java.util.ArrayList;

public class Round {

    //2 boolean state;

    public boolean isOver() {
        return false;
    }

    /**
     *  打印一张牌
     * @param card 一张牌
     */
    public void printCard(Card card) {
        if (card.hidden == 1)
            System.out.print("Hidden ");
        else {
            if (card.type == 0)
                System.out.print("方块" + card.number + " ");
            else if (card.type == 1)
                System.out.print("草花" + card.number + " ");
            else if (card.type == 2)
                System.out.print("红桃" + card.number + " ");
            else if (card.type == 3)
                System.out.print("黑桃" + card.number + " ");
        }
    }

    public void printFinnalCard(Card card) {

            if (card.type == 0)
                System.out.print("方块" + card.number + " ");
            else if (card.type == 1)
                System.out.print("草花" + card.number + " ");
            else if (card.type == 2)
                System.out.print("红桃" + card.number + " ");
            else if (card.type == 3)
                System.out.print("黑桃" + card.number + " ");

    }

    /**
     * 打印出一个玩家和庄家的所有的牌的牌数
     *
     * @param player 玩家
     * @param dealer 庄家
     */
    public void printState(Player player, Dealer dealer) {
        System.out.print("Dealer:");
        for (Card card : dealer.getDealerSuit())
            printCard(card);
        System.out.println("");
        System.out.print("player" + player.getName() + ":");
        for (Card card : player.getPlayerSuit()) {
            printCard(card);
        }
        System.out.println("");
    }


    public void printFinnalStates(ArrayList<Player> players, Dealer dealer) {
        System.out.print("Dealer:");
        for (Card card : dealer.getDealerSuit())
            printFinnalCard(card);
        System.out.println("");
        for(Player player:players) {
            System.out.print("player" + player.getName() + ":");
            for (Card card : player.getPlayerSuit()) {
                printFinnalCard(card);
            }
        }
        System.out.println("");
    }


    /**
     *  在这一个round中，添加一次牌
     * @param player 玩家
     * @param suit   本轮游戏的一副牌
     */
    public void addRound(Player player, Suit suit) {
        player.getRandomCard(suit);
        player.caculateCurrentPoints();
    }

    /**
     * 在这一个round中，翻倍赌注
     * @param player 玩家
     */
    public void doubleRound(Player player) {
        if (2 * player.getTempBet() <= player.bet)
            player.setTempBet(2 * player.getTempBet());
        else
            System.out.println("本金不足，无法加倍！");
    }

    /**
     *
     * @param players 玩家列表
     * @param dealer  庄家
     * @return 赢家列表
     */
    public ArrayList<String> DecidedWinner(ArrayList<Player> players, Dealer dealer) {

        int WinnerPoints = 0;
        ArrayList<String> WinnerName=new ArrayList<>();
        boolean isChange = false;
        if (dealer.getCurrentPoints() <= 21) {
            WinnerPoints = dealer.getCurrentPoints();
        }
        for (Player player : players) {
            if (player.currentPoints >= WinnerPoints && player.currentPoints <= 21) {
                WinnerPoints = player.getCurrentPoints();
                isChange = true;
            }
        }

        //产生赢的玩家
        for (Player player : players) {
            if (player.getCurrentPoints()==WinnerPoints&&isChange) {
                WinnerName.add(player.getName());
            }
        }


        if(dealer.getCurrentPoints()>21) {
            System.out.println("结果：dealer is out");
            WinnerName.add("dealer out");
        }
        //庄家胜利
        else if (WinnerPoints == dealer.getCurrentPoints()&&!isChange) {
            System.out.println("结果：winner is dealer whose points is " + dealer.getCurrentPoints());
            WinnerName.add("dealer");

            //平局
        } else if (WinnerPoints == dealer.getCurrentPoints() && isChange) {
            System.out.println("结果：it's draw");
            WinnerName.add("draw");
        }
        //玩家胜利
        else {
            System.out.println("结果：winner is player" + WinnerName + " whose points is " + WinnerPoints);
            System.out.println("dealer's points is " + dealer.getCurrentPoints());
        }
        return WinnerName;
    }

    /**
     *
     * @param player    所有的玩家
     * @param WinnerName    所有的赢家
     */
    public void checkOutMoney(ArrayList<Player> player, ArrayList<String> WinnerName) {
        for (Player player1 : player) {
//庄家爆点
            if(WinnerName.contains("dealer out"))
                player1.caculateBets(true);
            //平局

            else if(WinnerName.contains("dealer"))
            player1.caculateBets(false);
            else if(WinnerName.contains("draw")&&WinnerName.contains(player1.getName()))
                continue;

            else if(WinnerName.contains(player1.getName()))
                player1.caculateBets(true);
            else
                player1.caculateBets(false);
        }
    }
}

