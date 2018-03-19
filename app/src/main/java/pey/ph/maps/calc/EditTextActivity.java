/**
 * By Bertrand NANCY and Kevin NUNES
 */
package pey.ph.maps.calc;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import pey.ph.maps.R;

public class EditTextActivity extends AppCompatActivity implements EditFragment.OnFragmentInteractionListener {

    private boolean changing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        EditFragment editFragment = (EditFragment) getSupportFragmentManager().findFragmentById(R.id.edit_fragment);
        final EditText editTextInFragment = editFragment.getView().findViewById(R.id.edit_in_fragment);
        final EditText editText = findViewById(R.id.edit);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!changing) { // avoid deadlock
                    changing = true;
                    editTextInFragment.setText(editable.toString());
                    changing = false;
                }
            }
        });

        editTextInFragment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!changing) { // avoid deadlock
                    changing = true;
                    editText.setText(editable.toString());
                    changing = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    public void back(MenuItem item) {
        finishAffinity();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
