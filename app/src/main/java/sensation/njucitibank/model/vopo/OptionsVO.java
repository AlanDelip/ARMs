package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/16.
 */
public class OptionsVO {
    String futures_id;
    String futures_name;
    String number;
    String delta;
    String safe;

    public OptionsVO(String futures_id, String futures_name, String number, String delta, String safe) {
        this.futures_id = futures_id;
        this.futures_name = futures_name;
        this.number = number;
        this.delta = delta;
        this.safe = safe;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }
}
