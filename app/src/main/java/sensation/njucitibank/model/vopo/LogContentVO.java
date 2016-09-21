package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogContentVO {
    String date;
    String price;
    String quantity;

    public LogContentVO(String date, String price, String quantity) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
