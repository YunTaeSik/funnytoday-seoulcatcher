package todday.funny.seoulcatcher.viewmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;

import java.util.ArrayList;

import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.interactor.OnUploadFinishListener;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.model.PlaceData;
import todday.funny.seoulcatcher.model.User;
import todday.funny.seoulcatcher.util.Keys;
import todday.funny.seoulcatcher.util.PermissionCheck;
import todday.funny.seoulcatcher.util.RequestCode;
import todday.funny.seoulcatcher.util.ShowIntent;

public class CallViewModel extends BaseViewModel {
    public ObservableField<Call> mCall = new ObservableField<>(new Call());
    public ObservableBoolean isCardiac = new ObservableBoolean(false);
    public ObservableBoolean isSelectLocation = new ObservableBoolean(false);


    public CallViewModel(Context context, User user) {
        super(context);
        mCall.get().setUser(user);
    }

    public void onClickKind(View view) {
        if (view.getId() == R.id.btn_cardiac) {
            isCardiac.set(true);
        } else {
            isCardiac.set(false);
        }
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;
            String kind = radioButton.getText().toString();
            mCall.get().setKind(kind);
        }
        isCardiac.notifyChange();
    }

    public void onClickAge(View view) {
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;
            String age = radioButton.getText().toString();
            mCall.get().setAge(age);

        }
    }

    public void onClickLocation() {
        PermissionCheck.loactionCheck(mContext, new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                ShowIntent.location(mContext, RequestCode.SELECT_CALL_LOCATION);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        });
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(Keys.SELECT_CALL_LOCATION)) {
                    isSelectLocation.set(true);
                    PlaceData placeData = intent.getParcelableExtra(Keys.PLACE);
                    LatLng latLng = placeData.getLatLng();

                    mCall.get().setName(placeData.getName());
                    mCall.get().setAddress(placeData.getAddress());
                    mCall.get().setLatitude(latLng.latitude);
                    mCall.get().setLongitude(latLng.longitude);
                    mCall.notifyChange();
                }
            }

        }
    };

    public BroadcastReceiver getBroadcastReceiver() {
        return broadcastReceiver;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Keys.SELECT_CALL_LOCATION);
        return intentFilter;
    }
}
