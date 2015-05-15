package io.ibrahim.wearmirror;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import breeze.linalg.*;

import android.os.AsyncTask;
import io.ibrahim.wearmirror.TestCS;
import org.netlib.blas.*;

public class CSTestsActivity extends Activity
{
    private TextView tv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cstests);
        tv = (TextView)findViewById(R.id.cs_tests_output);
        tv.append("BLAS implementation:"+BLAS.getInstance().getClass().getName() + "\n");
        CSBenchTask cst = new CSBenchTask();
        cst.execute();
    }

    class CSBenchTask extends AsyncTask<Void, String, Double>
    {
        long elapsedTime;
        @Override
        public Double doInBackground(Void... unused){
            long startTime = System.currentTimeMillis();
            Double errors = TestCS.testCS(3, 1000);
            long stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            return errors;
        }
        @Override
        public void onPostExecute(Double result){
            if(!isCancelled()){
                Log.d("WearMirror", "Yay we win");
                tv.append("Finished Compressed Sensing Benchmark with mean squared error "+ result.toString() + " in "+elapsedTime+"ms.");
            }
        }

    }
}
