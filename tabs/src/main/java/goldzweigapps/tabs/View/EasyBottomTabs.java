package goldzweigapps.tabs.View;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import goldzweigapps.tabs.R;

/**
 * Created by gilgoldzweig on 17/11/2016.
 */

public class EasyBottomTabs extends RelativeLayout {
    private TabLayout tabs;
    private ViewPager pager;
    private View rootView;
    public EasyBottomTabs(Context context) {
        super(context);
        rootView = inflate(context, R.layout.easybottomtabs, this);
        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        pager = (ViewPager) rootView.findViewById(R.id.pager);


    }

    public EasyBottomTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        rootView = inflate(context, R.layout.easybottomtabs, this);
        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        pager = (ViewPager) rootView.findViewById(R.id.pager);

    }

    public EasyBottomTabs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = inflate(context, R.layout.easybottomtabs, this);
        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        pager = (ViewPager) rootView.findViewById(R.id.pager);
    }



    public ViewPager getViewPager(){
        return pager;
    }
    public TabLayout getTabLayout(){
        return tabs;
    }

}