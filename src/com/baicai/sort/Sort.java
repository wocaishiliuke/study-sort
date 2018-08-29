package com.baicai.sort;

public class Sort {

    public static void main(String[] args) {
        int[] arr = null;
    }

    /**
     * 直插排序（从小到大）
     * @param arr
     * @return
     */
    public int[] insertionSort(int[] arr) {
        if (arr == null || arr.length <=1) return arr;
        int sentinel;                                   //哨兵：1.指向当前值，判断索引越界
        for (int i = 1; i < arr.length; i++) {          //将arr[1]到arr[n-1]插入到有序区。初始有序区：arr[0]
            if (arr[i] < arr[i-1]) {                    //当要插入的arr[i]>arr[i-1]，即大于有序区所有记录，则保持不动，否则才进行逐一比较
                sentinel = arr[i];                      //哨兵=当前要插入的记录arr[i]
                do {

                }while();

            }

        }
        return arr;
    }
}
