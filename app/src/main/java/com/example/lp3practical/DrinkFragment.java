package com.example.lp3practical;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class DrinkFragment extends Fragment {

    RadioGroup group;
    RadioButton rb;

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_drink, container, false);
        group = v.findViewById(R.id.group);
        lv = v.findViewById(R.id.lvDrink);

        int selected = group.getCheckedRadioButtonId();
        rb = v.findViewById(selected);
        String category = rb.getText().toString().toLowerCase();

        DBHelper db = new DBHelper(getActivity());
        al = db.getItemsOfCategory(category);

        aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, al);

        lv.setAdapter(aa);
        db.close();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selected = group.getCheckedRadioButtonId();
                rb = v.findViewById(selected);
                String category = rb.getText().toString().toLowerCase();

                DBHelper db = new DBHelper(getActivity());
                al = db.getItemsOfCategory(category);

                aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, al);

                lv.setAdapter(aa);
                db.close();
            }
        });

        return v;
    }

}