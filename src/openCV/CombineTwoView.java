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
public class CombineTwoView {






	//
	// Detects faces in an image, draws boxes around them, and writes the
	// results
	// to "faceDetection.png".
	//
	public void run() {
		System.out.println("\nRunning CombineTwoView");

		// Create a face detector from the cascade file in the resources
		// directory.

		CascadeClassifier faceDetector_profile = new CascadeClassifier(
				"/Users/jessyli/opencv-3.0.0-rc1/data/haarcascades/haarcascade_profileface.xml");

		// Mat image = Imgcodecs
		// .imread("/Users/jessyli/Documents/SchoolImages/005.jpg");

		File folder = new File("/Users/jessyli/Documents/SchoolImages/frontalface");
		File[] listOfFilesnew = folder.listFiles();
		Mat[] imagesnew = new Mat[listOfFilesnew.length];
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < listOfFilesnew.length; i++) {
			if (listOfFilesnew[i].isFile()) {
				imagesnew[i] = Imgcodecs.imread(listOfFilesnew[i].getAbsolutePath());
//				System.out.println(images[i].size());
				// System.out.println(listOfFiles[i].getName());
				// Detect faces in the image.
				// MatOfRect is a special container class for Rect.
				MatOfRect faceDetections = new MatOfRect();
				double w = (double)imagesnew[i].width();
			    double h = (double)imagesnew[i].height();
			    double max = Math.max(w,h);
			    faceDetector_profile.detectMultiScale(imagesnew[i], faceDetections, 1.10, 3, 
			                   Objdetect.CASCADE_DO_ROUGH_SEARCH, new Size(8, 8), new Size(max/2, max/2));
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
					double imagearea = imagesnew[i].size().area();
					double rate = imagearea/area;
					Imgproc.rectangle(
							imagesnew[i],
							new Point(rect.x, rect.y),
							new Point(rect.x + rect.width, rect.y + rect.height),
							new Scalar(0, 0, 255));
					Point center = new Point((2*rect.x+rect.width)/2, (2*rect.y+rect.height)/2);
					Point imagecenter  = new Point((imagesnew[i].size().width)/2, (imagesnew[i].size().height)/2);
					double CenterRadius = Math.abs((center.x-imagecenter.x)*(center.x-imagecenter.x)+(center.y-imagecenter.y)*(center.y-imagecenter.y));
//					System.out.println(CenterRadius);
//					System.out.println(imagearea);
//					System.out.println(area);
//					System.out.println(imagearea/area);
//					System.out.println(listOfFilesnew[i].getName());
//					System.out.println("$$$$$$$$$$$$$$$$$$$$$$");
					if(rate<=18 && faceDetections.toArray().length<=1 && CenterRadius<=3000){
					
					
					String fullPath = "/Users/jessyli/Documents/SchoolImages/frontalface/";
					String filename = listOfFilesnew[i].getName();
					String path = fullPath + filename;
//						System.out.println("$$$$$$$$$$$$$$$$$$$$");
						Imgcodecs.imwrite(path, imagesnew[i]);
						
					}
					else{
						String fullPath = "/Users/jessyli/Documents/SchoolImages/frontalface/";
						String filename = listOfFilesnew[i].getName();
						String path = fullPath + filename+".jpg";
						File file = new File(path );
						if (file.exists()) {
//							System.out.println(path);
//							imagesnew[i].release();
							file.delete();
						}
					}
					
					
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

