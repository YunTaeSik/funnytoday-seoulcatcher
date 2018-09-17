package todday.funny.seoulcatcher.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.SupportMapFragment;

import todday.funny.seoulcatcher.BaseActivity;
import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.databinding.MapBinding;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.util.Keys;
import todday.funny.seoulcatcher.viewmodel.MapViewModel;

public class MapActivity extends BaseActivity {
    private MapBinding binding;
    private MapViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        Call call = getIntent().getParcelableExtra(Keys.CALL);
        model = new MapViewModel(this);
        binding.setModel(model);


        binding.map.onCreate(null);
        model.setMapView(binding.map);

    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.map.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.map.onStop();
    }

    @Override
    protected void onPause() {
        binding.map.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        binding.map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.map.onLowMemory();
    }
}
