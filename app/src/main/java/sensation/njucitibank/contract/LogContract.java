package sensation.njucitibank.contract;

import sensation.njucitibank.model.vopo.LogVO;
import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.view.widget.BaseView;

/**
 * Created by Alan on 2016/9/19.
 */
public interface LogContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void setLogList(LogVO logVO);
    }

    interface Presenter extends BasePresenter {
        void getLogList(String futureID);
    }
}
