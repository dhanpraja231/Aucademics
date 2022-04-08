package com.example.aucademics.cgpaFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;

public class CgpaFragment extends Fragment {
    RecyclerView rvBunkList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.cgpa_fragment,container,false);
        return root;
    }
}

