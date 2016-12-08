package com.example.daniel.accesoadatos_xml.Ej4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.R;
import com.example.daniel.accesoadatos_xml.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import cz.msebera.android.httpclient.Header;

public class ViewNewsActivity extends AppCompatActivity {

    private NewsSite newsSite;
    private ListView listView;
    private RssNewAdapter adapter;
    private TextView txv_title;

    public static final String NEWSITE_TAG = "newsite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        newsSite = getIntent().getParcelableExtra(NEWSITE_TAG);

        txv_title = (TextView)findViewById(R.id.txv_title);
        txv_title.setText("Noticias de "+newsSite.getName());
        Drawable drawable = getResources().getDrawable(newsSite.getIcon());
        drawable.setBounds(0,0, 200, 200);
        txv_title.setCompoundDrawables(null, null, null, drawable);

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(adapter.getItem(i).getLink()));
                startActivity(intent);
            }
        });

        downloadNews();
    }

    private void downloadNews(){
        RestClient.get(newsSite.getUrl(), new FileAsyncHttpResponseHandler(ViewNewsActivity.this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(ViewNewsActivity.this, throwable.getMessage() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    adapter = new RssNewAdapter(ViewNewsActivity.this, RssNewHelper.analyzeRssNews(file));
                    listView.setAdapter(adapter);


                } catch (XmlPullParserException e) {
                    Toast.makeText(ViewNewsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(ViewNewsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    Toast.makeText(ViewNewsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
