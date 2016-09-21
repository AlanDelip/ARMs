package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombContentVO {
    String option_id;
    String type;
    String due_date;
    String delta;
    String sell_price;
    String futures_price;
    String cost;

    public CombContentVO(String option_id, String type, String due_date, String delta, String sell_price, String futures_price, String cost) {
        this.option_id = option_id;
        this.type = type;
        this.due_date = due_date;
        this.delta = delta;
        this.sell_price = sell_price;
        this.futures_price = futures_price;
        this.cost = cost;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getFutures_price() {
        return futures_price;
    }

    public void setFutures_price(String futures_price) {
        this.futures_price = futures_price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
