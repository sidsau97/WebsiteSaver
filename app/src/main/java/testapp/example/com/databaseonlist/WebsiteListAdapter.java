package testapp.example.com.databaseonlist;

/**
 * Created by roeland on 16-6-2016.
 */


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
public class WebsiteListAdapter extends ArrayAdapter<Website> {

    public WebsiteListAdapter(Context context, List<Website> websites) {
        super(context, 0, websites);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.website_list_item, parent, false);
        final Website website = getItem(position);

        TextView textViewWebsiteName = (TextView) convertView.findViewById(R.id.websiteName);
        TextView textViewWebsiteUsername = (TextView) convertView.findViewById(R.id.websiteUsername);
        Button buttonOpenWebsite = (Button) convertView.findViewById(R.id.openWebsite);
        String name = website.getName().toString();
        if (name.equals("facebook")){
            buttonOpenWebsite.setBackgroundResource(R.drawable.facebook);
            buttonOpenWebsite.setText("");
        }else{
            buttonOpenWebsite.setText("open");
        }

        buttonOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = website.getUrl();
                if(!url.startsWith("http://") && !url.startsWith("https://")){
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                parent.getContext().startActivity(browserIntent);
            }
        });

        textViewWebsiteName.setText(website.getName());
        textViewWebsiteUsername.setText(website.getUsername());

        return convertView;
    }
}