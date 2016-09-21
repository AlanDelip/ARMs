package sensation.njucitibank.model.modelimpl;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sensation.njucitibank.model.modelinterface.IndexModel;
import sensation.njucitibank.model.response.NewsResponse;
import sensation.njucitibank.model.response.OptionsResponse;
import sensation.njucitibank.model.service.NewsService;
import sensation.njucitibank.model.service.OptionsListService;

/**
 * 新闻数据模块
 * Created by Alan on 2016/9/15.
 */
public class IndexModelImpl extends ModelImpl implements IndexModel {

    @Override
    public void getNewsList(Subscriber<NewsResponse> subscriber, String keyword) {
        NewsService newsService = retrofit.create(NewsService.class);

        newsService.getNewsList(keyword)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getOptionsList(Subscriber<OptionsResponse> subscriber) {
        OptionsListService optionsListService = retrofit.create(OptionsListService.class);

        optionsListService.getOptionsList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
