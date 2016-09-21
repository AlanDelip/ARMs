package sensation.njucitibank.view.sell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;
import sensation.njucitibank.R;
import sensation.njucitibank.contract.SellContract;
import sensation.njucitibank.view.widget.ViewTool;

/**
 * 主页碎片
 */
public class SellFragment extends Fragment implements SellContract.View {
    private final String EU_H = "-1", DEFAULT = "10";
    SellContract.Presenter mSellPresenter;

    @BindView(R.id.block_level)
    EditText mBlockLevelEdit;

    @BindView(R.id.sell_price)
    EditText mPriceEdit;

    @BindView(R.id.futures_type)
    Spinner mFuturesType;

    @BindView(R.id.futures_name)
    Spinner mFuturesSpinner;

    @BindView(R.id.options_type)
    Spinner mOptionsType;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.confirm)
    Button mConfirmButton;

    public static SellFragment newInstance() {
        return new SellFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sell, container, false);
        ButterKnife.bind(this, root);
        initViews();
        if (mSellPresenter != null) {
            mSellPresenter.start();
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (mSellPresenter != null) {
                        mSellPresenter.start();
                    }
                }
            }, 100);
        }

        return root;
    }

    private void initViews() {
        initSpinners();
        initProgressBar();
    }

    private void initSpinners() {
        mFuturesType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = (String) parent.getItemAtPosition(position);
                mSellPresenter.getFuturesName(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mOptionsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mBlockLevelEdit.setText(EU_H);
                    mBlockLevelEdit.setEnabled(false);
                } else {
                    mBlockLevelEdit.setText(DEFAULT);
                    mBlockLevelEdit.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBlockLevelEdit.setEnabled(true);
            }
        });
    }

    @OnClick(R.id.check_price)
    void checkPrice() {
        String type = (String) mOptionsType.getSelectedItem();
        if (type.equals("欧式看跌期权")) {
            type = "Eu";
        } else {
            type = "Ba";
        }
        mSellPresenter.getPrice(
                ((String) mFuturesSpinner.getSelectedItem()).split(" ")[1],
                type,
                ViewTool.checkNullEdit(mPriceEdit.getText().toString()),
                ViewTool.checkNullEdit(mBlockLevelEdit.getText().toString())
        );
    }

    @OnClick(R.id.confirm)
    void confirm() {
        String type = (String) mOptionsType.getSelectedItem();
        if (type.equals("欧式看跌期权")) {
            type = "Eu";
        } else {
            type = "Ba";
        }
        mSellPresenter.confirmPrice(
                ((String) mFuturesSpinner.getSelectedItem()).split(" ")[1],
                type,
                ViewTool.checkNullEdit(mPriceEdit.getText().toString()),
                ViewTool.checkNullEdit(mBlockLevelEdit.getText().toString())
        );
    }


    private void initProgressBar() {
        progressBar.setIndeterminateDrawable(new IndeterminateProgressDrawable(getContext()));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setFuturesType(List<String> optionsList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFuturesType.setAdapter(adapter);
    }

    @Override
    public void setFuturesName(List<String> futuresList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, futuresList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFuturesSpinner.setAdapter(adapter);
    }

    @Override
    public void showPrice(String money) {
        String buttonText = "确认购买 ￥" + money + "元";
        mConfirmButton.setText(buttonText);
        mConfirmButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void sellSuccess() {
        Toast.makeText(getContext(), "购买成功!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void sellFail() {
        Toast.makeText(getContext(), "购买失败!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(SellContract.Presenter presenter) {
        mSellPresenter = presenter;
    }
}
