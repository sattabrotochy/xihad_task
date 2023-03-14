package com.example.xihad_task;

import java.util.ArrayList;

public class UserResponseModel {
    private ArrayList<UserModel> userList;
    private ArrayList<UserDataModel> otherList;

    public UserResponseModel(ArrayList<UserModel> userList, ArrayList<UserDataModel> otherList) {
        this.userList = userList;
        this.otherList = otherList;
    }

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public ArrayList<UserDataModel> getOtherList() {
        return otherList;
    }
}
