package com.zhy.flowlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    /**
     * 指示器容器布局
     */
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    /**
     * 指示器容器布局的Title标题
     */
    private String[] mTabTitles = new String[]
            {"Muli Selected", "Limit 3", "Event Test", "ScrollView Test", "Single Choose", "Gravity", "ListView Sample"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // 示器容器布局
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0: // 简单的流布局---无限多选
                        return new SimpleFragment();

                    case 1: // 简单的流布局---最多选3个
                        return new LimitSelectedFragment();

                    case 2: // 流布局----设置点击监听和选中监听
                        return new EventTestFragment();

                    case 3: // 流布局----设置点击监听和选中监听，带滑动
                        return new ScrollViewTestFragment();

                    case 4: // 简单的流布局---最多选1个
                        return new SingleChooseFragment();

                    case 5: // 流布局---对齐方式：左对齐、中间对齐、右对齐
                        return new GravityFragment();

                    case 6:
                        return new ListViewTestFragment();

                    default: // 流布局----设置点击监听和选中监听
                        return new EventTestFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // 指示器容器布局的Title标题
                return mTabTitles[position];
            }

            @Override
            public int getCount() {
                return mTabTitles.length;
            }
        });

        // 给指示器布局容器设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
