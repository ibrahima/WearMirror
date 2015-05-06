package io.ibrahim.wearmirror;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import breeze.linalg.*;

import io.ibrahim.wearmirror.TestCS;

public class CSTestsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cstests);
        TextView tv = (TextView)findViewById(R.id.cs_tests_output);
        tv.append("Testing appending to this TextView\n");
        DenseMatrix A = TestCS.gen_a(3, 5);
        tv.append(TestCS.mat2String(A));
    }
}
