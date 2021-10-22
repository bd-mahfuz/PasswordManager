package com.jfaq.passmanager.model;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.jfaq.passmanager.BR;

public class AddCredentialModel implements Observable {

    private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    private String title;
    private String webSiteUrl;
    private String imageUrl;
    private int categoryId;
    private String categoryName;
    private String email;
    private String userName;
    private String password;
    private String confirmPassword;
    private String passwordHint;

    public AddCredentialModel() {
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        propertyChangeRegistry.notifyChange(this, BR.title);
    }
    @Bindable
    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
        propertyChangeRegistry.notifyChange(this, BR.webSiteUrl);
    }
    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        propertyChangeRegistry.notifyChange(this, BR.imageUrl);
    }
    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        propertyChangeRegistry.notifyChange(this, BR.categoryId);
    }
    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        propertyChangeRegistry.notifyChange(this, BR.categoryName);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        propertyChangeRegistry.notifyChange(this, BR.email);
    }
    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        propertyChangeRegistry.notifyChange(this, BR.userName);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        propertyChangeRegistry.notifyChange(this, BR.password);
    }
    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        propertyChangeRegistry.notifyChange(this, BR.confirmPassword);
    }
    @Bindable
    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
        propertyChangeRegistry.notifyChange(this, BR.passwordHint);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }
}
