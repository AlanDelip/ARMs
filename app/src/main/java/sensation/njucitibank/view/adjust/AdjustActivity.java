package sensation.njucitibank.view.adjust;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;
import sensation.njucitibank.R;
import sensation.njucitibank.contract.AdjustContract;
import sensation.njucitibank.model.RepositoryFactory;
import sensation.njucitibank.model.vopo.PredictVO;
import sensation.njucitibank.presenter.AdjustPresenter;

public class AdjustActivity extends AppCompatActivity implements AdjustContract.View {
    private static final String ARG_OPTIONS = "options";
    private static final String ARG_ID = "id";
    @BindView(R.id.chart)
    LineChart mLineChart;
    @BindView(R.id.gamma)
    TextView mGammaView;
    @BindView(R.id.var)
    TextView mVarView;
    @BindView(R.id.confirm_layout)
    View mConfirmView;
    @BindView(R.id.type)
    TextView mTypeView;
    @BindView(R.id.capacity)
    TextView mCapacityView;
    @BindView(R.id.origin_cost)
    TextView mOriginCostView;
    @BindView(R.id.origin_num)
    TextView mOriginNumView;
    @BindView(R.id.origin_delta)
    TextView mOriginDeltaView;
    @BindView(R.id.current_delta)
    TextView mCurrentDeltaView;
    @BindView(R.id.current_num)
    TextView mCurrentNumView;
    @BindView(R.id.current_var)
    TextView mCurrentVarView;
    @BindView(R.id.safety)
    TextView mSafetyView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.confirm)
    Button mConfirmGammaBtn;
    @BindView(R.id.confirm_adjust)
    Button mConfirmAdjustBtn;
    @BindView(R.id.reset)
    Button mResetBtn;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private String[] optionList;
    private String futureID;
    private AdjustContract.Presenter mAdjustPresenter;
    private boolean isStarted = false;

    public static void activityStart(Context context, String[] optionList, String futureID) {
        Intent intent = new Intent(context, AdjustActivity.class);
        intent.putExtra(ARG_OPTIONS, optionList);
        intent.putExtra(ARG_ID, futureID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        futureID = getIntent().getStringExtra(ARG_ID);
        optionList = getIntent().getStringArrayExtra(ARG_OPTIONS);

        mAdjustPresenter = new AdjustPresenter(RepositoryFactory.getInternetRepository(), this, optionList);

        ButterKnife.bind(this);
        initViews();
        if (!isStarted) {
            isStarted = true;
            mAdjustPresenter.start();
        }
    }

    private void initViews() {
        initChart();
        mProgressBar.setIndeterminateDrawable(new IndeterminateProgressDrawable(this));
    }

    private void initChart() {
        mLineChart.setNoDataTextDescription("");
        mLineChart.setNoDataText("");
        mLineChart.setDescription("");
        mLineChart.setDrawBorders(false);
        mLineChart.animateXY(500, 500);
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getXAxis().setTextSize(13);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getAxisLeft().setTextSize(13);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                mGammaView.setText(e.getX() + "");
                mVarView.setText(e.getY() + "");
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    @OnClick(R.id.fab)
    void back() {
        finish();
    }

    @OnClick(R.id.confirm)
    void confirmGamma() {
        mAdjustPresenter.getContrast(Arrays.asList(optionList), mGammaView.getText().toString());
        mConfirmView.post(new Runnable() {
            @Override
            public void run() {
                mConfirmView.startAnimation(confirmViewAnim(1));
                mConfirmView.setVisibility(View.VISIBLE);
            }
        });
        fab.startAnimation(fabAnim(2));
        fab.setVisibility(View.GONE);
        mConfirmGammaBtn.startAnimation(btnAnim(2));
        mConfirmGammaBtn.setVisibility(View.GONE);
        mConfirmAdjustBtn.setVisibility(View.VISIBLE);
        mConfirmAdjustBtn.startAnimation(btnAnim(1));
        mResetBtn.setVisibility(View.VISIBLE);
        mResetBtn.startAnimation(btnAnim(1));
        mLineChart.setTouchEnabled(false);
    }

    @OnClick(R.id.confirm_adjust)
    void confirmAdjust() {
        mAdjustPresenter.adjust(futureID, Arrays.asList(optionList), mGammaView.getText().toString());
    }

    @OnClick(R.id.reset)
    void reset() {
        mConfirmView.startAnimation(confirmViewAnim(2));
        mConfirmView.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(fabAnim(1));
        mConfirmAdjustBtn.startAnimation(btnAnim(2));
        mConfirmAdjustBtn.setVisibility(View.GONE);
        mResetBtn.startAnimation(btnAnim(2));
        mResetBtn.setVisibility(View.GONE);
        mConfirmGammaBtn.setVisibility(View.VISIBLE);
        mConfirmGammaBtn.startAnimation(btnAnim(1));
        mLineChart.setTouchEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (mConfirmView.getVisibility() == View.VISIBLE) {
            reset();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setChart(float[] xData, float[] yData) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xData.length && i < yData.length; i++) {
            entries.add(new Entry(xData[i], yData[i]));
        }

        LineDataSet dataSet = new LineDataSet(entries, "GAMMA-VAR"); // add entries to dataset
        dataSet.setColor(getResources().getColor(R.color.colorPrimary));
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(getResources().getColor(R.color.colorPrimary));
        dataSet.setValueTextColor(getResources().getColor(android.R.color.transparent));

        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
        mGammaView.setText(xData[0] + "");//init
        mVarView.setText(yData[0] + "");
    }

    @Override
    public void showPrediction(PredictVO predictVO) {
        mTypeView.setText(predictVO.getFutureName());
        mCapacityView.setText(predictVO.getNumber());
        mOriginCostView.setText(predictVO.getOriginCost());
        mOriginDeltaView.setText(predictVO.getOriginDelta());
        mOriginNumView.setText(predictVO.getOriginNum());
        mCurrentDeltaView.setText(predictVO.getCurrentDelta());
        mCurrentNumView.setText(predictVO.getCurrentNum());
        mCurrentVarView.setText(predictVO.getVar());
        String safety = predictVO.getSafe();
        if (safety.equals("warning")) {
            mSafetyView.setText(safety);
            mSafetyView.setBackgroundColor(getResources().getColor(R.color.warning));
        }
    }

    @Override
    public void adjustSuccess() {
        Toast.makeText(AdjustActivity.this, "调仓成功!", Toast.LENGTH_SHORT).show();
        reset();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 1000);
    }

    @Override
    public void adjustFail() {
        Toast.makeText(AdjustActivity.this, "调仓失败!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(AdjustContract.Presenter presenter) {
        mAdjustPresenter = presenter;
    }

    /**
     * confirm面板动画
     *
     * @param inOrOut in-1 out-2
     */
    private Animation confirmViewAnim(int inOrOut) {
        float from, to;
        if (inOrOut == 1) {
            from = -mConfirmView.getWidth() - 20;
            to = 0;
        } else {
            from = 0;
            to = -mConfirmView.getWidth() - 20;
        }
        Animation anim = new TranslateAnimation(from, to, 0, 0);
        anim.setDuration(500);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    /**
     * 按钮动画
     *
     * @param inOrOut in-1 out-2
     * @return
     */
    private AnimationSet btnAnim(int inOrOut) {
        float from, to, fadeIn, fadeOut;
        if (inOrOut == 1) {
            from = mConfirmGammaBtn.getWidth() + 20;
            to = 0;
            fadeIn = 0;
            fadeOut = 1;
        } else {
            from = 0;
            to = mConfirmGammaBtn.getWidth() + 20;
            fadeIn = 1;
            fadeOut = 0;
        }
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        Animation transAnim = new TranslateAnimation(from, to, 0, 0),
                alphaAnim = new AlphaAnimation(fadeIn, fadeOut);
        set.addAnimation(transAnim);
        set.addAnimation(alphaAnim);
        set.setDuration(500);
        return set;
    }

    /**
     * fab动画
     *
     * @param inOrOut in-1 out-2
     * @return
     */
    private Animation fabAnim(int inOrOut) {
        float in, out;
        if (inOrOut == 1) {
            in = 0;
            out = 1;
        } else {
            in = 1;
            out = 0;
        }
        Animation anim = new AlphaAnimation(in, out);
        anim.setDuration(500);
        return anim;
    }
}
