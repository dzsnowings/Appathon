package com.example.studybuddyfinder.ui.chat;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studybuddyfinder.R;

import java.util.List;

public class ChatViewModel extends RecyclerView.Adapter<ChatViewModel.ViewHolder> {
    private List<Message> messages;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        TextView tvSender;

        ViewHolder(View view) {
            super(view);
            this.tvMessage = view.findViewById(R.id.messageEditText);
            this.tvSender = view.findViewById(R.id.loginUsername);
        }

        private void setMessage(Message message) {
            tvMessage.setText(message.getMessage());
            tvSender.setText(message.getSender());
        }

    }

    ChatViewModel(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewModel.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMessage(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}



//public class ChatViewModel extends ViewModel {
//
//    private MutableLiveData<String> mText;
//
//    public ChatViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is chat fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//}