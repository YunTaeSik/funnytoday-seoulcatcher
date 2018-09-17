package todday.funny.seoulcatcher.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
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

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.model.routeModel.Route;
import todday.funny.seoulcatcher.model.routeModel.SendRouteData;

public class MapViewModel extends BaseViewModel implements OnMapReadyCallback {
    private float MAP_ZOOM_LEVEL_WORLD = 1.0f;
    private float MAP_ZOOM_LEVEL_LANDMASS_CONTINENT = 5.0f;
    private float MAP_ZOOM_LEVEL_CITY = 10.0f;
    private float MAP_ZOOM_LEVEL_STREETS = 15.0f;
    private float MAP_ZOOM_LEVEL_STREETS_BULDINGS = 17.5f;
    private float MAP_ZOOM_LEVEL_BUILDINGS = 20.0f;

    public ObservableField<Call> mCall = new ObservableField<>();
    public ObservableField<Route> mRoute = new ObservableField<>();
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private CompositeDisposable mCompositeDisposable;

    public MapViewModel(Context context, Call call) {
        super(context);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        mCall.set(call);
    }

    public void setCompositeDisposable(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public void setMapView(MapView mapView) {
        if (mapView != null) {
            mapView.getMapAsync(this);
            mapView.onResume();
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
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), MAP_ZOOM_LEVEL_STREETS_BULDINGS));
                    getRoute(location);
                }
            }
        });
    }

    private void getRoute(Location location) {
        if (location != null && mCall.get() != null) {
            SendRouteData sendRouteData = new SendRouteData(location.getLatitude(), location.getLongitude(), Double.parseDouble(mCall.get().getLatitude()), Double.parseDouble(mCall.get().getLongitude()), mContext.getString(R.string.origin), mCall.get().getAddress());
            mCompositeDisposable.add(mServerDataController.getRoute(sendRouteData).subscribe(new Consumer<Route>() {
                @Override
                public void accept(Route route) throws Exception {

                }
            }));
        }
    }
}
