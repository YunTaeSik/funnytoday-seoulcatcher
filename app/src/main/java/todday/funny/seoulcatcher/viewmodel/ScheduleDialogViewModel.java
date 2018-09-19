package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import todday.funny.seoulcatcher.model.Schedule;
import todday.funny.seoulcatcher.util.Keys;

public class ScheduleDialogViewModel extends BaseViewModel {
    public String locationname = "광나루";


    public ScheduleDialogViewModel(Context context) {
        super(context);
    }

    public void inputScheduleDateBase(final String date) {
        mServerDataController.saveUserSchedule(new Schedule(date, locationname));
    }
}

