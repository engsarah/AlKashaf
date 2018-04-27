package com.alkahshafqatar.sarahmohamed.alkashafqatar.Presenter;

import android.app.Activity;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Model.BackendInteractor;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Model.BackendInteractorImpl;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.LoginFragment;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.LoginActivity;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.MainActivity;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {


    private BackendInteractor interactor = new BackendInteractorImpl(this);
    private LoginActivity loginActivity;
    private MainActivity mainActivity;

    public MainPresenterImpl(LoginActivity loginActivity, MainActivity mainActivity) {

        this.loginActivity = loginActivity;
        this.mainActivity = mainActivity;
    }

    @Override
    public void authenticateUser(String username, String password) {
        loginActivity.showPrgoressDialog("Authenticating...");
        interactor.authenticateUser(username, password);
    }

    @Override
    public void fetchUserProjects(String userId) {
        interactor.fetchUserProjects(userId);

    }

    @Override
    public void onSuccessfulLogin(String guid) {
        //loginFragment.displayToast("Token Generated Successfully !!!" + guid);


    }

    @Override
    public void onLoginFaliure() {
        loginActivity.showError();

    }

    @Override
    public void showProjectsList(List<Project> projectList) {
        mainActivity.loadProjectsFragment(projectList);
    }

    @Override
    public void showWelcomeFragment(String guid) {
        mainActivity.gotoWelcomeFragment(guid);
    }

    @Override
    public void showMainActivity(String guid) {
        loginActivity.gotoMainActivity(guid);
    }
}
