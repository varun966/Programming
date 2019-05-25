package volunteer.softwares.com.appa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    android.support.v4.app.Fragment test = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab_back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //onBackPressed();
                //setContentView(R.layout.activity_main2);
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                //finish();
            }
        });

        int which = getIntent().getExtras().getInt("selection");

        if(which == 0){
            android.support.v4.app.Fragment fragment=getSupportFragmentManager().findFragmentByTag(LionFragment.class.getName());

            if(fragment!=null){
                ReplaceFragment(fragment);
            }else{
                ReplaceFragment(new LionFragment());
            }

        }else if(which == 1){
            android.support.v4.app.Fragment fragment=getSupportFragmentManager().findFragmentByTag(FoodFragment.class.getName());

            if(fragment!=null){
                ReplaceFragment(fragment);
            }else{
                ReplaceFragment(new FoodFragment());
            }

        }else if(which == 2){
            android.support.v4.app.Fragment fragment=getSupportFragmentManager().findFragmentByTag(HouseFragment.class.getName());

            if(fragment!=null){
                ReplaceFragment(fragment);
            }else{
                ReplaceFragment(new HouseFragment());
            }
        }
    }

    private void ReplaceFragment(android.support.v4.app.Fragment fragment) {
        test = fragment;
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment, fragment.getClass().getName());
        ft.commit();
        test=null;
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
