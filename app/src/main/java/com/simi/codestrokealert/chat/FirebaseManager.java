package com.simi.codestrokealert.chat;

import android.app.Activity;
import android.content.Context;
import android.icu.util.UniversalTimeScale;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Nikhil Savaliya on 24/7/18
 */

public class FirebaseManager {

    public static final String USERS = "Users";
    public static final String RECENT_CHAT = "RecentChats";
    public static final String MESSAGES = "MESSAGE";
    public static final String MEMBERS="MEMBERS";

    public static String memberId="";
    private static final DatabaseReference REF_USERS = FirebaseDatabase
            .getInstance().getReference().child(USERS);
/*
    private static final DatabaseReference REF_RECENT_CHAT = FirebaseDatabase
            .getInstance().getReference().child(RECENT_CHAT);
*/
    private static final DatabaseReference REF_MESSAGES = FirebaseDatabase
            .getInstance().getReference().child(MESSAGES);

    private static final DatabaseReference REF_MEMBERS = FirebaseDatabase
            .getInstance().getReference().child(MEMBERS);

    public static TypingChangeListener typingChangeListner;
    private static ChildEventListener recentChatListener;
    private static ChildEventListener messageListener;
    private static ValueEventListener memberListener;

    public static void loginAnonymously(Activity context){
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            }

                        // ...
                    }
                });
    }
    public static void addMessageChildEventListner(String conversationId, final Context context, long timeStamp) {
        messageListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                AppDatabase.getInstance(context)
                        .messageDao().insertMessge(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                AppDatabase.getInstance(context)
                        .messageDao().insertMessge(message);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        if (timeStamp == 0)
            REF_MESSAGES.child(conversationId).addChildEventListener(messageListener);
        else REF_MESSAGES.child(conversationId).orderByChild("timeStamp").startAt(timeStamp)
                .addChildEventListener(messageListener);

    }

    /* public static void addRecentChatChildEventListner(String userId, Context context) {
         recentChatListener = new ChildEventListener() {
             boolean isTyping = false;

             @Override
             public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                 RecentChat recentChat1 = dataSnapshot.getValue(RecentChat.class);
                 AppDatabase.getInstance(context)
                         .recentChatDao().insertRecentChat(recentChat1);
             }

             @Override
             public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                 RecentChat recentChat1 = dataSnapshot.getValue(RecentChat.class);
                 if (!isTyping) {
                     if (recentChat1 != null && recentChat1.isTyping) {
                         if (typingChangeListner != null) {
                             typingChangeListner.onTypingChange(
                                     recentChat1.conversationId,
                                     true);
                             isTyping = true;
                             return;
                         }
                     }
                     AppDatabase.getInstance(context)
                             .recentChatDao().insertRecentChat(recentChat1);
                 } else {
                     isTyping = false;
                     if (recentChat1 != null && typingChangeListner != null) {
                         typingChangeListner.onTypingChange(
                                 recentChat1.conversationId,
                                 false);
                     }
                 }
             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         };
         REF_RECENT_CHAT.child(userId).addChildEventListener(recentChatListener);
     }
 */
    public static void sendMessage(Message message) {
        HashMap<String, Object> messageMap = message.toHash();
        DatabaseReference reference = REF_MESSAGES.child(message.groupID).push();
        message.messageId = reference.getKey() == null ? "" : reference.getKey();
        messageMap.put("messageId", message.messageId);
        //Add Message to message node
        reference.setValue(messageMap);
        //Update child on Recent chat
  /*      REF_RECENT_CHAT.child(String.valueOf(Utility.CURRENT_USER_ID))
                .child(otherUserId)
                .updateChildren(RecentChat.
                        toMap(message, messageMap.get("timeStamp")));
        REF_RECENT_CHAT.child(otherUserId)
                .child(String.valueOf(Utility.CURRENT_USER_ID))
                .updateChildren(RecentChat.
                        toMap(message, messageMap.get("timeStamp")));
*/
        //updateBadge(otherUserId);
    }

/*
    private static void updateBadge(String otherUserId) {
        DatabaseReference reference = REF_RECENT_CHAT.child(otherUserId)
                .child(String.valueOf(Utility.CURRENT_USER_ID))
                .child("badgeCount");
        reference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Integer badgeCount = mutableData.getValue(Integer.class);
                if (badgeCount == null) {
                    badgeCount = 1;
                } else {
                    badgeCount++;
                }
                mutableData.setValue(badgeCount);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.i("Error", databaseError.getDetails());
                }  //   updateBadge(userId, conversationId);

                // Transaction completed
            }

        });
    }
*/
/*

    public static void removeRecentChatListener(String userId) {
        REF_RECENT_CHAT.child(userId).removeEventListener(recentChatListener);
    }
*/

    public static void removeMessageChatListener(String convId) {
        REF_MESSAGES.child(convId).removeEventListener(recentChatListener);
    }

/*
    public static void resetBadge(String otherUserId, Context context) {
        DatabaseReference reference = REF_RECENT_CHAT.child(String.valueOf(Utility.CURRENT_USER_ID))
                .child(otherUserId).child("badgeCount");
        reference.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer badgeCount = mutableData.getValue(Integer.class);
                if (badgeCount == null) {
                    return Transaction.success(mutableData);
                }
                badgeCount = 0;
                mutableData.setValue(badgeCount);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                if (databaseError == null) {
                    String convId = Utility.getConversationId(otherUserId);
                    if (convId != null) {
                        AppDatabase.getInstance(context)
                                .recentChatDao().updateBadgeCount(convId
                                , 0
                        );
                    }
                }
            }
        });
    }
*/

/*
    public static void updateMessageTypingState(boolean isTyping,
                                                String memberId) {
        String otherUserId = Utility.getOtherUserId(memberId);
        DatabaseReference reference = REF_RECENT_CHAT.
                child(otherUserId).child(String.valueOf(Utility.CURRENT_USER_ID));
        Map<String, Object> map = new HashMap<>();
        map.put("isTyping", isTyping);
        reference.updateChildren(map);


    }
*/
    public static void updateMemberNode(String groupId){
        String id= String.valueOf(Utility.CURRENT_USER_ID);
        HashMap<String,Object> map=new HashMap<>();
        map.put(id, id);
        REF_MEMBERS.getRef().child(groupId).updateChildren(map);
    }
    public static void addMemberListner(final Context context, final String groupId){
        memberListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memberId="";
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    if (snapshot!=null){
                        String id=snapshot.getValue(String.class);
                        memberId= TextUtils.isEmpty(memberId) ? ""
                                + id
                                : memberId.concat("," + id);
                        Utility.addMembersToPref(context,groupId,memberId);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        REF_MEMBERS.child(groupId).addValueEventListener(memberListener);
    }

    public interface TypingChangeListener {
        void onTypingChange(String conversationId, boolean isTyping);
    }
}
