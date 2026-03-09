package com.example.ex5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragB extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static int cnt=0;

    public FragB() {}

    public static FragB newInstance(boolean up) {
        FragB fragment = new FragB();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, up);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            boolean up = getArguments().getBoolean(ARG_PARAM1);
//            if (up)
//            {
//                cnt++;
//            }
//            else
//            {
//                cnt--;
//            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (!isVisible())
        {
            return;
        }

        inflater.inflate(R.menu.menu_b, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.reset)
        {
            cnt = 0;
            ((TextView)this.getView().findViewById(R.id.tvCounter)).setText(String.valueOf(cnt));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((TextView)view.findViewById(R.id.tvCounter)).setText(String.valueOf(cnt));
        super.onViewCreated(view, savedInstanceState);
    }

    public void updateCounter(boolean up)
    {
        if (up)
        {
            cnt++;
        }
        else
        {
            cnt--;
        }
        ((TextView)this.getView().findViewById(R.id.tvCounter)).setText(String.valueOf(cnt));
    }


}