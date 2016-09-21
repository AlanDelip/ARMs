package sensation.njucitibank.presenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import sensation.njucitibank.contract.IndexContract;
import sensation.njucitibank.model.ModelRepository;
import sensation.njucitibank.model.modelinterface.IndexModel;
import sensation.njucitibank.model.response.NewsResponse;
import sensation.njucitibank.model.response.OptionsResponse;
import sensation.njucitibank.model.vopo.NewsPO;
import sensation.njucitibank.model.vopo.NewsVO;
import sensation.njucitibank.model.vopo.OptionsPO;
import sensation.njucitibank.model.vopo.OptionsTypeVO;

/**
 * 主页控制器
 * Created by Alan on 2016/9/15.
 */
public class IndexPresenter implements IndexContract.Presenter {

    IndexModel mIndexModel;
    IndexContract.View mIndexView;

    public IndexPresenter(ModelRepository modelRepository, IndexContract.View indexView) {
        mIndexModel = modelRepository.getIndexModel();
        mIndexView = indexView;
        mIndexView.setPresenter(this);
    }

    @Override
    public void start() {
        mIndexView.showLoading();
        getNewsList();
        getOptionsList();
    }

    @Override
    public void getNewsList() {
        mIndexModel.getNewsList(new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(NewsResponse newsResponse) {
                List<NewsVO> newsVOList = new ArrayList<NewsVO>();
                for (NewsPO newsPO : newsResponse.getData()) {
                    newsVOList.add(newsPO.toVO());
                }
                mIndexView.loadNewsList(newsVOList);
            }
        }, "期权");
    }

    @Override
    public void getOptionsList() {
        mIndexModel.getOptionsList(new Subscriber<OptionsResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(OptionsResponse optionsResponse) {
                List<OptionsTypeVO> optionsTypeVOList = new ArrayList<OptionsTypeVO>();
                for (OptionsPO optionsPO : optionsResponse.getData()) {
                    optionsTypeVOList.add(optionsPO.toFuturesTypeVO());
                }
                mIndexView.loadFuturesList(optionsTypeVOList);
                mIndexView.hideLoading();
            }
        });
    }
}
