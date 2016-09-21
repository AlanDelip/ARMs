package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/16.
 */
public class NewsPO {
    String title;
    String link;

    public NewsPO(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public NewsVO toVO() {
        return new NewsVO(title, link);
    }
}
