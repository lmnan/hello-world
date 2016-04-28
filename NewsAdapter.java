package com.example.day9_3_netpage.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.day9_3_netpage.R;
import com.example.day9_3_netpage.dao.ImageTask;
import com.example.day9_3_netpage.dao.ImageTask.ImageCallback;
import com.example.day9_3_netpage.entity.News;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	private Context ctx;
	private List<News> datas;
	// �洢�Ѿ����ص�ͼƬ--ͼƬ����
	private Map<String, Bitmap> imgs = new HashMap<String, Bitmap>();

	public NewsAdapter(Context ctx, List<News> datas) {
		super();
		this.ctx = ctx;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	A a = null;

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {

		if (convertView == null) {
			a = new A();
			convertView = View.inflate(ctx, R.layout.newsadapter, null);
			a.img = (ImageView) convertView.findViewById(R.id.imageView1);
			a.title = (TextView) convertView.findViewById(R.id.textView1);
			a.time = (TextView) convertView.findViewById(R.id.textView2);
			convertView.setTag(a);
		} else {
			a = (A) convertView.getTag();
		}

		News news = datas.get(position);
        String  imgUrl=news.imgUrl;
        a.img.setTag(imgUrl);
		String url = "http://www.1ccf.com/" +imgUrl ;
		// ���ͼƬ������û�ж�Ӧ��ͼƬ,������
		if (!imgs.containsKey(imgUrl)) {
			ImageTask mt = new ImageTask(imgUrl, new ImageCallback() {
				@Override
				public void showImage(String imgUrl, Bitmap bm) {
					//������ͼƬ,��ͼƬ��ַ��Ϊ��,ͼƬ������Ϊֵ
					imgs.put(imgUrl, bm);
					ImageView mv = (ImageView) parent.findViewWithTag(imgUrl);
					if (mv!=null) {
						// �����ص�ͼƬ��ʾ��imageview��
						mv.setImageBitmap(bm);
					}
					
				}
			});

			// mt.execute(url);
			mt.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
		} else {
			//���ͼƬ�������Ѿ��и�ͼƬ��ֱ����ʾ
			a.img.setImageBitmap(imgs.get(news.imgUrl));
		}
		a.title.setText(news.title);
		a.time.setText(news.datetime);

		return convertView;
	}

	static class A {
		TextView title;
		ImageView img;
		TextView time;
	}

}
