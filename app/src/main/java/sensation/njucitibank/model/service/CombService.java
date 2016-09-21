package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.CombResponse;

/**
 * Created by Alan on 2016/9/17.
 */
public interface CombService {
    @GET("capital")
    Observable<CombResponse> getComb(@Query("futures_id") String futuresID);
}
