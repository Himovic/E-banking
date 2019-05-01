package com.app.ebank.mbanking;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hichem Himovic on 11/06/2017.
 */

public class ServicesAdapter extends FragmentPagerAdapter {
    private Context context;
    public ServicesAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new ExchangeFragment();
        }else if(position == 1) {
            return new ConvertFragment();
        }else if(position == 2){
            return new ActuFragment();
        }
        return new ExchangeFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return context.getString(R.string.exchange);
            case 1 : return context.getString(R.string.convertir);
            case 2 : return context.getString(R.string.actualite);
            default: return null;
        }
    }
}
