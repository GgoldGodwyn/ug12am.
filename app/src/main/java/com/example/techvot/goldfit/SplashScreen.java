package com.example.techvot.goldfit;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import github.chenupt.springindicator.SpringIndicator;
import me.drakeet.materialdialog.MaterialDialog;


public class SplashScreen extends AppCompatActivity {



    // godwin added
    UsbManager usbManager;
    UsbDevice device;
    UsbDeviceConnection connection;
    //stoped here


    String bVNPhoneString;


    CustomViewPager viewPager;
    MaterialDialog mMaterialDialog;
    MaterialEditText bVNPhoneEdit ;

    String TAG = "Result";

    ImageView imageViewConnect, imageViewDisconnect;
    TextView textViewConnect;
    boolean fingerPrint ;

//    private USBReceiver mReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        textConsole = findViewById(R.id.consoleText);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            fingerPrint = getIntent().getExtras().getBoolean("fingerprint");
            setConnected(fingerPrint);
        } else {
            setConnected(false);
        }




        viewPager = findViewById(R.id.view_pager);
        SpringIndicator springIndicator = findViewById(R.id.indicator);
        springIndicator.setOnTabClickListener(null);


        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        viewPager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.left);
        viewPager.fixScrollSpeed();



        // just set viewPager
        springIndicator.setViewPager(viewPager);


        View v = getLayoutInflater().inflate(R.layout.alert_pop, (RelativeLayout)findViewById(R.id.activity_dashboard_relative_alert_root), false);
        bVNPhoneEdit = v.findViewById(R.id.activity_pending_material_job_token);

        mMaterialDialog = new MaterialDialog(this)
                .setView(v)
                .setPositiveButton("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bVNPhoneString = bVNPhoneEdit.getText().toString().trim();
                        if (bVNPhoneString.isEmpty()){
                            showToastInMiddle("BVN cannot be empty");
                        } else {

                            if (isInternetAvailable()){
                                mMaterialDialog.dismiss();
                            } else {
                                showToastInMiddle("Check your network connectivity and try again");
                            }
                            mMaterialDialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("NO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
        mMaterialDialog.show();

//        mReceiver = new USBReceiver(this);

//        if (isFingerPrintConnect(this)){
//            setConnected(true);
//        }else {
//            setConnected(false);
//        }





        //godwin added
//        usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
//        HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
//        if(!usbDevices.isEmpty())
//        {
//            boolean keep = true;
//            for(Map.Entry<String, UsbDevice> entry : usbDevices.entrySet())
//            {
//                device = entry.getValue();
//                int deviceVID = device.getVendorId();
//                int devicePID = device.getProductId();
//                if(deviceVID != 0x1d6b || (devicePID != 0x0001 || devicePID != 0x0002 || devicePID != 0x0003))
//                {
//                    // We are supposing here there is only one device connected and it is our serial device
//                    connection = usbManager.openDevice(device);
//                    keep = false;
//                }else
//                {
//                    connection = null;
//                    device = null;
//                }
//
//                if(!keep)
//                    break;
//            }
//        }

        // ends here

    }




//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//
//        int page = viewPager.getCurrentItem();
//        View v = getLayoutInflater().inflate(R.layout.enter, (RelativeLayout)findViewById(R.id.enter_parent), false);
//        MaterialEditText descriptionEdt = v.findViewById(R.id.activity_sign_up_material_name);
//        MaterialEditText amountEdt = v.findViewById(R.id.activity_sign_up_material_password);
//
//        savedInstanceState.putInt("page", page);
//        savedInstanceState.putString("description", descriptionEdt.getText().toString());
//        savedInstanceState.putString("amount", amountEdt.getText().toString());
//
//
//        // Call the superclass to save the state of all the other controls in the view hierarchy
//        super.onSaveInstanceState(savedInstanceState);
//    }


//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        // Call the superclass to restore the state of all the other controls in the view hierarchy
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Restore value of counters from saved stat
//        View v = getLayoutInflater().inflate(R.layout.enter, (RelativeLayout)findViewById(R.id.enter_parent), false);
//        MaterialEditText descriptionEdt = v.findViewById(R.id.activity_sign_up_material_name);
//        MaterialEditText amountEdt = v.findViewById(R.id.activity_sign_up_material_password);
//
//        viewPager.setCurrentItem(savedInstanceState.getInt("page"));
//        descriptionEdt.setText(savedInstanceState.getString("description"));
//        amountEdt.setText(savedInstanceState.getString("amount"));
//
//    }



    public void writeSerial(View view){



    }


    public void showToastInMiddle(String message){
        Toast toast = Toast.makeText(SplashScreen.this, message, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    public boolean isInternetAvailable(){

        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

// godwin Comment this
   /*public static boolean isFingerPrintConnect(Context context){
        UsbManager usbManager = context.getSystemService(UsbManager.class);
        Map<String, UsbDevice> connectedDevices = usbManager.getDeviceList();
        for (UsbDevice device : connectedDevices.values()) {
            if (device.getVendorId() == 0x2341 && device.getProductId() == 0x0001) {
//                Log.i(TAG, "Device found: " + device.getDeviceName());
//                startSerialConnection(usbManager, device);
                return true;
            }
        }
        return false;
    }*/
    //ends here

    public void setConnected(boolean toggle){

        imageViewConnect =  findViewById(R.id.status_connected);
        imageViewDisconnect = findViewById(R.id.status_disconnected);
        textViewConnect = findViewById(R.id.status_indicator);

        if (toggle){

            textViewConnect.setText("Connected");
            imageViewConnect.setVisibility(View.VISIBLE);
            imageViewDisconnect.setVisibility(View.GONE);

        } else {
            textViewConnect.setText("Disconnected, Kindly connect a FIT fingerprint");
            imageViewConnect.setVisibility(View.GONE);
            imageViewDisconnect.setVisibility(View.VISIBLE);
    }
}


//    @Override
//    public void onSampleDataReady(boolean toggle) {
//        setConnected(toggle);
//    }
}
