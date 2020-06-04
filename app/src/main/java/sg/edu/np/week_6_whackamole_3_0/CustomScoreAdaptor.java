package sg.edu.np.week_6_whackamole_3_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    /* Hint:
        1. This is the custom adaptor for the recyclerView list @ levels selection page

     */
    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "Whack-A-Mole3.0!";
    String username;
    List<Integer> levels = new ArrayList<>();
    List<Integer> score = new ArrayList<>();

    public CustomScoreAdaptor(UserData userdata){
        /* Hint:
        This method takes in the data and readies it for processing.
         */
        username = userdata.getMyUserName();
        levels = userdata.getLevels();
        score = userdata.getScores();
    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        /* Hint:
        This method dictates how the viewholder layuout is to be once the viewholder is created.
         */
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.level_select,
                parent,
                false);
        CustomScoreViewHolder viewHolder = new CustomScoreViewHolder(item);
        return viewHolder;
    }

    public void onBindViewHolder(final CustomScoreViewHolder holder, final int position){

        /* Hint:
        This method passes the data to the viewholder upon bounded to the viewholder.
        It may also be used to do an onclick listener here to activate upon user level selections.

        Log.v(TAG, FILENAME + " Showing level " + level_list.get(position) + " with highest score: " + score_list.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());
         */
        holder.Button.setText("Level " + Integer.toString(levels.get(position)));
        holder.Score.setText("Highest Score: "+ Integer.toString(score.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("username", username);
                extras.putInt("levelchosen", levels.get(levels.get(position)));
                Log.v(TAG, FILENAME + " Showing level " + levels.get(position) + " with highest score: " + score.get(position));
                Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + username);

                Intent activityName = new Intent(holder.Button.getContext(),Main4Activity.class);
                activityName.putExtras(extras);
                holder.Button.getContext().startActivity(activityName);


            }
        });
    }

    public int getItemCount(){
        /* Hint:
        This method returns the the size of the overall data.
         */
        return levels.size();
    }
}