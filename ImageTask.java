package com.example.day9_3_netpage.dao;

import com.example.day9_3_netpage.utils.HttpUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageTask extends AsyncTask<String, Integer, byte[]>{
	ImageCallback  cb;
	String url;
	 public ImageTask(String url,ImageCallback  cb) {
		 this.cb=cb;
		 this.url=url;
	}
    @Override
    protected void onPostExecute(byte[] result) {
    	 try
    	 {
    		 //转化为图片
			Bitmap bm = BitmapFactory.decodeByteArray(result,0,result.length);
			 cb.showImage(url, bm);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	@Override
	protected byte[] doInBackground(String... params) {
		
		 byte[] bs = HttpUtils.downloadImage(params[0]);
		return bs;
	}
	//接口
    public interface ImageCallback{
    	public void showImage(String url,Bitmap bm);
    }

}
