package com.example.aucademics.cgpaFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.bunkFragment.bunkRVAdapter;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;

import java.util.ArrayList;

public class CgpaFragment extends Fragment {
    RecyclerView rvCgpaList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.cgpa_fragment,container,false);
        rvCgpaList = root.findViewById(R.id.rv_cgpa_list);
        ArrayList<SemesterItem> tileData = new ArrayList<>();
        for(int i =1;i<=8;i++){
            tileData.add(new SemesterItem(i,null));
            //query the semester-gpa table to get gpas
        }
        System.out.println("tile Data: "+tileData);
        SemesterTileRVAdapter rvAdapter = new SemesterTileRVAdapter(tileData,this.getContext());
        rvCgpaList = root.findViewById(R.id.rv_cgpa_list);
        rvCgpaList.setAdapter(rvAdapter);
        rvCgpaList.setLayoutManager(new GridLayoutManager(this.getContext(),2,GridLayoutManager.VERTICAL,false));
        return root;
    }
}

