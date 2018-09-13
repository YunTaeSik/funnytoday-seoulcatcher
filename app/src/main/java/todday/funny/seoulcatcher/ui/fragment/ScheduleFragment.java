package todday.funny.seoulcatcher.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import todday.funny.seoulcatcher.BaseActivity;
import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.databinding.ScheduleBinding;
import todday.funny.seoulcatcher.interactor.OnEduDateListener;
import todday.funny.seoulcatcher.interactor.OnLoadScheduleListListener;
import todday.funny.seoulcatcher.model.EduDate;
import todday.funny.seoulcatcher.model.Schedule;
import todday.funny.seoulcatcher.server.ServerDataController;
import todday.funny.seoulcatcher.ui.adapter.ScheduleAdapter;
import todday.funny.seoulcatcher.ui.dialog.ScheduleDialog;
import todday.funny.seoulcatcher.util.CommonDecorator;
import todday.funny.seoulcatcher.util.EventDecorator;
import todday.funny.seoulcatcher.util.SaturdayDecorator;
import todday.funny.seoulcatcher.util.SundayDecorator;
import todday.funny.seoulcatcher.util.TodayDecorator;
import todday.funny.seoulcatcher.viewmodel.ScheduleViewModel;


public class ScheduleFragment extends Fragment {
    private ScheduleBinding binding;
    private ScheduleViewModel model;
    private Context context;
    private MaterialCalendarView calendarView = null;
    private SundayDecorator sundayDecorator = new SundayDecorator();
    private SaturdayDecorator saturdayDecorator = new SaturdayDecorator();
    private CommonDecorator commonDecorator = new CommonDecorator();
    private TodayDecorator todayDecorator = new TodayDecorator();
    private TodayDecorator setModel = new TodayDecorator();

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;

    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<EduDate> eduDates ;

    private ServerDataController serverDataController = ServerDataController.getInstance(getContext());

    private TextView textView;

    public static ScheduleFragment newInstance() {
        Bundle args = new Bundle();
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            model = ((BaseActivity) getActivity()).getScheduleViewModel();
            binding.setModel(model);
        }

        View view = binding.getRoot();
        context = container.getContext();

        calendarView = view.findViewById(R.id.calendarView);
        recyclerView = view.findViewById(R.id.scheduleFragment_recyclerView);
        textView = view.findViewById(R.id.scheduleFragment_textView);

        adapter = new ScheduleAdapter(getContext(), schedules);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        getScheduleDataBase();
        setEducationDate();


        return view;
    }

    private void setEducationDate(){
        serverDataController.getEducationDate(new OnEduDateListener() {
            @Override
            public void onComplete(ArrayList<EduDate> list) {
                eduDates = list;
                settingCalendar();
            }
        });
    }

    private void getScheduleDataBase() {
        String uid =FirebaseAuth.getInstance().getUid();
        serverDataController.getUserSchedule(uid, new OnLoadScheduleListListener() {
            @Override
            public void onComplete(List<Schedule> scheduleList) {
                schedules = (ArrayList<Schedule>) scheduleList;
            }
        });
    }

    private void deleteItem(String deleteKey) {
        for (int i = 0; i < scheduleModelsKey.size(); i++) {
            if (deleteKey.equals(scheduleModelsKey.get(i))) {
                scheduleModelsKey.remove(i);
            }
        }
    }



    private void settingCalendar() {



        for(int i=0;i<eduDates.size();i++){
            Log.e("!@!@",eduDates.get(i).getDate());
        }

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String year = String.valueOf(date.getYear());
                String month = String.valueOf(date.getMonth() + 1);
                String dayy = String.valueOf(date.getDay());
                final String datee = year + "-" + month + "-" + dayy;
                for (int i = 0; i < eduDates.size(); i++) {
                    if ((eduDates.get(i).getDate()).equals(datee)) {

                        Bundle bundle = new Bundle();
                        bundle.putString("date", datee);

                        ScheduleDialog scheduleDialog = ScheduleDialog.newInstance(datee);
                        scheduleDialog.show(getFragmentManager(), "scheduleDialog");


                    }
                }
            }
        });
        if(eduDates.size() != 0) {
            new CheckPointCalender(eduDates).executeOnExecutor(Executors.newSingleThreadExecutor());
        }else {
            Log.e("null","null");
        }
        calendarView.addDecorators(commonDecorator, sundayDecorator, saturdayDecorator, todayDecorator);
    }

    private class CheckPointCalender extends AsyncTask<Void, Void, ArrayList<CalendarDay>> {

        private ArrayList<EduDate> timeResult ;
        private ArrayList<CalendarDay> list = new ArrayList<CalendarDay>();

        public CheckPointCalender(ArrayList<EduDate> timeResult) {
            this.timeResult = timeResult;
            timeResult.add(timeResult.get(timeResult.size()-1));
        }

        @Override
        protected ArrayList<CalendarDay> doInBackground(Void... voids) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < timeResult.size()+1; i++) {
                try {
                    CalendarDay day = CalendarDay.from(calendar);
                    String[] time = timeResult.get(i).getDate().split("-");
                    int year = Integer.parseInt(time[0]);
                    int month = Integer.parseInt(time[1]);
                    int dayy = Integer.parseInt(time[2]);

                    /*Date date = sdf.parse(timeResult.get(i).getDate());

                    Log.e("hahahah",timeResult.get(i).getDate().toString());*/

                    calendar.set(year, month - 1, dayy);
                    if (i != 0) {
                        list.add(day);
                    }
                }catch (IndexOutOfBoundsException e){

                } /*catch (ParseException e) {
                    e.printStackTrace();
                }*/
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            calendarView.addDecorator(new EventDecorator(Color.GREEN, list, context));
        }
    }

}
