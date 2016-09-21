package sensation.njucitibank.model.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sensation.njucitibank.model.response.NewsResponse;

/**
 * Created by Alan on 2016/9/15.
 */
public interface NewsService {
    @GET("news")
    Observable<NewsResponse> getNewsList(@Query("key_word") String keyword);

}
