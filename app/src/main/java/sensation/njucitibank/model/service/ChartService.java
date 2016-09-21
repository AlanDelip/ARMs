package sensation.njucitibank.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.ChartResponse;

/**
 * Created by Alan on 2016/9/20.
 */
public interface ChartService {
    @GET("selectOptions")
    Observable<ChartResponse> getChart(@Query("option_list") List<String> optionList);
}
