package sensation.njucitibank.model;

import sensation.njucitibank.model.modelinterface.AdjustModel;
import sensation.njucitibank.model.modelinterface.CombModel;
import sensation.njucitibank.model.modelinterface.IndexModel;
import sensation.njucitibank.model.modelinterface.LogModel;
import sensation.njucitibank.model.modelinterface.SellModel;

/**
 * 模块仓库
 * Created by Alan on 2016/9/15.
 */
public interface ModelRepository extends Repository {

    /**
     * 获取首页模块
     *
     * @return
     */
    IndexModel getIndexModel();

    /**
     * 获取售卖模块
     *
     * @return
     */
    SellModel getSellModel();

    /**
     * 获取组合模块
     *
     * @return
     */
    CombModel getCombModel();

    /**
     * 获取交易记录模块
     *
     * @return
     */
    LogModel getLogModel();

    /**
     * 获取调仓模块
     *
     * @return
     */
    AdjustModel getAdjustModel();
}
