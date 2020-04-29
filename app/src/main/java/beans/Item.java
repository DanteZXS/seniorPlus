package beans;

public class Item {
    //图片的url
    int url;

    public String getDescption() {
        return descrption;
    }

    String descrption;
    public Item(int url) {
        this.url = url;
    }

    public Item(int url, String descrption) {
        this.url = url;
        this.descrption = descrption;
    }

    public int getUrl() {
        return url;
    }

}
