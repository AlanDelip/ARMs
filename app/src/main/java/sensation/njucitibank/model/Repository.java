package sensation.njucitibank.model;

/**
 * 仓库
 * Created by Alan on 2016/9/15.
 */
public interface Repository {
    /**
     * 是否连接
     *
     * @return 网络/数据库连接情况
     */
    boolean isConnected();
}
