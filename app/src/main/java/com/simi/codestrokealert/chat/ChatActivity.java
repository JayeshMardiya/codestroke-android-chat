package com.simi.codestrokealert.chat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.simi.codestrokealert.R;

import java.util.List;


public class ChatActivity extends AppCompatActivity {

    private static final String CONVERSATION_ID = "c_id";
    private static final String CONVERSATION_NAME = "c_name";
    private ChatAdapter chatAdapter;
    private String groupId;
    private String groupName;
    private boolean isTyping = false;
    private RecyclerView rvChat;
    private ImageButton btnSend;
    private EditText etMsg;
    private TextView tvDate;


    public static void pushActivity(Context activity, String conversationId, String conversationName) {
        Intent intent = new Intent(activity, ChatActivity.class);
        intent.putExtra(CONVERSATION_ID, conversationId);
        intent.putExtra(CONVERSATION_NAME, conversationName);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
        init();
    }

    private void init() {
        rvChat = findViewById(R.id.rv_chat);
        btnSend = findViewById(R.id.btn_send);
        etMsg = findViewById(R.id.et_msg);
        tvDate = findViewById(R.id.tv_date);
        groupId = getIntent().getStringExtra(CONVERSATION_ID);
        groupName = getIntent().getStringExtra(CONVERSATION_NAME);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    rvChat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rvChat.scrollToPosition(
                                    chatAdapter.getItemCount() - 1);
                        }
                    }, 100);
                    tvDate.setVisibility(View.GONE);
                }
            }
        });
        rvChat.setLayoutManager(new

                LinearLayoutManager(this));
        chatAdapter = new

                ChatAdapter();
        rvChat.setAdapter(chatAdapter);
        long timeStamp = AppDatabase.getInstance(this)
                .messageDao().getTimeStamp(groupId);
        FirebaseManager.addMessageChildEventListner(groupId, this,
                timeStamp);
        FirebaseManager.updateMemberNode(groupId);
        FirebaseManager.addMemberListner(this
                , groupId);
        AppDatabase.getInstance(this).messageDao().getMessages(groupId).observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                if (messages != null) {
                    rvChat.scrollToPosition(chatAdapter.addMessages(messages) - 1);
                    tvDate.setVisibility(View.GONE);
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = etMsg.getText().toString();
                if (!TextUtils.isEmpty(messageText)) {
                    sendMessage(messageText);
                    etMsg.setText("");
                }
            }
        });
        rvChat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isFirst = true;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_AXIS_VERTICAL) {
                    if (!TextUtils.isEmpty(tvDate.getText())) {
                        tvDate.setVisibility(View.VISIBLE);
                        if (isFirst) {
                            tvDate.animate()
                                    .translationY(
                                            Utility.convertDpToPixel(10, ChatActivity.this))
                                    .setListener(null);
                            isFirst = false;
                        }
                    }
                } else {
                    isFirst = true;
                    tvDate.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvDate.animate()
                                    .translationY(0)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            tvDate.setVisibility(View.GONE);
                                        }
                                    });
                        }
                    }, 2000);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int pos = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();
                tvDate.setText(
                        Utility.getDate(
                                chatAdapter.msgList.get(pos).timeStamp));

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void sendMessage(String messageText) {
        Message message = new Message(
                String.valueOf(Utility.CURRENT_USER_ID),
                Utility.CURRENT_USER,
                "text",
                messageText,
                groupId,
                groupName,
                Utility.getMemberId(this,groupId));
        FirebaseManager.sendMessage(message);

    }
}
