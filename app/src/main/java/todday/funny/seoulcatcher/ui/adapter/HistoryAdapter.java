package todday.funny.seoulcatcher.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.databinding.HistoryEmptyBinding;
import todday.funny.seoulcatcher.databinding.MembershipBinding;
import todday.funny.seoulcatcher.databinding.ProfileEmptyBinding;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.model.Schedule;
import todday.funny.seoulcatcher.model.User;
import todday.funny.seoulcatcher.viewmodel.EmptyViewModel;

public class HistoryAdapter extends RecyclerView.Adapter {
    private int EMPTY_TYPE = 0;
    private int USER_TYPE = 1;
    private int CALL_TYPE = 2;
    private int SCHEDULE_TYPE = 3;


    private Context mContext;
    private List<Object> mItemList;

    public HistoryAdapter(Context context, List<Object> itemList) {
        mContext = context;
        mItemList = itemList;
    }


    @Override
    public int getItemViewType(int position) {
        Object item = mItemList.get(position);
        if (item instanceof User) {
            return USER_TYPE;
        } else if (item instanceof Call) {
            return CALL_TYPE;
        } else if (item instanceof Schedule) {
            return SCHEDULE_TYPE;
        } else {
            return EMPTY_TYPE;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == USER_TYPE) {

        } else if (viewType == CALL_TYPE) {

        } else if (viewType == SCHEDULE_TYPE) {

        }
        HistoryEmptyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_empty_history, viewGroup, false);
        return new HistoryEmptyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == USER_TYPE) {

        } else if (viewType == CALL_TYPE) {

        } else if (viewType == SCHEDULE_TYPE) {

        } else {
            HistoryEmptyViewHolder holder = (HistoryEmptyViewHolder) viewHolder;
            EmptyViewModel model = new EmptyViewModel(mContext, mContext.getString(R.string.empty_profile));
            holder.setViewModel(model);
        }

    }


    @Override
    public int getItemCount() {
        if (mItemList != null) {
            Log.e("test", String.valueOf(mItemList.size()));
            return mItemList.size();
        }
        return 0;
    }


    private class HistoryEmptyViewHolder extends RecyclerView.ViewHolder {
        private HistoryEmptyBinding binding;

        public HistoryEmptyViewHolder(@NonNull HistoryEmptyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(EmptyViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
