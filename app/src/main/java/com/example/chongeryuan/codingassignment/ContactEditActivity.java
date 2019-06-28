package com.example.chongeryuan.codingassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactEditActivity extends AppCompatActivity {

    @BindView(R.id.edittext_firstname)
    EditText edittextFirstname;
    @BindView(R.id.edittext_lastname)
    EditText edittextLastname;
    @BindView(R.id.edittext_ID)
    EditText edittextID;

    private String id, fname, lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        fname = intent.getStringExtra("fname");
        lname = intent.getStringExtra("lname");

        edittextFirstname.setText(fname);
        edittextLastname.setText(lname);
        edittextID.setText(id);
    }

    @OnClick({R.id.textviewCancelButton, R.id.textviewDoneButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textviewCancelButton:
                this.finish();
                break;
            case R.id.textviewDoneButton:
                break;
        }
    }
}
