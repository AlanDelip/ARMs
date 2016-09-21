package sensation.njucitibank.view.index.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import sensation.njucitibank.MApplication;
import sensation.njucitibank.R;
import sensation.njucitibank.model.vopo.OptionsTypeVO;
import sensation.njucitibank.model.vopo.OptionsVO;

/**
 * Created by Alan on 2016/9/16.
 */
public class FuturesTypeAdapter extends BaseExpandableListAdapter {
    Context context;
    FuturesTitleVO[] futuresTitleArray;
    OptionsVO[][] optionsVOArray;

    public FuturesTypeAdapter(Context context, List<OptionsTypeVO> futuresTypeList) {
        this.context = context;
        futuresTitleArray = new FuturesTitleVO[futuresTypeList.size()];
        optionsVOArray = new OptionsVO[futuresTypeList.size()][];

        for (int i = 0; i < futuresTypeList.size(); i++) {
            OptionsTypeVO optionsTypeVO = futuresTypeList.get(i);
            futuresTitleArray[i] = new FuturesTitleVO(optionsTypeVO.getType(), optionsTypeVO.getNumber());

            List<OptionsVO> optionsVOList = optionsTypeVO.getOptionsVOList();
            optionsVOArray[i] = new OptionsVO[optionsVOList.size()];
            for (int j = 0; j < optionsVOList.size(); j++) {
                optionsVOArray[i][j] = optionsVOList.get(j);
            }
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.futures_type_list_item, parent, false);
            groupViewHolder = new GroupViewHolder();

            groupViewHolder.mFuturesNameView = (TextView) convertView.findViewById(R.id.type_name);
            groupViewHolder.mFuturesNumView = (TextView) convertView.findViewById(R.id.type_num);

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.mFuturesNameView.setText(futuresTitleArray[groupPosition].type);
        groupViewHolder.mFuturesNumView.setText(futuresTitleArray[groupPosition].number + "条记录");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.futures_content_list_item, parent, false);

            childViewHolder = new ChildViewHolder();

            childViewHolder.mFuturesNameView = (TextView) convertView.findViewById(R.id.futures_name);
            childViewHolder.mNumberView = (TextView) convertView.findViewById(R.id.futures_num);
            childViewHolder.mDeltaView = (TextView) convertView.findViewById(R.id.futures_delta);
            childViewHolder.mSafetyView = (TextView) convertView.findViewById(R.id.safety);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childViewHolder.mFuturesNameView.setText(optionsVOArray[groupPosition][childPosition].getFutures_name());

        childViewHolder.mNumberView.setText(optionsVOArray[groupPosition][childPosition].getNumber());

        childViewHolder.mDeltaView.setText(optionsVOArray[groupPosition][childPosition].getDelta());

        String safety = optionsVOArray[groupPosition][childPosition].getSafe();
        if (safety.equals("warning")) {
            childViewHolder.mSafetyView.setBackgroundColor(MApplication.getContext().getResources().getColor(R.color.warning));
        }

        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return futuresTitleArray[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return optionsVOArray[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getGroupCount() {
        return futuresTitleArray.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return optionsVOArray[groupPosition].length;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    static class GroupViewHolder {
        TextView mFuturesNameView, mFuturesNumView;
    }

    static class ChildViewHolder {
        TextView mFuturesNameView, mNumberView, mDeltaView, mSafetyView;
    }

    class FuturesTitleVO {
        String type;
        String number;

        public FuturesTitleVO(String type, String number) {
            this.type = type;
            this.number = number;
        }
    }
}
