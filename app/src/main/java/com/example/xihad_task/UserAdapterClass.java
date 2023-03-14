package com.example.xihad_task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class UserAdapterClass extends  RecyclerView.Adapter<UserAdapterClass.userHolder>  {

    private Context mContext;
    private List<UserDataModel> mUserList;

    public UserAdapterClass(Context mContext, List<UserDataModel> mUserList) {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }
    public void setUserList(List<UserDataModel> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UserAdapterClass.userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_design, parent, false);
        return new userHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterClass.userHolder holder, int position) {
        UserDataModel user = mUserList.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event for this item
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("selected_user", user);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mUserList == null) {
            return 0;
        }
        return mUserList.size();
    }




    public class userHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mEmailTextView;
        public userHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            mEmailTextView = itemView.findViewById(R.id.emailTextView);


        }

        public void bind(UserDataModel user) {
            mNameTextView.setText(user.getTitle());
            mEmailTextView.setText(user.getBody());


        }
    }

}
