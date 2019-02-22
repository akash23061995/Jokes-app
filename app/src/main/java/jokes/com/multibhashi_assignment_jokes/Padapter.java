package jokes.com.multibhashi_assignment_jokes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

class Padapter extends RecyclerView.Adapter<Padapter.PViewHolder> {

    private ArrayList<String> jokes_list;
    private Context context;
    String[] allColors;

//    private ArrayList<String> colors;

    public Padapter(ArrayList<String> jokes_list, Context context ) {
        this.jokes_list = jokes_list;
        this.context = context;
        allColors = context.getApplicationContext().getResources().getStringArray(R.array.colors);
    }

    @NonNull
    @Override
    public PViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, parent, false);
        return new PViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PViewHolder holder, int position) {
       final String title=jokes_list.get(position);
        holder.joke.setText(title);
        Random random  =  new Random();

        int i = random.nextInt(5);
        holder.cardView.setCardBackgroundColor(Color.parseColor(allColors[i]));
//        holder.cardView.setBackgroundResource(Color.parseColor(allColors[random.nextInt(5)]));

    }


    @Override
    public int getItemCount() {
        return jokes_list.size();
    }

    public class PViewHolder extends RecyclerView.ViewHolder{
        TextView joke;
        CardView cardView;
        public PViewHolder(@NonNull View itemView) {
            super(itemView);
            joke = itemView.findViewById(R.id.joke);
             cardView = itemView.findViewById(R.id.card);

        }
    }
}
