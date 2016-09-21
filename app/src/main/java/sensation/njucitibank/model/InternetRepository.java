package sensation.njucitibank.model;

import sensation.njucitibank.model.modelimpl.AdjustModelImpl;
import sensation.njucitibank.model.modelimpl.CombModelImpl;
import sensation.njucitibank.model.modelimpl.IndexModelImpl;
import sensation.njucitibank.model.modelimpl.LogModelImpl;
import sensation.njucitibank.model.modelimpl.SellModelImpl;
import sensation.njucitibank.model.modelinterface.AdjustModel;
import sensation.njucitibank.model.modelinterface.CombModel;
import sensation.njucitibank.model.modelinterface.IndexModel;
import sensation.njucitibank.model.modelinterface.LogModel;
import sensation.njucitibank.model.modelinterface.SellModel;

/**
 * 创建数据服务的工厂类
 * Created by Alan on 2016/9/15.
 */
public class InternetRepository implements ModelRepository {
    private static InternetRepository internetRepository;

    private InternetRepository() {
    }

    public static InternetRepository getInstance() {
        if (internetRepository == null) {
            internetRepository = new InternetRepository();
        }
        return internetRepository;
    }

    @Override
    public IndexModel getIndexModel() {
        return new IndexModelImpl();
    }

    @Override
    public SellModel getSellModel() {
        return new SellModelImpl();
    }

    @Override
    public CombModel getCombModel() {
        return new CombModelImpl();
    }

    @Override
    public LogModel getLogModel() {
        return new LogModelImpl();
    }

    @Override
    public AdjustModel getAdjustModel() {
        return new AdjustModelImpl();
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
