package sensation.njucitibank.model.modelinterface;

import rx.Subscriber;
import sensation.njucitibank.model.response.LogResponse;

/**
 * Created by Alan on 2016/9/19.
 */
public interface LogModel {
    void getLogList(Subscriber<LogResponse> subscriber, String futureID);
}
