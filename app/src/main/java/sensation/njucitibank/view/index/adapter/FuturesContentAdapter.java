package sensation.njucitibank.view.index.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sensation.njucitibank.R;
import sensation.njucitibank.model.vopo.OptionsVO;

/**
 * Created by Alan on 2016/9/16.
 */
public class FuturesContentAdapter extends ArrayAdapter<OptionsVO> {
    private int resourceID;

    public FuturesContentAdapter(Context context, int resource, List<OptionsVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OptionsVO optionsVO = getItem(position);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        TextView mFuturesNameView = (TextView) view.findViewById(R.id.futures_name);
        mFuturesNameView.setText(optionsVO.getFutures_name());

        TextView mNumberView = (TextView) view.findViewById(R.id.futures_num);
        mNumberView.setText(optionsVO.getNumber());

        TextView mDeltaView = (TextView) view.findViewById(R.id.futures_delta);
        mDeltaView.setText(optionsVO.getDelta());

        View deltaLayout = view.findViewById(R.id.futures_delta_layout);
        TextView mSafetyView = (TextView) deltaLayout.findViewById(R.id.safety);
        String safety = optionsVO.getSafe();
        if (safety.equals("warning")) {
            mSafetyView.setBackgroundColor(getContext().getResources().getColor(R.color.warning));
        }

        return view;
    }
}
