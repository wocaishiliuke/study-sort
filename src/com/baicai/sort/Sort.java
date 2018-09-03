package com.baicai.sort;

/**
 * 排序（从小到大）
 */
public class Sort {

    public static void main(String[] args) {
        //int[] arr = {3,51,34,7,53,89,6,5,21,17,14,108,8,46,91,28,7,21};
        int[] arr = {9,3,4,5,46,8,7,21};

        //1.插入排序
        //insertionSort(arr);

        //2.希尔排序
        /*int increment = arr.length;
        do {
            increment = increment / 2;
            arr = shellSort(arr,increment);          //排4次,increment=9,4,2,1
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }while (increment > 1);*/

        //3.冒泡排序
        //bubbleSort(arr);
        //betterBubbleSort(arr);

        //4.快速排序
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 4.快速排序
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length <= 1) return arr;
        if (left < 0 || right >= arr.length || left > right) return null;

        int pivotPosition = partition(arr, left, right);     //对R[left...right]划分，并返回划分后基准记录的位置
        if (left < pivotPosition - 1)
            quickSort(arr, left, pivotPosition - 1);        //对左区间递归快速排序
        if (right > pivotPosition + 1)
            quickSort(arr, pivotPosition + 1, right);        //对右区间递归快速排序
        return arr;
    }

    //划分
    private static int partition(int[] arr, int left, int right) {
        //参数异常
        if (arr == null || arr.length < 1 || left < 0 || right >= arr.length || left > right) return -1;
        //默认初始基准=arr[left]
        int temp;
        //左右交替扫描，直到left=right
        while (left < right) {
            //从右向左扫描
            while (left < right && arr[right] >= arr[left]) right--;
            //不能用arr[right]<pivot，因为还有可能是left=right的原因跳出循环
            //交换前基准=arr[left]，交换后基准=arr[right]，比其小的放在了其左边（arr[left]）
            if (left < right ) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }

            //从左向右扫描
            while (left < right && arr[left] <= arr[right]) left++;
            if (left < right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                right--;
            }
        }
        //此时left=right，就是基准的位置
        return left;
    }

    /**
     * 3.冒泡排序(优化1)
     * @param arr
     * @return
     */
    private static int[] betterBubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        int temp;
        boolean flag;
        int lastExchangeIndex = 0;                      //初始值只为了求第一趟开始前的无序区开始索引
        for (int i = 1; i < arr.length; i++) {          //第i趟冒泡,共n-1趟
            flag = false;                               //每趟都要重置为false
            //第i趟开始前，无序区的起始索引
            int unorderedBeginIndex = (i - 1 >= lastExchangeIndex) ? i - 1 : lastExchangeIndex;
            for (int j = arr.length - 1; j > unorderedBeginIndex; j--) {
                if (arr[j] < arr[j - 1]) {
                    //交换
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = true;                        //有交换就置为true
                    lastExchangeIndex = j;
                }
            }
            if (!flag) return arr;                      //没有交换就直接终止整个排序
        }
        return arr;
    }

    /**
     * 3.冒泡排序
     * @param arr
     * @return
     */
    private static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        int temp;
        boolean flag;
        for (int i = 1; i < arr.length; i++) {                  //第i趟冒泡,共n-1趟
            flag = false;                                       //每趟都要置为false
            for (int j = arr.length - 1; j >= i; j--) {         //无序区arr[i-1],arr[i]...arr[n-1]
                if (arr[j] < arr[j - 1]) {                      //j=arr[i]...arr[n-1]
                    //交换
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    flag = true;                                //有交换就置为true
                }
            }
            if (!flag) return arr;                              //没有交换就直接终止整个排序
        }
        return arr;
    }

    /**
     * 2.希尔排序
     * @param arr
     * @param d
     * @return
     */
    private static int[] shellSort(int[] arr, int d) {
        if (arr == null || arr.length <= 1) return arr;
        int mirror,cursor;
        //将各组的记录进行直插排序
        for (int i = d; i < arr.length; i++) {
            if (arr[i] < arr[i-d]) {
                mirror = arr[i];
                cursor = i - d;
                do {
                    arr[cursor + d] = arr[cursor];
                    cursor -= d;
                }while (cursor >= 0 && mirror < arr[cursor]);

                arr[cursor + d] = mirror;
            }

            /*
            mirror = arr[i];
            cursor = i - d;
            while (cursor >= 0 && mirror < arr[mirror]) {
                arr[cursor + d] = arr[cursor];
                cursor -= d;
            }
            arr[cursor + d] = mirror;*/
        }
        return arr;
    }

    /**
     * 1.直插排序
     * @param arr
     * @return
     */
    private static int[] insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return arr;
        /* 1.mirror：不是哨兵，只保存当前要插入记录的副本，不做越界控制
           2.cursor：逐一比较的游标 */
        int mirror,cursor;
        // 将arr[1]到arr[n-1]逐个插入到有序区。初始有序区：arr[0]
        for (int i = 1; i < arr.length; i++) {
            // 当要插入的arr[i]>arr[i-1]，即大于有序区所有记录，则保持不动，否则才进行逐一比较
            if (arr[i] < arr[i-1]) {
                mirror = arr[i];
                cursor = i - 1;
                // 从后向前逐一比对
                do {
                    arr[cursor + 1] = arr[cursor];  // 后移有序区记录
                    cursor--;                       // 游标前移
                }while (cursor >= 0 && mirror < arr[cursor]);
                // 比对完，插入arr[i]到正确的位置
                arr[cursor + 1] = mirror;
            }

            /*
            //while实现方式
            mirror = arr[i];
            cursor = i - 1;
            while (cursor >= 0 && mirror < arr[cursor]) {
                arr[cursor + 1] = arr[cursor];
                cursor--;
            }
            arr[cursor + 1] = mirror;*/
        }
        return arr;
    }
}
