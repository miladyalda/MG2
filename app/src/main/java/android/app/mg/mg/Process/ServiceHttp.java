package android.app.mg.mg.Process;

import android.app.mg.mg.MainActivity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by miladyalda on 2017-10-07.
 */

public class ServiceHttp extends AsyncTask<Void, Void, String> {

    public RunProcess runProgresss;


    public ServiceHttp(RunProcess runProgress){

        this.runProgress = runProgress;



    }

    public RunProcess runProgress = new MainActivity();


    protected void onPreExecute() {


        runProgress.RunProgress();
    }

    @Override
    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL("http://526a5795.ngrok.io/getProcesses");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }

    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Gson gson = new Gson();

        Log.i("INFO", response);

        try {

            Log.i("INFO", "try");
            List<ProcessModel> processes = Arrays.asList(gson.fromJson(response, ProcessModel[].class));

            runProgress.stopProgress(processes);

            this.getList(processes);
           // Log.i("PostActivity", processes.size() + " posts loaded.");
           // for (ProcessModel post : processes) {
             //   Log.i("PostActivity", post._id + ": " + post.name);
            //}



        }

         catch (Exception e) {

            // Log.i("PostActivity", processes.size() + " posts loaded.");

             e.printStackTrace();
            }
    }


    private List<ProcessModel> getList(List<ProcessModel> list){


        return list;
    }
}
