package goldzweigapps.tabs.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import goldzweigapps.tabs.Items.AdapterItem;


public class ViewTestPagerAdapter extends FragmentStatePagerAdapter {
    private AdapterItem AdapterList;

    public ViewTestPagerAdapter(FragmentManager FragmentManger, AdapterItem AdapterList) {
        super(FragmentManger);
        this.AdapterList = AdapterList;

    }

    @Override
    public Fragment getItem(int position) {
        return AdapterList.getFragmentList().get(position);
    }

    @Override
    public int getCount() {

        return AdapterList.getFragmentList().size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (AdapterList.isTitle()) {
            return null;
        } else {
            return AdapterList.getTitleList().get(position);
        }
    }

}
