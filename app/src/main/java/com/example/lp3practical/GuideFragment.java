package com.example.lp3practical;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class GuideFragment extends Fragment {

    ZoomageView zv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_guide, container, false);

        zv = v.findViewById(R.id.zv);

        String imageURL = "https://cdn-blog.seedly.sg/wp-content/uploads/2018/10/03105442/coffee-guide-in-article.png";
        Picasso.with(getActivity()).load(imageURL).into(zv);

        return v;
    }
}