package csm117.rulerless;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    LocationListener locListen;
    TextView display;
    Location currentLoc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Application Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.distText);
        locListen = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                display.setText(location.toString());
                currentLoc = location;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                display.setText(s);
            }

            @Override
            public void onProviderEnabled(String s) {
                display.setText(s);
            }

            @Override
            public void onProviderDisabled(String s) {
                display.setText(s);
            }
        };
    }
}
