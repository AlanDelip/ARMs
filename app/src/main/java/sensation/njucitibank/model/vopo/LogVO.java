package sensation.njucitibank.model.vopo;

import java.util.List;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogVO {
    String quantity;
    String ave;
    List<LogContentVO> LogList;

    public LogVO(String quantity, String ave, List<LogContentVO> logList) {
        this.quantity = quantity;
        this.ave = ave;
        LogList = logList;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAve() {
        return ave;
    }

    public void setAve(String ave) {
        this.ave = ave;
    }

    public List<LogContentVO> getLogList() {
        return LogList;
    }

    public void setLogList(List<LogContentVO> logList) {
        LogList = logList;
    }

}
