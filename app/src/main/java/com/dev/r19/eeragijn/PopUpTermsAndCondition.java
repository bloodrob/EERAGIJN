package com.dev.r19.eeragijn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
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

        // initialization and set up for pop out layout
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupWindow popW = new PopupWindow(inflater.inflate(R.layout.activity_pop_up_terms_and_condition, null, false),100, 100, true);
        popW.showAtLocation(this.findViewById(R.id.read_term), Gravity.CENTER, 0 , 0);
        submitAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if checkBox clicked
                    if (checkTerm.isChecked()) {
                        Intent intent = new Intent(PopUpTermsAndCondition.this, UserPersionalInfoInsert.class);
                        startActivity(intent);
                    }
                    // if checkbox Not clicked
                    if (!checkTerm.isChecked()) {
                        Toast.makeText(PopUpTermsAndCondition.this, "Please Check the checkbox before proceed.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });
    }
}
