package sensation.njucitibank.presenter;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import rx.Subscriber;
import sensation.njucitibank.contract.AdjustContract;
import sensation.njucitibank.model.ModelRepository;
import sensation.njucitibank.model.modelinterface.AdjustModel;
import sensation.njucitibank.model.response.AdjustResponse;
import sensation.njucitibank.model.response.ChartResponse;
import sensation.njucitibank.model.response.PredictResponse;

/**
 * Created by Alan on 2016/9/21.
 */
public class AdjustPresenter implements AdjustContract.Presenter {
    AdjustModel mAdjustModel;
    AdjustContract.View mAdjustView;
    List<String> optionList;

    public AdjustPresenter(ModelRepository modelRepository, AdjustContract.View mAdjustView, String[] optionList) {
        mAdjustModel = modelRepository.getAdjustModel();
        this.mAdjustView = mAdjustView;
        this.optionList = Arrays.asList(optionList);

        mAdjustView.setPresenter(this);
    }


    @Override
    public void getChart(List<String> optionList) {
        mAdjustModel.getChart(new Subscriber<ChartResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ChartResponse chartResponse) {
                mAdjustView.setChart(chartResponse.getData().getX_data(), chartResponse.getData().getY_data());
                mAdjustView.hideLoading();
            }
        }, optionList);
    }

    @Override
    public void getContrast(List<String> optionList, String lowerGamma) {
        mAdjustView.showLoading();
        Log.d("Antares", "getContrast: " + lowerGamma + " " + lowerGamma.split("\\.").length);
        mAdjustModel.getPrediction(new Subscriber<PredictResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Antares", "onError: " + e);
            }

            @Override
            public void onNext(PredictResponse predictResponse) {
                mAdjustView.showPrediction(predictResponse.getData().toVO());
                mAdjustView.hideLoading();
            }
        }, lowerGamma.split("\\.")[0], optionList);
    }

    @Override
    public void adjust(String futureID, List<String> optionList, String lowerGamma) {
        mAdjustView.showLoading();
        mAdjustModel.confirmAdjust(new Subscriber<AdjustResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("Antares", "onError: " + e.toString());
                mAdjustView.adjustFail();
                mAdjustView.hideLoading();
            }

            @Override
            public void onNext(AdjustResponse adjustResponse) {
                mAdjustView.adjustSuccess();
                mAdjustView.hideLoading();
            }
        }, futureID, lowerGamma.split("\\.")[0], optionList);
    }

    @Override
    public void start() {
        mAdjustView.showLoading();
        getChart(optionList);
    }
}
