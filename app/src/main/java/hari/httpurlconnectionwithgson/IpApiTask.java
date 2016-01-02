package hari.httpurlconnectionwithgson;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IpApiTask extends AsyncTask<String, String, String> {
    private TaskListener taskListener;

    public IpApiTask(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        taskListener.before();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        taskListener.after(s);
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer stringBuffer = null;
        try {
            //create URL object
            URL url = new URL(Constants.IP_API_URL);
            //open connection and cast it to HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //set Request method
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();

            // initialise string buffer to hold whole data (expandable)
            stringBuffer = new StringBuffer();

            //to read one by one char
            int ch;

            //read stream char by char
            while ((ch = inputStream.read()) != -1) {
                stringBuffer.append((char) ch);//casting int char to character
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return output string
        return stringBuffer.toString();
    }
}
