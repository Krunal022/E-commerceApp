package com.example.internshipp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    ImageView imageView;
    TextView name,price, desc;
    Button buyNow;
    SharedPreferences sp;
    Checkout checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        name = findViewById(R.id.product_detail_name);
        imageView = findViewById(R.id.product_detail_image);
        price = findViewById(R.id.product_detail_price);
        desc = findViewById(R.id.product_detail_desc);
        buyNow = findViewById(R.id.product_detail_buynow);

        name.setText(sp.getString(ConstantSp.PRODUCT_NAME, ""));
        imageView.setImageResource(sp.getInt(ConstantSp.PRODUCT_IMAGE, 0));
        price.setText(ConstantSp.PRICE_SYMBOL + sp.getString(ConstantSp.PRODUCT_PRICE, ""));

        desc.setText(sp.getString(ConstantSp.PRODUCT_DESC, ""));

        Checkout.preload(getApplicationContext());

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });

    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        co.setKeyID("rzp_test_wfAUmWJIE1sSKp");

        try {
            JSONObject options = new JSONObject();
            options.put("name", getResources().getString(R.string.app_name));
            options.put("description", "Online Order Payment");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://cdn-icons-png.flaticon.com/512/1933/1933833.png");
            options.put("currency", "INR");
            options.put("amount", Integer.parseInt(sp.getString(ConstantSp.PRODUCT_PRICE, ""))*100);

            JSONObject preFill = new JSONObject();
            preFill.put("email", sp.getString(ConstantSp.EMAIL,""));
            preFill.put("contact", sp.getString(ConstantSp.CONTACT,""));

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try {
            Toast.makeText(this, "Payment Successful :\nPayment ID: " + s + "\nPayment Data: " + paymentData.getData(), Toast.LENGTH_SHORT).show();
            Log.d("RESPONSE_SUCCESS", "Payment Successful :\nPayment ID: " + s + "\nPayment Data: " + paymentData.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try {
            Toast.makeText(this, "Payment Failed:\nPayment Data: " + paymentData.getData(), Toast.LENGTH_SHORT).show();
            Log.d("RESPONSE_FAIL", "Payment Failed:\nPayment Data: " + paymentData.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}