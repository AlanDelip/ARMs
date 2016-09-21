package sensation.njucitibank.model.response;

import java.util.List;

import sensation.njucitibank.model.vopo.OptionsPO;

/**
 * Created by Alan on 2016/9/16.
 */
public class OptionsResponse extends Response<List<OptionsPO>> {

    public OptionsResponse(String condition, String msg, List<OptionsPO> data) {
        super(condition, msg, data);
    }
}
