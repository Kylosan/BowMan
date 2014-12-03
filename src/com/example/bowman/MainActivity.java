package com.example.bowman;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	/** Hold a reference to our GLSurfaceView */
	private GLSurf mGLSurfaceView;
	private GLRenderer mRenderer;
	
	private static final int MIN_DIALOG = 1;
	private static final int MAG_DIALOG = 2;
	
	private int mMinSetting = -1;
	private int mMagSetting = -1;
	
	private static final String MIN_SETTING = "min_setting";
	private static final String MAG_SETTING = "mag_setting";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		mGLSurfaceView = (GLSurf)findViewById(R.id.gl_surface_view);

		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		if (supportsEs2) 
		{
			// Request an OpenGL ES 2.0 compatible context.
			mGLSurfaceView.setEGLContextClientVersion(2);
			
			final DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

			// Set the renderer to our demo renderer, defined below.
			mRenderer = new GLRenderer(this);
			mGLSurfaceView.setRenderer(mRenderer, displayMetrics.density);					
		} 
		else 
		{
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
			return;
		}
		
		findViewById(R.id.button_set_fire).setOnClickListener(new OnClickListener() 
		{		
			@Override
			public void onClick(View v) 
			{
				showDialog(MIN_DIALOG);				
			}
		});
		
		// Restore previous settings
		if (savedInstanceState != null)
		{
		//????????
		}
	}

	@Override
	protected void onResume() 
	{
		// The activity must call the GL surface view's onResume() on activity
		// onResume().
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() 
	{
		// The activity must call the GL surface view's onPause() on activity
		// onPause().
		super.onPause();
		mGLSurfaceView.onPause();
	}
	
	@Override
	protected void onSaveInstanceState (Bundle outState)
	{
		outState.putInt(MIN_SETTING, mMinSetting);
		outState.putInt(MAG_SETTING, mMagSetting);
	}
	
	
	
	
	
}