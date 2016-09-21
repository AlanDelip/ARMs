package sensation.njucitibank.model.response;

import sensation.njucitibank.model.vopo.PredictPO;

/**
 * Created by Alan on 2016/9/21.
 */
public class PredictResponse extends Response<PredictPO> {

    public PredictResponse(String condition, String msg, PredictPO data) {
        super(condition, msg, data);
    }
}
