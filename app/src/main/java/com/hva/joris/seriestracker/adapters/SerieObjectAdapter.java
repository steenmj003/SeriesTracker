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
import com.hva.joris.seriestracker.models.SerieObject;

import java.util.List;

public class SerieObjectAdapter extends RecyclerView.Adapter<SerieObjectAdapter.SerieObjectViewHolder>{
    private SerieClickListener serieClickListener;
    private List<SerieObject> serieObjects;
    private final Resources resources;

    public SerieObjectAdapter(List<SerieObject> serieObjects, Resources resources, SerieClickListener serieClickListener) {
        this.serieObjects = serieObjects;
        this.resources = resources;
        this.serieClickListener = serieClickListener;
    }

    @NonNull
    @Override
    public SerieObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.serie_card, viewGroup,false);

        return new SerieObjectViewHolder(view, serieClickListener);
    }

    public class SerieObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleView;
        public TextView platformView;
        public TextView dateView;
        public SerieObjectAdapter.SerieClickListener serieClickListener;

        public SerieObjectViewHolder(View itemView, SerieObjectAdapter.SerieClickListener serieClickListener){
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
    public void onBindViewHolder(@NonNull SerieObjectViewHolder viewHolder, int i) {
        SerieObject serieObject = serieObjects.get(i);

        viewHolder.titleView.setText(serieObject.getTitle());
        viewHolder.platformView.setText(serieObject.getNotes());
        viewHolder.dateView.setText(serieObject.getLastModified());
    }

    @Override
    public int getItemCount() {
        return serieObjects.size();
    }

    public void swapList(List<SerieObject> newList){
        serieObjects = newList;
        if(newList != null){
            this.notifyDataSetChanged();
        }
    }

    public interface SerieClickListener {
        public void serieOnClick(int i);
    }
}
