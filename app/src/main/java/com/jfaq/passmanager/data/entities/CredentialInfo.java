package com.jfaq.passmanager.data.entities;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jfaq.passmanager.R;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "credential_info",
        foreignKeys = {@ForeignKey(entity = CredentialCategory.class, parentColumns = "id",
                childColumns = "credential_category_id")},
        indices = {@Index(value = "credential_category_id")})
public class CredentialInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_name")
    private String userName;
    private String email;
    private String password;
    private String hint;
    private String title;
    private String url;
    @ColumnInfo(name = "image_url")
    private String imageUrl;
    @ColumnInfo(name = "credential_category_id")
    private int credentialCategoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCredentialCategoryId() {
        return credentialCategoryId;
    }

    public void setCredentialCategoryId(int credentialCategoryId) {
        this.credentialCategoryId = credentialCategoryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @BindingAdapter("imageUrl")
    public  static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.photo_female_1)
                .apply(new RequestOptions().circleCrop())
                .into(imageView);
    }

}
