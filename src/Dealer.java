import java.util.ArrayList;
////庄家类

public class Dealer {

    //用来存在他现在有的牌们
    protected ArrayList<Card> dCards=new ArrayList<>();

    //现在的总点数
    protected int currentPoints;

    public ArrayList<Card> getdCards() {
        return dCards;
    }

    //获取一张随机的牌
    public void getRandomCard(Cards cards){
        Card card=cards.randomCard();
        dCards.add(card);
    }

    //获取一张hidden的牌，第一张为隐藏
    public void getRandomHiddenCard(Cards cards){
        Card card=cards.randomCard();
        card.hidden=1;
        dCards.add(card);
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void caculateCurrentPoints(){
        int CurrentPoints=0;
        for(Card card :dCards){
            CurrentPoints+=card.getNumber();
        }
        this.currentPoints=CurrentPoints;

    }
}
