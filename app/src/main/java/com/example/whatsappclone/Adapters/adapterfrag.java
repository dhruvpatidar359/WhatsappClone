package com.example.whatsappclone.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsappclone.Fragments.CallFraments;
import com.example.whatsappclone.Fragments.ChatsFragmetns;
import com.example.whatsappclone.Fragments.StatusFraments;

public class adapterfrag extends FragmentPagerAdapter {
    public adapterfrag(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return  new ChatsFragmetns();
            case 1: return new StatusFraments();
            case 2:return new CallFraments();
            default: return  new ChatsFragmetns();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0) {
            return "CHATS";
        }if(position == 1) {
            return "STATUS";
        }if(position == 2) {
            return "CALLS";
        }

        return  null;
    }
}
