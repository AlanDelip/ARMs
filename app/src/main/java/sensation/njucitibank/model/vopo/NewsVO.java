package sensation.njucitibank.model.vopo;

/**
 * Created by Alan on 2016/9/15.
 */
public class NewsVO {
    String title;
    String link;

    public NewsVO(String title, String link) {
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
}
