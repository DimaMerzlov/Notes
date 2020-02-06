package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    public Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_list_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title=notes.get(position).getTitle();
        Long id=notes.get(position).getID();
        holder.nTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nTitle,nDetailes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nDetailes=itemView.findViewById(R.id.noteDitail);
            nTitle=itemView.findViewById(R.id.noteTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(),Details_Note.class);
                    i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    Toast.makeText(v.getContext(),"item clicked"+notes.get(getAdapterPosition()).getID(),Toast.LENGTH_SHORT).show();

                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
