package sensation.njucitibank.model.response;

/**
 * Created by Alan on 2016/9/16.
 */
public class PriceResponse extends Response<Double> {
    public PriceResponse(String condition, String msg, Double data) {
        super(condition, msg, data);
    }
}
