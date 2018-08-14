package com.codestroke.codestrokealert.chat;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by nikhil on 11/12/17.
 */

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message WHERE groupID= :conversationId")
    LiveData<List<Message>> getMessages(String conversationId);

    @Query("SELECT COUNT(*) from message")
    int countMessages();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessge(Message message);


    @Query("DELETE FROM message")
    void delete();

    @Query("SELECT timeStamp FROM message  WHERE groupID== :conversationId ORDER BY timeStamp DESC LIMIT 1")
    long getTimeStamp(String conversationId);

}
