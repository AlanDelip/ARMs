package sensation.njucitibank.model.vopo;

import java.util.List;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombVO {
    String cost;
    String delta;
    String safe;
    List<CombContentVO> data;

    public CombVO(String cost, String delta, String safe, List<CombContentVO> data) {
        this.cost = cost;
        this.delta = delta;
        this.safe = safe;
        this.data = data;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public List<CombContentVO> getData() {
        return data;
    }

    public void setData(List<CombContentVO> data) {
        this.data = data;
    }
}
