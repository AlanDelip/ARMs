package sensation.njucitibank.model.response;

import java.util.List;

/**
 * Created by Alan on 2016/9/16.
 */
public class FuturesTypeResponse extends Response<List<String>> {

    public FuturesTypeResponse(String condition, String msg, List<String> data) {
        super(condition, msg, data);
    }
}
