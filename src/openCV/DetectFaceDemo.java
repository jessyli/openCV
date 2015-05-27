package openCV;

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

// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
class DetectFaceDemo {
	public void run() {
		System.out.println("\nRunning DetectFaceDemo");

		// Create a face detector from the cascade file in the resources
		// directory.

		CascadeClassifier faceDetector_frontal = new CascadeClassifier(
				"/Users/jessyli/opencv-3.0.0-rc1/data/haarcascades/haarcascade_frontalface_alt2.xml");

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
						faceDetections, 1.05, 3,
						Objdetect.CASCADE_DO_ROUGH_SEARCH, new Size(8, 8),
						new Size(max, max));
				if (faceDetections.toArray().length != 0) {
					al.add(i);
				}
				// Draw a bounding box around each face.
				for (Rect rect : faceDetections.toArray()) {
					int area = rect.width * rect.height;
					double imagearea = images[i].size().area();
					double rate = imagearea / area;
					Imgproc.rectangle(
							images[i],
							new Point(rect.x, rect.y),
							new Point(rect.x + rect.width, rect.y + rect.height),
							new Scalar(0, 255, 0));
					Point center = new Point((2 * rect.x + rect.width) / 2,
							(2 * rect.y + rect.height) / 2);
					Point imagecenter = new Point((images[i].size().width) / 2,
							(images[i].size().height) / 2);
					double CenterRadius = Math.abs((center.x - imagecenter.x)
							* (center.x - imagecenter.x)
							+ (center.y - imagecenter.y)
							* (center.y - imagecenter.y));

					// Save the visualized detection.
					// String filename = listOfFiles[i].getName();
					String fullPath = "/Users/jessyli/Documents/SchoolImages/result/";
					String filename = listOfFiles[i].getName();
					String path = fullPath + filename + ".jpg";

					String Paths = "/Users/jessyli/Documents/SchoolImages/frontalface/";
					String names = listOfFiles[i].getName();
					String path2s = Paths + names + ".jpg";
					File files = new File(path2s);
					if (!files.exists()) {
						Imgcodecs.imwrite(path, images[i]);
					}
				}
			}

		}
		System.out.println(al);
	}
}
