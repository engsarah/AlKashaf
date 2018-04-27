package com.alkahshafqatar.sarahmohamed.alkashafqatar.View;



import android.app.ProgressDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.Presenter.MainPresenterImpl;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.R;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.View.dummy.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProjectListFragment.OnListFragmentInteractionListener, MainView {


    private DrawerLayout mDrawerLayout;
    private MainPresenterImpl presenter;
    protected ProgressDialog progressDialog;
    protected  Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.main_activity_drawer_layout);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView username = (TextView) headerView.findViewById(R.id.username);
        username.setText("Your Text Here");

        presenter = new MainPresenterImpl(null,this);
        final String userId = getIntent().getExtras().getString("userId");
        progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        if(menuItem.getItemId() == R.id.nav_projects)
                        {
                            presenter.fetchUserProjects(userId);
                            progressDialog.setIndeterminate(true);
                            progressDialog.setMessage("Reloading your projects...");
                            progressDialog.show();
                        }
                        return true;
                    }
                });

        navigationView.getMenu().getItem(1).setChecked(true);


        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened

                        //drawerView.ge
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_burger_new);
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        gotoWelcomeFragment(userId);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Project project) {

        navigationView.getMenu().getItem(1).setChecked(false);
        Bundle bundle = new Bundle();
        bundle.putString("name", project.name);
        bundle.putString("id", project.id);
        bundle.putString("buildingName", project.buildingName);
        bundle.putString("desc", project.description);
        DetailsFragment fragobj = new DetailsFragment();
        fragobj.setArguments(bundle);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                R.anim.slide_out_right, R.anim.slide_in_right);
        mFragmentTransaction.replace(R.id.content_frame, fragobj);
        mFragmentTransaction.addToBackStack("details_screen_navigation");
        mFragmentTransaction.commit();

    }

    @Override
    public void gotoWelcomeFragment(String guid) {


        WelcomeFragment welcomeFragment = new WelcomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", guid);
        welcomeFragment.setArguments(bundle);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                R.anim.slide_out_right, R.anim.slide_in_right);
        mFragmentTransaction.replace(R.id.content_frame,welcomeFragment);
        mFragmentTransaction.commit();
    }



    @Override
    public void loadProjectsFragment(List<Project> projectList)
    {

        if(progressDialog.isShowing())
        {
            progressDialog.hide();
        }
        ProjectListFragment listFragment = new ProjectListFragment();
        listFragment.populateProjectsList(projectList);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                R.anim.slide_out_right, R.anim.slide_in_right);
        mFragmentTransaction.replace(R.id.content_frame,listFragment);
        mFragmentTransaction.commit();
    }
}
