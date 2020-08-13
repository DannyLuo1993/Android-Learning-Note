class Day07_Test_Rectangle {
	

	public static void main(String[] args){
		
		Rectangle rectangle = new Rectangle();
		System.out.println(rectangle.calculatePerimeter() + "/n" + rectangle.calculateArea());
	} 
}

class Rectangle {
	
	double length;
	double width;
	
	public double calculatePerimeter(){
		length = 2.0;
		width = 3.0;
		return (length+width)*2;
	} 
	
	public double calculateArea(){
		length = 2.0;
		width = 3.0;
		return length * width;
	}
}