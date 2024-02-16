package com.farisafra.projectuasmobile;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private ArrayList<Produk> listProduk = new ArrayList<>();
    private Activity activity;
    private DatabaseProduk DatabaseProduk;


    public ProdukAdapter(Activity activity) {
        this.activity = activity;
        DatabaseProduk = new DatabaseProduk(activity);
    }

    public ArrayList<Produk> getListProduk() {

        return listProduk;
    }

    public void setListProduk(ArrayList<Produk> listNotes) {
        if (listNotes.size() > 0) {
            this.listProduk.clear();
        }
        this.listProduk.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {
        holder.tvJudul.setText(listProduk.get(position).getJudul());
        holder.tvPenulis.setText(listProduk.get(position).getPenulis());
        holder.tvdeskripsi.setText(listProduk.get(position).getDeskripsi());
        holder.tvspek.setText(listProduk.get(position).getSpek());
        holder.tvkategori.setText(listProduk.get(position).getKategori());
        holder.tvHarga.setText(listProduk.get(position).getHarga());
        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateProduk.class);
            intent.putExtra("user", (Serializable) listProduk.get(position));
            activity.startActivity(intent);
        });


        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setPositiveButton("YA", (dialog, which) -> {
                DatabaseProduk.deleteUser(listProduk.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, DataProduk.class);
                activity.startActivity(myIntent);
                activity.finish();
            });

            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {

        final TextView tvJudul, tvPenulis,tvdeskripsi, tvHarga, tvspek, tvkategori;

        final Button btnEdit,btnDelete;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.Judul);
            tvPenulis = itemView.findViewById(R.id.Penulis);
            tvHarga = itemView.findViewById(R.id.Harga);
            tvdeskripsi = itemView.findViewById(R.id.Deskripsi);
            tvspek = itemView.findViewById(R.id.Spesifikasi);
            tvkategori = itemView.findViewById(R.id.Kategori);
            btnEdit = itemView.findViewById(R.id.Detail);
            btnDelete = itemView.findViewById(R.id.delete);
        }
    }


}
