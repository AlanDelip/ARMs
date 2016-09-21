package sensation.njucitibank.view.combination;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sensation.njucitibank.R;
import sensation.njucitibank.contract.CombContract;
import sensation.njucitibank.model.RepositoryFactory;
import sensation.njucitibank.model.vopo.CombContentVO;
import sensation.njucitibank.model.vopo.CombVO;
import sensation.njucitibank.presenter.CombPresenter;
import sensation.njucitibank.view.adjust.AdjustActivity;
import sensation.njucitibank.view.combination.adpater.CombAdapter;
import sensation.njucitibank.view.widget.ScrollChildSwipeRefreshLayout;
import sensation.njucitibank.view.widget.SwipeBackActivity;

public class CombActivity extends SwipeBackActivity implements CombContract.View {
    private static final String ARG_ID = "future_id";
    private static final String ARG_NAME = "future_name";
    boolean isStarted = false;
    CombContract.Presenter combPresenter;
    CombAdapter combAdapter;
    List<String> selectedOptionList = new ArrayList<>();
    TextView mCostView, mDeltaView, mSafetyView, mSafetyBlock;
    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.listview)
    ListView mCombListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.adjust_btn)
    TextView mAdjustView;
    private String futureID, futureName;

    public static void activityStart(Activity activity, String futureID, String futureName) {
        Intent intent = new Intent(activity, CombActivity.class);
        intent.putExtra(ARG_ID, futureID);
        intent.putExtra(ARG_NAME, futureName);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in, R.anim.half_silde_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comb);

        futureID = getIntent().getStringExtra(ARG_ID);
        futureName = getIntent().getStringExtra(ARG_NAME);
        combPresenter = new CombPresenter(RepositoryFactory.getInternetRepository(), this, futureID);

        ButterKnife.bind(this);
        if (!isStarted) {
            isStarted = true;
            combPresenter.start();
        }
        initViews();
    }

    private void initViews() {
        initToolBar();
        initRefresh();
        initListView();
    }

    private void initListView() {
        View view = LayoutInflater.from(this).inflate(R.layout.comb_header, null);
        view.setClickable(false);
        view.setFocusable(false);
        mCombListView.addHeaderView(view);
        mCostView = (TextView) view.findViewById(R.id.cost);
        mDeltaView = (TextView) view.findViewById(R.id.delta);
        mSafetyView = (TextView) view.findViewById(R.id.safety);
        mSafetyBlock = (TextView) view.findViewById(R.id.safety_title);

        mCombListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CombContentVO combContentVO = (CombContentVO) parent.getItemAtPosition(position);
                if (combContentVO != null) {
                    mCombListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    mCombListView.setItemChecked(position, true);
                    selectedOptionList.add(combContentVO.getOption_id());

                    combAdapter.notifyDataSetChanged();
                    mCombListView.invalidate();
                    mAdjustView.setVisibility(View.VISIBLE);
                    mAdjustView.startAnimation(combAnim(1));


                    mAdjustView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String[] optionList = new String[selectedOptionList.size()];
                            selectedOptionList.toArray(optionList);
                            AdjustActivity.activityStart(CombActivity.this, optionList, futureID);
                        }
                    });
                }
                return true;
            }
        });
        mCombListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CombContentVO combContentVO = (CombContentVO) parent.getItemAtPosition(position);
                if (combContentVO != null) {
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                    if (checkBox.isChecked()) {
                        mCombListView.setItemChecked(position, false);
                        selectedOptionList.remove(combContentVO.getOption_id());
                    } else {
                        mCombListView.setItemChecked(position, true);
                        selectedOptionList.add(combContentVO.getOption_id());
                    }
                    combAdapter.notifyDataSetChanged();
                    mCombListView.invalidate();
                }
            }
        });
    }

    private void initToolBar() {
        toolbar.setTitle(futureName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRefresh() {
        mRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );
        mRefreshLayout.setScrollUpChild(findViewById(R.id.listview));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                combPresenter.start();
            }
        });
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
    public void setCombList(CombVO combVO) {
        combAdapter = new CombAdapter(this, R.layout.comb_list_item, combVO.getData(), mCombListView);
        mCombListView.setAdapter(combAdapter);

        mCostView.setText("￥" + combVO.getCost());
        mDeltaView.setText(combVO.getDelta());
        String safety = combVO.getSafe();
        if (!safety.equals("safe")) {
            mSafetyView.setText("Warning");
            mSafetyBlock.setBackgroundColor(getResources().getColor(R.color.warning));
        } else {
            mSafetyView.setText("Safety");
            mSafetyBlock.setBackgroundColor(getResources().getColor(R.color.safe));
        }

    }

    @Override
    public void setPresenter(CombContract.Presenter combPresenter) {
        this.combPresenter = combPresenter;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.half_slide_in, R.anim.slide_out);
    }

    @Override
    public void onBackPressed() {
        if (mCombListView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE) {
            mCombListView.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
            mCombListView.clearChoices();
            combAdapter.notifyDataSetChanged();
            mCombListView.invalidate();
            mAdjustView.startAnimation(combAnim(2));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    /**
     * 浮动按钮的anim
     *
     * @param inOrOut in-1 out-2
     * @return
     */
    private AnimationSet combAnim(int inOrOut) {
        float from, to, alphaIn, alphaOut;
        if (inOrOut == 1) {
            from = mAdjustView.getHeight();
            to = 0;
            alphaIn = 0;
            alphaOut = 1;
        } else {
            to = mAdjustView.getHeight();
            from = 0;
            alphaIn = 1;
            alphaOut = 0;
        }
        Animation translateAnim = new TranslateAnimation(0, 0, from, to);
        translateAnim.setInterpolator(new DecelerateInterpolator());
        Animation alphaAnim = new AlphaAnimation(alphaIn, alphaOut);
        AnimationSet set = new AnimationSet(true);
        set.setFillAfter(true);
        set.addAnimation(translateAnim);
        set.addAnimation(alphaAnim);
        set.setDuration(300);
        return set;
    }
}

