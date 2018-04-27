package com.alkahshafqatar.sarahmohamed.alkashafqatar.Presenter;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;

import java.util.List;

public interface MainPresenter {

    public void authenticateUser(String username, String password);
    public void fetchUserProjects(String userId);
    public void onSuccessfulLogin(String guid);
    public void onLoginFaliure();
    public void showProjectsList(List<Project> projectList);
    public void showWelcomeFragment(String guid);
    public void showMainActivity(String guid);

}
