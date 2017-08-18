package com.zhy.flowlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by zhy on 15/9/10.
 */
public class SimpleFragment extends Fragment {

    /**
     * 流布局选项内容的数组
     */
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    /**
     * 自定义的流式布局控件---View
     */
    private TagFlowLayout mFlowLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        // 自定义的流式布局控件---View
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
        // 设置适配器
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                // 流布局中每个TextView
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }
}
