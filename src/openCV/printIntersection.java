package openCV;

import java.awt.Rectangle;
import java.util.ArrayList;

public class printIntersection {
	


public static void main(String[] args) {
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	Rectangle r1 = new Rectangle(0, 10, 5, 8);
    Rectangle r2 = new Rectangle(3, 15, 17, 14);
    
    rectangles.add(r1);
    rectangles.add(r2);
    if(r1.intersects(r2)){
    	
    Rectangle r3 = r1.intersection(r2);
//    System.out.println(r3);
    
	rectangles.add(1,r3);
	
	rectangles.remove(0);
	
	
	rectangles.remove(1);
	for(int i=0; i<rectangles.size(); i++){
		System.out.println(rectangles.get(i));
	}
//    System.out.println(rectangles.get(0));
    }
    else{
    	System.out.println(r1);
        System.out.println(r2);
    }
    
    
}
}