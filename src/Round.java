import java.util.ArrayList;

public class Round {

    //2 boolean state;

    public boolean isOver() {
        return false;
    }

    //show卡面
    public void printCard(Card card) {
        if (card.hidden == 1)
            System.out.println("Hidden ");
        else {
            //System.out.print(card.number + " of ");
            if (card.type == 0)
                System.out.print("方块" + card.number + " ");
            else if (card.type == 1)
                System.out.print("草花" + card.number + " ");
            else if (card.type == 2)
                System.out.print("红桃" + card.number + " ");
            else if (card.type == 3)
                System.out.print("黑桃" + card.number + " ");
//            else if (card.type == 4)
//                System.out.println("小王");
//            else if (card.type == 5)
//                System.out.println("大王");

        }
    }

    /**
     * 打印出一个玩家和庄家的所有的牌的牌数
     *
     * @param player 玩家
     * @param dealer 庄家
     */
    public void printState(Player player, Dealer dealer) {
        System.out.print("Dealer:");
        for (Card card : dealer.getdCards())
            printCard(card);
        System.out.println("");
        System.out.print("player" + player.getName() + ":");
        for (Card card : player.getpCards()) {
            printCard(card);
        }
        System.out.println("");
    }

    public void CaculatePoint(Player player, Dealer dealer) {

    }

//    /**
//     *
//     * @param players
//     * @param dealer
//     * @param cards
//     */
//    public void startRound(ArrayList<Player> players,Dealer dealer,Cards cards){
//        dealer.getRandomCard(cards);
//        for (Player player:players)
//            player.getRandomCard(cards);
//     //   printState(players,dealer);
//    }

    //要几张？
//    public void startDoubleRound(ArrayList<Player> players,Dealer dealer,Cards cards){
//        dealer.getRandomCard(cards);
//        for (Player player:players) {
//            player.getRandomCard(cards);
//            player.setTempBet(player.getTempBet()*2);
//        }
//        printState(players,dealer);
//    }

    public void stayRound() {

    }

    public void addRound(Player player, Cards cards) {
        player.getRandomCard(cards);
        player.caculateCurrentPoints();
    }


    public void doubleRound(Player player) {
        if (2 * player.getTempBet() <= player.bet)
            player.setTempBet(2 * player.getTempBet());
        else
            System.out.println("本金不足，无法加倍！");
    }


    /**
     * try to decided who is winner dealer or the any player
     *
     * @param players the list of players
     * @param dealer  the dealer
     */
//    public String DecidedWinner(ArrayList<Player> players,Dealer dealer){
//        int WinnerPoints=0;
//        String WinnerName = "0";
//        if(dealer.getCurrentPoints()<=21) {
//            WinnerPoints = dealer.getCurrentPoints();
//            WinnerName = "dealer";
//        }
//        for(Player player :players) {
//            if (player.currentPoints > WinnerPoints && player.currentPoints <= 21) {
//                WinnerPoints = player.getCurrentPoints();
//                WinnerName = player.getName();
//            }
//        }
//        if(dealer.getCurrentPoints()>21) {
//            System.out.println("dealer is out");
//            WinnerName="everyone";
//        }
//        else if(WinnerPoints<dealer.getCurrentPoints()) {
//            System.out.println("winner is dealer whose points is " + dealer.getCurrentPoints());
//        }
//        else if(WinnerPoints==dealer.getCurrentPoints())
//        {
//            System.out.println(WinnerPoints);
//            System.out.println("it's draw");
//            WinnerName="no one";
//        }
//        else
//            {
//            System.out.println("winner is  "+ WinnerName+" whose points is "+WinnerPoints);
//            System.out.println("dealer's points is "+dealer.getCurrentPoints());
//            }
//        return WinnerName;
//    }

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
                //  WinnerName = player.getName();
                isChange = true;
            }
        }

        for (Player player : players) {
            if (player.getCurrentPoints()==WinnerPoints) {
                WinnerName.add(player.getName());
            }
        }
        //庄家胜利
        if (WinnerPoints < dealer.getCurrentPoints()) {
            System.out.println("winner is dealer whose points is " + dealer.getCurrentPoints());
            WinnerName.add("dealer");
            //平局
        } else if (WinnerPoints == dealer.getCurrentPoints() && isChange) {
            System.out.println("it's draw");
            WinnerName.add("draw");
        }
        //玩家胜利
        else {
            System.out.println("winner is  " + WinnerName + " whose points is " + WinnerPoints);
            System.out.println("dealer's points is " + dealer.getCurrentPoints());
        }

        return WinnerName;
    }

    public void checkOutMoney(ArrayList<Player> player, ArrayList<String> WinnerName) {
        for (Player player1 : player) {

            if(WinnerName.contains("draw")&&WinnerName.contains(player1.getName()))
                break;
            if(WinnerName.contains(player1.getName()))
                player1.caculateBets(true);
            else
                player1.caculateBets(false);

//            if (WinnerName.equals("everyone"))
//                player1.caculateBets(true);
//            else if (WinnerName.equals("no one"))
//                break;
//            else if (player1.getName().equals(WinnerName))
//                player1.caculateBets(true);
//            else
//                player1.caculateBets(false);
        }
    }
}

