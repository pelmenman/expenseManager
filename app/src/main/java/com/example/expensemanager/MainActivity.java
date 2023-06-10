package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.expensemanager.database.FinanceDao;
import com.example.expensemanager.databinding.ActivityMainBinding;
import com.example.expensemanager.view.ListFragment;
import com.example.expensemanager.view.MainFragment;
import com.example.expensemanager.view.AddFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    FinanceDao dao;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        ActivityMainBinding  binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListFragment list_fragment = new ListFragment();
        MainFragment main_fragment = new MainFragment(dao);
        AddFragment add_fragment = new AddFragment(dao);

        setCurrentFragment(main_fragment);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bar_add: {
                    findViewById(R.id.menu_bar_add).setSelected(true);
                    setCurrentFragment(add_fragment);
                    return true;
                }
                case R.id.menu_bar_list: {
                    findViewById(R.id.menu_bar_list).setSelected(true);
                    setCurrentFragment(list_fragment);
                    return true;
                }
                case R.id.menu_bar_main : {
                    findViewById(R.id.menu_bar_main).setSelected(true);
                    setCurrentFragment(main_fragment);
                    return true;
                }
            }
            return false;
        });
    }


    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction s = getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment);
        s.commit();
    }
}