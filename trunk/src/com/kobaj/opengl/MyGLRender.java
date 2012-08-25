package com.kobaj.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.kobaj.audio.Music;
import com.kobaj.audio.MusicPlayList;
import com.kobaj.audio.Sound;
import com.kobaj.math.FPSManager;
import com.kobaj.math.Physics;
import com.kobaj.opengldrawable.Text;
import com.kobaj.openglgraphics.AmbientLightShader;

public abstract class MyGLRender implements GLSurfaceView.Renderer
{
	//and fps
	protected FPSManager fps;
	
	//text mmm
	protected Text text;
	
	//shaders
	protected AmbientLightShader ambient_light;
	
	// camera
	protected float[] my_view_matrix = new float[16];
	protected float[] my_proj_matrix = new float[16];
	
	//sound and music
	protected Music music;
	
	public void onSurfaceCreated(GL10 unused, EGLConfig config)
	{
		//gotta reset
		com.kobaj.loader.GLBitmapReader.resetLoadedTextures();
		
		// Set the background frame color
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// Use culling to remove back faces.
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		
		// disable depth testing
		GLES20.glDisable(GLES20.GL_DEPTH_TEST);
		
		//mmm blending
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA); // no see thru
		
		// shaders
		ambient_light = new AmbientLightShader();
		com.kobaj.math.Constants.ambient_light = ambient_light;
		
		//fps
		fps = new FPSManager();
		
		//text setup
		text = new Text(ambient_light, my_view_matrix, my_proj_matrix);
		com.kobaj.math.Constants.text = text; //referencing!
		
		//sound and audio setup
		music = new Music();
		com.kobaj.math.Constants.music_play_list = new MusicPlayList(music);
		com.kobaj.math.Constants.sound = new Sound();

		//physics setup
		com.kobaj.math.Constants.physics = new Physics();
		
		//camera
		com.kobaj.math.Constants.my_view_matrix = my_view_matrix;
		com.kobaj.math.Constants.my_proj_matrix = my_proj_matrix;
		
		onInitialize();
	}
	
	abstract void onInitialize();
	
	public void onSurfaceChanged(GL10 unused, int width, int height)
	{
		GLES20.glViewport(0, 0, width, height);
		
		float ratio = (float) width / height;
		com.kobaj.math.Constants.shader_width = ratio * 2.0;
		
		// this projection matrix is applied to object coodinates
		// in the onDrawFrame() method
		Matrix.orthoM(my_proj_matrix, 0, -ratio, ratio, -1, 1, .99999999f, 2);
		Matrix.setLookAtM(my_view_matrix, 0, //this is the identity...
				0, 0, 0, 
				0f, 0f, -5.0f, 
				0f, 1.0f, 0.0f);
		
		com.kobaj.math.Constants.x_shader_translation = 0;
		com.kobaj.math.Constants.y_shader_translation = 0;
	}
	
	public void onSurfaceDestroyed()
	{
		//empty for now
	}
	
	public void onDrawFrame(GL10 unused)
	{
		onUpdateFrame();
		
		// Redraw background color
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		
		onDraw();
	}
	
	abstract void onDraw();
	
	public void onUpdateFrame()
	{
		//might put fps here.
		fps.onUpdate(SystemClock.uptimeMillis());
		
		onUpdate(fps.getDelta());
	}
	
	abstract void onUpdate(double delta);
	
	public void onScreenPause()
	{
		onPause();
	}
	
	abstract void onPause();
}