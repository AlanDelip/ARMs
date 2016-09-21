package sensation.njucitibank.contract;

import android.content.Context;

import java.util.List;

import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.view.widget.BaseView;

/**
 * 卖期权合同接口
 * Created by Alan on 2016/9/16.
 */
public interface SellContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void setFuturesType(List<String> futuresTypeList);

        void setFuturesName(List<String> futuresNameList);

        void showPrice(String money);

        void sellSuccess();

        void sellFail();

        Context getContext();
    }

    interface Presenter extends BasePresenter {

        void getFuturesType();

        void getFuturesName(String type);

        void getPrice(String futureID, String type, String price, String h);

        void confirmPrice(String futureID, String type, String price, String h);
    }
}
