/**
 * By Bertrand NANCY and Kevin NUNES
 */

package pey.ph.maps;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import pey.ph.maps.sql.AppDatabase;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        SharedPrefFragment sharedPrefFragment = (SharedPrefFragment) getSupportFragmentManager().findFragmentById(R.id.shared_fragment);
        final TextView sharedPrefTextView = sharedPrefFragment.getView().findViewById(R.id.shared_pref_text);
        SQLiteFragment sqLiteFragment = (SQLiteFragment) getSupportFragmentManager().findFragmentById(R.id.sql_fragment);
        final TextView sqliteTextView = sqLiteFragment.getView().findViewById(R.id.sqlite_pref_text);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.shared_pref), Context.MODE_PRIVATE);

        Set<String> locations = sharedPref.getStringSet("locations", new HashSet<String>());
        sharedPrefTextView.setText(locations.toString());

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sqliteTextView.setText(db.locationDao().getAll().toString());
            }
        }).start();
    }
}
