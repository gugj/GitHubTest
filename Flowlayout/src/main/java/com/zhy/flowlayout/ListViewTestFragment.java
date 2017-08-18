package com.zhy.flowlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhy on 15/9/10.
 */
public class ListViewTestFragment extends Fragment {

    /**
     * ListView的适配器的数据源
     */
    private List<List<String>> mDatas = new ArrayList<List<String>>();
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        // 初始化数据
        initDatas();

        mListView = (ListView) view.findViewById(R.id.id_listview);
        // ListView设置适配器
        mListView.setAdapter(new CommonAdapter<List<String>>(getActivity(), R.layout.item_for_listview, mDatas) {
            // ListView流布局中的选中内容的集合
            Map<Integer, Set<Integer>> selectedMap = new HashMap<Integer, Set<Integer>>();

            @Override
            public void convert(final ViewHolder viewHolder, List<String> strings) {
                // ListView流布局中适配器条目的布局View
                TagFlowLayout tagFlowLayout = viewHolder.getView(R.id.id_tagflowlayout);
                // 流布局的适配器
                TagAdapter<String> tagAdapter = new TagAdapter<String>(strings) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        //can use viewholder
                        TextView tv = (TextView) mInflater.inflate(R.layout.tv, parent, false);
                        tv.setText(o);
                        return tv;
                    }
                };
                // 给流布局设置适配器
                tagFlowLayout.setAdapter(tagAdapter);
                //重置状态----全部设置为未选中状态
                tagAdapter.setSelectedList(selectedMap.get(viewHolder.getItemPosition()));

                // 流布局设置选项选中监听
                tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        selectedMap.put(viewHolder.getItemPosition(), selectPosSet);
                    }
                });
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        for (int i = 'A'; i < 'z'; i++) {
            List<String> itemData = new ArrayList<String>(3);
            for (int j = 0; j < 3; j++) {
                itemData.add((char) i + "");
            }
            mDatas.add(itemData);
        }
    }
}
