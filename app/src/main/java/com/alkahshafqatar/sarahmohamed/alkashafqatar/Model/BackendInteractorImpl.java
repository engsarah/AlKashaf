package com.alkahshafqatar.sarahmohamed.alkashafqatar.Model;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.User;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Presenter.MainPresenter;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Presenter.MainPresenterImpl;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.dagger2.ApplicationComponent;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.dagger2.DaggerApplicationComponent;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.dagger2.RestAPIModule;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BackendInteractorImpl implements BackendInteractor {

    public static final String BASE_URL = "http://api.alkashafqatar.com";
    private MainPresenter presenter;
    private ApplicationComponent applicationComponent;

    @Inject
    protected Retrofit retrofitObject;


    protected User user;

    public BackendInteractorImpl(MainPresenterImpl mainPresenter) {
        presenter = mainPresenter;
        //using generated dagger class to inject dependicies specified by modules defined in the ApplicationComponent
        //interface which is annotated by @Component
        applicationComponent = DaggerApplicationComponent.builder().restAPIModule(new RestAPIModule(BASE_URL)).build();
        applicationComponent.inject(this);
    }

    @Override
    public void authenticateUser(String username, String password) {

        RestAPIManager restManager = retrofitObject.create(RestAPIManager.class);
        User user = new User(username, password);
        Call call = restManager.authenticateUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String guid = response.body();
                if(guid != null)
                {
                    presenter.onSuccessfulLogin(guid);
                    presenter.showMainActivity(guid);
                }
                else
                {
                    presenter.onLoginFaliure();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    @Override
    public void fetchUserProjects(String userId) {

        RestAPIManager restManager = retrofitObject.create(RestAPIManager.class);
        Call call = restManager.fetchProjects(userId);

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                List<Project> projectList = response.body();
                presenter.showProjectsList(projectList);
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {

            }
        });
    }



}
