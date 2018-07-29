package co.proficiency.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.proficiency.app.R;
import co.proficiency.app.model.DataList;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataViewHolder> {

    private Context mContext;
    private ArrayList<DataList> dataItemList;

    public DataListAdapter(Context context, ArrayList<DataList> data) {
        this.mContext = context;
        this.dataItemList = data;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, final int position) {
        holder.setDataItem(dataItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public DataList getCategory(int position) {
        return dataItemList.get(position);
    }

    public ArrayList<DataList> getItemDetails() {
        return dataItemList;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        public TextView txtTitle;
        @BindView(R.id.txtDescription)
        public TextView txtDescription;
        @BindView(R.id.imageContent)
        public ImageView imgContent;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDataItem(DataList dataList) {
            txtTitle.setText(dataList.getTitle());
            txtDescription.setText(dataList.getDescription());
            Glide.with(mContext)
                    .load(dataList.getImageHref())
                    .placeholder(R.drawable.icon_noimage)
                    .fitCenter()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgContent);
        }
    }
}
