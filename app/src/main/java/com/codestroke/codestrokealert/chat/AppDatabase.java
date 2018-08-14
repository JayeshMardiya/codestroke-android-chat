package com.codestroke.codestrokealert.chat;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by nikhil on 11/12/17.
 */

@Database(entities = {Message.class}, version = AppDatabase.DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    static final int DB_VERSION = 1;
    private static final String DB_NAME = "user-database";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract MessageDao messageDao();

    //public abstract RecentChatDao recentChatDao();

}

