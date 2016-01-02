package hari.httpurlconnectionwithgson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Button hello_ip_api_btn;
    private TaskListener taskListener = new TaskListener() {
        @Override
        public void before() {
            //create progress dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            //set title to progress dialog
            progressDialog.setTitle("Please Wait..");
            //show progress dialog
            progressDialog.show();
        }

        @Override
        public void after(String response) {
            //dismiss progress dialog
            progressDialog.dismiss();

            //add gson dependency to build.gradle (app) file

            IpApIResponseModel ipApIResponseModel = IpApIResponseModel.toPojo(response);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello_ip_api_btn = (Button) findViewById(R.id.hello_ip_api_btn);
        hello_ip_api_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add internet permission in manifest

                //create async task object
                IpApiTask ipApiTask = new IpApiTask(taskListener);
                //execute it
                ipApiTask.execute();
            }
        });
    }
}
