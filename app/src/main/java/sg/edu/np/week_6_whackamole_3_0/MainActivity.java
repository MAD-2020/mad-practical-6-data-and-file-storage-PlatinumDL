package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
        1. This is the main page for user to log in
        2. The user can enter - Username and Password
        3. The user login is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user does not exist. This loads the level selection page.
        4. There is an option to create a new user account. This loads the create user page.
     */
    private static final String FILENAME = "MainActivity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint:
            This method creates the necessary login inputs and the new user creation ontouch.
            It also does the checks on button selected.
            Log.v(TAG, FILENAME + ": Create new user!");
            Log.v(TAG, FILENAME + ": Logging in with: " + etUsername.getText().toString() + ": " + etPassword.getText().toString());
            Log.v(TAG, FILENAME + ": Valid User! Logging in");
            Log.v(TAG, FILENAME + ": Invalid user!");
        */
        final EditText username = findViewById(R.id.editname);
        final EditText password = findViewById(R.id.editpassword);
        final Button login = findViewById(R.id.loginbutton);
        TextView register = findViewById(R.id.newactivityview);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, FILENAME + ": Create new user!");
                Intent activityName = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(activityName);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredname = username.getText().toString();
                String enteredpassword = password.getText().toString();
                if (isValidUser(enteredname, enteredpassword)) {
                    Intent activityName = new Intent(MainActivity.this, Main3Activity.class);
                    activityName.putExtra("enteredname", enteredname);
                    startActivity(activityName);
                }
                else {
                    Toast.makeText(login.getContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onStop(){
        super.onStop();
        finish();
    }

    public boolean isValidUser(String userName, String password){

        /* HINT:
            This method is called to access the database and return a true if user is valid and false if not.
            Log.v(TAG, FILENAME + ": Running Checks..." + dbData.getMyUserName() + ": " + dbData.getMyPassword() +" <--> "+ userName + " " + password);
            You may choose to use this or modify to suit your design.
         */
        UserData temp = new UserData();
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        temp = dbHandler.findUser(userName);
        dbHandler.close();
        if (temp == null) {
            return false;
        }
        if (temp.getMyPassword().equals(password)) {
            return true;
        }
        else {
            return false;
        }



    }

}
