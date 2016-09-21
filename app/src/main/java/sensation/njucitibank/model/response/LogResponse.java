package sensation.njucitibank.model.response;

import sensation.njucitibank.model.vopo.LogPO;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogResponse extends Response<LogPO> {
    public LogResponse(String condition, String msg, LogPO data) {
        super(condition, msg, data);
    }
}
