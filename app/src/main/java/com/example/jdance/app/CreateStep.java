package com.example.jdance.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jdance.app.model.DefaultStep;
import com.example.jdance.app.model.Repository;
import com.example.jdance.app.util.MyOnSeekBarChangeListener;

/**
 * Created by mclo on 08/12/13.
 */
public class CreateStep extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_step);

        //Seek bar LeftMotorVelocity
        final SeekBar sbLeftMotorVelocity = (SeekBar) findViewById(R.id.sbLeftMotorVelocity);
        final TextView txtLeftMotorVelocity = (TextView) findViewById(R.id.txtLeftMotorVelocity);
        sbLeftMotorVelocity.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener(txtLeftMotorVelocity, 100));

        //Seek bar RightMotorVelocity
        final SeekBar sbRightMotorVelocity = (SeekBar) findViewById(R.id.sbRightMotorVelocity);
        final TextView txtRightMotorVelocity = (TextView) findViewById(R.id.txtRightMotorVelocity);
        sbRightMotorVelocity.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener(txtRightMotorVelocity, 100));

        //Seek bar SecondsDuration
        final SeekBar sbSecondsDuration = (SeekBar) findViewById(R.id.sbSecondsDuration);
        final TextView txtSecondsDuration = (TextView) findViewById(R.id.txtSecondsDuration);
        sbSecondsDuration.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener(txtSecondsDuration, 0));

        //button Create Step
        View btnCreateStep = findViewById(R.id.btnCreateStep);
        btnCreateStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tomando los valores de la vista
                EditText txtName = (EditText) findViewById(R.id.txtName);
                String name = txtName.getText().toString();
                int leftMotorVelocity = sbLeftMotorVelocity.getProgress() - 100;
                int rightMotorVelocity = sbRightMotorVelocity.getProgress() - 100;
                int secondsDuration = sbSecondsDuration.getProgress();

                //creo un step y lo guardo
                DefaultStep defaultStep = new DefaultStep(name, leftMotorVelocity, rightMotorVelocity, secondsDuration);
                Repository.getInstance().getSteps().add(defaultStep);
                Toast.makeText(getApplicationContext(), getString(R.string.step_created), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
