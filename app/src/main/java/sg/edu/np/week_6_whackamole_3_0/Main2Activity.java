package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    /* Hint:
        1. This is the create new user page for user to log in
        2. The user can enter - Username and Password
        3. The user create is checked against the database for existence of the user and prompts
           accordingly via Toastbox if user already exists.
        4. For the purpose the practical, successful creation of new account will send the user
           back to the login page and display the "User account created successfully".
           the page remains if the user already exists and "User already exist" toastbox message will appear.
        5. There is an option to cancel. This loads the login user page.
     */


    private static final String FILENAME = "Main2Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /* Hint:
            This prepares the create and cancel account buttons and interacts with the database to determine
            if the new user created exists already or is new.
            If it exists, information is displayed to notify the user.
            If it does not exist, the user is created in the DB with default data "0" for all levels
            and the login page is loaded.

            Log.v(TAG, FILENAME + ": New user created successfully!");
            Log.v(TAG, FILENAME + ": User already exist during new user creation!");

         */
        final EditText username = findViewById(R.id.editname2);
        final EditText password = findViewById(R.id.editpassword2);
        Button cancel = findViewById(R.id.cancelbutton);
        final Button create = findViewById(R.id.createbutton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityName = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(activityName);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                ArrayList<Integer> levellist = new ArrayList<Integer>(10);
                ArrayList<Integer> scorelist = new ArrayList<Integer>(10);

                for (int i = 0; i <10; i++) {
                    levellist.add(i+1);
                    scorelist.add(0);
                }

                if (ifUserExist(username2)){
                    UserData newuser = new UserData(username2,password2,levellist,scorelist);
                    MyDBHandler dbHandler = new MyDBHandler(Main2Activity.this, null, null, 1);
                    dbHandler.addUser(newuser);
                    dbHandler.close();
                    Log.v(TAG, FILENAME + ": New user created successfully!");
                    Toast.makeText(create.getContext(), "User created successfully!", Toast.LENGTH_SHORT).show();
                    Intent activityName = new Intent(Main2Activity.this,MainActivity.class);
                    Log.v(TAG, "DEBUG2");
                    startActivity(activityName);
                }

                else{
                    Log.v(TAG, FILENAME + ": User already exist during new user creation!");
                }

            }
        });
    }

    protected void onStop() {
        super.onStop();
        finish();
    }

    public boolean ifUserExist(String userName){
        UserData temp = null;
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        temp = dbHandler.findUser(userName);
        dbHandler.close();
        if (temp == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
