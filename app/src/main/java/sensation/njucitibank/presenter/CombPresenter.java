package sensation.njucitibank.presenter;

import rx.Subscriber;
import sensation.njucitibank.contract.CombContract;
import sensation.njucitibank.model.ModelRepository;
import sensation.njucitibank.model.modelinterface.CombModel;
import sensation.njucitibank.model.response.CombResponse;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombPresenter implements CombContract.Presenter {
    CombModel mCombModel;
    CombContract.View mCombView;
    String futuresID;

    public CombPresenter(ModelRepository modelRepository, CombContract.View combView, String futuresID) {
        mCombModel = modelRepository.getCombModel();
        mCombView = combView;
        this.futuresID = futuresID;
        combView.setPresenter(this);
    }

    @Override
    public void getCombList(String futuresID) {
        mCombModel.getComb(new Subscriber<CombResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(CombResponse combResponse) {
                mCombView.setCombList(combResponse.getData().toVO());
                mCombView.hideLoading();
            }
        }, futuresID);

    }

    @Override
    public void start() {
        mCombView.showLoading();
        getCombList(futuresID);
    }
}
