package com.tablesoft.principal;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class CameraRecorder extends AppCompatActivity implements SurfaceHolder.Callback {
    //Madres del Arturo
    private Button get_place;
    int PLACE_PICKER_REQUEST=1;

    private static final String TAG = CameraRecorder.class.getSimpleName();

    Intent myIntent;
    String address = null;

    public static SurfaceView mSurfaceView;
    public static SurfaceHolder mSurfaceHolder;
    public static Camera mCamera;
    public static boolean mRecordingStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_place=(Button)findViewById(R.id.buttonLocation);

        mRecordingStatus=false;
        myIntent = new Intent(this, infoActivity.class);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        final ImageButton btnStartVideo = (ImageButton) findViewById(R.id.StartVideoService);
        btnStartVideo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(CameraRecorder.this, RecorderService.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(intent);
                finish();
            }
        });

        ImageButton btnStopVideo = (ImageButton) findViewById(R.id.StopVideoService);
        btnStopVideo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                stopService(new Intent(CameraRecorder.this, RecorderService.class));
            }
        });

        Button btnnextActivity = (Button) findViewById(R.id.nextActivity);
        btnnextActivity.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                myIntent.putExtra("ADDRESS", address);
                startActivity(myIntent);
            }
        });

        get_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(CameraRecorder.this);
                    //Place place = PlacePicker.getPlace(this.data);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);

                }catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                address=String.format("%s",place.getAddress());
            }
        }
    }
}
