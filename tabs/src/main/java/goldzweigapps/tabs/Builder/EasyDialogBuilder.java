package goldzweigapps.tabs.Builder;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import goldzweigapps.tabs.Adapters.ViewPagerAdapter;
import goldzweigapps.tabs.Adapters.ViewTestPagerAdapter;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.AdapterItem;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.R;
import goldzweigapps.tabs.View.EasyTabs;

/**
 * Created by gilgoldzweig on 10/11/2016.
 */

public class EasyDialogBuilder {
    private AlertDialog dialog;
    private EasyDialogBuilder builder;
    private TabLayout StaticTabsLayout;
    private ViewPager StaticViewPager;
    private AppCompatActivity StaticActivity;
    private boolean isHidden = false;
    private boolean isFade = false;
    private int iconsPosition = 0;
    private Drawable[] Icons;
    private int[] ResIdIcons;
    private List<Fragment> FragmentList = new ArrayList<>();
    private List<String>  TitleList = new ArrayList<>();


    public static EasyDialogBuilder with(Context context) {
        EasyDialogBuilder builder = new EasyDialogBuilder();
        EasyTabs easyTabs = new EasyTabs(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.addView(easyTabs);
        builder.dialog = new AlertDialog.Builder(context).setView(linearLayout).create();


        builder.StaticTabsLayout = easyTabs.getTabLayout();
        builder.StaticViewPager = easyTabs.getViewPager();
        builder.StaticActivity = (AppCompatActivity) context;
        builder.builder = builder;
        return builder;
    }
    public EasyDialogBuilder setCustomTypeface(final Typeface selected) {
        ViewGroup vg = (ViewGroup) StaticTabsLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);

                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(selected);
                }
            }
        }
        return this;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EasyDialogBuilder setRTLPosition(Boolean state) {

        if (state) {
            StaticTabsLayout.setLayoutDirection(TabLayout.LAYOUT_DIRECTION_RTL);
            StaticViewPager.setCurrentItem(FragmentList.size() - 1);

        } else {
            StaticViewPager.setCurrentItem(0);
        }
        return this;
    }
    public EasyDialogBuilder setTextColors(@ColorInt int selectedColor, @ColorInt int unselectedColor) {
        StaticTabsLayout.setTabTextColors(unselectedColor, selectedColor);
        return this;
    }
    public EasyDialogBuilder setTabsBackgroundColor(@ColorInt int Color) {
        StaticTabsLayout.setBackgroundColor(Color);
        return this;
    }
    public EasyDialogBuilder setIndicatorColor(@ColorInt int Color) {
        StaticTabsLayout.setSelectedTabIndicatorColor(Color);
        return this;
    }
    public EasyDialogBuilder addOnTabListener(final TabsListener tabsListener){
        StaticTabsLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabsListener.onScreenPosition(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return this;
    }
    public EasyDialogBuilder setIconFading(boolean state) {
        isFade = state;
        if (Icons != null){
            if (Icons.length == FragmentList.size()) {
                if (state) {
                    StaticViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            int alphaCurrent = (int) (255 - (128 * Math.abs(positionOffset)));
                            int alphaNext = (int) (128 + (128 * Math.abs(positionOffset)));

                            if (positionOffset != 0 && position != Icons.length) {
                                StaticTabsLayout.getTabAt(position).getIcon().setAlpha(alphaCurrent);
                                StaticTabsLayout.getTabAt(position + 1).getIcon().setAlpha(alphaNext);

                            }
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
//                    StaticViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                        @SuppressWarnings("ConstantConditions")
//                        @Override
//                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                            int alphaCurrent = (int) (255 - (128 * Math.abs(positionOffset)));
//                            int alphaNext = (int) (128 + (128 * Math.abs(positionOffset)));
//
//                            if (positionOffset != 0) {
//
//                                switch (Icons.length) {
//                                    case 0:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                        }
//                                        break;
//                                    case 1:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                        }
//                                        break;
//                                    case 2:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//                                    case 3:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                        }
//                                        break;
//                                    case 4:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                        }
//                                        break;
//                                    case 5:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//                                    case 6:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//
//                                        }
//                                        break;
//                                    case 7:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//                                    case 8:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//                                    case 9:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//
//                                    case 10:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 11:
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//                                    case 11:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 11:
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 12:
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaNext);
//                                                break;
//
//
//                                        }
//                                        break;
//                                    case 12:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 11:
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 12:
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 13:
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(14).getIcon().setAlpha(alphaNext);
//                                                break;
//
//
//                                        }
//                                        break;
//                                    case 13:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 11:
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 12:
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 13:
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(14).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 14:
//                                                StaticTabsLayout.getTabAt(14).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(15).getIcon().setAlpha(alphaNext);
//                                                break;
//                                        }
//                                        break;
//                                    case 14:
//                                        switch (position) {
//                                            case 0:
//                                                StaticTabsLayout.getTabAt(0).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 1:
//                                                StaticTabsLayout.getTabAt(1).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 2:
//                                                StaticTabsLayout.getTabAt(2).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 3:
//                                                StaticTabsLayout.getTabAt(3).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 4:
//                                                StaticTabsLayout.getTabAt(4).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 5:
//                                                StaticTabsLayout.getTabAt(5).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 6:
//                                                StaticTabsLayout.getTabAt(6).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 7:
//                                                StaticTabsLayout.getTabAt(7).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 8:
//                                                StaticTabsLayout.getTabAt(8).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 9:
//                                                StaticTabsLayout.getTabAt(9).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 10:
//                                                StaticTabsLayout.getTabAt(10).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 11:
//                                                StaticTabsLayout.getTabAt(11).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 12:
//                                                StaticTabsLayout.getTabAt(12).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 13:
//                                                StaticTabsLayout.getTabAt(13).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(14).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 14:
//                                                StaticTabsLayout.getTabAt(14).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(15).getIcon().setAlpha(alphaNext);
//                                                break;
//                                            case 15:
//                                                StaticTabsLayout.getTabAt(15).getIcon().setAlpha(alphaCurrent);
//                                                StaticTabsLayout.getTabAt(16).getIcon().setAlpha(alphaNext);
//                                                break;
//
//                                        }
//                                        break;
//
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onPageSelected(int position) {
//
//                        }
//
//                        @Override
//                        public void onPageScrollStateChanged(int state) {
//
//                        }
//
//                    });
                }
            } else {
                Throwable throwable = new Throwable("Amount of icons must be equal to the number of StaticTabsLayout. ");
                try {
                    throw throwable;
                } catch (Throwable throwable1) {
                    throwable1.printStackTrace();
                }


            }
        }else{
            if (ResIdIcons.length == FragmentList.size()) {
                if (state) {
                    StaticViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @SuppressWarnings("ConstantConditions")
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            int alphaCurrent = (int) (255 - (128 * Math.abs(positionOffset)));
                            int alphaNext = (int) (128 + (128 * Math.abs(positionOffset)));
                            if (positionOffset != 0 && position != ResIdIcons.length) {

                                StaticTabsLayout.getTabAt(position).getIcon().setAlpha(alphaCurrent);
                                StaticTabsLayout.getTabAt(position + 1).getIcon().setAlpha(alphaNext);

                            }
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }

                    });
                }
            } else {
                Throwable throwable = new Throwable("Amount of icons must be equal to the number of the Tabs. ");
                try {
                    throw throwable;
                } catch (Throwable throwable1) {
                    throwable1.printStackTrace();
                }


            }
        }


        return this;
    }
    public EasyDialogBuilder setTabLayoutScrollable(Boolean state) {
        if (state) {
            StaticTabsLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            StaticTabsLayout.setTabMode(TabLayout.MODE_FIXED);
        }
        if (FragmentList.size() > 6) {
            StaticTabsLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        return this;
    }
    public EasyDialogBuilder hideAllTitles(boolean hide){this.isHidden = hide; return this;}
    public EasyDialogBuilder addTabs(TabItem... tabs) {
        for (TabItem item : tabs){
            FragmentList.add(item.getFragment());
            TitleList.add(item.getTitle());
        }
        return this;
    }
    public EasyDialogBuilder addIcons(Drawable... icons) {
        this.Icons = icons;
        return this;
    }
    public EasyDialogBuilder addIcons(int... icons) {
        this.ResIdIcons = icons;
        return this;
    }   public EasyDialogBuilder changeIconPosition(String position){
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        switch (position) {
            case "left":
                lp.gravity = Gravity.LEFT;
                lp.setMargins(0,0, 0, 2);
                break;
            case "right":
                lp.gravity = Gravity.RIGHT;
                lp.setMargins(0, 2, 0, 0);
                break;
            case "top":
                lp.gravity = Gravity.TOP;
                lp.setMargins(2, 0, 0, 0);
                break;
            case "bottom":
                lp.gravity = Gravity.BOTTOM;
                lp.setMargins(0, 0, 2, 0);
                break;
            case "center":
                lp.gravity = Gravity.CENTER;
                lp.setMargins(0, 0, 0, 0);
                break;
            default:
                break;
        }
        ViewGroup vg = (ViewGroup) StaticTabsLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);

                if (tabViewChild instanceof ImageView) {
                    tabViewChild.setLayoutParams(lp);
                }
            }
        }
        return this;
    }
    public EasyDialogBuilder addTransformation(boolean reverseDrawingOrder, ViewPager.PageTransformer transform) {
        StaticViewPager.setPageTransformer(reverseDrawingOrder, transform);
        return this;
    }
    public void Build(){
        ViewTestPagerAdapter adapter = new ViewTestPagerAdapter(StaticActivity.getSupportFragmentManager(), new AdapterItem(FragmentList, TitleList, isHidden));
        StaticViewPager.setAdapter(adapter);
        StaticTabsLayout.setupWithViewPager(StaticViewPager);
        builder.dialog.show();
        StaticTabsLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                StaticViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (Icons != null){
            for (Drawable icon : Icons){

                try {
                    StaticTabsLayout.getTabAt(iconsPosition).setIcon(icon);
                    if (isFade && iconsPosition >= 1){
                        StaticTabsLayout.getTabAt(iconsPosition).getIcon().setAlpha(128);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                iconsPosition++;
            }
        }
        if (ResIdIcons != null){
            for (int icon : ResIdIcons){
                try {
                    StaticTabsLayout.getTabAt(iconsPosition).setIcon(icon);
                    if (isFade && iconsPosition >= 1){
                        StaticTabsLayout.getTabAt(iconsPosition).getIcon().setAlpha(128);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                iconsPosition++;
            }
        }



    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
