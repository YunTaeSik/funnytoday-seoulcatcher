package todday.funny.seoulcatcher.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapViewModel extends BaseViewModel implements OnMapReadyCallback {
    private float MAP_ZOOM_LEVEL_WORLD = 1.0f;
    private float MAP_ZOOM_LEVEL_LANDMASS_CONTINENT = 5.0f;
    private float MAP_ZOOM_LEVEL_CITY = 10.0f;
    private float MAP_ZOOM_LEVEL_STREETS = 15.0f;
    private float MAP_ZOOM_LEVEL_STREETS_BULDINGS = 17.5f;
    private float MAP_ZOOM_LEVEL_BUILDINGS = 20.0f;

    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public MapViewModel(Context context) {
        super(context);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

    }

    public void setMapView(MapView mapView) {
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        getCurrentLocation();
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), MAP_ZOOM_LEVEL_STREETS_BULDINGS));
                }
            }
        });

    }
}
