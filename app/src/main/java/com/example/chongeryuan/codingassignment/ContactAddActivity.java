package com.example.chongeryuan.codingassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactAddActivity extends AppCompatActivity {

    @BindView(R.id.edittext_firstname)
    EditText edittextFirstname;
    @BindView(R.id.edittext_lastname)
    EditText edittextLastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        ButterKnife.bind(this);

    }

    private void addContact() {
        boolean valid = true;

        if (TextUtils.isEmpty(edittextFirstname.getText().toString())) {
            edittextFirstname.setError("Please enter your first name.");
            valid = false;
        }

        if (TextUtils.isEmpty(edittextLastname.getText().toString())) {
            edittextLastname.setError("Please enter your last name.");
            valid = false;
        }

        if (valid) {

            this.onBackPressed();

        }
    }


    @OnClick({R.id.textviewCancelButton, R.id.textviewDoneButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textviewCancelButton:
                this.finish();
                break;
            case R.id.textviewDoneButton:
                addContact();
                break;
        }
    }
}
