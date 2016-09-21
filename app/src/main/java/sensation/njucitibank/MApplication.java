package sensation.njucitibank;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * 应用类
 * Created by Alan on 2016/9/14.
 */
public class MApplication extends Application {
    private static Context context;
    private static String serverIP;

    public static Context getContext() {
        return context;
    }

    public static String getServerIP() {
        return serverIP;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        initContext();
        initHostIP();

    }

    private void initContext() {
        context = getApplicationContext();
    }

    private void initHostIP() {
        Properties IPAddress = new Properties();
        try {
            IPAddress.load(getContext().getAssets().open("host.properties"));
            serverIP = IPAddress.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
