package sensation.njucitibank.model.modelinterface;

import rx.Subscriber;
import sensation.njucitibank.model.response.ConfirmResponse;
import sensation.njucitibank.model.response.FuturesNameResponse;
import sensation.njucitibank.model.response.FuturesTypeResponse;
import sensation.njucitibank.model.response.PriceResponse;

/**
 * Created by Alan on 2016/9/16.
 */
public interface SellModel {

    void getFuturesType(Subscriber<FuturesTypeResponse> subscriber);

    void getFuturesName(Subscriber<FuturesNameResponse> subscriber, String type);

    void getPrice(Subscriber<PriceResponse> subscriber, String futureID, String type, String price, String h);

    void confirmPrice(Subscriber<ConfirmResponse> subscriber, String futureID, String type, String price, String h);

}
