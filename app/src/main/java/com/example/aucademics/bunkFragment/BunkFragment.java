package com.example.aucademics.bunkFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;

import java.util.ArrayList;

public class BunkFragment extends Fragment {
    RecyclerView rvBunkList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.bunk_fragment, container, false);
        rvBunkList = root.findViewById(R.id.rv_bunk_list);
        BunkManagerDBHelper db = new BunkManagerDBHelper(this.getContext(),"userBunkDB",null,1);
        ArrayList<BunkItem> data = db.getAllSubjects();
        //System.out.println("Datalist ="+ data);
        bunkRVAdapter rvListAdapter = new bunkRVAdapter(data);
        rvBunkList.setAdapter(rvListAdapter);
        rvBunkList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }
}
