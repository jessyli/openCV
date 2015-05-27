package openCV;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class TestRecCom {

	//
	// Detects faces in an image, draws boxes around them, and writes the
	// results
	// to "faceDetection.png".
	//
	public void run() {
		System.out.println("\nRunning TestRecCom");

		// Create a face detector from the cascade file in the resources
		// directory.

		CascadeClassifier faceDetector_frontal = new CascadeClassifier(
				"/Users/jessyli/opencv-3.0.0-rc1/data/haarcascades/haarcascade_frontalface_alt2.xml");
		CascadeClassifier faceDetector_profile = new CascadeClassifier(
				"/Users/jessyli/opencv-3.0.0-rc1/data/haarcascades/haarcascade_profileface.xml");
		File folder = new File("/Users/jessyli/Documents/SchoolImages");
		File[] listOfFiles = folder.listFiles();
		Mat[] images = new Mat[listOfFiles.length];
		ArrayList<Integer> al = new ArrayList<Integer>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				images[i] = Imgcodecs.imread(listOfFiles[i].getAbsolutePath());
				// Detect faces in the image.
				// MatOfRect is a special container class for Rect.
				MatOfRect faceDetections = new MatOfRect();
				double w = (double) images[i].width();
				double h = (double) images[i].height();
				double max = Math.max(w, h);
				faceDetector_frontal.detectMultiScale(images[i],
						faceDetections, 1.10, 3,
						Objdetect.CASCADE_DO_CANNY_PRUNING, new Size(8, 8),
						new Size(max / 2, max / 2));
				//
				faceDetector_profile.detectMultiScale(images[i],
						faceDetections, 1.10, 3,
						Objdetect.CASCADE_DO_CANNY_PRUNING, new Size(8, 8),
						new Size(max / 2, max / 2));
				if (faceDetections.toArray().length != 0) {
					al.add(i);
				}
				ArrayList<Rect> rects = new ArrayList<Rect>();
				//
				// Draw a bounding box around each face.
				for (Rect rect : faceDetections.toArray()) {
					Imgproc.rectangle(
							images[i],
							new Point(rect.x, rect.y),
							new Point(rect.x + rect.width, rect.y + rect.height),
							new Scalar(0, 255, 0));
					rects.add(rect);
					String fullPath = "/Users/jessyli/Documents/SchoolImages/result/";
					String filename = listOfFiles[i].getName();
					// System.out.println(filename);
					String path = fullPath + filename + ".jpg";
					Imgcodecs.imwrite(path, images[i]);
				}

			}
		}
		System.out.println(al);
	}

}
