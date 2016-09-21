package sensation.njucitibank.contract;

import java.util.List;

import sensation.njucitibank.model.vopo.PredictVO;
import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.view.widget.BaseView;

/**
 * Created by Alan on 2016/9/20.
 */
public interface AdjustContract {
    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void setChart(float[] xData, float[] yData);

        void showPrediction(PredictVO predictVO);

        void adjustSuccess();

        void adjustFail();
    }

    interface Presenter extends BasePresenter {
        void getChart(List<String> optionList);

        void getContrast(List<String> optionList, String lowerGamma);

        void adjust(String futureID, List<String> optionList, String lowerGamma);
    }
}
