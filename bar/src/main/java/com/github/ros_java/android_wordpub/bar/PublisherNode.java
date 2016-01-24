package com.github.ros_java.android_wordpub.bar;

import org.ros.namespace.GraphName;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

import std_msgs.String;

/**
 * Created by wafa on 19.01.16.
 */
public class PublisherNode implements NodeMain{

    private Publisher<std_msgs.String> wordPublisher;

    public PublisherNode(){}

    @Override
    public GraphName getDefaultNodeName() {
        return null;
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        wordPublisher = connectedNode.newPublisher("words_to_write", String._TYPE);
    }

    @Override
    public void onShutdown(Node node) {

    }

    @Override
    public void onShutdownComplete(Node node) {

    }

    @Override
    public void onError(Node node, Throwable throwable) {

    }

    public void publishData(java.lang.String data){
        //for (int n=0; n < numMessages; n++){
            std_msgs.String message = wordPublisher.newMessage();
            message.setData(""+data);
            wordPublisher.publish(message);
        //}
    }
}

