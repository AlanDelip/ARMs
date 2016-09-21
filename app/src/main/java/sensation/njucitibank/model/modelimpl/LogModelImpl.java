package sensation.njucitibank.model.modelimpl;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sensation.njucitibank.model.modelinterface.LogModel;
import sensation.njucitibank.model.response.LogResponse;
import sensation.njucitibank.model.service.LogService;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogModelImpl extends ModelImpl implements LogModel {
    @Override
    public void getLogList(Subscriber<LogResponse> subscriber, String futureID) {
        LogService logService = retrofit.create(LogService.class);

        logService.getLogList(futureID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
