package com.herusantoso.latihan.moengagepushnotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.moe.pushlibrary.MoEHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnToken;
    private static final String TAG = "MainActivity";

    private MoEHelper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.sendMoeScreenTracking(this, "HOME");

        helper = MoEHelper.getInstance(this);
        setUserAttribute();

        btnToken = (Button) findViewById(R.id.btn_token);

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Token : " + token);
                Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private void setUserAttribute(){
        // Helper method to set User uniqueId. Can be String,int,long,float,double
        helper.setUniqueId("herusantoso23");
        // If you have first and last name separately
        helper.setFirstName("Heru");
        helper.setLastName("Santoso");
        // If you have full name
        helper.setFullName("Heru Santoso");
        helper.setBirthDate("01/01/1990");
        helper.setUserLocation(40.77,73.98);
        helper.setEmail("heruelrealsantoso@gmail.com");
        helper.setGender("Male");
        //Helper method to set mobile number
        helper.setNumber("087760586526");
    }


}
