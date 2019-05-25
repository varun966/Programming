package volunteer.softwares.com.appa;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
public class Main15Activity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setId(Main2Activity.getNewId());
        layout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setX(0);
        layout.setY(0);
        layout.setBackground(ContextCompat.getDrawable(this, R.drawable.back1));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int swidth = dm.widthPixels;
        int sheight = dm.heightPixels;

        ImageView animal = new ImageView(this);
        animal.setId(Main2Activity.getNewId());
        animal.setLayoutParams(new RelativeLayout.LayoutParams(175, 175));
        animal.setX(swidth / 2 - 150);
        animal.setY(sheight / 2 - 450);
        animal.setScaleType(ImageView.ScaleType.CENTER_CROP);
        animal.setImageResource(R.drawable.animal_button);
//        animal.setBackgroundColor(Color.argb(255, 200, 200, 200));
        animal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("selection", 0);
                startActivity(intent);
                //finish();
            }
        });

        ImageView food = new ImageView(this);
        food.setId(Main2Activity.getNewId());
        food.setLayoutParams(new RelativeLayout.LayoutParams(175, 175));
        food.setX(swidth / 2 - 150);
        food.setY(sheight / 2 - 150);
        food.setScaleType(ImageView.ScaleType.CENTER_CROP);
        food.setImageResource(R.drawable.food1);
//        food.setBackgroundColor(Color.argb(255, 200, 200, 200));
        food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("selection", 1);
                startActivity(intent);
                //finish();
            }
        });

        ImageView house = new ImageView(this);
        house.setId(Main2Activity.getNewId());
        house.setLayoutParams(new RelativeLayout.LayoutParams(175, 175));
        house.setX(swidth / 2 - 150);
        house.setY(sheight / 2 + 150);
        house.setScaleType(ImageView.ScaleType.CENTER_CROP);
        house.setImageResource(R.drawable.house2);
//        house.setBackgroundColor(Color.argb(255, 200, 200, 200));
        house.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("selection", 2);
                startActivity(intent);
                //finish();
            }
        });


        layout.addView(animal);
        layout.addView(food);
        layout.addView(house);

        setContentView(layout);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
