package com.example.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DoctorSearchAdapter extends FirebaseRecyclerAdapter<TempDoctor, DoctorSearchAdapter.doctorsViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DoctorSearchAdapter(@NonNull FirebaseRecyclerOptions<TempDoctor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull doctorsViewholder holder, int position, @NonNull TempDoctor model) {
        Doctor doctor = model.getInfo();
        holder.name.setText(doctor.getName());
        holder.spcl.setText(doctor.getSpecialisation());
        holder.doctorUserID.setText(doctor.getUID());
    }

    @NonNull
    @Override
    public doctorsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_list, parent, false);
        return new com.example.android.DoctorSearchAdapter.doctorsViewholder(view);
    }

    class doctorsViewholder extends RecyclerView.ViewHolder {
        TextView name, spcl, doctorUserID;
        public doctorsViewholder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            spcl = itemView.findViewById(R.id.spcl);
            doctorUserID = itemView.findViewById(R.id.doctorUserID);
        }
    }

}
