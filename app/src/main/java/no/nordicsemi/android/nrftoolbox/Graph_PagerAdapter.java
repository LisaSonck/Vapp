package no.nordicsemi.android.nrftoolbox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Graph_PagerAdapter extends FragmentPagerAdapter {
    @NonNull

    private int numOfTabs;
    //used to communicate between this and the graphfragment
    public Graph_PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Graph_DayFragment();
            case 1:
                return new Graph_WeekFragment();
            case 2:
                return new Graph_MonthFragment();
            case 3:
                return new Graph_YearFragment();
                default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
