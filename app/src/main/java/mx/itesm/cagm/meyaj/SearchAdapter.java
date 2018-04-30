package mx.itesm.cagm.meyaj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;

    ArrayList<String> nameServiceList;
    ArrayList<String> imgServiceList;

    class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView imgService;
        TextView tvService;

        public SearchViewHolder(View itemView) {
            super(itemView);

            imgService = itemView.findViewById(R.id.imgService);
            tvService = itemView.findViewById(R.id.tvService);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> nameServiceList, ArrayList<String> imgServiceList) {
        this.context = context;
        this.nameServiceList = nameServiceList;
        this.imgServiceList = imgServiceList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new SearchAdapter.SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.tvService.setText(nameServiceList.get(position));

    }

    @Override
    public int getItemCount() {
        return nameServiceList.size();
    }
}
