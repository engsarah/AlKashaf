package com.alkahshafqatar.sarahmohamed.alkashafqatar.dagger2;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.User;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Model.BackendInteractorImpl;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {RestAPIModule.class})
public interface ApplicationComponent {

    public void inject(BackendInteractorImpl backendInteractor);
}




