package com.group21.recyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MessageAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messageList;

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        View view;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;

        public ViewHolder0(View itemView) {
            super(itemView);
            view = itemView;
            titleView = itemView.findViewById(R.id.type0_title);
            authorView = itemView.findViewById(R.id.type0_author);
            publishTimeView = itemView.findViewById(R.id.type0_publishTime);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;

        public ViewHolder1(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.type1_image);
            titleView = itemView.findViewById(R.id.type1_title);
            authorView = itemView.findViewById(R.id.type1_author);
            publishTimeView = itemView.findViewById(R.id.type1_publishTime);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;

        public ViewHolder2(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.type2_image);
            titleView = itemView.findViewById(R.id.type2_title);
            authorView = itemView.findViewById(R.id.type2_author);
            publishTimeView = itemView.findViewById(R.id.type2_publishTime);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;

        public ViewHolder3(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.type3_image);
            titleView = itemView.findViewById(R.id.type3_title);
            authorView = itemView.findViewById(R.id.type3_author);
            publishTimeView = itemView.findViewById(R.id.type3_publishTime);
        }
    }

    static class ViewHolder4 extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;

        public ViewHolder4(View itemView) {
            super(itemView);
            view = itemView;
            imageView1 = itemView.findViewById(R.id.type4_image_01);
            imageView2 = itemView.findViewById(R.id.type4_image_02);
            imageView3 = itemView.findViewById(R.id.type4_image_03);
            imageView4 = itemView.findViewById(R.id.type4_image_04);
            titleView = itemView.findViewById(R.id.type4_title);
            authorView = itemView.findViewById(R.id.type4_author);
            publishTimeView = itemView.findViewById(R.id.type4_publishTime);
        }
    }


    public MessageAdapt(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type0, parent, false);
                final ViewHolder0 holder0 = new ViewHolder0(view);
                holder0.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasToken(v)) {
                            jumpArticle(holder0.getAdapterPosition(), v);
                        } else {
                            jumpLogin(v);
                        }
                    }
                });
                return holder0;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type1, parent, false);
                final ViewHolder1 holder1 = new ViewHolder1(view);
                holder1.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasToken(v)) {
                            jumpArticle(holder1.getAdapterPosition(), v);
                        } else {
                            jumpLogin(v);
                        }
                    }
                });
                return holder1;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type2, parent, false);
                final ViewHolder2 holder2 = new ViewHolder2(view);
                holder2.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasToken(v)) {
                            jumpArticle(holder2.getAdapterPosition(), v);
                        } else {
                            jumpLogin(v);
                        }
                    }
                });
                return holder2;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type3, parent, false);
                final ViewHolder3 holder3 = new ViewHolder3(view);
                holder3.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasToken(v)) {
                            jumpArticle(holder3.getAdapterPosition(), v);
                        } else {
                            jumpLogin(v);
                        }
                    }
                });
                return holder3;
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type4, parent, false);
                final ViewHolder4 holder4 = new ViewHolder4(view);
                holder4.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hasToken(v)) {
                            jumpArticle(holder4.getAdapterPosition(), v);
                        } else {
                            jumpLogin(v);
                        }
                    }
                });
                return holder4;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder instanceof ViewHolder0) {
            ViewHolder0 holder0 = (ViewHolder0) holder;
            holder0.titleView.setText(message.getTitle());
            holder0.authorView.setText(message.getAuthor());
            holder0.publishTimeView.setText(message.getPublishTime());
        } else if (holder instanceof ViewHolder1) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.titleView.setText(message.getTitle());
            holder1.authorView.setText(message.getAuthor());
            holder1.publishTimeView.setText(message.getPublishTime());
            holder1.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.titleView.setText(message.getTitle());
            holder2.authorView.setText(message.getAuthor());
            holder2.publishTimeView.setText(message.getPublishTime());
            holder2.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder3) {
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.titleView.setText(message.getTitle());
            holder3.authorView.setText(message.getAuthor());
            holder3.publishTimeView.setText(message.getPublishTime());
            holder3.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder4) {
            ViewHolder4 holder4 = (ViewHolder4) holder;
            holder4.titleView.setText(message.getTitle());
            holder4.authorView.setText(message.getAuthor());
            holder4.publishTimeView.setText(message.getPublishTime());
            int[] covers = message.getCovers();
            holder4.imageView1.setImageResource(covers[0]);
            holder4.imageView2.setImageResource(covers[1]);
            holder4.imageView3.setImageResource(covers[2]);
            holder4.imageView4.setImageResource(covers[3]);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private boolean hasToken(View v) {
        SharedPreferences pref = v.getContext().getSharedPreferences("token", MODE_PRIVATE);
        String token = pref.getString("token", "");
        return !token.equals("");
    }

    private void jumpLogin(View v) {
        Intent intent = new Intent(v.getContext(), LoginActivity.class);
        v.getContext().startActivity(intent);
    }

    private void jumpArticle(int position, View v) {
        String id = messageList.get(position).getId();
        String author = messageList.get(position).getAuthor();
        String title = messageList.get(position).getTitle();
        String publishTime = messageList.get(position).getPublishTime();
        Intent intent = new Intent(v.getContext(), ShowMarkdownActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("publishTime", publishTime);
        v.getContext().startActivity(intent);
    }

}
