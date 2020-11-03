import java.util.ArrayList;

public class Player {
    protected int bet;//总有钱数
    protected String name;

    protected int tempBet;   //下注数值
    protected  ArrayList<Card> playerSuit =new ArrayList<>();  //自己拥有的牌

    protected int currentPoints;

    protected Player(int bet,String name){    //构造函数，初始化Player
        this.bet=bet;
        this.name=name;
        this.currentPoints =0;
    }

    public int getBet() {
        return bet;
    }

    public String getName() {
        return name;
    }

    public int getTempBet() {
        return tempBet;
    }

    public ArrayList<Card> getPlayerSuit() {
        return playerSuit;
    }

    public void setTempBet(int tempBet) {
        this.tempBet = tempBet;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void getRandomCard(Suit suit){
        Card card= suit.randomCard();
        playerSuit.add(card);
    }
    public void deleteCard(){
        playerSuit =new ArrayList<>();
    }
    public void caculateCurrentPoints(){
        int CurrentPoints=0;
        for(Card card : playerSuit){
            CurrentPoints+=card.getValue();
        }
        this.currentPoints=CurrentPoints;

    }

    public void caculateBets(boolean win){

        if(win) {
            if(blackJack())
                this.bet += 2*this.tempBet;
            else this.bet += this.tempBet;

        }
        else
            this.bet-=this.tempBet;
    }



    public boolean blackJack(){

        if(playerSuit.get(0).getNumber().equals("1")&&playerSuit.get(1).getValue()==10&&playerSuit.get(2).getValue()==10) {
           System.out.println("BlackJack!");
            return true;
        }
        else return false;

    }
}
