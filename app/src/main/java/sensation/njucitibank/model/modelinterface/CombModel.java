package sensation.njucitibank.model.modelinterface;

import rx.Subscriber;
import sensation.njucitibank.model.response.CombResponse;

/**
 * Created by Alan on 2016/9/17.
 */
public interface CombModel {
    void getComb(Subscriber<CombResponse> subscriber, String futuresID);
}
