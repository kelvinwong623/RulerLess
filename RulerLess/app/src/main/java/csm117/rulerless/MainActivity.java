package csm117.rulerless;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    LocationListener locListen;
    LocationManager locManager;
    TextView display;
    Location currentLoc;
    Button calcButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Application Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.distText);
        calcButton = (Button) findViewById(R.id.calcButton);
        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locListen = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("test", "done");
                display.setText("\n " + location.getLongitude() + " " + location.getLatitude());
                //currentLoc = location;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                //display.setText(s);
            }

            @Override
            public void onProviderEnabled(String s) {
                //display.setText(s);
            }

            @Override
            public void onProviderDisabled(String s) {
                //display.setText(s);
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        Log.d("test", "start");
        configure_button();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

        void configure_button(){
            // first check for permissions
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                            ,10);
                }
                return;
            }
            // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
            calcButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //noinspection MissingPermission
                    locManager.requestLocationUpdates("gps", 0, 0, locListen);
                }
            });
        }
    {
        double ans = 0;
        return ans;
    }
}


