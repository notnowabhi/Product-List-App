package com.example.newappproject;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchProducts();
    }

    private void fetchProducts() {
        Log.d("ProductListActivity", "Fetching products...");

        ApiClient.getInstance().getProductsFromApi(new ApiCallback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                Log.d("ProductListActivity", "Fetched " + products.size() + " products.");

                // If no products are fetched, show a log message
                if (products.isEmpty()) {
                    Log.w("ProductListActivity", "No products found!");
                }

                productAdapter = new ProductAdapter(products, ProductListActivity.this);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProductListActivity", "Failed to fetch products: " + errorMessage);
            }
        });
    }
}
