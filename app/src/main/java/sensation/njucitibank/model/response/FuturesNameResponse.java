package sensation.njucitibank.model.response;

import java.util.List;

import sensation.njucitibank.model.vopo.FuturesPO;

/**
 * Created by Alan on 2016/9/16.
 */
public class FuturesNameResponse extends Response<List<FuturesPO>> {
    public FuturesNameResponse(String condition, String msg, List<FuturesPO> data) {
        super(condition, msg, data);
    }
}
