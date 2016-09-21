package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.FuturesNameResponse;

/**
 * Created by Alan on 2016/9/16.
 */
public interface FuturesNameService {
    @GET("getFuturesId")
    Observable<FuturesNameResponse> getFuturesList(@Query("type") String type);
}
