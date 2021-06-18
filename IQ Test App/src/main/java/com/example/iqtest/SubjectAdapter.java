package com.example.iqtest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.Viewholder> {
    private List<SubjectModel>subjectModelList;

    public SubjectAdapter(List<SubjectModel> subjectModelList) {
        this.subjectModelList = subjectModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(subjectModelList.get(position).getImageUrl(),subjectModelList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return subjectModelList.size();
    }

     class Viewholder extends RecyclerView.ViewHolder{
        private CircleImageView imageView;
         private TextView title;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
        }
        private void setData(String url, final String title){
            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.title.setText(title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent = new Intent (itemView.getContext(),SetsActivity.class);
                    setIntent.putExtra("title",title);
                    itemView.getContext().startActivity(setIntent);
                }
            });
        }
    }
}
