package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import todday.funny.seoulcatcher.interactor.OnEduDateListener;
import todday.funny.seoulcatcher.model.EduDate;
import todday.funny.seoulcatcher.model.Schedule;

public class ScheduleViewModel extends BaseViewModel {
    final ArrayList<EduDate> eduDates = new ArrayList<>();
    final ArrayList<String> listKeys = new ArrayList<>();

    public ObservableField<Schedule> mSchedule = new ObservableField<>();

    public String userUid = FirebaseAuth.getInstance().getUid();

    public ScheduleViewModel(Context context) {
        super(context);
        //setEducationDate();
    }

    public ScheduleViewModel(Context context, Schedule schedule) {
        super(context);
        mSchedule.set(schedule);

    }

    public void setEducationDate(final OnEduDateListener educationDate){
        if(educationDate != null) {
            FirebaseFirestore.getInstance().collection("educationDate").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (queryDocumentSnapshots != null) {
                        if (queryDocumentSnapshots.getDocuments() == null) {
                            Log.e("getDocuments", "aaaaaaaaa");

                        } else {
                            List<DocumentChange> query = queryDocumentSnapshots.getDocumentChanges();
                            for (int i = 0; i < query.size(); i++) {
                                EduDate eduDate = (query.get(i).getDocument()).toObject(EduDate.class);
                                switch (query.get(i).getType()) {
                                    case ADDED:
                                        eduDates.add(eduDate);
                                        listKeys.add(query.get(i).getDocument().getId());
                                        educationDate.onComplete(eduDates);
                                        break;
                                    case REMOVED:
                                        //deleteItem(query.get(i).getDocument().getId());
                                        break;
                                }
                            }
                        }
                    } else {
                        Log.e("queryDocumentSnapshots", "aaaaaaaaa");

                    }
                }
            });
        }
    }

    public ArrayList getEventDay() {
        final ArrayList<String> list = new ArrayList<>();
        /*list.add("2018,9,18");
        list.add("2018,10,12");
        list.add("2018,10,19");
        list.add("2018,10,24");*/

        return eduDates;
    }
}
