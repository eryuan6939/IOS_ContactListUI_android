package com.example.chongeryuan.codingassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chongeryuan.codingassignment.adapter.ContactAdapter;
import com.example.chongeryuan.codingassignment.api.RetrofitClient;
import com.example.chongeryuan.codingassignment.models.Contact;
import com.example.chongeryuan.codingassignment.models.ContactResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnLoanListener {

    @BindView(R.id.recyclerviewContact)
    RecyclerView recyclerviewContact;

    private ContactAdapter adapter;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerviewContact.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<Contact>();
        initContactListView();
    }

    private void initContactListView() {
        recyclerviewContact.setHasFixedSize(true);
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewContact.setLayoutManager(lManager);
        adapter = new ContactAdapter(MainActivity.this, contactList, this);
        recyclerviewContact.setAdapter(adapter);
        loadContact();
    }

    private void loadContact() {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("per_page", "10");

        Call<ContactResponse> call = RetrofitClient
                .getInstance().getApi().GetContactList(params);
        call.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                contactList = response.body().getContact();
                displayContactList(contactList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displayContactList(List<Contact> contactList) {
        adapter = new ContactAdapter(MainActivity.this, contactList, this);
        recyclerviewContact.setAdapter(adapter);
    }

    @Override
    public void onLoanClick(int position) {
        Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
        intent.putExtra("id", contactList.get(position).getId());
        intent.putExtra("fname", contactList.get(position).getFirst_name());
        intent.putExtra("lname", contactList.get(position).getLast_name());
        intent.putExtra("avatar", contactList.get(position).getAvatar());
        startActivity(intent);


    }

    @OnClick(R.id.framelayout_add)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, ContactAddActivity.class);
        startActivity(intent);
    }
}
