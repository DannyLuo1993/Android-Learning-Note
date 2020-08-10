class Day5_Array1{
	public static void main(String[] args){
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("Please input the student quanlity: ");
		int quanlity = input.nextInt();
		
		//init Array
		//数组类型 数组名 = new 数组类型[元素个数]
		int[] score = new int[quanlity];
		
		//写for循环时，常出没有写i数据类型的错误
		for(int i = 0; i<quanlity; i++){
			System.out.println("Please record the score of student " + (i+1));
			score[i] = input.nextInt();
		}
		for(int i = 0; i<quanlity; i++){
			System.out.println(score[i]);
		}
	}
}