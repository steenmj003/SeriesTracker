package com.hva.joris.seriestracker.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hva.joris.seriestracker.R;
import com.hva.joris.seriestracker.models.Serie;

import java.util.List;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.SerieViewHolder>{
    private SerieClickListener serieClickListener;
    private List<Serie> series;
    private final Resources resources;

    public SerieAdapter(List<Serie> series, Resources resources, SerieClickListener serieClickListener) {
        this.series = series;
        this.resources = resources;
        this.serieClickListener = serieClickListener;
    }

    @NonNull
    @Override
    public SerieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.serie_card, viewGroup,false);

        return new SerieViewHolder(view, serieClickListener);
    }

    public class SerieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleView;
        public TextView platformView;
        public TextView dateView;
        public SerieAdapter.SerieClickListener serieClickListener;

        public SerieViewHolder(View itemView, SerieAdapter.SerieClickListener serieClickListener){
            super(itemView);
            itemView.setOnClickListener(this);

            titleView = itemView.findViewById(R.id.titleView);
            platformView = itemView.findViewById(R.id.platformView);
            dateView = itemView.findViewById(R.id.dateView);
            this.serieClickListener = serieClickListener;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            serieClickListener.serieOnClick(position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SerieViewHolder viewHolder, int i) {
        Serie serieObject = series.get(i);

        viewHolder.titleView.setText(serieObject.getName());
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public void swapList(List<Serie> newList){
        series = newList;
        if(newList != null){
            this.notifyDataSetChanged();
        }
    }

    public interface SerieClickListener {
        public void serieOnClick(int i);
    }
}
