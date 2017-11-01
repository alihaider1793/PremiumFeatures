package com.example.haide.premiumfeatures;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button buttonFree, buttonPremium, buttonBonus;
    boolean pUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPref = getSharedPreferences("userType",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        buttonFree = (Button) findViewById(R.id.button_free);
        buttonBonus = (Button) findViewById(R.id.button_bonus);
        buttonPremium = (Button) findViewById(R.id.button_premium);

        buttonPremium.setEnabled(false);
        buttonBonus.setEnabled(false);

        if(isPremium()){
            Toast.makeText(getApplicationContext(),"value returned to isPremium(): "+isPremium(),Toast.LENGTH_SHORT).show();
            buttonPremium.setEnabled(true);
        }

        buttonFree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("FREE FEATURE");
                alert.setMessage("Click OK to unlock Premium Feature");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pUser = true;
                        editor.putBoolean("isPremium",pUser);
                        editor.apply();
                        buttonPremium.setEnabled(true);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
            }
        });

        buttonBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonBonus.isEnabled()==true){
                    Toast.makeText(getApplicationContext(),"You get this bonus every time after using the premium feature!",Toast.LENGTH_LONG).show();
                    buttonBonus.setEnabled(false);
                }
            }
        });
        buttonPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonBonus.setEnabled(true);
            }
        });
    }
    private boolean isPremium(){
        SharedPreferences sharedPref = getSharedPreferences("userType",MODE_PRIVATE);
        Toast.makeText(getApplicationContext(),"inside isPremium "+sharedPref.getBoolean("isPremium",false),Toast.LENGTH_SHORT).show();
        return sharedPref.getBoolean("isPremium",false);
    }
}
