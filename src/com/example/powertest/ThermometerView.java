package com.example.powertest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ThermometerView extends View {

	private Bitmap disk;
	private Bitmap pointer;
	private int pointerDegrees = -90;
	private int setTemperature = 0;
	private Matrix matrix = new Matrix();
	private Canvas diskCanvas;
	private Paint paint = new Paint();
	private int currentTemperature = 0;
	private float pointerTranslate = 0;
	private float pointerRotateCenterXY;

	public ThermometerView(Context context) {
		super(context);
		disk = BitmapFactory.decodeResource(getResources(), R.drawable.disk);
		Log.d("yue.huang", "disk--height:" + disk.getHeight());
		pointer = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer);
		diskCanvas = new Canvas();
		pointerTranslate = disk.getWidth() * 0.16f;
		diskCanvas.translate(pointerTranslate, pointerTranslate);
		pointerRotateCenterXY = disk.getWidth() / 2 - pointerTranslate;
		paint.setColor(Color.BLACK);
		paint.setTextSize(25);
	}

	public ThermometerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		disk = BitmapFactory.decodeResource(getResources(), R.drawable.disk);
		pointer = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer);
		diskCanvas = new Canvas();
		pointerTranslate = disk.getWidth() * 0.16f;
		diskCanvas.translate(pointerTranslate, pointerTranslate);
		pointerRotateCenterXY = disk.getWidth() / 2 - pointerTranslate;
		paint.setColor(Color.BLACK);
		paint.setTextSize(25);
	}

	public ThermometerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		disk = BitmapFactory.decodeResource(getResources(), R.drawable.disk);
		pointer = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer);
		diskCanvas = new Canvas();
		pointerTranslate = disk.getWidth() * 0.16f;
		diskCanvas.translate(pointerTranslate, pointerTranslate);
		pointerRotateCenterXY = disk.getWidth() / 2 - pointerTranslate;
		paint.setColor(Color.BLACK);
		paint.setTextSize(25);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		Bitmap bitmap = draw(pointerDegrees);
		canvas.drawBitmap(bitmap, null, new RectF(0, 0, getMeasuredWidth(),
				getMeasuredHeight()), null);
		super.onDraw(canvas);

		if (setTemperature > currentTemperature) {
			pointerDegrees += 2;
			currentTemperature++;
		} else if (setTemperature < currentTemperature) {
			pointerDegrees -= 2;
			currentTemperature--;
		} else {
			return;
		}

		try {
			Thread.sleep(20);
		} catch (Exception e) {
			// TODO: handle exception
		}
		invalidate();
	}

	@SuppressLint("NewApi")
	private Bitmap draw(int degrees) {
		Bitmap diskTmp = disk.copy(Bitmap.Config.ARGB_8888, true);
		diskCanvas.setBitmap(diskTmp);
		matrix.setRotate(degrees, pointerRotateCenterXY, pointerRotateCenterXY);
		diskCanvas.drawBitmap(pointer, matrix, null);

		String temString = currentTemperature + "'C";
		float textWidth = paint.measureText(temString);
		diskCanvas.drawText(temString, pointerRotateCenterXY - textWidth / 2,
				pointerRotateCenterXY, paint);
		return diskTmp;
	}

	public void setTemperature(int temperature) {
		if (temperature > 135) {
			setTemperature = 135;
		} else {
			setTemperature = temperature;
		}
		invalidate();
	}

}
