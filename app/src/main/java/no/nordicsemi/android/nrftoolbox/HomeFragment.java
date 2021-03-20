package no.nordicsemi.android.nrftoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //zo layout inflaten
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button gr_button = (Button) view.findViewById(R.id.graph_today_button);
        Button st_button = (Button) view.findViewById(R.id.statistics_today_button);
        //NavigationView navigationView = view.findViewById(R.id.nav_view);
        gr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new GraphFragment();
                FragmentChangeListener fc = (FragmentChangeListener)getActivity();
                fc.replaceFragment(fr);
            }
        });
        st_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new NumbersFragment();
                FragmentChangeListener fc = (FragmentChangeListener)getActivity();
                fc.replaceFragment(fr);
            }
        });
    }
}
