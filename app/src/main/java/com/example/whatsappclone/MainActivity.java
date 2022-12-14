package com.example.whatsappclone;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whatsappclone.Adapters.adapterfrag;
import com.example.whatsappclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mAuth =FirebaseAuth.getInstance();
        setContentView(binding.getRoot());

binding.viewpager.setAdapter(new adapterfrag(getSupportFragmentManager()));
binding.tablayout.setupWithViewPager(binding.viewpager);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    switch(item.getItemId()){
        case R.id.settings:
            Toast.makeText(this, "You have clicked on settings", Toast.LENGTH_SHORT).show();
            break;
        case R.id.logout:
            mAuth.signOut();

            Intent intent = new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            break;

    }
        return super.onOptionsItemSelected(item);
    }
}
