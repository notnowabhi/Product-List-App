package com.example.newappproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView, priceTextView, ratingTextView, brandTextView, categoryTextView;
    private ImageView thumbnailImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Initialize views
        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        brandTextView = findViewById(R.id.brandTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        thumbnailImageView = findViewById(R.id.thumbnailImageView);

        // Get the product from the Intent
        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            // Set product details to the views
            titleTextView.setText(product.getTitle());
            descriptionTextView.setText(product.getDescription());
            priceTextView.setText("$" + product.getPrice());
            ratingTextView.setText("Rating: " + product.getRating());
            brandTextView.setText("Brand: " + product.getBrand());
            categoryTextView.setText("Category: " + product.getCategory());

            // Load thumbnail image using Glide
            Glide.with(this).load(product.getThumbnail()).into(thumbnailImageView);
        }
    }
}
