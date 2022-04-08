package com.example.aucademics.bunkFragment;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aucademics.R;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bunkRVAdapter extends RecyclerView.Adapter<bunkRVAdapter.bunkViewHolder> {

    ArrayList<BunkItem> dataList;

    public bunkRVAdapter(ArrayList<BunkItem> dataList) {
        this.dataList = dataList;
    }

    class bunkViewHolder extends RecyclerView.ViewHolder {
        TextView mSubjectName;
        TextView mBunksLeft;
        TextView mAttendance;
        TextView mBunksDone;
        AppCompatButton bEdit;
        AppCompatButton bIncrement;
        View itemView;
        public bunkViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            mSubjectName = itemView.findViewById(R.id.bunk_rv_subject_name);
            mBunksLeft = itemView.findViewById(R.id.bunk_rv_bunks_left);
            mBunksDone = itemView.findViewById(R.id.bunk_rv_bunks_done);
            mAttendance = itemView.findViewById(R.id.bunk_rv_attendance);
            bEdit = itemView.findViewById(R.id.bunk_holder_edit_button);
            bIncrement = itemView.findViewById(R.id.bunk_holder_increment_button);

            itemView.findViewById(R.id.bunk_holder_increment_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: increment button
                }
            });


            itemView.findViewById(R.id.bunk_holder_edit_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO:pop alert
                }
            });

        }

    }

    @NonNull
    @Override
    public bunkRVAdapter.bunkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bunk_rv_view_holder,parent,false);
        bunkViewHolder evh = new bunkViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull bunkRVAdapter.bunkViewHolder holder, int position) {
        BunkItem currentItem = dataList.get(position);
        holder.mAttendance.setText(String.valueOf( currentItem.getAttendancePercent()));
        holder.mBunksDone.setText(String.valueOf(currentItem.getBunkedHours()));
        holder.mBunksLeft.setText(String.valueOf(currentItem.getBunkHoursLeft()));
        holder.mSubjectName.setText(currentItem.getSubject_name());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
