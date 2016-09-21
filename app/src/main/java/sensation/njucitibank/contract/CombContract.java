package sensation.njucitibank.contract;

import sensation.njucitibank.model.vopo.CombVO;
import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.view.widget.BaseView;

/**
 * Created by Alan on 2016/9/17.
 */
public interface CombContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void setCombList(CombVO combVO);
    }

    interface Presenter extends BasePresenter {
        void getCombList(String futuresID);
    }
}
