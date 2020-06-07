package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener{

    /* Hint:
        1. This creates the Whack-A-Mole layout and starts a countdown to ready the user
        2. The game difficulty is based on the selected level
        3. The levels are with the following difficulties.
            a. Level 1 will have a new mole at each 10000ms.
                - i.e. level 1 - 10000ms
                       level 2 - 9000ms
                       level 3 - 8000ms
                       ...
                       level 10 - 1000ms
            b. Each level up will shorten the time to next mole by 100ms with level 10 as 1000 second per mole.
            c. For level 1 ~ 5, there is only 1 mole.
            d. For level 6 ~ 10, there are 2 moles.
            e. Each location of the mole is randomised.
        4. There is an option return to the login page.
     */
    private static final String FILENAME = "Main4Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    CountDownTimer readyTimer;
    CountDownTimer newMolePlaceTimer;
    private List<Button> ButtonList = new ArrayList<>();
    private TextView Score;
    int count = 0;
    int levelchosen = 0;
    String username = "temp";
    CountDownTimer myCountDown;
    private long timeleft = 10000;

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        myCountDown = new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                Toast.makeText(getApplicationContext(),"Get Ready In "+String.valueOf(millisUntilFinished/1000)+" seconds",Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);

            }

            public void onFinish(){
                Log.v(TAG, "Ready CountDown Complete!");
                Toast.makeText(getApplicationContext(),"GO!",Toast.LENGTH_SHORT).show();
                placeMoleTimer();
            }
        };
        myCountDown.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        if (levelchosen == 10){
            myCountDown=new CountDownTimer(1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 9){
            myCountDown=new CountDownTimer(2000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 8){
            myCountDown=new CountDownTimer(3000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 7){
            myCountDown=new CountDownTimer(4000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 6){
            myCountDown=new CountDownTimer(5000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 5){
            myCountDown=new CountDownTimer(6000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 4){
            myCountDown=new CountDownTimer(7000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

        else if (levelchosen == 3){
            myCountDown=new CountDownTimer(8000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }
        else if (levelchosen == 2){
            myCountDown=new CountDownTimer(9000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }
        else if (levelchosen == 1){
            myCountDown=new CountDownTimer(10000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.v(TAG, "New Mole Location!");
                    setNewMole(levelchosen);
                }

                @Override
                public void onFinish() {
                    updateUserScore();
                }
            };
            myCountDown.start();
        }

    }
    private static final int[] BUTTON_IDS = {
            /* HINT:
                Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
                You may use if you wish to change or remove to suit your codes.*/
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares level difficulty.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
            It also prepares the back button and updates the user score to the database
            if the back button is selected.

         */
        Intent getscore = getIntent();
        username = getscore.getStringExtra("username");
        levelchosen = getscore.getIntExtra("levelchosen",0);

        Button backbutton = findViewById(R.id.buttonback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityName = new Intent(Main4Activity.this,Main3Activity.class);
                activityName.putExtra("enteredname", username);
                startActivity(activityName);
            }
        });

        Button ButtonLeft = (Button) findViewById(R.id.ButtonLeft);
        Button ButtonRight = (Button) findViewById(R.id.ButtonRight);
        Button ButtonMiddle = (Button) findViewById(R.id.ButtonMiddle);
        Button ButtonMiddleLeft = (Button) findViewById(R.id.ButtonLeft);
        Button ButtonMiddleMiddle = (Button) findViewById(R.id.ButtonMiddleMiddle);
        Button ButtonMiddleRight = (Button) findViewById(R.id.ButtonMiddleRight);
        Button ButtonBottomLeft = (Button) findViewById(R.id.ButtonBottomLeft);
        Button ButtonBottomMiddle = (Button) findViewById(R.id.ButtonBottomMiddle);
        Button ButtonBottomRight = (Button) findViewById(R.id.ButtonButtomRight);
        ButtonList.add(ButtonMiddle);
        ButtonList.add(ButtonLeft);
        ButtonList.add(ButtonRight);
        ButtonList.add(ButtonMiddleLeft);
        ButtonList.add(ButtonMiddleMiddle);
        ButtonList.add(ButtonMiddleRight);
        ButtonList.add(ButtonBottomLeft);
        ButtonList.add(ButtonBottomMiddle);
        ButtonList.add(ButtonBottomRight);

        Score = (TextView) findViewById(R.id.Score);
        String numberAsString = String.valueOf(count);
        Score.setText(numberAsString);

        ButtonLeft.setOnClickListener(this);
        ButtonMiddle.setOnClickListener(this);
        ButtonRight.setOnClickListener(this);
        ButtonMiddleLeft.setOnClickListener(this);
        ButtonMiddleMiddle.setOnClickListener(this);
        ButtonMiddleRight.setOnClickListener(this);
        ButtonBottomLeft.setOnClickListener(this);
        ButtonBottomMiddle.setOnClickListener(this);
        ButtonBottomRight.setOnClickListener(this);

    }
    @Override
    protected void onStart(){
        super.onStart();
        readyTimer();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, FILENAME + ": Hit, score added!");
            Log.v(TAG, FILENAME + ": Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText().equals("*")){
            Log.v(TAG, "Hit, score added!");
        }
        else {
            Log.v(TAG, FILENAME + ": Missed, point deducted!");
        }

    }

    public void setNewMole(int level)
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole. Adds additional mole if the level difficulty is from 6 to 10.
         */
        for (Button i: ButtonList){
            i.setText("O");
        }
        if (level < 6) {
            Random ran = new Random();
            int randomLocation = ran.nextInt(9);
            Button random = ButtonList.get(randomLocation);
            random.setText("*");
        }

        else {
            Random ran = new Random();
            int randomLocation = ran.nextInt(9);
            Button random = ButtonList.get(randomLocation);
            int randomLocation2 = ran.nextInt(9);
            Button random2 = ButtonList.get(randomLocation);
            random.setText("*");
            random2.setText("*");
        }

    }

    private void updateUserScore()
    {

     /* Hint:
        This updates the user score to the database if needed. Also stops the timers.
        Log.v(TAG, FILENAME + ": Update User Score...");
      */

        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.updateScore(username,levelchosen,count);
    }

    @Override
    public void onClick(View v) {
        Button temp = (Button) v;
        if (temp.getText() == "*"){
            count = count + 1;
            temp.setText("O");
            String numberAsString = String.valueOf(count);
            Score.setText(numberAsString);
            setNewMole(levelchosen);
            doCheck(temp);
        }
        else {
            count = count - 1;
            String numberAsString = String.valueOf(count);
            Score.setText(numberAsString);
            setNewMole(levelchosen);
            doCheck(temp);
        }
    }

}
