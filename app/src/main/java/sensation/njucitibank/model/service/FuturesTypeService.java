package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import rx.Observable;
import sensation.njucitibank.model.response.FuturesTypeResponse;

/**
 * Created by Alan on 2016/9/16.
 */
public interface FuturesTypeService {
    @GET("getFuturesType")
    Observable<FuturesTypeResponse> getFuturesType();
}
