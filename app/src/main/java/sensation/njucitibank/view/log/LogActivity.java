package sensation.njucitibank.view.log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sensation.njucitibank.R;
import sensation.njucitibank.contract.LogContract;
import sensation.njucitibank.model.RepositoryFactory;
import sensation.njucitibank.model.vopo.LogVO;
import sensation.njucitibank.presenter.LogPresenter;
import sensation.njucitibank.view.log.adapter.LogAdapter;
import sensation.njucitibank.view.widget.ScrollChildSwipeRefreshLayout;
import sensation.njucitibank.view.widget.SwipeBackActivity;

public class LogActivity extends SwipeBackActivity implements LogContract.View {
    private static final String ARG_ID = "future_id";
    private static final String ARG_NAME = "future_name";
    String futureID, futureName;
    boolean isStarted = false;
    LogContract.Presenter mLogPresenter;

    TextView mNumberView, mAveView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.listview)
    ListView mLogListView;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout mRefreshLayout;

    public static void activityStart(Activity activity, String futureID, String futureName) {
        Intent intent = new Intent(activity, LogActivity.class);
        intent.putExtra(ARG_ID, futureID);
        intent.putExtra(ARG_NAME, futureName);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.half_silde_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        futureID = getIntent().getStringExtra(ARG_ID);
        futureName = getIntent().getStringExtra(ARG_NAME);
        mLogPresenter = new LogPresenter(RepositoryFactory.getInternetRepository(), this, futureID);

        ButterKnife.bind(this);
        initViews();
        if (!isStarted) {
            isStarted = true;
            mLogPresenter.start();
        }
    }

    private void initViews() {
        initToolBar();
        initRefreshLayout();
        initListView();
    }

    private void initListView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.log_header, null);
        mLogListView.addHeaderView(headerView);

        mNumberView = (TextView) headerView.findViewById(R.id.number);
        mAveView = (TextView) headerView.findViewById(R.id.ave);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );
        mRefreshLayout.setScrollUpChild(findViewById(R.id.listview));

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLogPresenter.start();
            }
        });
    }

    private void initToolBar() {
        toolbar.setTitle(futureName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.half_slide_in, R.anim.slide_out);
    }

    @Override
    public void showLoading() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setLogList(LogVO logVO) {
        LogAdapter logAdapter = new LogAdapter(this, R.layout.log_list_item, logVO.getLogList());
        mLogListView.setAdapter(logAdapter);

        mNumberView.setText(logVO.getQuantity());
        mAveView.setText("￥" + logVO.getAve());
    }

    @Override
    public void setPresenter(LogContract.Presenter presenter) {
        mLogPresenter = presenter;
    }
}
