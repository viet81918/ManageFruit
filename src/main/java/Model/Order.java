
package Model;

public class Order {
    private String Cusname;
    private String Proname;
    private int quantity ;
    private int price;
    private int amount;

    public Order(String Cusname, String Proname, int quantity, int price) {
        this.Cusname = Cusname;
        this.Proname = Proname;
        this.quantity = quantity;
        this.price = price;
        this.amount = price * quantity;
    }

    public String getCusname() {
        return Cusname;
    }

    public void setCusname(String Cusname) {
        this.Cusname = Cusname;
    }

    public String getProname() {
        return Proname;
    }

    public void setProname(String Proname) {
        this.Proname = Proname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
