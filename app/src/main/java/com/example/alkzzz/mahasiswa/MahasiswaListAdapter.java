package com.example.alkzzz.mahasiswa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<Mahasiswa> listMahasiswa;
    private final OnItemClickListener listener;

    MahasiswaListAdapter(Context context, OnItemClickListener itemClickListener) {
        layoutInflater = LayoutInflater.from(context);
        listener = itemClickListener;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mahasiswa = listMahasiswa.get(position);
        holder.textView.setText(mahasiswa.getNama());
    }

    void setListMahasiswa(List<Mahasiswa> listMahasiswa){
        this.listMahasiswa = listMahasiswa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listMahasiswa != null)
            return listMahasiswa.size();
        else return 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_nama);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTextClick(v, listMahasiswa.get(getAdapterPosition()));
                }
            });
        }
    }

    public Mahasiswa getMahasiswaAtPosition (int position) {
        return listMahasiswa.get(position);
    }

    public interface OnItemClickListener {
        void onTextClick(View v, Mahasiswa mahasiswa);
    }
}
