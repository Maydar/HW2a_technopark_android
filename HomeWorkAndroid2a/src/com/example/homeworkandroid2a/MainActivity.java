package com.example.homeworkandroid2a;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView)findViewById(R.id.first_list);
		listView.setAdapter(new MyAdapter());
	}
	
	private class ViewHolder {
		public ImageView imageView;
		public TextView  textView;
		public DownloadTask downloadTask;
	}
	
	private class MyAdapter extends BaseAdapter {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			
			return 100;
			
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.frame_layout, parent, false);
				holder = new ViewHolder();
				holder.textView = (TextView) convertView.findViewById(R.id.text1);
				holder.imageView = (ImageView) convertView.findViewById(R.id.image);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.textView.setText("example txt");
			holder.imageView.setImageResource(R.drawable.ic_launcher);
			holder.downloadTask = new DownloadTask(holder.imageView);
			holder.downloadTask.execute("f");
			
			return convertView;
		}
		
		
	}
	
	private class DownloadTask extends AsyncTask<String, Integer, Drawable> {
		private String urlString = "http://mpandroid.filin.mail.ru/pic?email=gxample@mail.ru&width=90&height=90&name=";
		private ImageView imageView;
		public DownloadTask(ImageView view) {
			this.imageView = view;
		}
		@Override
		protected Drawable doInBackground(String... params) {
			Drawable drawable;
			try {
				URL url = new URL(urlString + params[0]);
	            InputStream input = (InputStream) url.getContent();
	            drawable = Drawable.createFromStream(input, "img");
	            input.close();
	            return drawable;
			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
				return null;
			}
			
			
		}
		
		@Override
		protected void onPostExecute(Drawable result) {
			// TODO Auto-generated method stub
			if (result != null) {
				imageView.setImageDrawable(result);
			}
			
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
