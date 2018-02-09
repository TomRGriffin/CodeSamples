package Samples;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        HashMap complementsMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (complementsMap.containsKey(nums[i])) {
                int firstIndex = (int) complementsMap.get(nums[i]);
                return new int [] {firstIndex, i};
            } else {
                int complement = target - nums[i];
                complementsMap.put(complement, i);
            }
        }
        return new int [] {};
    }

}
