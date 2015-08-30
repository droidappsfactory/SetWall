package droidappsfactory.setwall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.InputStream;

import droidappsfactory.setwall.adaptor.Imageadaptor;
import droidappsfactory.setwall.database.DBBitmapUtility;
import droidappsfactory.setwall.database.DatabaseHelper;


public class MainActivity extends Activity {

    ImageView IV_preview;
    Button BT_takepicture,BT_setwallpaper;
    Intent i;
    Bitmap bmp;
    DatabaseHelper databaseHelper;
    ListView listView;
    Imageadaptor imageadaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        IV_preview = (ImageView)findViewById(R.id.iv_preview);
        listView = (ListView)findViewById(R.id.lstview);

       /* if(databaseHelper.getAllImages().size()!=0){
            IV_preview.setImageBitmap(DBBitmapUtility.getImage(databaseHelper.getAllImages().get(1)));
        }*/
        imageadaptor = new Imageadaptor(getApplicationContext(),databaseHelper.getAllImages());
        listView.setAdapter(imageadaptor);

        //BT_setwallpaper = (Button)findViewById(R.id.bt_setwallpaper);
        BT_takepicture = (Button)findViewById(R.id.bt_takepicture);



        BT_takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
            }
        });
        /*BT_setwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(bmp!=null){
                        getApplicationContext().setWallpaper(bmp);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap)extras.get("data");
            Log.d("MainActivity", "bmp data" + bmp);
     //       IV_preview.setImageBitmap(bmp);
            databaseHelper.addEntry(DBBitmapUtility.getBytes(bmp));
            imageadaptor = new Imageadaptor(getApplicationContext(),databaseHelper.getAllImages());
            listView.setAdapter(imageadaptor);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
