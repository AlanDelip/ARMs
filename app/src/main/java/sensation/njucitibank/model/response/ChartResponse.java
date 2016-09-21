package sensation.njucitibank.model.response;

import sensation.njucitibank.model.vopo.DataSet;

/**
 * Created by Alan on 2016/9/20.
 */
public class ChartResponse extends Response<DataSet> {

    public ChartResponse(String condition, String msg, DataSet data) {
        super(condition, msg, data);
    }
}
