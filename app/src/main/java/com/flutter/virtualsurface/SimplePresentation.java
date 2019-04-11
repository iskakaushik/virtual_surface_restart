package com.flutter.virtualsurface;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class SimplePresentation extends Presentation {

  public SimplePresentation(Context outerContext, Display display) {
    super(outerContext, display);
    prepareDisplayWindow();
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.simple_presentation);
  }

    private void prepareDisplayWindow() {
    Window window = getWindow();
    if (window != null) {
      window.setType(WindowManager.LayoutParams.TYPE_PRIVATE_PRESENTATION);
      window.addFlags(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);
      window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
  }
}
