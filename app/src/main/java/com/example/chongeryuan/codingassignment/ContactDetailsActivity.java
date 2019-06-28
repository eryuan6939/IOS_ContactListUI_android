package com.example.chongeryuan.codingassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chongeryuan.codingassignment.util.ImageDownloadTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetailsActivity extends AppCompatActivity {

    @BindView(R.id.textviewName)
    TextView textviewName;
    @BindView(R.id.edittext_ID)
    EditText edittextID;
    @BindView(R.id.imageview_ProfilePicture)
    CircleImageView imageviewProfilePicture;

    private String id, fname, lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        fname = intent.getStringExtra("fname");
        lname = intent.getStringExtra("lname");

        edittextID.setText(id);
        textviewName.setText(fname + " " + lname);
        new ImageDownloadTask(imageviewProfilePicture).execute(intent.getStringExtra("avatar"));
    }

    @OnClick({R.id.textviewContactButton, R.id.textviewEditButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textviewContactButton:
                this.finish();
                break;
            case R.id.textviewEditButton:
                Intent intent = new Intent(ContactDetailsActivity.this, ContactEditActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("fname", fname);
                intent.putExtra("lname", lname);
                startActivity(intent);
                break;
        }
    }
}
