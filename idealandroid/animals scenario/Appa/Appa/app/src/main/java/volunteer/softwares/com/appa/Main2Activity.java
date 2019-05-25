package volunteer.softwares.com.appa;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener{

    private static int ID_TRACKER = new Random().nextInt(50000000);

    public static final String MyPREFERENCES = "MyPrefs" ;
    private static Context cxt;
    SharedPreferences pref;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private RelativeLayout rootLayout;
    private int _xDelta;
    private int _yDelta;
    public int Result1=0;
    public int Result2=0;
    public int Result3=0;

    private HashMap<Integer, Integer> maps = new HashMap<>();
    private HashMap<Integer, String> tmaps = new HashMap<>();
    int swidth;
    int sheight;
    private ArrayList<Integer> foodList = new ArrayList<Integer>();
    private ArrayList<String> foodListName = new ArrayList<String>();
    private ArrayList<String> animalListName = new ArrayList<String>();
    private long time=System.currentTimeMillis();
    private int prev_index=-1;
    public String flag="0";

    private String lionEats="m1 meat meat2 fish";

    private String monkeyEats="x3 apple grapes straw papaya orange";

    private String elephantEats="x3 leaves grapes straw papaya orange";

    private String wolfEats="m1 meat meat2 fish";

    private String b1Eats="apple x3 grapes straw papaya orange";

    private String b2Eats="apple x3 grapes straw papaya orange";

    private String z2Eats="x3 grapes straw papaya orange leaves";

    private String z3Eats="fish";

    private String z4Eats="fish meat m1 meat2 ";

    private String z5Eats="fish meat m1 meat2";

    private String snakeEats="eggs m1 meat meat2 fish";

    //private String snakeEats="egg";

    private String camelEats="orange grapes x3 papaya orange";

    private String foxEats="m1 meat meat2 fish";

    private String[] animalArray={"Lion", "Monkey", "Elephant", "Wolf", "b1","b2","z2","z3","z4","z5",
            "Snake", "Camel","Fox"};
    private String[] foodArray={lionEats, monkeyEats, elephantEats, wolfEats, b1Eats,b2Eats,z2Eats,
            z3Eats,z4Eats,z5Eats,snakeEats, camelEats,foxEats};

    MediaPlayer mediaPlayer;
    private int windowwidth, windowheight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pref =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        mediaPlayer=MediaPlayer.create(Main2Activity.this, R.raw.swallow);

        try{
            cxt = getApplicationContext();
            int lastId = findViewById(R.id.button).getId();
            rootLayout = (RelativeLayout) findViewById(R.id.view_root);

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            swidth = dm.widthPixels;
            sheight = dm.heightPixels;
            System.out.println("varun"+flag);

            Button reset = new Button(this);
            reset.setId(getNewId());
            reset.setLayoutParams(new RelativeLayout.LayoutParams(50, 50));
            reset.setX(swidth / 1 - 115);
            reset.setY(sheight - 1900);
            reset.setVisibility(View.VISIBLE);
            reset.setText("Reset");
            reset.setBackgroundColor(Color.argb(255, 200, 200, 200));
            reset.setTextColor(Color.argb(255, 0,0,0));
            reset.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Reset();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            rootLayout.addView(reset);

            ImageView housebtn =(ImageButton)findViewById(R.id.home_btn);
            housebtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("selection", 2);
                    startActivity(intent);
                    //finish();
                }
            });
            if(pref.contains("house")){
                JSONArray house = new JSONArray(pref.getString("house", "[]"));
                lastId = findViewById(R.id.button).getId();

                for(int i = 0; i < house.length(); i++) {
                    ImageView imageView = new ImageView(this);
                    imageView.setId(getNewId());
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(600, 600));
                    imageView.setY(pref.getFloat("housey" + i, sheight / 2 - 150 / 2));
                    imageView.setX(pref.getFloat("housex" + i, swidth / 2 - 150 / 2));

                    imageView.setOnTouchListener(new CTouchListener());
                    imageView.setImageResource(house.getInt(i));

                    tmaps.put(imageView.getId(), "house");
                    maps.put(imageView.getId(), i);

                    rootLayout.addView(imageView);
                }

            }


            ImageView animalbtn = (ImageButton)findViewById(R.id.animal_btn);
            animalbtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("selection", 0);
                    startActivity(intent);
                    finish();
                }
            });

            if(pref.contains("animal")){
                JSONArray animal = new JSONArray(pref.getString("animal", "[]"));
                JSONArray animalName = new JSONArray(pref.getString("animalName", "[]"));

                for (int i = 0; i < animal.length(); i++) {
                    ImageView imageView = new ImageView(this);
                    imageView.setId(getNewId());
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(250, 250));
                    imageView.setX(pref.getFloat("animalx" + i, swidth / 2 - 150 / 2));
                    imageView.setY(pref.getFloat("animaly" + i, sheight / 2 - 150 / 2));
                    imageView.setOnTouchListener(new CTouchListener());
                    imageView.setImageResource(animal.getInt(i));
                    animalListName.add(animalName.getString(i));
                    tmaps.put(imageView.getId(), "animal");
                    maps.put(imageView.getId(), i);

                    rootLayout.addView(imageView);
                }
            }

            ImageView foodbtn = (ImageButton)findViewById(R.id.food_btn);
            foodbtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("selection", 1);
                    startActivity(intent);
                    finish();
                }
            });

            if(pref.contains("food")){
                JSONArray food = new JSONArray(pref.getString("food", "[]"));
                JSONArray imagesName = new JSONArray(pref.getString("foodName", "[]"));
                lastId = findViewById(R.id.button).getId();

                for (int i = 0; i < food.length(); i++) {
                    ImageView imageView = new ImageView(this);
                    imageView.setId(getNewId());
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(120, 120));

                    imageView.setX(pref.getFloat("foodx" + i, swidth / 2 - 150 / 2));
                    imageView.setY(pref.getFloat("foody" + i, sheight / 2 - 150 / 2));
                    imageView.setOnTouchListener(new CTouchListener());
//                    imageView.setTag(String.valueOf(imageView.getId()));
//                    imageView.setOnLongClickListener(this);
//                    imageView.setOnDragListener(this);
                    imageView.setImageResource(food.getInt(i));
                    foodList.add(food.getInt(i));
                    foodListName.add(imagesName.getString(i));
                    tmaps.put(imageView.getId(), "food");
                    maps.put(imageView.getId(), i);

                    rootLayout.addView(imageView);
                }
            }





//            Log.d("wtf", Arrays.toString(tmaps.entrySet().toArray()));
//            Log.d("wtf", Arrays.toString(maps.entrySet().toArray()));

            rootLayout.invalidate();


            rootLayout.post(new Runnable() {
                @Override
                public void run() {

                    windowwidth = getWindowManager().getDefaultDisplay().getWidth();
                    windowheight = getWindowManager().getDefaultDisplay().getHeight();
                }
            });

            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Reset();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void checkResult(View view){
        //if(Result1==1 && Result2==1 && Result3==1)
        //{

        startActivity(new Intent(this, Main15Activity.class));
        finish();

        //}
        //else
          //  Toast.makeText(this, "Drop images carefully", Toast.LENGTH_SHORT).show();
    }


    public static int getNewId(){
        return ID_TRACKER++;
    }


    public static final void Reset(){
        cxt.getSharedPreferences("MyPREFERENCES", MODE_PRIVATE).edit().clear().apply();
        cxt = null;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }




    private class CTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
                    .getLayoutParams();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int x = X - _xDelta;
                    int y = Y - _yDelta;
                    layoutParams.leftMargin = x;
                    layoutParams.topMargin = y;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    v.setLayoutParams(layoutParams);
//
//                    int x_cord = (int) event.getRawX();
//                    int y_cord = (int) event.getRawY();
//
//                    if (Math.abs(Math.abs(y_cord) - windowheight) > 100) {
//                        layoutParams.leftMargin = x;
//                        layoutParams.topMargin = y;
//                        v.setLayoutParams(layoutParams);
//                    }
                    break;
            }

            rootLayout.invalidate();

            Rect rectf = new Rect();
            v.getLocalVisibleRect(rectf);
            v.getGlobalVisibleRect(rectf);
            pref.edit().putFloat("housey", Y).putFloat("housex", X).apply();
            int link = maps.get(v.getId());
            String type = tmaps.get(v.getId());
            float realY=v.getY();
            float realX=v.getX();

            pref.edit().putFloat(type + "y" + link, v.getY()).putFloat(type + "x" + link, v.getX()).apply();

            Log.d("wtf", pref.getFloat(type + "x" + link, 0) + "   " + type + "  " + link);

            long now=System.currentTimeMillis();
            long diff=now-time;


            if (event.getAction() == MotionEvent.ACTION_UP) {
                check_food(v, link, type, realY, realX, now);
            }
            return true;
        }

        private void check_food(View v, int link, String type, float realY, float realX, long now) {

            


            if(type.equals("food") && pref.contains("animal") && prev_index!=link){
                time=now;
                try {
                    JSONArray animal = new JSONArray(pref.getString("animal", "[]"));
                    for (int i = 0; i < animal.length(); i++) {
                        float x= pref.getFloat("animalx" + i, swidth / 2 - 150 / 2);
                        float y= pref.getFloat("animaly" + i, sheight / 2 - 150 / 2);
                        float diffX=0, diffY=0;
                        if(x>realX)
                            diffX=x-realX;
                        else
                            diffX=realX-x;

                        if (y>realY)
                            diffY=y-diffY;
                        else
                            diffY=realY-y;


                        if(diffX<200 && diffY<200 && prev_index!=link){

                            String animalName=animalListName.get(i);
                            int n=animalArray.length;
                            for(int j=0; j<n; j++){
                                if(animalName.equalsIgnoreCase(animalArray[j])){
                                    System.out.println("who"+animalArray[j]);
                                    if (foodArray[j].contains(foodListName.get(link))){
                                        System.out.println("whom"+foodArray[j]);
                                        try{
                                            System.out.println("hari"+flag);
                                            foodList.set(link, null);
                                            foodListName.set(link, null);
                                            prev_index=link;
                                            mediaPlayer.reset();
                                            mediaPlayer.release();
                                            mediaPlayer= MediaPlayer.create(Main2Activity.this, R.raw.swallow);
                                            mediaPlayer.start();
                                            v.setVisibility(View.GONE);
                                        }catch (IndexOutOfBoundsException e){
                                            e.printStackTrace();
                                        }
                                    }else {

                                        if(flag.equals("0")) {
                                            mediaPlayer.reset();
                                            mediaPlayer.release();

                                            mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.music2);
                                            System.out.println("koushik" + flag);

                                            mediaPlayer.start();



                                            



                                        }
                                    }

                                }
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onStop() {
        try{
            foodList.removeAll(Collections.singleton(null));
            foodListName.removeAll(Collections.singleton(null));
            JSONArray jsonArray=new JSONArray(foodList);
            JSONArray jsonArray1=new JSONArray(foodListName);
            pref.edit().putString("food", jsonArray.toString()).apply();
            pref.edit().putString("foodName", jsonArray1.toString()).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public boolean onLongClick(View v) {
        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        // Starts the drag
        v.startDrag(data        // data to be dragged
                , dragshadow   // drag shadow builder
                , v           // local data about the drag and drop operation
                , 0          // flags (not currently used, set to 0)
        );
        return true;
    }
    // This is the method that the system calls when it dispatches a drag event to the listener.
    @Override
    public boolean onDrag(View v, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept data.
                    // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    // Invalidate the view to force a redraw in the new tint
                      v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
//                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //It will clear a color filter .
//                v.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);
                // Gets the text data from the item.
                String dragData = item.getText().toString();
                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                // Turns off any color tints
//                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();

                View vw = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) vw.getParent();
                owner.removeView(vw); //remove the dragged view
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                LinearLayout container = (LinearLayout) v;
                container.addView(vw);//Add the dragged view
                vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
//                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();
                // Does a getResult(), and displays what happened.
                if (event.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                // returns true; the value is ignored.
                return true;
            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

}

