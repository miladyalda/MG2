package android.app.mg.mg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.mg.mg.Process.ProcessModel;
import android.app.mg.mg.Process.ProcessShowActivity;
import android.app.mg.mg.Process.RunProcess;
import android.app.mg.mg.Process.ServiceHttp;
import android.app.mg.mg.Process.Steps;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



public class MainActivity extends AppCompatActivity implements RunProcess {



    ArrayList<String> processModelsIDS = new ArrayList<>();
    final String TAG = "Debug";
    AlertDialog.Builder builder;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new ServiceHttp(this).execute();



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), ProcessShowActivity.class);
                intent.putExtra("jsonData", processModelsIDS);
                view.getContext().startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });



        //if (!isInternetAvailable()) {

//            this.showDialog();
  ///  }
    }


    private boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

    private void showDialog(){



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("OPPS")
                .setMessage("Du har ingen Internet")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    @Override
    public void RunProgress() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void stopProgress(List<ProcessModel> processModels) {

        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        
        //this.processModels = processModels;

          parseData(processModels);


    }

    private String[] parseData(List<ProcessModel> processModels)
    {


                    Log.i("INFO", "try");

        for (int i=0; i<processModels.size(); i++)
        {
            Hashtable<String,Steps> hash = new Hashtable<>();

            Steps firstStep = null;

            Steps[] steps = new Steps[processModels.get(i).getSteps().length];

            Steps[] s = processModels.get(i).getSteps();

            for (int j=0; j< s.length; j++)
            {
                if(s[j].getPrev() == null)
                {

                    firstStep = s[j];
                    steps[0] = firstStep;
                }
                hash.put(s[j].getObjid(),s[j]);

            }

            for (int k = 0; k < s.length -1; k++){
                steps[k+1] = hash.get(steps[k].getNext());
            }

            processModels.get(i).setSteps(steps);

            Log.d("sorterat enligt tonny", processModels.get(0).getSteps()[2].getObjid());



        }

                    for(Steps ob : processModels.get(0).getSteps()){

                        this.processModelsIDS.add(ob.getObjid());

                    }




        /*
        Steps [] array = new Steps[processModels.size()];
        for(int i=1; i<processModels.size(); i++){


            if (processModels.get(i).getSteps()[1] == null)
            {
                array[0] = processModels.get(i).getSteps()[0];
                array[ge]

            }
            if (processModels.get(i).getSteps()[2] == null)
            {
                array[processModels.size()] = processModels.get(i).getSteps()[i];
            }


        }
*/
return null;
    }

}
