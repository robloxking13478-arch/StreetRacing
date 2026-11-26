package com.bulat.streetracing

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.opengl.GLES20
import android.opengl.GLSurfaceView.Renderer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainActivity : Activity() {
    private lateinit var gameView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        gameView = GLSurfaceView(this).apply {
            setEGLContextClientVersion(2)
            setRenderer(StreetRacingRenderer())
            renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        }
        setContentView(gameView)
    }

    override fun onResume() {
        super.onResume()
        if (::gameView.isInitialized) gameView.onResume()
    }

    override fun onPause() {
        if (::gameView.isInitialized) gameView.onPause()
        super.onPause()
    }
}

private class StreetRacingRenderer : Renderer {
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.035f, 0.045f, 0.065f, 1.0f)
        GLES20.glDisable(GLES20.GL_DEPTH_TEST)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }
}
