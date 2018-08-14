package com.codestroke.codestrokealert.chat;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

/**
 * Created by Nikhil Savaliya on 24/7/18
 */

@Entity
public class Message {
    @NonNull
    @PrimaryKey
    public String messageId = "";
    public String senderID;
    public String senderName;
    public String messageType;
    public long timeStamp;
    public String messageText;
    public boolean isRead = false;
    public String groupID;
    public String groupName;
    public String memberID;

    public Message() {
    }

    public Message(
            String senderId,
            String senderName,
            String messageType,
            String messageText,
            String groupID,
            String groupName,
            String memberID) {
        this.senderID = senderId;
        this.senderName = senderName;
        this.messageType = messageType;
        this.messageText = messageText;
        this.groupID = groupID;
        this.groupName = groupName;
        this.memberID = memberID;
    }

    @Exclude
    @Ignore
    public HashMap<String, Object> toHash() {
        HashMap<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("senderID", senderID);
        messageMap.put("senderName", senderName);
        messageMap.put("messageType", messageType);
        messageMap.put("timeStamp", ServerValue.TIMESTAMP);
        messageMap.put("messageText", messageText);
        messageMap.put("isRead", isRead);
        messageMap.put("groupID", groupID);
        messageMap.put("groupName", groupName);
        messageMap.put("memberID", memberID);
        return messageMap;
    }

    @Override
    public boolean equals(Object obj) {
        Message message1 = (Message) obj;
        return message1 != null && message1.messageId.equals(this.messageId);

    }

}
