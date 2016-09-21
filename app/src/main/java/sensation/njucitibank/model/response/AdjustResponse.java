package sensation.njucitibank.model.response;

/**
 * Created by Alan on 2016/9/21.
 */
public class AdjustResponse extends Response<Object> {
    public AdjustResponse(String condition, String msg, Object data) {
        super(condition, msg, data);
    }
}
