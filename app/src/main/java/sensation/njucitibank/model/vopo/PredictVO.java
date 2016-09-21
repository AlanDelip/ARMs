package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/21.
 */
public class PredictVO {
    String futureID;
    String futureName;
    String number;
    String originCost;
    String originNum;
    String originDelta;
    String currentNum;
    String currentDelta;
    String var;
    String safe;

    public PredictVO(String futureID, String futureName, String number, String originCost, String originNum, String originDelta, String currentNum, String currentDelta, String var, String safe) {
        this.futureID = futureID;
        this.futureName = futureName;
        this.number = number;
        this.originCost = originCost;
        this.originNum = originNum;
        this.originDelta = originDelta;
        this.currentNum = currentNum;
        this.currentDelta = currentDelta;
        this.var = var;
        this.safe = safe;
    }

    public String getFutureID() {
        return futureID;
    }

    public void setFutureID(String futureID) {
        this.futureID = futureID;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOriginCost() {
        return originCost;
    }

    public void setOriginCost(String originCost) {
        this.originCost = originCost;
    }

    public String getOriginNum() {
        return originNum;
    }

    public void setOriginNum(String originNum) {
        this.originNum = originNum;
    }

    public String getOriginDelta() {
        return originDelta;
    }

    public void setOriginDelta(String originDelta) {
        this.originDelta = originDelta;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getCurrentDelta() {
        return currentDelta;
    }

    public void setCurrentDelta(String currentDelta) {
        this.currentDelta = currentDelta;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }
}
