package droidappsfactory.setwall.adaptor;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import droidappsfactory.setwall.R;
import droidappsfactory.setwall.database.DBBitmapUtility;

/**
 * Created by Rishi on 27-06-2015.
 */
public class Imageadaptor extends BaseAdapter {

    Context context;
    ArrayList<byte[]> data;
    LayoutInflater layoutInflater;

    public Imageadaptor(Context context, ArrayList<byte[]> data){

        this.context = context;
        this.data = data;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        ItemHolder holder = new ItemHolder();
        if(convertView == null){
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.image_row,null);
            ImageView imgView = (ImageView)v.findViewById(R.id.iv_preview);
            holder.imageView = imgView;
            v.setTag(holder);
        }else{
            holder = (ItemHolder)v.getTag();
        }

        final byte[] tmpIV = data.get(position);
        holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(DBBitmapUtility.getImage(tmpIV),480,320,false));

        return v;
    }

    private static class ItemHolder{
        public ImageView imageView;
    }
}
