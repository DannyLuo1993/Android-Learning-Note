class Day05_Array2{
	
	public static void main (String[] args){
		
		char[] lowercaseletter = new char[26];
		for(int i = 0; i<26; i++){
			lowercaseletter[i] = (char)('a' + i);
			System.out.println(lowercaseletter[i]);
		}
	}
}