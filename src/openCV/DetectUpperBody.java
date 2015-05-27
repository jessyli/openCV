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

public class DetectUpperBody {

	//
	// Detects faces in an image, draws boxes around them, and writes the
	// results
	// to "faceDetection.png".
	//
	public void run() {
		System.out.println("\nRunning DetectFaceDemo");

		// Create a face detector from the cascade file in the resources
		// directory.

		CascadeClassifier faceDetector_upper = new CascadeClassifier(
				"/Users/jessyli/opencv-3.0.0-rc1/data/haarcascades/haarcascade_upperbody.xml");

		// Mat image = Imgcodecs
		// .imread("/Users/jessyli/Documents/SchoolImages/005.jpg");

		File folder = new File("/Users/jessyli/Documents/SchoolImages/frontalface");
		File[] listOfFiles = folder.listFiles();
		Mat[] images = new Mat[listOfFiles.length];
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				images[i] = Imgcodecs.imread(listOfFiles[i].getAbsolutePath());
//				System.out.println(images[i].size());
				// System.out.println(listOfFiles[i].getName());
				// Detect faces in the image.
				// MatOfRect is a special container class for Rect.
				MatOfRect faceDetections = new MatOfRect();
				double w = (double)images[i].width();
			    double h = (double)images[i].height();
			    double max = Math.max(w,h);
			    faceDetector_upper.detectMultiScale(images[i], faceDetections, 1.05, 0, 
			                   Objdetect.CASCADE_DO_CANNY_PRUNING, new Size(30, 30), new Size(max/2, max/2));
				// faceDetector_frontal
				// .detectMultiScale(images[i], faceDetections);
				// faceDetector_pedestrians.detectMultiScale(image,
				// faceDetections);
				// faceDetector_profileface.detectMultiScale(image,
				// faceDetections);
//				System.out.println(String.format("Detected %s faces",
//						faceDetections.toArray().length));
				if (faceDetections.toArray().length != 0) {
					al.add(i);
				}
				// Draw a bounding box around each face.
				for (Rect rect : faceDetections.toArray()) {
					int area = rect.width*rect.height;
					double imagearea = images[i].size().area();
					double rate = imagearea/area;
//					if(rate<=17){
					Imgproc.rectangle(
							images[i],
							new Point(rect.x, rect.y),
							new Point(rect.x + rect.width, rect.y + rect.height),
							new Scalar(255, 0, 0));
					System.out.println(imagearea);
					System.out.println(area);
					System.out.println(imagearea/area);
					System.out.println(listOfFiles[i].getName());
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$");
					String fullPath = "/Users/jessyli/Documents/SchoolImages/UpperBody/";
					String filename = listOfFiles[i].getName();
					String path = fullPath + filename+".jpg";
//					File file = new File(path );
//					if (!file.exists()) {
//						System.out.println("$$$$$$$$$$$$$$$$$$$$");
						Imgcodecs.imwrite(path, images[i]);
//					}
//					}
					
					
				}

				// Save the visualized detection.
				// String filename = listOfFiles[i].getName();
				

				// String filename =
				// "/Users/jessyli/Documents/SchoolImages/result/Name";
				// System.out.println(String.format("Writing %d", i));
				
//				System.out.println(path);
				
			}

		}
		System.out.println(al);
		// for (int i = 0; i < al.size(); i++) {
		// String fullPath = "/Users/jessyli/Documents/SchoolImages/result";
		// Imgcodecs.imwrite(Integer.toString(al.get(i)), images[i]);
	}
}
// }


