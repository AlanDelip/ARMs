package sensation.njucitibank.model.vopo;

import java.text.DecimalFormat;

/**
 * Created by Alan on 2016/9/21.
 */
public class PredictPO {
    String futures_id;
    String futures_name;
    int number;
    double origin_cost;
    int origin_number;
    double origin_delta;
    int current_number;
    double current_delta;
    double var;
    String safe;

    public PredictVO toVO() {
        DecimalFormat df1 = new DecimalFormat("0.0000"),
                df2 = new DecimalFormat("0.00");
        return new PredictVO(futures_id, futures_name, number + "", df2.format(origin_cost), origin_number + "", df1.format(origin_delta), current_number + "", df1.format(current_delta), df1.format(var), safe);
    }
}
