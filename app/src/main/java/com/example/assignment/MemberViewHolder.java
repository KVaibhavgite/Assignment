package com.example.assignment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
class MemberViewHolder extends RecyclerView.ViewHolder {
    TextView tvUserName, tvFirstName,tvLastName,tvNumber,tvAddress;
    ImageView deleteMember;
    ImageView editMember;
    MemberViewHolder(View itemView) {
        super(itemView);
        tvUserName = itemView.findViewById(R.id.userName);
        tvFirstName = itemView.findViewById(R.id.firstName);
        tvLastName  = itemView.findViewById(R.id.lastName);
        tvNumber = itemView.findViewById(R.id.number);
        //tvAddress  = itemView.findViewById(R.id.address);
        deleteMember = itemView.findViewById(R.id.deleteContact);
        editMember = itemView.findViewById(R.id.editContact);
    }
}
