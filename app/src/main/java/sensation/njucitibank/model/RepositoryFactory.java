package sensation.njucitibank.model;

/**
 * Created by Alan on 2016/9/15.
 */
public class RepositoryFactory {

    public static InternetRepository getInternetRepository() {
        return InternetRepository.getInstance();
    }
}
