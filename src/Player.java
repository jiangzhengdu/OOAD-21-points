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

        if(win)
            this.bet+=this.tempBet;
        else
            this.bet-=this.tempBet;
    }
}
