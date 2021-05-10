package no.nordicsemi.android.nrftoolbox;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

public class NumbersFragment extends Fragment {
    //reference to buttons on layout
    Button btn_1;
    EditText et_data, et_name, et_act;
    RecyclerView recycle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //zo layout inflaten
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);

        btn_1 = view.findViewById(R.id.btn_1);
        //et_date = view.findViewById(R.id.et_date);
        et_data = view.findViewById(R.id.et_data);
        et_act = view.findViewById(R.id.et_act);
        et_name = view.findViewById(R.id.et_name);

        //listeners
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseModel databasemodel;
                try{
                    databasemodel = new DatabaseModel(-1, et_name.getText().toString(), et_act.getText().toString(),et_data.getText().toString());
                    Toast.makeText(view.getContext(), databasemodel.toString(),Toast.LENGTH_SHORT).show();}
                catch (Exception e){
                    Toast.makeText(view.getContext(), "foute input",Toast.LENGTH_SHORT).show();
                    databasemodel =  new DatabaseModel(-1, "name0", "act0", "data0");
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                boolean success = databaseHelper.addOne(databasemodel);
                Toast.makeText(view.getContext(), "success = " + success,Toast.LENGTH_SHORT).show();
            }
        } );




        return view;
    }
}
