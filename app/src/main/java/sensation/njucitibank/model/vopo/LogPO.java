package sensation.njucitibank.model.vopo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogPO {
    int quantity;
    double average_price;
    List<LogContentPO> data;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAverage_price() {
        return average_price;
    }

    public void setAverage_price(double average_price) {
        this.average_price = average_price;
    }

    public List<LogContentPO> getData() {
        return data;
    }

    public void setData(List<LogContentPO> data) {
        this.data = data;
    }

    public LogVO toVO() {
        DecimalFormat df = new DecimalFormat("0.00");
        List<LogContentVO> logContentVOList = new ArrayList<LogContentVO>();
        for (LogContentPO logContentPO : data) {
            logContentVOList.add(logContentPO.toVO());
        }
        return new LogVO(quantity + "", df.format(average_price), logContentVOList);
    }

    class LogContentPO {
        String date;
        double price;
        int quantity;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public LogContentVO toVO() {
            DecimalFormat df = new DecimalFormat("0.00");
            return new LogContentVO(date, df.format(price), quantity + "");
        }
    }
}
