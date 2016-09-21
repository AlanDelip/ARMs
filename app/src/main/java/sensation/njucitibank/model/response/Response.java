package sensation.njucitibank.model.response;

/**
 * Created by Alan on 2016/9/15.
 */
public class Response<T> {
    String condition;
    String msg;
    T data;

    public Response(String condition, String msg, T data) {
        this.condition = condition;
        this.msg = msg;
        this.data = data;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
