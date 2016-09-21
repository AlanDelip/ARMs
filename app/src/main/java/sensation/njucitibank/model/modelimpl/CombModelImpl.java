package sensation.njucitibank.model.modelimpl;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sensation.njucitibank.model.modelinterface.CombModel;
import sensation.njucitibank.model.response.CombResponse;
import sensation.njucitibank.model.service.CombService;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombModelImpl extends ModelImpl implements CombModel {

    @Override
    public void getComb(Subscriber<CombResponse> subscriber, String futuresID) {
        CombService combService = retrofit.create(CombService.class);

        combService.getComb(futuresID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
