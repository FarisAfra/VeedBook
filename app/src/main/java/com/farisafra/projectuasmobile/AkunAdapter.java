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

public class AkunAdapter extends RecyclerView.Adapter<AkunAdapter.AkunViewHolder> {

    private ArrayList<Akun> listAkun = new ArrayList<>();
    private Activity activity;
    private DatabaseAkun DatabaseAkun;

    public AkunAdapter(Activity activity) {
        this.activity = activity;
        DatabaseAkun = new DatabaseAkun(activity);
    }

    public ArrayList<Akun> getListAkun() {
        return listAkun;
    }

    public void setListAkun(ArrayList<Akun> listNotes) {
        if (listNotes.size() > 0) {
            this.listAkun.clear();
        }
        this.listAkun.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AkunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_akun, parent, false);
        return new AkunViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AkunViewHolder holder, int position) {
        holder.tvNama.setText(listAkun.get(position).getNama());
        holder.tvUsername.setText(listAkun.get(position).getUsername());
        holder.tvPassword.setText(listAkun.get(position).getPassword());
        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateAkun.class);
            intent.putExtra("user", (Serializable) listAkun.get(position));
            activity.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setPositiveButton("YA", (dialog, which) -> {
                DatabaseAkun.deleteUser(listAkun.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, DataAkun.class);
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
        return listAkun.size();
    }

    public class AkunViewHolder extends RecyclerView.ViewHolder {

        final TextView tvNama, tvUsername, tvPassword;
        final Button btnEdit, btnDelete;

        public AkunViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvUsername = itemView.findViewById(R.id.tv_item_username);
            tvPassword = itemView.findViewById(R.id.tv_item_password);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }

    }
}


