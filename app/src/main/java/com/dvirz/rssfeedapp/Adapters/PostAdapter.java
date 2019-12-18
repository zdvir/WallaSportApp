package com.dvirz.rssfeedapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvirz.rssfeedapp.Model.Post;
import com.dvirz.rssfeedapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter <PostAdapter.ViewHolder>
{
    private Context mContext;
    private List<Post> list;
    private OnItemClickListener onItemClickListener;

    public PostAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public PostAdapter(Context mContext, List<Post> list)
    {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.post_item, parent ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position)
    {
        final Post currentObject = list.get(position);

        if(currentObject!=null)
        {
            viewHolder.bind(currentObject);
        }
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView postImage;
        TextView postTitle, postPubDate;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.post_title);
            postPubDate = itemView.findViewById(R.id.post_pubDate);

            itemView.setOnClickListener(this);
        }

        public void bind(Post post)
        {
            Picasso.get().load(post.getImage())
                    .fit()
                    .centerCrop()
                    .into(postImage);

            postTitle.setText(post.getTitle());
            postPubDate.setText(post.getPubDate());
        }

        @Override
        public void onClick(View view)
        {
            if(onItemClickListener!=null)
            {
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                {
                    onItemClickListener.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        onItemClickListener = listener;
    }

    public void setList(List<Post> list)
    {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<Post> getList() {
        return list;
    }
}
