package todday.funny.seoulcatcher.ui.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.databinding.ScheduleDialogBinding;
import todday.funny.seoulcatcher.viewmodel.ScheduleDialogViewModel;

public class ScheduleDialog extends DialogFragment implements OnMapReadyCallback{
    private ScheduleDialogBinding binding;
    private ScheduleDialogViewModel model;
    private Button button_ok;
    private Button button_cancel;
    private GoogleMap map = null;
    private MapView mapView = null;
    private String date;

    private String uid;

    public static ScheduleDialog newInstance(String date) {
        Bundle args = new Bundle();
        args.putString("date",date);
        ScheduleDialog fragment = new ScheduleDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_schedule,container,false);

        model = new ScheduleDialogViewModel(getActivity());
        binding.setModel(model);

        View view = binding.getRoot();

        uid = FirebaseAuth.getInstance().getUid();

        button_ok = view.findViewById(R.id.schedueldialog_comfirm);
        button_cancel = view.findViewById(R.id.schedueldialog_cancel);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.inputScheduleDateBase(date);
                dismiss();
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if(getArguments()!=null) {
            date = getArguments().getString("date");
        }

        model.textDate = date;

        mapView = view.findViewById(R.id.schedueldialog_mapview);
        if(mapView != null){
            mapView.onCreate(savedInstanceState);
        }
        mapView.getMapAsync(this);



        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(37.551401,127.077142));

        markerOptions.title("광나루 안점체험관");

        map.addMarker(markerOptions);

        LatLng nowLang = new LatLng(37.551401,127.077142);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(nowLang, 12);
        map.animateCamera(cameraUpdate);

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
