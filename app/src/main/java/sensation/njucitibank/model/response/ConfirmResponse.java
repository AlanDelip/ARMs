package sensation.njucitibank.model.response;

/**
 * Created by Alan on 2016/9/16.
 */
public class ConfirmResponse extends Response<Object> {
    public ConfirmResponse(String condition, String msg, Object data) {
        super(condition, msg, data);
    }
}
