package com.flutter.virtualsurface;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;

public class MainActivity extends AppCompatActivity {

  // change this to 50,000 and the application will restart
  private static final int VIRTUAL_DISPLAY_WIDTH = 50000;
  private static final int VIRTUAL_DISPLAY_HEIGHT = 2000;

  private VirtualDisplay mVirtualDisplay;
  private SurfaceTexture mSurfaceTexture;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
    int densityDpi = getResources().getDisplayMetrics().densityDpi;

    mSurfaceTexture = new SurfaceTexture(0);
    mSurfaceTexture.detachFromGLContext();

    Surface surface = new Surface(mSurfaceTexture);

    mVirtualDisplay = mDisplayManager.createVirtualDisplay(
        "crashing_virtual_display",
        VIRTUAL_DISPLAY_WIDTH,
        VIRTUAL_DISPLAY_HEIGHT,
        densityDpi,
        surface,
        0
    );

    SimplePresentation presentation = new SimplePresentation(this, mVirtualDisplay.getDisplay());
    presentation.show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mVirtualDisplay.release();
    mSurfaceTexture.release();
  }

}
