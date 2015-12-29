package com.kisese.app.lightremote;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;


public class ColorPickerActivity extends AppCompatActivity {

    private ScrollView colorView;
    private TextView coloText, colorRgb;
    private String hexColor;
    private String rgbColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        colorView = (ScrollView) findViewById(R.id.colorView);
        coloText = (TextView) findViewById(R.id.colorText);
        colorRgb = (TextView) findViewById(R.id.colorRgb);
        final ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
        SVBar svBar = (SVBar) findViewById(R.id.svbar);

        fade();

        picker.addSVBar(svBar);

        //to get the color
        picker.getColor();

        picker.setOldCenterColor(picker.getColor());


        picker.setShowOldCenterColor(false);
        picker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                ColorPickerActivity.this.findViewById(android.R.id.content).setBackgroundColor(picker.getColor());
                hexColor = String.format("#%06X", (0xFFFFFF & color));

                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                rgbColor = red + ", " + green + ", " + blue;

                coloText.setText("Hex Values: " + hexColor);
                colorRgb.setText("RGB Values: " + rgbColor);

                //set bg color
                colorView.setBackgroundColor(Color.parseColor(hexColor));
            }
        });

    }

    public void fade() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        colorView.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); // call super's method

        return true; // options menu creation was handled
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch based on the MenuItem id
        switch (item.getItemId()) {

        } // end switch

        return super.onOptionsItemSelected(item); // call super's method
    }

}
