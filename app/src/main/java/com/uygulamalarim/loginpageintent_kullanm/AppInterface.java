package com.uygulamalarim.loginpageintent_kullanm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uygulamalarim.loginpageintent_kullanm.Adaptor.CategoryAdaptor;
import com.uygulamalarim.loginpageintent_kullanm.Adaptor.PopularAdaptor;
import com.uygulamalarim.loginpageintent_kullanm.Domain.CategoryDomain;
import com.uygulamalarim.loginpageintent_kullanm.Domain.FoodDomain;
import com.uygulamalarim.loginpageintent_kullanm.utils.spacingbetweenrecyclerview;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class AppInterface extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    private ImageView imageView;
    private SearchView searchfood;

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList,searchRecycler,recyclerViewSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_interface);

        viewPager= findViewById(R.id.viewpager);
        circleIndicator=findViewById(R.id.circle_indicator);

        photoAdapter=new PhotoAdapter(this,getListPhoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        imageView= findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.person1);

        searchfood=findViewById(R.id.searchfood);

        
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        searchRecycler();

    }
    private void searchRecycler(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewSearchList=findViewById(R.id.searchRecycler);
        recyclerViewSearchList.setLayoutManager(linearLayoutManager);

        spacingbetweenrecyclerview itemdecorator=new spacingbetweenrecyclerview(10);// these 2 lines makes spacing between recylerview items.
        recyclerViewSearchList.addItemDecoration(itemdecorator);
        recyclerViewSearchList.setVisibility(View.GONE);


        // i added the foods and their names, titles etc..
        ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("Hamburger Menu","meal1","Hamburger menu with fries and drink",12.00));
        foodList.add(new FoodDomain("Pepperoni Pizza","meal2","Delicious pepperoni pizza",9.50));
        foodList.add(new FoodDomain("Lasagna","meal3","Fresh baked lasagna",10.50));
        foodList.add(new FoodDomain("Chicken Schnitzel","meal4","Schnitzel with fries",7.25));
        foodList.add(new FoodDomain("Pasta","meal5","Delicious pasta by chief's recommendation",8.75));
        adapter2=new PopularAdaptor(foodList);
        recyclerViewSearchList.setAdapter(adapter2);

        //TODO: SEARCH FOOD FİLTRELEMESİ YAP

        searchfood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });








    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.fab);
        BottomNavigationItemView homeBtn=findViewById(R.id.miHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppInterface.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppInterface.this,AppInterface.class));
            }
        });
    }

    private void recyclerViewCategory() {
        // this is categorylist in the main page
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.cartView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Main Dishes","pic1"));
        category.add(new CategoryDomain("Salads","pic2"));
        category.add(new CategoryDomain("Desserts","pic3"));
        category.add(new CategoryDomain("Drinks","pic4"));
        category.add(new CategoryDomain("Appetiser","pic5"));
        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private List<Photo> getListPhoto(){
        List<Photo> list=new ArrayList<>();

        list.add(new Photo(R.drawable.food1));
        list.add(new Photo(R.drawable.food2));
        list.add(new Photo(R.drawable.food3));
        list.add(new Photo(R.drawable.food4));

        return list;
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerViewPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        spacingbetweenrecyclerview itemdecorator=new spacingbetweenrecyclerview(10);// these 2 lines makes spacing between recylerview items.
        recyclerViewPopularList.addItemDecoration(itemdecorator);


        // i added the foods and their names, titles etc..
        ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("Hamburger Menu","meal1","Hamburger menu with fries and drink",12.00));
        foodList.add(new FoodDomain("Pepperoni Pizza","meal2","Delicious pepperoni pizza",9.50));
        foodList.add(new FoodDomain("Lasagna","meal3","Fresh baked lasagna",10.50));
        foodList.add(new FoodDomain("Chicken Schnitzel","meal4","Schnitzel with fries",7.25));
        foodList.add(new FoodDomain("Pasta","meal5","Delicious pasta by chief's recommendation",8.75));
        adapter2=new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }


}