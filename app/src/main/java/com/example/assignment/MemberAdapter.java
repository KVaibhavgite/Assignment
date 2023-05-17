package com.example.assignment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;
class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder>
        implements Filterable {
    private Context context;
    private ArrayList<Member> listMembers;
    private ArrayList<Member> mArrayList;
    private SqliteDatabase mDatabase;
    MemberAdapter(Context context, ArrayList<Member> listMembers) {
        this.context = context;
        this.listMembers = listMembers;
        this.mArrayList = listMembers;
        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_layout, parent, false);
        return new MemberViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        final Member member = listMembers.get(position);

        holder.tvUserName.setText(member.getEmployeeId());
        holder.tvFirstName.setText(member.getFirstName());
        holder.tvLastName.setText(member.getLastName());
        holder.tvNumber.setText(member.getMobileNo());
      //  holder.tvAddress.setText(member.getAddress());

        holder.editMember.setOnClickListener(view -> editTaskDialog(member));
        holder.deleteMember.setOnClickListener(view -> {
            mDatabase.deleteMember(member.getId());
            ((Activity) context).finish();
            context.startActivity(((Activity) context).getIntent());
        });
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listMembers = mArrayList;
                }
                else {
                    ArrayList<Member> filteredList = new ArrayList<>();
                    for (Member members : mArrayList) {
                        if (members.getEmployeeId().toLowerCase().contains(charString)) {
                            filteredList.add(members);
                        }
                    }
                    listMembers = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listMembers;
                return filterResults;
            }
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listMembers = (ArrayList<Member>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        return listMembers.size();
    }
    //
    private void editTaskDialog(final Member member) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_memeber, null);
        final EditText username = subView.findViewById(R.id.enterUsername);
        final EditText firstName = subView.findViewById(R.id.enterFirstName);
        final EditText lastName = subView.findViewById(R.id.enterLastName);
        final EditText number = subView.findViewById(R.id.enterMobileNo);
        //final EditText address = subView.findViewById(R.id.enterAddress);

        if (member != null) {
            username.setText(member.getEmployeeId());
            firstName.setText(member.getFirstName());
            lastName.setText(member.getLastName());
            number.setText(member.getMobileNo());
            //address.setText(member.getAddress());

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit contact");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT MEMBER LIST", (dialog, which) -> {
            final String username1 = username.getText().toString();
            final String firstName1 = firstName.getText().toString();
            final String lastName1 = lastName.getText().toString();
            final String number1 = number.getText().toString();
            //final String address1 = address.getText().toString();

            if (TextUtils.isEmpty(username1)) {
                Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
            } else {
                mDatabase.updateMembers(new
                        Member(Objects.requireNonNull(member).getId(), username1,firstName1,lastName1,number1));
                ((Activity) context).finish();
                context.startActivity(((Activity)
                        context).getIntent());
            }
        });
        builder.setNegativeButton("CANCEL", (dialog, which) -> Toast.makeText(context, "Task cancelled",Toast.LENGTH_LONG).show());
        builder.show();
    }
}
