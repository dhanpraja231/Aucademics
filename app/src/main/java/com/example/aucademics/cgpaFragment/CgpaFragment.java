package com.example.aucademics.cgpaFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.bunkFragment.bunkRVAdapter;
import com.example.aucademics.databases.CGPA_DB.BigBadCGPATableDBHelper;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CgpaFragment extends Fragment {
    RecyclerView rvCgpaList;
    TextView mFinalGpa;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.cgpa_fragment,container,false);
        rvCgpaList = root.findViewById(R.id.rv_cgpa_list);
        mFinalGpa = root.findViewById(R.id.cgpa_final_tv);
        ArrayList<SemesterItem> tileData = new ArrayList<>();
        BigBadCGPATableDBHelper db = new BigBadCGPATableDBHelper(this.getContext());

        Integer semestersAccounted=0;
        Double cgpaCumulative=0.0;
        for(int i =1;i<=8;i++){
            Double cgpa = db.calculateGpaOfSemester(i);
            if(cgpa!=null){
            cgpa = round(cgpa,2);
            cgpaCumulative +=cgpa;
            semestersAccounted++;}
            tileData.add(new SemesterItem(i,cgpa));
        }
        db.close();
        if(semestersAccounted!=0){
            double result = (double)cgpaCumulative/(double)semestersAccounted;
            result = round(result,2);
            mFinalGpa.setText(String.valueOf(result));
        }

        System.out.println("tile Data: "+tileData);
        SemesterTileRVAdapter rvAdapter = new SemesterTileRVAdapter(tileData,this.getContext());
        rvCgpaList = root.findViewById(R.id.rv_cgpa_list);
        rvCgpaList.setAdapter(rvAdapter);
        rvCgpaList.setLayoutManager(new GridLayoutManager(this.getContext(),2,GridLayoutManager.VERTICAL,false));
        return root;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

