package sensation.njucitibank.presenter;

import rx.Subscriber;
import sensation.njucitibank.contract.LogContract;
import sensation.njucitibank.model.ModelRepository;
import sensation.njucitibank.model.modelinterface.LogModel;
import sensation.njucitibank.model.response.LogResponse;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogPresenter implements LogContract.Presenter {

    LogModel mLogModel;
    LogContract.View mLogView;
    String futureID;

    public LogPresenter(ModelRepository modelRepository, LogContract.View logView, String futureID) {
        mLogModel = modelRepository.getLogModel();
        mLogView = logView;
        this.futureID = futureID;
        mLogView.setPresenter(this);
    }

    @Override
    public void getLogList(String futureID) {
        mLogModel.getLogList(new Subscriber<LogResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(LogResponse logResponse) {
                mLogView.setLogList(logResponse.getData().toVO());
                mLogView.hideLoading();
            }
        }, futureID);
    }

    @Override
    public void start() {
        mLogView.showLoading();
        getLogList(futureID);
    }
}
