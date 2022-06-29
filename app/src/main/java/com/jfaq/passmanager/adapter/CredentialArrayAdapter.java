package com.jfaq.passmanager.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jfaq.passmanager.R;
import com.jfaq.passmanager.data.entities.CredentialCategory;

import java.util.List;

public class CredentialArrayAdapter extends ArrayAdapter<CredentialCategory> {

    private Context context;
    private List<CredentialCategory> credentialCategories;


    public CredentialArrayAdapter(@NonNull Context context, int resource, @NonNull List<CredentialCategory> credentialCategories) {
        super(context, resource, credentialCategories);
        this.context = context;
        this.credentialCategories = credentialCategories;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }

        TextView label=convertView.findViewById(R.id.category_tv);
        label.setText(credentialCategories.get(position).getTitle());

        if (position == 0) {//Special style for dropdown header
            label.setTextColor(context.getResources().getColor(R.color.blue_grey_900));
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new ArrayFilter();
    }

    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CredentialCategory) resultValue).getTitle();
        }
    }

}
