package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;

import java.util.List;

import todday.funny.seoulcatcher.interactor.OnLoadHistoryListListener;
import todday.funny.seoulcatcher.model.History;
import todday.funny.seoulcatcher.util.Keys;

public class HistoryViewModel extends BaseViewModel {
    public ObservableArrayList<Object> mHistoryList = new ObservableArrayList<>();
    private String mUserId;

    public HistoryViewModel(Context context, String userId) {
        super(context);
        mUserId = userId;
    }

    public void getUserHistory(String userId) {
        mServerDataController.getUserHistory(userId, new OnLoadHistoryListListener() {
            @Override
            public void onComplete(List<History> historyList) {
                mHistoryList.clear();
                if (historyList != null && historyList.size() > 0) {
                    mHistoryList.addAll(historyList);
                } else {
                    mHistoryList.add(Keys.EMPTY);
                }

            }
        });
    }
}
