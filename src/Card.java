public class Card {
    ////card 一张牌的性质



    protected int type;//花色0-3
    protected String number;//牌面1-13
    protected int value;//数值1-10

    protected int hidden;//可见   0为可见  1为不可见

    ////构造函数 确定Card的属性
    protected Card(int type,String number,int value){
        this.type=type;
        this.number=number;
        this.value=value;
        hidden=0;
    }
    public int getValue(){
        return value;
    }

    public int getType() {
        return type;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNumber() {
        return number;
    }
}
