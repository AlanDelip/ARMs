package sensation.njucitibank.presenter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import sensation.njucitibank.contract.SellContract;
import sensation.njucitibank.model.ModelRepository;
import sensation.njucitibank.model.modelinterface.SellModel;
import sensation.njucitibank.model.response.ConfirmResponse;
import sensation.njucitibank.model.response.FuturesNameResponse;
import sensation.njucitibank.model.response.FuturesTypeResponse;
import sensation.njucitibank.model.response.PriceResponse;
import sensation.njucitibank.model.vopo.FuturesPO;

/**
 * Created by Alan on 2016/9/16.
 */
public class SellPresenter implements SellContract.Presenter {

    SellModel mSellModel;
    SellContract.View mSellView;

    public SellPresenter(ModelRepository modelRepository, SellContract.View sellView) {
        mSellModel = modelRepository.getSellModel();
        mSellView = sellView;
        mSellView.setPresenter(this);
    }

    @Override
    public void getFuturesType() {
        mSellView.showLoading();
        mSellModel.getFuturesType(new Subscriber<FuturesTypeResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(FuturesTypeResponse futuresTypeResponse) {
                mSellView.setFuturesType(futuresTypeResponse.getData());
                mSellView.hideLoading();
            }
        });
    }

    @Override
    public void getFuturesName(String type) {
        mSellView.showLoading();
        mSellModel.getFuturesName(new Subscriber<FuturesNameResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(FuturesNameResponse futuresNameResponse) {
                List<String> futuresTypeVOList = new ArrayList<String>();
                for (FuturesPO futuresPO : futuresNameResponse.getData()) {
                    futuresTypeVOList.add(futuresPO.getFutures_name() + " " + futuresPO.getFutures_id());
                }
                mSellView.setFuturesName(futuresTypeVOList);
                mSellView.hideLoading();
            }
        }, type);
    }

    @Override
    public void getPrice(String futureID, String type, String price, String h) {
        mSellView.showLoading();
        mSellModel.getPrice(new Subscriber<PriceResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(PriceResponse priceResponse) {
                DecimalFormat df = new DecimalFormat("0.00");
                mSellView.showPrice(df.format(priceResponse.getData()));
                mSellView.hideLoading();
            }
        }, futureID, type, price, h);
    }

    @Override
    public void confirmPrice(String futureID, String type, String price, String h) {
        mSellView.showLoading();
        mSellModel.confirmPrice(new Subscriber<ConfirmResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mSellView.hideLoading();
                mSellView.sellFail();
            }

            @Override
            public void onNext(ConfirmResponse confirmResponse) {
                mSellView.hideLoading();
                mSellView.sellSuccess();
            }
        }, futureID, type, price, h);
    }

    @Override
    public void start() {
        getFuturesType();
    }
}
