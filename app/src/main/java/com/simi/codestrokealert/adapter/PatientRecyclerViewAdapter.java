package com.simi.codestrokealert.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.activity.clinicians.RecyclerViewItemClickListener;
import com.simi.codestrokealert.model.Patient;

import java.util.List;


public class PatientRecyclerViewAdapter extends RecyclerView.Adapter<PatientRecyclerViewAdapter.ViewHolder> {

    private List<Patient> patientList;
    private Context context;
    private RecyclerViewItemClickListener itemClickListener;

    public PatientRecyclerViewAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_card, parent, false);

        PatientRecyclerViewAdapter.ViewHolder viewHolder = new PatientRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.name.setText(patient.getName());
        holder.gender.setText(patient.getGender());
        holder.age.setText("Age: " + String.valueOf(patient.getAge()));
        holder.eta.setText(patient.getEta());
    }


    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public void setClickListener(RecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, age, gender, eta;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            gender = (TextView) itemView.findViewById(R.id.gender);
            age = (TextView) itemView.findViewById(R.id.age);
            eta = (TextView) itemView.findViewById(R.id.eta);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onClick(view, getAdapterPosition());

        }
    }
}
