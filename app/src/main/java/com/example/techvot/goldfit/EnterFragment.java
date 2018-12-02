package com.example.techvot.goldfit;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import github.chenupt.springindicator.viewpager.ScrollerViewPager;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description TODO
 */
public class EnterFragment extends Fragment {

    private int bgRes;
    private ImageView imageView;
    int value;
    Button button;
    MaterialEditText amountEdit;
    MaterialEditText descriptionEdit;

    public static EnterFragment newInstance(int value){
        EnterFragment fragment = new EnterFragment();
        Bundle args = new Bundle();
        args.putInt("value", value );
        fragment.setArguments(args);

        return fragment;
    }

    public EnterFragment() {


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getInt("data");

        if (getArguments() != null) {
            value = getArguments().getInt("value");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ScrollerViewPager viewPager = getActivity().findViewById(R.id.view_pager);
         button = getView().findViewById(R.id.activity_sign_up_button_continue);
         amountEdit  = getView().findViewById(R.id.activity_sign_up_material_password);
         descriptionEdit  = getView().findViewById(R.id.activity_sign_up_material_name);


         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (amountEdit.getText().toString().isEmpty() || descriptionEdit.getText().toString().isEmpty()){
                     showToastInMiddle("Description Or Amount Cannot be empty");
                 }else {
                     viewPager.setCurrentItem(1);
//                     continueRead(view);

                 }
             }
         });


//        imageView = (ImageView) getView().findViewById(R.id.image);
//        imageView.setBackgroundResource(bgRes);
    }

    public void showToastInMiddle(String message){
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }



//    public void continueRead(final View view) {
//        class ContinueRead extends AsyncTask<Void, Void, String> {
//
//            UsbSerialPort port;
//            UsbSerialDriver driver;
//            UsbDeviceConnection connection;
//
//
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//                // Find all available drivers from attached devices.
//                UsbManager manager = (UsbManager) getContext().getSystemService(Context.USB_SERVICE);
//                List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
//                if (availableDrivers.isEmpty()) {
//
//                } else {
//                    // Open a connection to the first available driver.
//                    driver = availableDrivers.get(0);
//                    connection = manager.openDevice(driver.getDevice());
//
//                }
//
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                if (connection == null) {
//                    // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
//                } else {
//                    int numBytesRead = 0;
//                    while (numBytesRead == 0) {
//                        // Read some data! Most have just one port (port 0).
//                        port = driver.getPorts().get(0);
//                        try {
//                            port.open(connection);
//                            port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
////                            byte[] b = "Testing result".getBytes(Charset.forName("UTF-8"));
//                            byte[][] b = {
//                                    intToBytes(239),
//                                    intToBytes(1),
//                                    intToBytes(255),
//                                    intToBytes(255),
//                                    intToBytes(255),
//                                    intToBytes(255),
//                                    intToBytes(1),
//                                    intToBytes(0),
//                                    intToBytes(3),
//                                    intToBytes(1),
//                                    intToBytes(0),
//                                    intToBytes(0),
//                                    intToBytes(0),
//                                    intToBytes(0),
//                                    intToBytes(0),
//                                    intToBytes(5)
//                            };
//                            for (int k = 0 ; k <= 15 ;k++){
//                                port.write(b[k], 100);
//                            }
//
//                            byte[] buffer = new byte[16];
//                            numBytesRead = port.read(buffer, 1000);
//                        } catch (IOException e) {
//                        } finally {
//                            try {
//                                port.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                }
//
//                return null;
//            }
//
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(getContext(), "Finished", Toast.LENGTH_LONG).show();
//            }
//
//        }
//        ContinueRead uv = new ContinueRead();
//        uv.execute();
//    }


    public byte[] intToBytes( final int i ) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }



}


