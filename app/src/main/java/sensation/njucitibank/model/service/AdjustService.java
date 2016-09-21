package sensation.njucitibank.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.AdjustResponse;

/**
 * Created by Alan on 2016/9/21.
 */
public interface AdjustService {
    @GET("adjustBin")
    Observable<AdjustResponse> adjustBin(@Query("futures_id") String futureID, @Query("lower_gamma") String lowerGamma, @Query("option_list") List<String> optionList);
}
