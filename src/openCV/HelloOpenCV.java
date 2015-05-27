package openCV;

import org.opencv.core.Core;

public class HelloOpenCV {
	  public static void main(String[] args) {
	    System.out.println("Hello, OpenCV");

	    // Load the native library.
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    
	    
//	    new TestRecCom().run();
//	    new TestRecNonCom().run();
	    new DetectFrontalFace().run();
	    new DetectProfileFace().run();
	    new CombineTwoView().run();
////////	    new DetectUpperBody().run();
	    new DetectFaceDemo().run();
	  }
	}
