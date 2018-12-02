package com.example.techvot.goldfit;

import android.os.Bundle;
import android.os.Handler;
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

import github.chenupt.springindicator.viewpager.ScrollerViewPager;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description TODO
 */
public class FingerprintFragment extends Fragment {

    private int bgRes;
    private ImageView imageView;
    int value;
    Button proceed;
    MaterialEditText phoneNumber;
    ImageView imageView2;





    public static FingerprintFragment newInstance(int value){
        FingerprintFragment fragment = new FingerprintFragment();
        Bundle args = new Bundle();
        args.putInt("value", value );
        fragment.setArguments(args);

        return fragment;
    }

    public FingerprintFragment() {




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
        return inflater.inflate(R.layout.fingerprint, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ScrollerViewPager viewPager = getActivity().findViewById(R.id.view_pager);

         proceed  = getView().findViewById(R.id.finger_print);
         phoneNumber = getView().findViewById(R.id.phone_number_fingerprint);
         imageView2 = getView().findViewById(R.id.imageView);

         proceed.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (phoneNumber.getText().toString().isEmpty()){
                     showToastInMiddle("Phone Number Cannot be empty");

                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {

                             imageView2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                         }
                     }, 7000);


                 }else {
                     viewPager.setCurrentItem(2);

                 }
             }
         });





//        if (SplashScreen.isFingerPrintConnect(getContext())){
//            (SplashScreen)getContext().setConnected(true);
//        }else {
//            setConnected(false);
//        }





    }


    public void showToastInMiddle(String message){
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }


}


