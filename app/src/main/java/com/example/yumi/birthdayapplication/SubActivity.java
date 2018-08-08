package com.example.yumi.birthdayapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.yumi.birthdayapplication.R.id.editText3;

public class SubActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        findViewById(R.id.buttonToMain).setOnClickListener(this);//画面遷移

        Intent intent = getIntent();
        String message = intent.getExtras().getString("message");
        TextView birthday = (TextView) findViewById(R.id.textView2);
        birthday.setText(message);


    }

    public void onClick(View view) {

        finish();


    }
}
