package sensation.njucitibank.model.vopo;

import java.util.List;

/**
 * Created by Alan on 2016/9/16.
 */
public class OptionsTypeVO {
    String type;
    String number;
    List<OptionsVO> optionsVOList;

    public OptionsTypeVO(String type, String number, List<OptionsVO> optionsVOList) {
        this.type = type;
        this.number = number;
        this.optionsVOList = optionsVOList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<OptionsVO> getOptionsVOList() {
        return optionsVOList;
    }

    public void setOptionsVOList(List<OptionsVO> optionsVOList) {
        this.optionsVOList = optionsVOList;
    }
}
