package com.example.haitran.cura.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.haitran.cura.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class InternetImageView extends ImageView {
	public InternetImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	private Bitmap bitmap;
	private ProgressBar progressBar = null;
	public void setImageAsync(String url) {
		setImageResource(R.drawable.thumbnail_default);
		new DownloadImageTask().execute(url);
	}

	public void setImageAsync(String url, ProgressBar progressBar) {
		setImageResource(R.drawable.thumbnail_default);
		this.progressBar = progressBar;
		new DownloadImageTask().execute(url);
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}

	////////////////////////////

	protected class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

		private static final String TAG = "DownloadImageTask";

		@Override
		protected void onPreExecute() {
			InternetImageView.this.setImageResource(R.drawable.thumbnail_default);
		}

		@Override
		protected Bitmap doInBackground(String... param) {
			String url = param[0];
			Bitmap bitmap = null;
				bitmap = downloadBitmap(url);
			return bitmap;
		}


		private Bitmap downloadBitmap(String url) {
			HttpURLConnection urlConnection = null;
			Bitmap				bitmap = null;

			try {
				Log.i(TAG, "Downloading:  " + url);
				urlConnection = (HttpURLConnection) new URL(url).openConnection();
				int responseCode = urlConnection.getResponseCode();
				if (responseCode < 0) {
					Log.e(TAG, "Image not found.  Response code = " + responseCode);
				} else {
					InputStream inputStream = urlConnection.getInputStream();
					if (inputStream != null) {
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						BitmapFactory.decodeStream(inputStream, null, options);
						options.inSampleSize = calculateInSampleSize(options, 720, 1080);
						options.inJustDecodeBounds = false;
						urlConnection = (HttpURLConnection) new URL(url).openConnection();
						inputStream = urlConnection.getInputStream();
						bitmap = BitmapFactory.decodeStream(inputStream, null, options);
						if(inputStream!=null)
							inputStream.close();
					}
				}
			} catch (Exception e) {
				urlConnection.disconnect();
				bitmap = null;
				Log.e(TAG, "Error has occurred while downloading image from " + url, e);
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}

			return bitmap;
		}


		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (bitmap != null) {
				InternetImageView.this.bitmap = bitmap;
				InternetImageView.this.setImageBitmap(bitmap);
				if(InternetImageView.this.progressBar!=null)
				InternetImageView.this.progressBar.setVisibility(GONE);
			}
		else{
				InternetImageView.this.bitmap = null;
				InternetImageView.this.setImageResource(R.drawable.thumbnail_default);
				if(InternetImageView.this.progressBar!=null)
				InternetImageView.this.progressBar.setVisibility(GONE);
				Toast.makeText(getContext(), "Can not download image!", Toast.LENGTH_LONG).show();
			}
		}
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
											int reqWidth, int reqHeight) {
		// BEGIN_INCLUDE (calculate_sample_size)
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}

			long totalPixels = width * height / inSampleSize;

			// Anything more than 2x the requested pixels we'll sample down further
			final long totalReqPixelsCap = reqWidth * reqHeight * 2;

			while (totalPixels > totalReqPixelsCap) {
				inSampleSize *= 2;
				totalPixels /= 2;
			}
		}
		return inSampleSize;
		// END_INCLUDE (calculate_sample_size)
	}

}
