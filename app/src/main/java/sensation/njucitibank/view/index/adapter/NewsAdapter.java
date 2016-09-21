package sensation.njucitibank.view.index.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sensation.njucitibank.R;
import sensation.njucitibank.model.vopo.NewsVO;

/**
 * Created by Alan on 2016/9/15.
 */
public class NewsAdapter extends ArrayAdapter<NewsVO> {
    private int resourceID;

    public NewsAdapter(Context context, int resource, List<NewsVO> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsVO newsVO = getItem(position);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
        } else {
            view = convertView;
        }

        TextView mTitleView = (TextView) view.findViewById(R.id.news_title);
        mTitleView.setText(newsVO.getTitle());

        return view;
    }
}
