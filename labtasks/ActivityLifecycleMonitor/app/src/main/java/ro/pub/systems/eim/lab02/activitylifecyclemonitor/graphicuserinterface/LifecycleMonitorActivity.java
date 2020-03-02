package ro.pub.systems.eim.lab02.activitylifecyclemonitor.graphicuserinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import ro.pub.systems.eim.lab02.activitylifecyclemonitor.R;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Constants;
import ro.pub.systems.eim.lab02.activitylifecyclemonitor.general.Utilities;

public class LifecycleMonitorActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private EditText uname;
    private EditText passwd;
    private CheckBox rememberMe;


    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            uname = (EditText)findViewById(R.id.username_edit_text);
            passwd = (EditText)findViewById(R.id.password_edit_text);
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.ok_button_content))) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                String username = uname.getText().toString();
                String password = passwd.getText().toString();
                View popupContent;
                if (Utilities.allowAccess(getApplicationContext(), username, password)) {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_success, null);
                } else {
                    popupContent = layoutInflater.inflate(R.layout.popup_window_authentication_fail, null);
                }
                final PopupWindow popupWindow = new PopupWindow(popupContent, android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
                Button dismissButton = (Button)popupContent.findViewById(R.id.dismiss_button);
                dismissButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
            if (((Button)view).getText().toString().equals(getResources().getString(R.string.cancel_button_content))) {
                uname.setText(getResources().getText(R.string.empty));
                passwd.setText(getResources().getText(R.string.empty));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_monitor);

        uname = (EditText)findViewById(R.id.username_edit_text);
        passwd = (EditText)findViewById(R.id.password_edit_text);
        rememberMe = (CheckBox)findViewById(R.id.remember_me_checkbox);
        if ((savedInstanceState != null) && (savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT) != null)) {
            uname.setText(savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT));
        }


        Button okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);


        Log.d(Constants.TAG, "onCreate() method was invoked without a previous state");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "onStart() method was invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "onResume() method was invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG, "onPause() method was invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "onStop() method was invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method was invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "onRestart() method was invoked");
    }

    // metode folosite pentru salvarea si restaurarea starii

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onSaveInstanceState(savedInstanceState);

        if (rememberMe.isChecked()) {
            savedInstanceState.putString(Constants.USERNAME_EDIT_TEXT, uname.getText().toString());
            savedInstanceState.putString(Constants.PASSWORD_EDIT_TEXT, passwd.getText().toString());
            savedInstanceState.putBoolean(Constants.REMEMBER_ME_CHECKBOX, rememberMe.isChecked());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // apelarea metodei din activitatea parinte este recomandata, dar nu obligatorie
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT) != null) {
            uname.setText(savedInstanceState.getString(Constants.USERNAME_EDIT_TEXT));
            passwd.setText(savedInstanceState.getString(Constants.PASSWORD_EDIT_TEXT));
        }
    }
}
