package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import todday.funny.seoulcatcher.model.Schedule;
import todday.funny.seoulcatcher.util.Keys;

public class ScheduleDialogViewModel extends BaseViewModel{
    public String locationname = "광나루";
    public String textDate;


    public ScheduleDialogViewModel(Context context) {
        super(context);
    }

    public void inputScheduleDateBase(final String date) {

        String uid = FirebaseAuth.getInstance().getUid();
        Log.e("aaaa","%%%%%%"+date+"    "+uid);
        FirebaseFirestore.getInstance().collection(Keys.USERS).document(uid).collection(Keys.SCHEDULES).document().set(new Schedule(date,locationname))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("데이터 베이스 삽입 성공!", date);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("데이터 베이스 삽입 실패", e.toString());
            }
        });
    }
}
