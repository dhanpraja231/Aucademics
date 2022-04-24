package com.example.aucademics.homePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aucademics.R;

import java.util.ArrayList;

public class HelpNFAQ extends AppCompatActivity {
    Toolbar tBar;
    RecyclerView rv;
    ArrayList<faqItem> dataList;
    HelpPageRVAdapter rvAdapter;
    TextView contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_n_faq);
        tBar = findViewById(R.id.help_n_faq_toolbar);
        rv = findViewById(R.id.faq_rv);
        contact = findViewById(R.id.helpFaqContact_tv);
        tBar.setNavigationIcon(getDrawable(R.drawable.ic_baseline_arrow_back_24));
        tBar.setTitle("FAQ");
        tBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
        dataList = new ArrayList<>();
        dataList.add(new faqItem("Will moving to previous/next semester clear ALL my data?","NO, moving to another semester will only clear your existing" +
                " attendance data of the current semester. Your CGPA data will persist"));
        dataList.add(new faqItem("I have selected incorrect department/regulation","Use wipe data to start over again. It will take you to the start page where you may enter " +
                "your details again. NOTE: current data will be lost" ));
        dataList.add(new faqItem("How to increment bunks for labs and other 4 hour block subjects","The answer is obvious. Every time you bunk a lab, increment 4 " +
                "skips in the bunk manager for that particular subject" ));
        dataList.add(new faqItem("Will the wipe data option clear ALL my data?","YES, wipe data will clear all data including attendance AND CGPA. " +
                "You may also accomplish this by clearing the storage of this app in settings"));
        dataList.add(new faqItem("I have mistakenly entered CGPA data for a semester, I am unable to clear it. How to keep it from affecting my overall cgpa calculation?",
                "Setting all your subject marks as RA in the semester will make the app neglect the respected semester from being included in calculations"));
        dataList.add(new faqItem("How will RA subjects be accounted for in GPA calculation?",
                "RA subjects will not be accounted for GPA calculation"));
        dataList.add(new faqItem("Will uninstalling the app delete my data?","NO, Your data will persist through uninstalls and updates. However, in some cases uninstalling the app " +
                "may cause unexpected behaviour and is hence not recommended "));
        dataList.add(new faqItem("Developed by",
                "Subramania Raja ECE'23"));
        dataList.add(new faqItem("Content and design team",
                "Hemalatha.M IT'23\n" +
                        "Harsh.S EEE'23\n" +
                        "Adithya.D ECE'23\n" +
                        "Ajmitra.G IBT'23\n" +
                        "Saranya.R ECE'23\n" +
                        "Vikeesh.K IE'23\n" +
                        "Yashaswini.M ECE'23\n" +
                        "Omar.H CSE'23\n" +
                        "Adithyaa.J.S ECE'23\n" +
                        "Rohith.G MtSc'23\n" +
                        "Saranya.G MNE'25\n" +
                        "John.P Civil'23"));

        rvAdapter = new HelpPageRVAdapter(dataList);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        contact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:aucadev@gmail.com?subject=");
            intent.setData(data);
            startActivity(intent);
        });


    }
}