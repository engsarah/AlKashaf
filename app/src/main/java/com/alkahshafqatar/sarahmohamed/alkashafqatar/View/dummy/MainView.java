package com.alkahshafqatar.sarahmohamed.alkashafqatar.View.dummy;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;

import java.util.List;

public interface MainView {

    public void loadProjectsFragment(List<Project> projectList);
    public void gotoWelcomeFragment(String guid);
}
