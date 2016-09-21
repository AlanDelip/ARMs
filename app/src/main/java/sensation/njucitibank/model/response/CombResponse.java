package sensation.njucitibank.model.response;

import sensation.njucitibank.model.vopo.CombPO;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombResponse extends Response<CombPO> {
    public CombResponse(String condition, String msg, CombPO data) {
        super(condition, msg, data);
    }
}
