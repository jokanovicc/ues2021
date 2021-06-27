package com.example.taverna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taverna.R;
import com.example.taverna.model.AkcijaPrikaz;

import java.util.List;

public class AkcijePrikazAdapter extends RecyclerView.Adapter<AkcijePrikazAdapter.ViewHolder> {
    private List<AkcijaPrikaz> mData;
    private Context context;
    private Button button;

    public static final String MyPres = "MyPre1";
    public static final String Username = "usernameKey";
    public static final Integer ID = 2;


    public AkcijePrikazAdapter(List<AkcijaPrikaz> data, Context context) {
        this.mData = data;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView artikli;
        TextView popust;
        TextView doKad;
        TextView odKad;


        ViewHolder(View itemView) {
            super(itemView);
            artikli = itemView.findViewById(R.id.prikazAkcijeArtikli);
            popust = itemView.findViewById(R.id.prikazPopust);
            doKad = itemView.findViewById(R.id.prikazDoKad);
            odKad = itemView.findViewById(R.id.prikazOdKad);


        }
    }

    @Override
    public AkcijePrikazAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.akcije_row, parent, false);
        return new AkcijePrikazAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AkcijePrikazAdapter.ViewHolder holder, int position) {
        AkcijaPrikaz akcijaPrikaz = mData.get(position);


        holder.artikli.setText(akcijaPrikaz.getArtikli());
        holder.popust.setText("Попуст: "+akcijaPrikaz.getProcenat());
        holder.doKad.setText("До: " + akcijaPrikaz.getDoKad());
        holder.odKad.setText("Од: " + akcijaPrikaz.getOdKad());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
