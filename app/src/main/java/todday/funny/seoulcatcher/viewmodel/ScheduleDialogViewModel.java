package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
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

public class ScheduleDialogViewModel extends BaseViewModel{
    public String locationname = "광나루";
    public String textDate;


    public ScheduleDialogViewModel(Context context) {
        super(context);
    }

    public void inputScheduleDateBase(final String date) {

        final String uid = FirebaseAuth.getInstance().getUid();
        Log.e("aaaa","%%%%%%"+date+"    "+uid);
        FirebaseFirestore.getInstance().collection(Keys.USERS).document(uid).collection(Keys.SCHEDULES).add(new Schedule(date,locationname))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference reference) {
                        Log.e("데이터 베이스 삽입 성공!", date);
                        String key = reference.getId();
                        Schedule schedule = new Schedule(key,date,locationname);
                        FirebaseFirestore.getInstance().collection(Keys.USERS).document(uid).collection(Keys.SCHEDULES).document(key).set(schedule);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("데이터 베이스 삽입 실패", e.toString());
            }
        });
    }
}

