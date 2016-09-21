package sensation.njucitibank.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.PredictResponse;

/**
 * Created by Alan on 2016/9/21.
 */
public interface PredictService {
    @GET("predictResult")
    Observable<PredictResponse> getPrediction(@Query("lower_gamma") String lowerGamma, @Query("option_list") List<String> optionList);
}
