package io.ibrahim.wearmirror;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import io.ibrahim.wearmirror.MyClassTest;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Button b = (Button)findViewById(R.id.settings_button);
        MyClassTest.foo();
    }

    public void openSettings(View settingsButton){
        Log.d("WearMirror", "Clicked button");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
