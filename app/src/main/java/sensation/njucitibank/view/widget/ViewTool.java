package sensation.njucitibank.view.widget;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * 视图控制工具
 * Created by Alan on 2016/9/15.
 */
public class ViewTool {
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public static void hideFragments(@NonNull FragmentTransaction transaction, @NonNull Fragment... fragments) {
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }

    public static String checkNullEdit(String str) {
        if (str == null || !isNumber(str) || str.equals("")) {
            return "10";
        } else {
            return str;
        }
    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]*$";
        return str.matches(reg);
    }
}
