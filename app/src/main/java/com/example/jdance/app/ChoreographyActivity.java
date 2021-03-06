package com.example.jdance.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jdance.app.model.Choreography;
import com.example.jdance.app.model.Repository;
import com.example.jdance.app.model.Robot;
import com.example.jdance.app.util.DeleteOnItemLongClickListener;
import com.example.jdance.app.util.ItemAdapter;

import java.util.List;

/**
 * Created by mclo on 08/12/13.
 */
public class ChoreographyActivity extends ListActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Choreography> choreographies = Repository.getInstance().getChoreographies();
//        setListAdapter(new ArrayAdapter<Choreography>(this, R.layout.list_item, choreographies));
        setListAdapter(new ItemAdapter(this, choreographies, R.drawable.ic_choreography));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        //send to dance floor
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //intent
                Robot robot = (Robot) getIntent().getExtras().get("ROBOT");

                //set ic_choreography to the robot
                Choreography choreography = (Choreography) parent.getAdapter().getItem(position);
                robot.setChorepgraphy(choreography);
                List<Robot> danceFloor = Repository.getInstance().getDanceFloor();
                if (!danceFloor.contains(robot))
                    danceFloor.add(robot);


                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        robot.toString() + " <- " + choreography.toString(), Toast.LENGTH_SHORT).show();

                //new intent
                Intent intent = new Intent(getApplicationContext(), DanceFloorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation_to_right_enter, R.anim.animation_to_right_leave);

                //finish activity
                finish();
            }
        });

        //delete Choreography
        listView.setOnItemLongClickListener(new DeleteOnItemLongClickListener(choreographies));
    }

    //override animation transition
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation_to_left_enter, R.anim.animation_to_left_leave);
    }
}
