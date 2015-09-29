package soa.assignment.uetlib.adapter;

/**
 * Created by TuanTQ on 9/26/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import soa.assignment.uetlib.fragment.AllBookFragment;
import soa.assignment.uetlib.fragment.CategoryFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllBookFragment all = new AllBookFragment();
                return all;
            case 1:
                CategoryFragment category = new CategoryFragment();
                return category;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}