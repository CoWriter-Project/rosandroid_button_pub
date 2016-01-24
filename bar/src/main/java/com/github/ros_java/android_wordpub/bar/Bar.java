package com.github.ros_java.android_wordpub.bar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayout.LayoutParams;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import org.apache.commons.net.bsd.RLoginClient;
import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
import org.ros.node.topic.Publisher;
import org.ros.rosjava_tutorial_pubsub.Talker;

import std_msgs.String;




public class Bar extends RosActivity {

    private Button buttonChat;
    private PublisherNode publisherNode;
    private static final java.lang.String TAG = "wordpub";

    private java.lang.String wordTopicName;


    private java.lang.String wordList[];
    private java.lang.String wordList2[];
    private java.lang.String current_wordList[];

    public Bar(){
        super("Word Pub", "Word Pub");
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        wordList = new java.lang.String[]{"box", "fan", "dot", "jet", "cat", "bat", "pen","bed", "wig",
                                            "baby", "honey", "pillow", "penny", "lady", "tree"};
        wordList2 = new java.lang.String[]{"fox","pan","dog", "net" , "hat", "jam", "pig","web", "bus",
                                            "bunny", "hippo","money", "pony", "lego" ,"swim"};
        current_wordList= wordList;
        Button[]btn = new Button[current_wordList.length];

        GridLayout relativeLayout =  (GridLayout) findViewById(R.id.layout);

        for(int i = 0; i < current_wordList.length ; i++){

            Log.i(TAG, "passed at " + i);
            btn[i] = new Button(this);

            btn[i].setId(i);
            btn[i].setText(current_wordList[i]);
            btn[i].setTextColor(Color.parseColor("#FFFFFF"));
            btn[i].setTextSize(90);
            btn[i].setBackgroundResource(R.drawable.buttonshape);
            //btn.setPadding(15, 5, 15, 5);
            if(current_wordList[i].length()==3)
                btn[i].setGravity(Gravity.TOP);
            else
                btn[i].setGravity(Gravity.BOTTOM);
            relativeLayout.addView(btn[i]);

            btn[i].setClickable(true);
            btn[i].setOnClickListener(handleOnClick(btn[i]));
        }
        publisherNode = new PublisherNode();


    }


  @Override
  protected void init(NodeMainExecutor nodeMainExecutor) {


    // At this point, the user has already been prompted to either enter the URI
    // of a master to use or to start a master locally.

    // The user can easily use the selected ROS Hostname in the master chooser
    // activity.
    NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
    nodeConfiguration.setMasterUri(getMasterUri());

      nodeMainExecutor.execute(publisherNode, nodeConfiguration.setNodeName("android/wordpub"));


  }


    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                buttonChat = (Button)findViewById(v.getId());
                buttonChat.setTextColor(Color.parseColor("#FF3E3D3D"));
                buttonChat.setClickable(false);
                java.lang.String data = ""+buttonChat.getText();
                Log.i(TAG, "word published : " + data);
                //buttonChat.setVisibility(View.INVISIBLE);
                publisherNode.publishData(data);

            }
        };
    }



}
