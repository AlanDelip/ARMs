package sensation.njucitibank.contract;

import java.util.List;

import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.view.widget.BaseView;
import sensation.njucitibank.model.vopo.OptionsTypeVO;
import sensation.njucitibank.model.vopo.NewsVO;

/**
 * 主页接口
 * Created by Alan on 2016/9/15.
 */
public interface IndexContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void loadNewsList(List<NewsVO> newsList);

        void loadFuturesList(List<OptionsTypeVO> futuresTypeList);
    }

    interface Presenter extends BasePresenter {
        void getNewsList();

        void getOptionsList();

    }
}
