import java.util.ArrayList;

public class Player {
    protected int bet;//总有钱数
    protected String name;

    protected int tempBet;   //下注数值
    protected  ArrayList<Card> pCards=new ArrayList<>();  //自己拥有的牌

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

    public ArrayList<Card> getpCards() {
        return pCards;
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

    public void getRandomCard(Cards cards){
        Card card=cards.randomCard();
        pCards.add(card);
    }
    public void deleteCard(){
        pCards=new ArrayList<>();
    }
    public void caculateCurrentPoints(){
        int CurrentPoints=0;
        for(Card card :pCards){
            CurrentPoints+=card.getNumber();
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
