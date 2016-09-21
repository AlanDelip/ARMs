package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/16.
 */
public class FuturesPO {
    String futures_id;
    String futures_name;

    public FuturesPO(String futures_id, String futures_name) {
        this.futures_id = futures_id;
        this.futures_name = futures_name;
    }

    public String getFutures_id() {
        return futures_id;
    }

    public void setFutures_id(String futures_id) {
        this.futures_id = futures_id;
    }

    public String getFutures_name() {
        return futures_name;
    }

    public void setFutures_name(String futures_name) {
        this.futures_name = futures_name;
    }
}
