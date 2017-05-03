package csm117.rulerless;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    LocationListener locListen;
    LocationManager locManager;
    TextView display;
    Location currentLoc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Application Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.distText);
        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locListen = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                display.setText(location.toString());
                currentLoc = location;
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
    }
}
