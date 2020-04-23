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

public class RecyclerAlumniAdapter extends RecyclerView.Adapter<RecyclerAlumniAdapter.ViewHolder> {

    private List<Alumni> listAlumni;
    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public RecyclerAlumniAdapter(List<Alumni> listAlumni, Context context) {
        this.listAlumni = listAlumni;
        this.context = context;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Alumni model = listAlumni.get(position);

        holder.mTvNim.setText("" + model.getNim());
        holder.mTvNama.setText("" + model.getNama());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listAlumni.get(holder.getAdapterPosition()));
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

    public interface OnItemClickCallback {
        void onItemClicked(Alumni data);
    }

}
