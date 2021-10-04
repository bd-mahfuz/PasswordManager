package com.jfaq.passmanager.model;

import com.jfaq.passmanager.enums.Action;

public class ActionModel {
    private Action action;
    private String message;

    public ActionModel(Action action, String message) {
        this.action = action;
        this.message = message;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
