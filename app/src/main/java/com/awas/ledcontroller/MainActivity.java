package com.awas.ledcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.graphics.PathEffect;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ConsumerIrManager mCIR;

    private final int frequency = 38400;

    Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the ConsumerIrManager
        mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);

        // Get Pattern
        pattern = new Pattern();

        setContentView(R.layout.activity_main_new);

        findViewById(R.id.buttonUP).setOnClickListener(new TransmitClicker(pattern.BRIGHT_UP));
        findViewById(R.id.buttonDOWN).setOnClickListener(new TransmitClicker(pattern.BRIGHT_DOWN));
        findViewById(R.id.button_powerON).setOnClickListener(new TransmitClicker(pattern.ON));
        findViewById(R.id.button_powerOFF).setOnClickListener(new TransmitClicker(pattern.OFF));

        findViewById(R.id.button_red).setOnClickListener(new TransmitClicker(pattern.RED));
        findViewById(R.id.button_green).setOnClickListener(new TransmitClicker(pattern.GREEN));
        findViewById(R.id.button_blue).setOnClickListener(new TransmitClicker(pattern.BLUE));
        findViewById(R.id.button_white).setOnClickListener(new TransmitClicker(pattern.WHITE));

        findViewById(R.id.button_orange).setOnClickListener(new TransmitClicker(pattern.ORANGE));
        findViewById(R.id.button_lightORANGE).setOnClickListener(new TransmitClicker(pattern.LIGHT_GREEN));
        findViewById(R.id.button_lightBLUE).setOnClickListener(new TransmitClicker(pattern.LIGHT_BLUE));
        findViewById(R.id.button_flash).setOnClickListener(new TransmitClicker(pattern.FLASH));

        findViewById(R.id.button_lightORANGE).setOnClickListener(new TransmitClicker(pattern.LIGHT_ORANGE));
        findViewById(R.id.button_CYAN).setOnClickListener(new TransmitClicker(pattern.CYAN));
        findViewById(R.id.button_VIOLET).setOnClickListener(new TransmitClicker(pattern.VIOLET));
        findViewById(R.id.button_strobe).setOnClickListener(new TransmitClicker(pattern.STROBE));

        findViewById(R.id.button_darkYELLOW).setOnClickListener(new TransmitClicker(pattern.DARK_YELLOW));
        findViewById(R.id.button_darkCYAN).setOnClickListener(new TransmitClicker(pattern.DARK_CYAN));
        findViewById(R.id.button_lightVIOLET).setOnClickListener(new TransmitClicker(pattern.LIGHT_VIOLET));
        findViewById(R.id.button_fade).setOnClickListener(new TransmitClicker(pattern.FADE));

        findViewById(R.id.button_yellow).setOnClickListener(new TransmitClicker(pattern.YELLOW));
        findViewById(R.id.button_darkCYAN2).setOnClickListener(new TransmitClicker(pattern.DARK_CYAN_2));
        findViewById(R.id.button_pink).setOnClickListener(new TransmitClicker(pattern.PINK));
        findViewById(R.id.button_smooth).setOnClickListener(new TransmitClicker(pattern.SMOOTH));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.TRANSMIT_IR}, 1);
        }
    }

    public class TransmitClicker implements View.OnClickListener {

        int[] pattern;

        TransmitClicker(int[] pattern) {
            this.pattern = pattern;
        }


        @Override
        public void onClick(View v) {
            if (!mCIR.hasIrEmitter()) {
                Toast.makeText(MainActivity.this, "No IR Emitter found", Toast.LENGTH_LONG).show();
                return;
            }

            mCIR.transmit(frequency, pattern);
        }
    }
}