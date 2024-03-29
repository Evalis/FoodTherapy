package com.example.foodTherapy.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.foodTherapy.R;
import com.example.foodTherapy.model.Meal;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

        public interface OnListItemClickListener {
        void onListItemClick(Meal meal);
    }

    private List<Meal> meals;


    final private OnListItemClickListener mOnListItemClickListener;


    public RecipeAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;


    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);

    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Glide.with(viewHolder.itemView)
                .load(meals.get(position).getStrMealThumb())
                .into(viewHolder.image);
        viewHolder.text.setText(meals.get(position).getStrMeal());
    }

    public int getItemCount() {
        if(meals == null)
        {
            return 0;
        }
        return meals.size();
    }

    public void setMeals(List<Meal> meals)
    {
        this.meals = meals;
        notifyDataSetChanged();
    }

    public Meal getMealAt(int position)
    {
        return meals.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        ImageView image;


        ViewHolder(View itemView) {


            super(itemView);
            text = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.iv_icon);

                    itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(meals.get(getAdapterPosition()));

        }


    }
}
