package com.alkahshafqatar.sarahmohamed.alkashafqatar.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel.Project;
import com.alkahshafqatar.sarahmohamed.alkashafqatar.R;

public class LoginActivity extends AppCompatActivity implements LoginView, ProjectListFragment.OnListFragmentInteractionListener,LoginFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener, WelcomeFragment.OnFragmentInteractionListener
{


    private FragmentManager mFragmentManager;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);

        LoginFragment loginFragment = new LoginFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);

        mFragmentTransaction.add(R.id.fragment_container,loginFragment);
        mFragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void gotoMainActivity(String guid)
    {
        SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefEditor.putString("userId", guid);
        prefEditor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userId", guid);
        startActivity(intent);

    }






    @Override
    public void onListFragmentInteraction(Project project) {
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
        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        // Update your UI here.
                        getFragmentManager().popBackStack();
                    }
                });

        mFragmentTransaction.replace(R.id.fragment_container, fragobj);
        mFragmentTransaction.addToBackStack("details_frag").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void showPrgoressDialog(String msg)
    {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void showError()
    {
        hideProgressDialog();
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Error")
                .setMessage("Invalid Credentials...please enter valid username & password")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void hideProgressDialog()
    {
        progressDialog.hide();
    }
}
