package sensation.njucitibank.model.modelinterface;

import rx.Subscriber;
import sensation.njucitibank.model.response.NewsResponse;
import sensation.njucitibank.model.response.OptionsResponse;

/**
 * Created by Alan on 2016/9/15.
 */
public interface IndexModel {

    /**
     * 获取新闻列表
     *
     * @param keyword    关键字
     * @param subscriber 处理
     */
    void getNewsList(Subscriber<NewsResponse> subscriber, String keyword);

    /**
     * 获取期权状态列表
     *
     * @param subscriber 处理
     */
    void getOptionsList(Subscriber<OptionsResponse> subscriber);
}
