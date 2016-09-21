package sensation.njucitibank.view.log.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sensation.njucitibank.R;
import sensation.njucitibank.model.vopo.LogContentVO;

/**
 * Created by Alan on 2016/9/19.
 */
public class LogAdapter extends ArrayAdapter<LogContentVO> {
    @BindView(R.id.date)
    TextView mDateView;

    @BindView(R.id.price)
    TextView mPriceView;

    @BindView(R.id.number)
    TextView mNumberView;

    private int resourceID;

    public LogAdapter(Context context, int resource, List<LogContentVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LogContentVO logContentVO = getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }
        ButterKnife.bind(this, view);

        mDateView.setText(logContentVO.getDate());
        mPriceView.setText("￥" + logContentVO.getPrice());
        mNumberView.setText(logContentVO.getQuantity());

        return view;
    }
}
