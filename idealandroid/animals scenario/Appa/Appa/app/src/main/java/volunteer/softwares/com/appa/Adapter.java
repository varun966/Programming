package volunteer.softwares.com.appa;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import volunteer.softwares.com.Utility.RecycleViewItemClickListener;

/**
 * Created by rajvirsingh on 25/03/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder>{

    String[] drawables;
    int[] drawables1;
    Context context;
    RecycleViewItemClickListener recycleViewItemClickListener;

    public Adapter(int[] drawables1, Context context, RecycleViewItemClickListener recycleViewItemClickListener){
        this.drawables = drawables;
        this.drawables1 = drawables1;
        this.context = context;
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view,parent,false);
        final AdapterViewHolder adapterViewHolder = new AdapterViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                recycleViewItemClickListener.onItemClick(v,adapterViewHolder.getPosition());
            }
        });

        return adapterViewHolder;

    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position){
//       String string=drawables[position];
//       holder.name.setText(string);
//       holder.name.setTag(position);
        holder.img.setImageResource(drawables1[position]);
    }

    @Override
    public int getItemCount() {
        return drawables1.length;
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            img=itemView.findViewById(R.id.img);
        }
    }
}
