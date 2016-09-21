package sensation.njucitibank.model.modelinterface;

import java.util.List;

import rx.Subscriber;
import sensation.njucitibank.model.response.AdjustResponse;
import sensation.njucitibank.model.response.ChartResponse;
import sensation.njucitibank.model.response.PredictResponse;

/**
 * Created by Alan on 2016/9/20.
 */
public interface AdjustModel {
    void getChart(Subscriber<ChartResponse> subscriber, List<String> optionList);

    void getPrediction(Subscriber<PredictResponse> subscriber, String lowerGamma, List<String> optionList);

    void confirmAdjust(Subscriber<AdjustResponse> subscriber, String futureID, String lowerGamma, List<String> optionList);
}
