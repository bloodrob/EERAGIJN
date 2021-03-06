package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class PopUpTermsAndCondition extends AppCompatActivity {
    // checkBox
    private CheckBox checkTerm;
    //button
    private Button submitAccept, submitCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_terms_and_condition);
        // initialization
        checkTerm = (CheckBox)findViewById(R.id.check_term);
        submitAccept = (Button)findViewById(R.id.accept_terms);
        submitCancel = (Button)findViewById(R.id.cancel_terms);

        // submit button

        submitAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if checkBox clicked
                    if (checkTerm.isChecked()) {
                        Intent intent = new Intent(PopUpTermsAndCondition.this, UserProfileUpdate.class);
                        startActivity(intent);
                    }
                    // if checkbox Not clicked
                    if (!checkTerm.isChecked()) {
                        Toast.makeText(PopUpTermsAndCondition.this, "Please Check the checkbox before proceed.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
        // cancel Button
        submitCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PopUpTermsAndCondition.this, NewUserMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
