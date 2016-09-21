package sensation.njucitibank.model.vopo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/9/16.
 */
public class OptionsPO {

    String type;
    List<OptionsContentPO> data;

    public OptionsPO(String type, List<OptionsContentPO> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionsContentPO> getData() {
        return data;
    }

    public void setData(List<OptionsContentPO> data) {
        this.data = data;
    }

    public OptionsTypeVO toFuturesTypeVO() {
        List<OptionsVO> optionsVOList = new ArrayList<OptionsVO>();
        DecimalFormat df = new DecimalFormat("0.0000");
        for (OptionsContentPO optionsContentPO : data) {
            optionsVOList.add(
                    new OptionsVO(
                            optionsContentPO.futures_id,
                            optionsContentPO.futures_name,
                            optionsContentPO.number + "",
                            df.format(optionsContentPO.delta),
                            optionsContentPO.safe));
        }
        return new OptionsTypeVO(type, data.size() + "", optionsVOList);
    }

    class OptionsContentPO {
        String futures_id;
        String futures_name;
        int number;
        double delta;
        String safe;

        public OptionsContentPO(String futures_id, String futures_name, int number, double delta, String safe) {
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
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
    }
}
