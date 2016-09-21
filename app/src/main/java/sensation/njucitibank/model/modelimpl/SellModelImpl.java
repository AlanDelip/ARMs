package sensation.njucitibank.model.modelimpl;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sensation.njucitibank.model.modelinterface.SellModel;
import sensation.njucitibank.model.response.ConfirmResponse;
import sensation.njucitibank.model.response.FuturesNameResponse;
import sensation.njucitibank.model.response.FuturesTypeResponse;
import sensation.njucitibank.model.response.PriceResponse;
import sensation.njucitibank.model.service.ConfirmService;
import sensation.njucitibank.model.service.FuturesNameService;
import sensation.njucitibank.model.service.FuturesTypeService;
import sensation.njucitibank.model.service.PriceService;

/**
 * 销售数据模块
 * Created by Alan on 2016/9/16.
 */
public class SellModelImpl extends ModelImpl implements SellModel {

    @Override
    public void getFuturesType(Subscriber<FuturesTypeResponse> subscriber) {
        FuturesTypeService futuresTypeService = retrofit.create(FuturesTypeService.class);

        futuresTypeService.getFuturesType()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getFuturesName(Subscriber<FuturesNameResponse> subscriber, String type) {
        FuturesNameService futuresNameService = retrofit.create(FuturesNameService.class);

        futuresNameService.getFuturesList(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getPrice(Subscriber<PriceResponse> subscriber, String futureID, String type, String price, String h) {
        PriceService priceService = retrofit.create(PriceService.class);

        priceService.getPrice(futureID, type, price, h)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void confirmPrice(Subscriber<ConfirmResponse> subscriber, String futureID, String type, String price, String h) {
        ConfirmService confirmService = retrofit.create(ConfirmService.class);

        confirmService.confirmPrice(futureID, type, price, h)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
