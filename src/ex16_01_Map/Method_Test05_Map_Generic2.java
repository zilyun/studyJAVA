package ex16_01_Map;
import java.util.HashMap;
import java.util.Scanner;

public class Method_Test05_Map_Generic2 {

	static HashMap<String, Integer> max_min(int data[]){
		HashMap<String, Integer> minMaxMap = new HashMap<String, Integer>();
		
		for(int i = 0; i < data.length - 1; i++) {
			if(data[i] < data[i+1]) {
				int temp = data[i];
				data[i] = data[i+1];
				data[i+1] = temp;
			}
		}
		
		minMaxMap.put("min", data[data.length-1]);
		minMaxMap.put("max", data[0]);
		
		return minMaxMap; 
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int data[] = new int[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 5개를 입력하세요.");
		for(int i = 0; i < data.length; i++) 
			data[i] = sc.nextInt();
		HashMap<String, Integer> m = max_min(data);
		System.out.println("최댓값 = "+ m.get("max"));
		System.out.println("최솟값 = "+ m.get("min"));
	}

}
