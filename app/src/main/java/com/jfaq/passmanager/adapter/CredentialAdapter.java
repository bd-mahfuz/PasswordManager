package com.jfaq.passmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jfaq.passmanager.R;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.databinding.LayoutCredentialItemBinding;

import java.util.List;

public class CredentialAdapter extends RecyclerView.Adapter<CredentialAdapter.ViewHolder> {

    private Context context;
    private List<CredentialInfo> credentialInfoList;

    public CredentialAdapter(Context context, List<CredentialInfo> credentialInfoList) {
        this.context = context;
        this.credentialInfoList = credentialInfoList;
    }

    @NonNull
    @Override
    public CredentialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCredentialItemBinding layoutCredentialItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_credential_item, parent, false);
        return new ViewHolder(layoutCredentialItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CredentialAdapter.ViewHolder holder, int position) {
        CredentialInfo credentialInfo = credentialInfoList.get(position);
        holder.layoutCredentialItemBinding.setCredentialInfo(credentialInfo);
    }

    @Override
    public int getItemCount() {
        return credentialInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutCredentialItemBinding layoutCredentialItemBinding;
        public ViewHolder(@NonNull LayoutCredentialItemBinding layoutCredentialItemBinding) {
            super(layoutCredentialItemBinding.getRoot());
            this.layoutCredentialItemBinding = layoutCredentialItemBinding;
        }
    }
}
