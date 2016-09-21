package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.LogResponse;

/**
 * Created by Alan on 2016/9/19.
 */
public interface LogService {
    @GET("getHistory")
    Observable<LogResponse> getLogList(@Query("future_id")String futureID);
}
