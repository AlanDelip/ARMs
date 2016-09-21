package sensation.njucitibank.view.index;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sensation.njucitibank.R;
import sensation.njucitibank.contract.IndexContract;
import sensation.njucitibank.model.vopo.NewsVO;
import sensation.njucitibank.model.vopo.OptionsTypeVO;
import sensation.njucitibank.model.vopo.OptionsVO;
import sensation.njucitibank.view.browser.BrowserActivity;
import sensation.njucitibank.view.combination.CombActivity;
import sensation.njucitibank.view.index.adapter.FuturesTypeAdapter;
import sensation.njucitibank.view.index.adapter.NewsAdapter;
import sensation.njucitibank.view.log.LogActivity;
import sensation.njucitibank.view.widget.ScrollChildSwipeRefreshLayout;

/**
 * 主页碎片
 */
public class IndexFragment extends Fragment implements IndexContract.View {
    @BindView(R.id.detail_layout)
    View mDetailLayout;

    @BindView(R.id.comb_detail)
    Button mFuturesDetailBtn;

    @BindView(R.id.sell_detail)
    Button mSellDetailBtn;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.news_list)
    ListView mNewsList;

    @BindView(R.id.futures_type_list)
    ExpandableListView mFuturesList;

    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    boolean isStarted = false;
    IndexContract.Presenter mIndexPresenter;
    String currentOptionID, currentOptionName;

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_index, container, false);

        ButterKnife.bind(this, root);
        initViews();
        if (!isStarted) {
            isStarted = true;
            if (mIndexPresenter != null) {
                mIndexPresenter.start();
            } else {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (mIndexPresenter != null) {
                            mIndexPresenter.start();
                        }
                    }
                }, 100);
            }
        }
        return root;
    }

    private void initViews() {
        initRefreshLayout();
        initScrollView();
        initListView();
    }

    private void initListView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.index_header, null);
        mNewsList.addHeaderView(view);
    }

    private void initScrollView() {
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (mDetailLayout.getVisibility() == View.VISIBLE) {
                    mDetailLayout.startAnimation(indexAnim(2));
                    mDetailLayout.setVisibility(View.GONE);
                }
            }
        });
    }


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mIndexPresenter != null) {
                    mIndexPresenter.start();
                }
            }
        });
    }

    @OnClick(R.id.comb_detail)
    void openCombActivity() {
        CombActivity.activityStart(getActivity(), currentOptionID, currentOptionName);
    }

    @OnClick(R.id.sell_detail)
    void openSellActivity() {
        LogActivity.activityStart(getActivity(), currentOptionID, currentOptionName);
    }

    @Override
    public void setPresenter(IndexContract.Presenter presenter) {
        mIndexPresenter = presenter;
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
    public void loadNewsList(List<NewsVO> newsList) {
        NewsAdapter newsAdapter = new NewsAdapter(getContext(), R.layout.news_list_item, newsList);
        mNewsList.setAdapter(newsAdapter);

        mNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsVO newsVO = (NewsVO) parent.getItemAtPosition(position);
                BrowserActivity.activityStart(getActivity(), newsVO.getLink());
            }
        });
    }

    @Override
    public void loadFuturesList(List<OptionsTypeVO> futuresTypeList) {
        FuturesTypeAdapter futuresTypeAdapter = new FuturesTypeAdapter(getContext(), futuresTypeList);
        mFuturesList.setAdapter(futuresTypeAdapter);
        mFuturesList.expandGroup(0);
        mFuturesList.expandGroup(1);

        mFuturesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mDetailLayout.getVisibility() == View.VISIBLE) {
                    mDetailLayout.startAnimation(indexAnim(2));
                    mDetailLayout.setVisibility(View.GONE);
                }
            }
        });

        mFuturesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                OptionsVO optionsVO = (OptionsVO) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
                currentOptionID = optionsVO.getFutures_id();
                currentOptionName = optionsVO.getFutures_name();
                mDetailLayout.setVisibility(View.VISIBLE);
                mDetailLayout.startAnimation(indexAnim(1));

                return true;
            }
        });
    }

    /**
     * 浮动按钮的anim
     *
     * @param inOrOut
     * @return
     */
    private AnimationSet indexAnim(int inOrOut) {
        float from, to, alphaIn, alphaOut;
        if (inOrOut == 1) {
            from = mDetailLayout.getHeight();
            to = 0;
            alphaIn = 0;
            alphaOut = 1;
        } else {
            to = mDetailLayout.getHeight();
            from = 0;
            alphaIn = 1;
            alphaOut = 0;
        }
        Animation translateAnim = new TranslateAnimation(0, 0, from, to);
        translateAnim.setInterpolator(new DecelerateInterpolator());
        Animation alphaAnim = new AlphaAnimation(alphaIn, alphaOut);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnim);
        set.addAnimation(alphaAnim);
        set.setDuration(300);
        return set;
    }
}
