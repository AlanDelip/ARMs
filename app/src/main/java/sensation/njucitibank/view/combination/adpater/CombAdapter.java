package sensation.njucitibank.view.combination.adpater;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import sensation.njucitibank.R;
import sensation.njucitibank.model.vopo.CombContentVO;

/**
 * Created by Alan on 2016/9/17.
 */
public class CombAdapter extends ArrayAdapter<CombContentVO> {
    int resourceID;
    ListView listView;

    public CombAdapter(Context context, int resource, List<CombContentVO> objects, ListView listView) {
        super(context, resource, objects);
        resourceID = resource;
        this.listView = listView;
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view;
        CombContentVO combContentVO = getItem(position);

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        TextView mExpireView = (TextView) view.findViewById(R.id.expire);
        mExpireView.setText(combContentVO.getDue_date());

        TextView mDeltaView = (TextView) view.findViewById(R.id.delta);
        mDeltaView.setText(combContentVO.getDelta());

        TextView mPriceView = (TextView) view.findViewById(R.id.price);
        mPriceView.setText(combContentVO.getFutures_price());

        TextView mSellPriceView = (TextView) view.findViewById(R.id.sell_price);
        mSellPriceView.setText(combContentVO.getSell_price());

        TextView mCostView = (TextView) view.findViewById(R.id.cost);
        mCostView.setText(combContentVO.getCost());

        TextView mTypeView = (TextView) view.findViewById(R.id.type);
        mTypeView.setText(combContentVO.getType());

        CardView cardView = (CardView) view.findViewById(R.id.card);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        if (listView.getChoiceMode() == AbsListView.CHOICE_MODE_MULTIPLE) {
            checkBox.setVisibility(View.VISIBLE);
            cardView.setCardElevation(6.0f);
        } else {
            checkBox.setVisibility(View.GONE);
            cardView.setCardElevation(2.0f);
        }
        //这里是position+1因为有一个后加入的header
        if (listView.isItemChecked(position + 1)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        return view;
    }
}
