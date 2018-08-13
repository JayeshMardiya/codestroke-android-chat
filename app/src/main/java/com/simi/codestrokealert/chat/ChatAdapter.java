package com.simi.codestrokealert.chat;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simi.codestrokealert.R;

import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_DATE = 0;
    private final static int TYPE_MESSAGE_SELF = 1;
    private final static int TYPE_MESSAGE_OTHER = 2;
    public List<Message> msgList = new ArrayList<>();


    public int addMessages(List<Message> messages) {
        msgList.clear();
        if (messages.size() > 0) {
            long timeStamp = 0;
            for (int i = 0; i < messages.size(); i++) {
                Message message = messages.get(i);
                if (!Utility.isDateEqual(timeStamp, message.timeStamp)) {
                    Message message1 = new Message();
                    message1.timeStamp = message.timeStamp;
                    msgList.add(message1);
                    timeStamp = message.timeStamp;
                }
                msgList.add(messages.get(i));

            }
            notifyDataSetChanged();
        }
        return msgList.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (msgList != null) {
            Message msg = msgList.get(position);
            if (TextUtils.isEmpty(msg.messageId)) {
                return TYPE_DATE;
            } else if (msg.senderID.equals(String.valueOf(Utility.CURRENT_USER_ID))) {
                return TYPE_MESSAGE_SELF;
            } else return TYPE_MESSAGE_OTHER;
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_DATE:
                view = LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.layout_date, parent, false);
                return new DateHolder(view);
            case TYPE_MESSAGE_SELF:
                view = LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.layout_msg_send, parent, false);
                return new SenderHolder(view);
            case TYPE_MESSAGE_OTHER:
                view = LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.layout_msg_recieve, parent, false);
                return new ReceiverHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message msg = msgList.get(position);
        if (holder instanceof DateHolder) {
            ((DateHolder) holder).set(msg.timeStamp);
        } else if (holder instanceof SenderHolder) {
            ((SenderHolder) holder).set(msg);
        } else {
            ((ReceiverHolder) holder).set(msg);
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }


    public class DateHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;

        DateHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        void set(long timeStamp) {
            tvDate.setText(Utility.getDate(timeStamp));
        }
    }


    private class SenderHolder extends RecyclerView.ViewHolder {

        private TextView tvSent, tvDate;

        SenderHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tv_date);
            tvSent = view.findViewById(R.id.tv_sent);
        }

        void set(Message message) {
            tvSent.setText(message.messageText);
            tvDate.setText(Utility.getTime(message.timeStamp));
        }
    }

    private class ReceiverHolder extends RecyclerView.ViewHolder {

        private TextView tvRecieve, tvDate, tvSenderName;

        ReceiverHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tv_date);
            tvRecieve = view.findViewById(R.id.tv_recieve);
            tvSenderName = view.findViewById(R.id.tv_sendername);
        }

        void set(Message message) {
            tvRecieve.setText(message.messageText);
            tvDate.setText(Utility.getTime(message.timeStamp));
            tvSenderName.setText(message.senderName);
        }
    }
}
