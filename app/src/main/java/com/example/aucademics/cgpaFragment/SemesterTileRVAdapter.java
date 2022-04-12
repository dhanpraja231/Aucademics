package com.example.aucademics.cgpaFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.bunkFragment.bunkRVAdapter;
import com.example.aucademics.databases.CGPA_DB.BigBadCGPATableDBHelper;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;
import com.example.aucademics.homePage.EnterDetails;
import com.example.aucademics.homePage.bunkNcgpa;

import java.util.ArrayList;

public class SemesterTileRVAdapter extends RecyclerView.Adapter<SemesterTileRVAdapter.SemesterTileViewHolder>{
    ArrayList<SemesterItem> dataList;
    Context context;
    @NonNull
    @Override
    public SemesterTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_tile_rv_viewholder,parent,false);
        SemesterTileRVAdapter.SemesterTileViewHolder evh = new SemesterTileRVAdapter.SemesterTileViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterTileViewHolder holder, int position) {
        SemesterItem currentItem = dataList.get(position);
        System.out.println(currentItem);
        holder.mSemesterNo.setText(new StringBuilder().append("Semester ").append(currentItem.getSemester()).toString());
        if(currentItem.getCgpa()!=null){
        holder.mCgpa.setText(String.valueOf(currentItem.getCgpa()));
        }
        else{
            holder.mCgpa.setText("--");
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Context getContext(){
        return context;
    }



    public SemesterTileRVAdapter(ArrayList<SemesterItem> dataList,Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    class SemesterTileViewHolder extends RecyclerView.ViewHolder{
        TextView mSemesterNo;
        TextView mCgpa;
        View itemView;
        public SemesterTileViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            mSemesterNo = itemView.findViewById(R.id.semester_number_vh);
            mCgpa = itemView.findViewById(R.id.semester_gpa_vh);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
                    SharedPreferences.Editor spEditor = sp.edit();
                    String semesterText =  mSemesterNo.getText().toString();
                    Integer semesterNo = Integer.parseInt(semesterText.substring(semesterText.length()-1));
                    System.out.println("put semester no "+semesterNo+" in shared preference");
                    spEditor.putInt("semesterClicked" ,semesterNo);
                    spEditor.commit();


                    Intent intent = new Intent (view.getContext(), SelectGpaActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

    }



}
