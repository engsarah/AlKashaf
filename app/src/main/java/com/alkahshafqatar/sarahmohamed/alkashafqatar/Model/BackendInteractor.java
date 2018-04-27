package com.alkahshafqatar.sarahmohamed.alkashafqatar.Model;

public interface BackendInteractor {

    public void authenticateUser(String username, String password);
    public void fetchUserProjects(String userId);
}
