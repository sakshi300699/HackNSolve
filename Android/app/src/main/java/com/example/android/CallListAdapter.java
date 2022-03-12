package com.example.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CallListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<CallInfoModel> listOfInfos;
    private LayoutInflater inflater=null;
    Context ctx;
    private TextView name,date,time,link;

    public CallListAdapter(Activity activity, ArrayList<CallInfoModel> namesOfCalls){
        this.ctx = activity.getBaseContext();
        this.activity = activity;
        this.listOfInfos=namesOfCalls;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listOfInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi=convertView;

        if(convertView==null)
            vi = inflater.inflate(R.layout.item_call_list, null);

        name = (TextView)vi.findViewById(R.id.name);
        date = (TextView) vi.findViewById(R.id.date);
        time = vi.findViewById(R.id.time);
        link = vi.findViewById(R.id.link);

        CallInfoModel model = listOfInfos.get(position);
        String nameStr = model.getNameDoc()+" With "+model.getNamePat();
        String dateStr = model.getDate();
        String timeStr = model.getTime();
        String linkStr = model.getLink();

        name.setText(nameStr);
        date.setText(dateStr);
        time.setText(timeStr);
        link.setText(linkStr);

        return vi;
    }
}
