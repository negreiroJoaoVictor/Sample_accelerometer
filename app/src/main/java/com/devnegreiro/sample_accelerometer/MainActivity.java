package com.devnegreiro.sample_accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;

    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.mViewHolder.text_x = findViewById(R.id.Text_x);
        this.mViewHolder.text_y = findViewById(R.id.Text_y);
        this.mViewHolder.text_z = findViewById(R.id.Text_z);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
      /*
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.

        final float alpha = (float) 0.8;
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = sensorEvent.values[0] - gravity[0];
        linear_acceleration[1] = sensorEvent.values[1] - gravity[1];
        linear_acceleration[2] = sensorEvent.values[2] - gravity[2];

         */

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        //Float to decimal with a new scale
        BigDecimal bd_x = new BigDecimal(x).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_y = new BigDecimal(y).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_z = new BigDecimal(z).setScale(3, RoundingMode.HALF_EVEN);

        this.mViewHolder.text_x.setText("X:" + bd_x + "m/s^2");
        this.mViewHolder.text_y.setText("Y:" + bd_y + "m/s^2");
        this.mViewHolder.text_z.setText("Z:" + bd_z + "m/s^2");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public static class ViewHolder {
        TextView text_x, text_y, text_z;

    }
}