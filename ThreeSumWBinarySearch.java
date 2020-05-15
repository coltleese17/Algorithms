
public class ThreeSumWBinarySearch {
	
	//returns number of matches to target
	private static int threeSumWBinarySearch(int[] a, int target) {
		if (a == null || a.length == 0) {
			return -1;
		}
		int count = 0;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int low = j + 1, high = a.length - 1;
				
				while (low<=high) {
					int mid = (low + high) / 2;
					int sum = a[mid] + a[i] + a[j];
					if (sum == target) {
						count++;
						break;
					}
					if (sum > target) {
						high = mid -1;
					}
					if (sum < target) {
						low = mid + 1;
					}
				}
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		int[] a = {40,30,20,10,0,-10,-20,30,40};
		int target = 0;
		
		System.out.println(threeSumWBinarySearch(a,target));
	}

}
