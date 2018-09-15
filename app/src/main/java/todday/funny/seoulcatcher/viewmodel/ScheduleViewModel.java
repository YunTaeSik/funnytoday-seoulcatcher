package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import todday.funny.seoulcatcher.model.EduDate;
import todday.funny.seoulcatcher.model.Schedule;
import todday.funny.seoulcatcher.ui.dialog.ScheduleDialog;

public class ScheduleViewModel extends BaseViewModel {
    final ArrayList<EduDate> eduDates = new ArrayList<>();
    final ArrayList<String> listKeys = new ArrayList<>();

    public ObservableField<Schedule> mSchedule = new ObservableField<>();
    //public ObservableField<Boolean> isSchedule = new ObservableField<>();

    public String userUid = FirebaseAuth.getInstance().getUid();

    public boolean isSchedule = true;

    public ScheduleViewModel(Context context) {
        super(context);
        //setEducationDate();
    }

    public ScheduleViewModel(Context context, Schedule schedule) {
        super(context);
        mSchedule.set(schedule);
        //isSchedule.set();
    }

    public void openScheduleInfo(String date){
        ScheduleDialog dialog = ScheduleDialog.newInstance(date);
        addFragmentDialog(dialog, android.R.transition.slide_top);
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
