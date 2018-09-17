package todday.funny.seoulcatcher.viewmodel;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapViewModel extends BaseViewModel implements OnMapReadyCallback {
    private MapView mapView;

    public MapViewModel(Context context) {
        super(context);
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
