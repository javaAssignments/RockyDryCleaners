
public class Order {
    private String customerName;
    private int garments;

    public Order() {
    }

    public Order(String customerName, int garments) {
        this.customerName = customerName;
        this.garments = garments;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getGarments() {
        return garments;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setGarments(int garments) {
        this.garments = garments;
    }
    
    public double calculateCharge(){
        if(this.garments <= 2)
            return 8.50 * this.garments;
        return 20 + (this.garments - 3) * 6.50;
    }
}
