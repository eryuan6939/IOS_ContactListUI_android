package com.example.chongeryuan.codingassignment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chongeryuan.codingassignment.R;
import com.example.chongeryuan.codingassignment.models.Contact;
import com.example.chongeryuan.codingassignment.util.ImageDownloadTask;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mCtx;
    private List<Contact> contactList;
    private OnLoanListener mOnLoanListener;

    public ContactAdapter(Context mCtx, List<Contact> contactList, OnLoanListener onLoanListener) {
        this.mCtx = mCtx;
        this.contactList = contactList;
        this.mOnLoanListener = onLoanListener;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.contactlistview_layout, parent, false);
        return new ContactViewHolder(view, mOnLoanListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        new ImageDownloadTask(holder.imageviewprofilepicture).execute(contact.getAvatar());
        holder.textviewprofilename.setText(contact.getFirst_name() + " " + contact.getLast_name());
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CircleImageView imageviewprofilepicture;
        TextView textviewprofilename;
        OnLoanListener onLoanListener;

        public ContactViewHolder(View itemView, OnLoanListener onLoanListener) {
            super(itemView);

            imageviewprofilepicture = itemView.findViewById(R.id.imageViewProfilePicture);
            textviewprofilename = itemView.findViewById(R.id.tvProfileName);
            this.onLoanListener = onLoanListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onLoanListener.onLoanClick(getAdapterPosition());
        }
    }

    public interface OnLoanListener {
        void onLoanClick(int position);
    }



}
