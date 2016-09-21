package sensation.njucitibank.model.response;

import java.util.List;

import sensation.njucitibank.model.vopo.NewsPO;

/**
 * Created by Alan on 2016/9/15.
 */
public class NewsResponse extends Response<List<NewsPO>> {
    public NewsResponse(String condition, String msg, List<NewsPO> data) {
        super(condition, msg, data);
    }
}
