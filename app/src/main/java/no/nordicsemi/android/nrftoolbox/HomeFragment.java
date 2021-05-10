package no.nordicsemi.android.nrftoolbox;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button start_button = (Button) view.findViewById(R.id.start);
        Button stop_button = (Button) view.findViewById(R.id.stop);
        Button copy_button = (Button) view.findViewById(R.id.copy);
        EditText data_text = (EditText) view.findViewById(R.id.data_plaintext);

        //NavigationView navigationView = view.findViewById(R.id.nav_view);
        copy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", data_text.getText().toString());
                clipboard.setPrimaryClip(clip);
                clip.getDescription();
                Toast.makeText(HomeFragment.this.getContext(), "copied.", Toast.LENGTH_SHORT).show();
            }
        });
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementeren start copy data van uart
            }
        });

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementeren stop copy data van uart
            }
        });





        //Lisa backbone
        st_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new NumbersFragment();
                FragmentChangeListener fc = (FragmentChangeListener)getActivity();
                fc.replaceFragment(fr);
            }
        });
        gr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), GraphActivity.class);
                startActivity(i);
                /*Fragment fr = new GraphFragment();
                FragmentChangeListener fc = (FragmentChangeListener)getActivity();
                fc.replaceFragment(fr);*/
            }
        });
    }
}
