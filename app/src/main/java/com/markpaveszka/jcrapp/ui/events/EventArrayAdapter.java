package com.markpaveszka.jcrapp.ui.events;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.markpaveszka.jcrapp.R;

import java.util.ArrayList;

public class EventArrayAdapter extends ArrayAdapter<JCREvent> {


    private Context mContext;
    int mResource;


    public EventArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<JCREvent> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final JCREvent e = getItem(position);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);



        TextView eventTitleTV = (TextView) convertView.findViewById(R.id.eventTitleTV);
        TextView eventDateTV = (TextView) convertView.findViewById(R.id.eventDateTV);
        TextView eventDurationTV = (TextView) convertView.findViewById(R.id.eventDurationTV);
        TextView eventLocationTV = (TextView) convertView.findViewById(R.id.eventLocationTV);
        ImageView posterImageView = (ImageView) convertView.findViewById(R.id.posterIV);


        String date = e.getDate().toString();
        String [] temp = date.split("GMT");
        eventDateTV.setText(temp[0]);
//        eventDateTV.setText(e.getDate().toString());
        eventDurationTV.setText("Duration: " +e.getDuration());
        eventLocationTV.setText(e.getVenue());

        SpannableString content = new SpannableString(e.getName());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        eventTitleTV.setText(content);
        posterImageView.setImageBitmap(e.getImgBitmap());

        eventTitleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(e.getFacebookLink()));
                mContext.startActivity(browserIntent);
            }
        });



        return convertView;


    }
}
