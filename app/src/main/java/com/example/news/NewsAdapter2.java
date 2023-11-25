package com.example.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter2 extends RecyclerView.Adapter<NewsAdapter2.ExampleHolder> {

        private ArrayList<Article> pictureArrayList;
        private int itemLayout;


        public NewsAdapter2(ArrayList<Article> pictureArrayList, int itemLayout) {
            this.pictureArrayList = pictureArrayList;
            this.itemLayout = itemLayout;
        }

        @NonNull
        @Override
        public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ExampleHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ExampleHolder holder, final int position) {
            final Article newsModel = pictureArrayList.get(position);
            holder.title.setText(newsModel.getTitle());
//            holder.imageView.setImageResource(.getImage());
        }


        @Override
        public int getItemCount() {
            return pictureArrayList.size();
        }


        public class ExampleHolder extends RecyclerView.ViewHolder {

//            val title: TextView = itemView.findViewById(R.id.title)
//            val image: ImageView = itemView.findViewById(R.id.image)
//            @BindView(R.id.txt_title)
            TextView title;
//            @BindView(R.id.imageView)
//            ImageView imageView;
//
            ExampleHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
//                itemView.setOnClickListener(this);

            }


        }


    }
