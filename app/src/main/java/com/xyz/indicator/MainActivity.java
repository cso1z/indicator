package com.xyz.indicator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenyonghui
 */
public class MainActivity extends AppCompatActivity {

    private static final String[] PAGERS = new String[]{
            GuideFragment1.class.getName(),
            GuideFragment2.class.getName(),
            GuideFragment3.class.getName(),
            GuideFragment4.class.getName()};

    private ColorIndicator colorIndicator;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        colorIndicator = findViewById(R.id.indicator);
        GuidePagerAdapter guidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(guidePagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position >= PAGERS.length) {
                    colorIndicator.setVisibility(View.INVISIBLE);
                } else {
                    colorIndicator.selected(position);
                    colorIndicator.setVisibility(View.VISIBLE);
                }

                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, NNActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        colorIndicator.setIndicatorNum(PAGERS.length);
        colorIndicator.setNormalColor(0x7DA0DA3A);
        colorIndicator.setSelectedColor(0xFFA0DA3A);
        colorIndicator.setIndicatorPadding(30);
        colorIndicator.setIndicatorSize(30);

        ;
    }

    String key = "key3";

    private Map test() {

        Map<String, String> map = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("key", "value3");
            put("key4", "value4");
            put("key5", "value5");
            put("key6", "value6");
        }};
        return map;
    }


    private class GuidePagerAdapter extends FragmentPagerAdapter {

        private GuidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(MainActivity.this, PAGERS[position]);
        }

        @Override
        public int getCount() {
            return PAGERS.length;
        }
    }

    public static class GuideFragment1 extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return LayoutInflater.from(getActivity()).inflate(R.layout.guide_1, null);
        }

    }

    public static class GuideFragment2 extends Fragment {

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.guide_2, null);
        }
    }

    public static class GuideFragment3 extends Fragment {

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.guide_3, null);
        }

    }

    public static class GuideFragment4 extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.guide_4, null);
        }
    }
}