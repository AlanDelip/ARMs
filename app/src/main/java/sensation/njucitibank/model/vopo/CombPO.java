package sensation.njucitibank.model.vopo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombPO {
    double cost;
    double delta;
    String safe;
    List<CombContentPO> data;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public List<CombContentPO> getData() {
        return data;
    }

    public void setData(List<CombContentPO> data) {
        this.data = data;
    }

    public CombVO toVO() {
        List<CombContentVO> combContentVOList = new ArrayList<CombContentVO>();
        List<CombContentPO> combContentPOList = getData();
        for (CombContentPO combContentPO : combContentPOList) {
            combContentVOList.add(combContentPO.toVO());
        }
        DecimalFormat df1 = new DecimalFormat("0.0000"),
                df2 = new DecimalFormat("0.00");
        return new CombVO(df2.format(cost), df1.format(delta), safe, combContentVOList);
    }
}
