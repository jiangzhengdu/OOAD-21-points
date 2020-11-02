import java.util.ArrayList;

////cards是一副牌 是很多张card的arraylist

public class Suit {
     ArrayList<Card> suit=new ArrayList<>();;

    protected Suit(){
        for(int i=0;i<4;i++)
            for(int j=1;j<=13;j++)
                if(j>10)
                    suit.add(new Card(i,j,10));
                else
                    suit.add(new Card(i,j,j));
    }

    public ArrayList<Card> getCards() {
        return suit;
    }

    //用FisherYates方法
    public void shuffleCard() {
        int len = 52;
        for (int i = len - 1; i > 0; i--) {    ////随机选取一个牌和0...n-1的位置都换一次
            int j = ((int) (Math.random()*100)) % (i + 1);

            Card temp = suit.get(i);
            suit.set(i, suit.get(j));
            suit.set(j, temp);
        }
    }

    //返回一个随机的card从一副牌中
    public Card randomCard(){
        Card card;
        int i = suit.size();
        int j = ((int)(Math.random()*100)) % i;
        card=suit.get(j);
        suit.remove(j);
        return card;
    }
}
