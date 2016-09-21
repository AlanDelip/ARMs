package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import rx.Observable;
import sensation.njucitibank.model.response.OptionsResponse;

/**
 * Created by Alan on 2016/9/16.
 */
public interface OptionsListService {
    @GET("optionState")
    Observable<OptionsResponse> getOptionsList();
}
