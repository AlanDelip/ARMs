package sensation.njucitibank.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import sensation.njucitibank.R;
import sensation.njucitibank.model.RepositoryFactory;
import sensation.njucitibank.presenter.BasePresenter;
import sensation.njucitibank.presenter.IndexPresenter;
import sensation.njucitibank.presenter.SellPresenter;
import sensation.njucitibank.view.about.AboutFragment;
import sensation.njucitibank.view.index.IndexFragment;
import sensation.njucitibank.view.sell.SellFragment;
import sensation.njucitibank.view.widget.ViewTool;

public class MainActivity extends AppCompatActivity {

    /**
     * 是否准备退出标志位
     */
    boolean isExiting = false;

    IndexFragment indexFragment;
    SellFragment sellFragment;
    AboutFragment aboutFragment;
    FragmentManager fragmentManager;

    BasePresenter mIndexPresenter, mSellPresenter, mAboutPresenter;

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.index, R.drawable.home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.sell, R.drawable.add, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.about, R.drawable.friends, R.color.colorPrimary);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        switchTab(0);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switchTab(position);
                return true;
            }
        });
    }

    private void switchTab(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ViewTool.hideFragments(transaction, indexFragment, sellFragment, aboutFragment);

        switch (position) {
            case 0:
                toolbarTitleView.setText(getString(R.string.index_title));
                if (indexFragment != null) {
                    transaction.show(indexFragment);
                } else {
                    indexFragment = IndexFragment.newInstance();
                    mIndexPresenter = new IndexPresenter(RepositoryFactory.getInternetRepository(), indexFragment);
                    transaction.add(R.id.fragment_content, indexFragment);
                }
                break;
            case 1:
                toolbarTitleView.setText(getString(R.string.sell_title));
                if (sellFragment != null) {
                    transaction.show(sellFragment);
                } else {
                    sellFragment = SellFragment.newInstance();
                    mSellPresenter = new SellPresenter(RepositoryFactory.getInternetRepository(), sellFragment);
                    transaction.add(R.id.fragment_content, sellFragment);
                }
                break;
            case 2:
                toolbarTitleView.setText(getString(R.string.about_title));
                if (aboutFragment != null) {
                    transaction.show(aboutFragment);
                } else {
                    aboutFragment = AboutFragment.newInstance();
                    transaction.add(R.id.fragment_content, aboutFragment);
                }
                break;
        }

        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    /**
     * TimerTask倒计时实现exit by 2 clicks
     */
    private void exitBy2Click() {
        Timer tExit = null;
        Toast exitToast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
        if (!isExiting) {
            isExiting = true; // 准备退出
            exitToast.show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExiting = false; // 取消退出
                }
            }, 1500); // 如果1.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            exitToast.cancel();
            super.onBackPressed();
        }
    }
}
