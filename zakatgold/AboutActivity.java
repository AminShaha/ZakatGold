package com.example.zakatgold;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;



    public class AboutActivity extends Activity implements View.OnClickListener {

        Button button;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.about);

            button = findViewById(R.id.button);
            button.setOnClickListener(this);

            TextView appWebsiteTextView = findViewById(R.id.textViewAppWebsite);

            SpannableString spannableString = new SpannableString(appWebsiteTextView.getText());
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    String githubUrl = "https://github.com/AminShaha/";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                    startActivity(intent);
                }
            };

            spannableString.setSpan(clickableSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            appWebsiteTextView.setText(spannableString);
            appWebsiteTextView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
            appWebsiteTextView.setLinkTextColor(getResources().getColor(R.color.white)); // Set link text color to white
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                // Start MainActivity when the button is clicked
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

