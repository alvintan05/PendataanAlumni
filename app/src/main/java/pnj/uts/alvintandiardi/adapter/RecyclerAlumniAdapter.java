package pnj.uts.alvintandiardi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.data.entity.Alumni;
import pnj.uts.alvintandiardi.data.entity.Berita;

public class RecyclerAlumniAdapter extends RecyclerView.Adapter<RecyclerAlumniAdapter.ViewHolder> {

    private List<Alumni> listAlumni;
    private Context context;
    private ItemClickListener itemClickListener;

    public RecyclerAlumniAdapter(List<Alumni> listAlumni, Context context, ItemClickListener itemClickListener) {
        this.listAlumni = listAlumni;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Alumni model = listAlumni.get(position);

        holder.mTvNim.setText("" + model.getNim());
        holder.mTvNama.setText("" + model.getNama());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAlumni.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvNim, mTvNama;
        View container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;

            mTvNama = itemView.findViewById(R.id.tv_list_nama);
            mTvNim = itemView.findViewById(R.id.tv_list_nim);

        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
