package sensation.njucitibank.model.modelimpl;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sensation.njucitibank.model.modelinterface.AdjustModel;
import sensation.njucitibank.model.response.AdjustResponse;
import sensation.njucitibank.model.response.ChartResponse;
import sensation.njucitibank.model.response.PredictResponse;
import sensation.njucitibank.model.service.AdjustService;
import sensation.njucitibank.model.service.ChartService;
import sensation.njucitibank.model.service.PredictService;

/**
 * Creaited by Alan on 2016/9/20.
 */
public class AdjustModelImpl extends ModelImpl implements AdjustModel {

    @Override
    public void getChart(Subscriber<ChartResponse> subscriber, List<String> optionList) {
        ChartService chartService = retrofit.create(ChartService.class);

        chartService.getChart(optionList)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getPrediction(Subscriber<PredictResponse> subscriber, String lowerGamma, List<String> optionList) {
        PredictService predictService = retrofit.create(PredictService.class);

        predictService.getPrediction(lowerGamma, optionList)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void confirmAdjust(Subscriber<AdjustResponse> subscriber, String futureID, String lowerGamma, List<String> optionList) {
        AdjustService adjustService = retrofit.create(AdjustService.class);

        adjustService.adjustBin(futureID, lowerGamma, optionList)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
