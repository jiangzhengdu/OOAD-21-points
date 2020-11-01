import java.util.ArrayList;
import java.util.Scanner;
public class OneGame {
    protected ArrayList<Player> players;

    protected OneGame(ArrayList<Player> players){
        this.players=players;
    }

    ////runGame 进行一次游戏
    public void runGame(){
        //洗牌
        Cards cards=new Cards();
        cards.shuffleCard();

        //生成庄家
        Dealer dealer=new Dealer();

        ArrayList <String> Winnername=new ArrayList();
        //生成散家的信息
        for (Player player:players) {
            player.deleteCard();
            if(player.getBet()>0){
                //todo:输入下注
                Scanner input=new Scanner(System.in);
                System.out.println("请您输入玩家"+player.getName()+"的下注:");
                int tempBet=input.nextInt(); //输入整型
                player.setTempBet(tempBet);
                System.out.println("tempbet is"+player.getTempBet());
                if(player.getBet()<player.tempBet)
                {
                    System.out.println("玩家"+player.getName()+"赌资为"+player.getBet()+"，不能下注");
                    System.out.println("请您重新输入玩家"+player.getName()+"的下注:");
                    tempBet=input.nextInt(); //输入整型
                    player.setTempBet(tempBet);
                }
                System.out.println("玩家"+player.getName()+"赌资总数为："+player.getBet()+"，本轮下注："+player.getTempBet());

            }
            else
                System.out.println("玩家"+player.getName()+"赌资为0，不能下注");
        }
        Round round=new Round();
        System.out.println("——————————————————————————————————");
        System.out.println("New round...");
        //判断赢家
        dealer.getRandomCard(cards);
        dealer.getRandomCard(cards);
        dealer.caculateCurrentPoints();

        //如果总点数超过17，则庄家再拿一张牌，否则，不拿牌
        while(dealer.getCurrentPoints()<15) {
               dealer.getRandomCard(cards);
               dealer.caculateCurrentPoints();
           }

        for (Player player:players) {
            player.getRandomCard(cards);
            player.getRandomCard(cards);
            player.caculateCurrentPoints();
        }

        if(dealer.getCurrentPoints()<=21) {
            //todo:再每个人分发牌的时候,输入每一个玩家的决定
            for(Player player :players){
                String button;
                while(true) {
                    round.printState(player,dealer);
                    System.out.println("目前玩家"+player.getName()+"的点数: "+player.getCurrentPoints());
                    System.out.println("目前庄家的点数: "+dealer.getCurrentPoints());
                    if (player.getCurrentPoints()>21)
                    {
                        System.out.println("玩家"+player.getName()+"超过21点，自动跳过！");
                        break;
                    }
                    System.out.println("请您输入玩家 "+player.getName()+" 的决定:");
                    System.out.println("1.add 2.skip 3.double:");
                    Scanner input = new Scanner(System.in);
                    button = input.next();
                    ///choose to add another card or double the temp bets or choose to skip
                    if (button.equals("add"))
                        round.addRound(player, cards);
                    else if (button.equals("double")) {
                        round.doubleRound(player);
                    }
                    else if (button.equals("skip"))
                        break;
                    else System.out.println("add or skip or Double");
                }
            }
            //扣钱或者给钱

            Winnername=round.DecidedWinner( players,dealer);
            round.checkOutMoney(players,Winnername);
        }
    else
        {
            for(Player player :players){
            round.printState(player,dealer);
            Winnername.add(player.getName());
            }
            System.out.println("Dealer is out 21,round skips");

            round.checkOutMoney(players,Winnername);
        }

        for(Player player :players){
            System.out.println("玩家 "+player.getName()+"剩余金额为"+player.getBet());
        }
    }
}
