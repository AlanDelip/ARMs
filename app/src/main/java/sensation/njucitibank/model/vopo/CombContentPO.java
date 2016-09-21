package sensation.njucitibank.model.vopo;

import java.text.DecimalFormat;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombContentPO {
    String option_id;
    String type;
    String due_date;
    double delta;
    double sell_price;
    double futures_price;
    double cost;

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

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public double getFutures_price() {
        return futures_price;
    }

    public void setFutures_price(double futures_price) {
        this.futures_price = futures_price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public CombContentVO toVO() {
        DecimalFormat df1 = new DecimalFormat("0.0000"),
                df2 = new DecimalFormat("0.00");
        return new CombContentVO(
                option_id,
                type,
                due_date,
                df1.format(delta),
                df2.format(sell_price),
                df2.format(futures_price),
                df2.format(cost));
    }
}
