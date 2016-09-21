package sensation.njucitibank.model.service;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import sensation.njucitibank.model.response.PriceResponse;

/**
 * Created by Alan on 2016/9/16.
 */
public interface PriceService {
    @FormUrlEncoded
    @POST("getOptionsPrice")
    Observable<PriceResponse> getPrice(@Field("futures_id") String futuresID, @Field("type") String type, @Field("price") String price, @Field("H") String h);
}
