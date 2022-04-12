package com.example.aucademics.cgpaFragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;

public class selectGpaRVAdapter extends RecyclerView.Adapter<selectGpaRVAdapter.selectGPAViewHolder>{

    ArrayList<gpaItem> dataList;

    public selectGpaRVAdapter(ArrayList<gpaItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public selectGPAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cgpa_subject_item_vh,parent,false);
        selectGpaRVAdapter.selectGPAViewHolder evh = new selectGpaRVAdapter.selectGPAViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull selectGPAViewHolder holder, int position) {
        gpaItem currentItem = dataList.get(position);
        holder.mSubjectName.setText(currentItem.getSubjectName().toString());
        holder.mSubjectCode.setText(currentItem.getSubjectCode().toString());
        holder.mCredits.setText(currentItem.getCredits().toString());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ArrayList<gpaItem> getDataList(){
        return new ArrayList<>(dataList);
    }



    class selectGPAViewHolder extends RecyclerView.ViewHolder{
        TextView mSubjectName;
        TextView mCredits;
        TextView mSubjectCode;
        AppCompatSpinner mSelectGpa;
        View itemView;
        public selectGPAViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            this.mSubjectName = itemView.findViewById(R.id.subject_rv_subject_name);
            this.mCredits = itemView.findViewById(R.id.subject_rv_credits);
            this.mSubjectCode = itemView.findViewById(R.id.subject_rv_subject_code);
            this.mSelectGpa = itemView.findViewById(R.id.subject_rv_select_gpa);

            mSelectGpa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String selectedGpa = mSelectGpa.getSelectedItem().toString();
                    Integer correspondingGP;
                    System.out.println("selected gpa = "+selectedGpa);
                    switch (selectedGpa) {
                        case "O":
                            correspondingGP = 10;
                            break;
                        case "A+":
                            correspondingGP = 9;
                            break;
                        case "A":
                            correspondingGP = 8;
                            break;
                        case "B+":
                            correspondingGP = 7;
                            break;
                        case "B":
                            correspondingGP = 6;
                            break;
                        default:
                            correspondingGP = 0;
                    }
                    dataList.get(getAdapterPosition()).setGradeAchieved(correspondingGP);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // do nothing
                }

            });

        }


    }

}
