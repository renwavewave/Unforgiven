package cn.youngkaaa.myviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private YViewPager mViewPager;
    private List<FragmentInner> mFragments;
    private TextView mTextView;
    private int mCurrentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (YViewPager) findViewById(R.id.viewpager);
        mTextView = (TextView) findViewById(R.id.numIndicator);
        mViewPager.setDirection(YViewPager.VERTICAL);
        initData();

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

        mViewPager.setPageMargin(10);
        mViewPager.addOnPageChangeListener(new YViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled() position=>" + position + ",positionOffset=>" + positionOffset +
                        ",positionOffsetPixels=>" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected() position=>" + position);
                setIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged() state=>" + state);
            }
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        FragmentInner fragmentInner1 = FragmentInner.newInstance("fragment1", R.drawable.jay_jay);
        FragmentInner fragmentInner2 = FragmentInner.newInstance("fragment2", R.drawable.jay_fantexi);
        FragmentInner fragmentInner3 = FragmentInner.newInstance("fragment3", R.drawable.image2);
        FragmentInner fragmentInner4 = FragmentInner.newInstance("fragment4", R.drawable.logo);
        FragmentInner fragmentInner5 = FragmentInner.newInstance("fragment5", R.drawable.jay_jay);
        FragmentInner fragmentInner6 = FragmentInner.newInstance("fragment6", R.drawable.image2);
        mFragments.add(fragmentInner1);
        mFragments.add(fragmentInner2);
        mFragments.add(fragmentInner3);
        mFragments.add(fragmentInner4);
        mFragments.add(fragmentInner5);
        mFragments.add(fragmentInner6);
        setIndicators(mCurrentPos);
    }

    private void setIndicators(int position) {
        mTextView.setText(position + 1 + "/" + mFragments.size());
    }

    class FragmentAdapter extends YFragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
