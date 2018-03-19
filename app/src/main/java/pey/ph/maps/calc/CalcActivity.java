/**
 * By Bertrand NANCY and Kevin NUNES
 */
package pey.ph.maps.calc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pey.ph.maps.R;

public class CalcActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, KeyboardFragment.OnFragmentInteractionListener {

    private boolean inverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        inverse = false;
        initFragments(inverse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void initFragments(boolean inverse) {
        for(Fragment fragment:getSupportFragmentManager().getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(!inverse) {
            fragmentTransaction.add(R.id.main, TopFragment.newInstance());
            fragmentTransaction.add(R.id.main, KeyboardFragment.newInstance());
        } else {
            fragmentTransaction.add(R.id.main, KeyboardFragment.newInstance());
            fragmentTransaction.add(R.id.main, TopFragment.newInstance());
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void switchFragment() {
        initFragments(!inverse);
        inverse = !inverse;
    }

    public void handleButtonClick(View view) {
        String text = String.valueOf(((Button) view).getText());
        if(text.equals("="))
            switchFragment();
    }

    public void editTextActivity(MenuItem item) {
        Intent intent = new Intent(this, EditTextActivity.class);
        startActivity(intent);
    }
}
