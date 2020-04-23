package pnj.uts.alvintandiardi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import pnj.uts.alvintandiardi.R;
import pnj.uts.alvintandiardi.data.entity.Berita;

public class RecyclerBeritaAdapter extends RecyclerView.Adapter<RecyclerBeritaAdapter.ViewHolder> {

    private List<Berita> listBerita;
    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public RecyclerBeritaAdapter(List<Berita> listBerita, Context context) {
        this.listBerita = listBerita;
        this.context = context;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Berita model = listBerita.get(position);

        holder.mTvJudul.setText(model.getTitle());
        holder.mTvDesc.setText(model.getDesc());

        Glide.with(context)
                .load(model.getImage())
                .into(holder.mImgBerita);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listBerita.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImgBerita;
        TextView mTvJudul, mTvDesc;
        View container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;

            mImgBerita = itemView.findViewById(R.id.img_berita);
            mTvJudul = itemView.findViewById(R.id.tv_berita_judul);
            mTvDesc = itemView.findViewById(R.id.tv_berita_deskripsi);

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Berita data);
    }

}
