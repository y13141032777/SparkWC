package test;

import java.util.Arrays;
import java.util.Date;

public class QuockSort {
    public static void quickSort(int[] arr,int head,int tail){
        int i,j,flag,t;
        if(head>tail){
            return;
        }

        i=head;
        j=tail;
        //temp就是基准位
        flag = arr[head];

        while (i<j) {
            //先看右边，依次往左递减
            while (flag<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (flag>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换 arr[head] > arr[tail]
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
//5   4   7  8  8   5   6   3
        }
        //最后将flag与i和j相等位置的数字交换
        arr[head] = arr[i];
        arr[i] = flag;
        //递归调用左半数组
        quickSort(arr, head, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, tail);
    }




    public static void main(String[] args) {
        int[] arr1 = {1,31,7,44,9,22,13,23,14,24,15,25,16,26,17,27};
        int[] arr2 = {1,2,4,3,5};


        quickSort(arr2,0,arr2.length-1);
        for(int e : arr2){ System.out.print(e +","); }
    }
}
