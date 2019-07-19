package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sum {

    public static String twoSum(int[] nums, int target) {

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return String.format("[%d,%d]",i,j);
                }
            }
        }
        return "没有";
    }

    public static void main(String[] args) {
//      int[]  nums = {2, 7, 11, 15};
//       int target = 7;
//        System.out.println(twoSum(nums,target));
       HashMap<String,String> map = new HashMap();
       map.put("a","b");
        List<Integer> list =new ArrayList<Integer>();
        list.add(1);
        list.add(12);
        list.add(11);
        System.out.println(list.toString());
    }
}
