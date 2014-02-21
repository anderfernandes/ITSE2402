package com.example.dotsmasher;

import java.util.Random;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.graphics.*;

public class DotSmasherCanvas extends View implements OnTouchListener {
	int dotX, dotY, score;

	public int getDotX() {
		return dotX;
	}

	public void setDotX(int dotX) {
		this.dotX = dotX;
	}

	public int getDotY() {
		return dotY;
	}

	public void setDotY(int dotY) {
		this.dotY = dotY;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public DotSmasherCanvas(Context context) {
		super(context);
		moveDot();
		setOnTouchListener(this);
		// TODO Auto-generated constructor stub
	}

	public DotSmasherCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DotSmasherCanvas(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(detectHit((int)event.getX(), (int)event.getY())){
			score += 1;
			invalidate();
		}
		return false;
	}
	
	protected void moveDot() {
		// create two random numbers
		// assign random numbers to dotX, dotY
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		int w = getWidth() - 20; // to avoid covering score
		int h = getHeight() - 40; // to avoid covering score
		float f = generator.nextFloat();
		dotX = (int)(f*w)%w;
		f = generator.nextFloat();
		dotY = (int)(f*h)%h;
	}

	protected boolean detectHit(int x, int y) {
		if((x>=dotX && x<=dotX+20) && (y>=dotY && y<=dotY+20)){
			// You have a hit
			return true;
		}
		return false;
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		Paint dotPaint = new Paint();
		dotPaint.setColor(Color.RED);
		canvas.drawRect(dotX,  dotY, dotX + 20, dotY + 20, dotPaint);
		canvas.drawText("Score: " + score,  20, 20, dotPaint);
	}
	
}


