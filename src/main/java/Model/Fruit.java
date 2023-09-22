package Model;
public class Fruit {
    private String ID;
    private String name;
    private int price;
    private int quantity;
    private String Origin;

    public Fruit(String ID, String name, int price, int quantity, String Origin) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.Origin = Origin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

  
    
}
