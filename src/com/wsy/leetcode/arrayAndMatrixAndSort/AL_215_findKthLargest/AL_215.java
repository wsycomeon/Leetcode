package com.wsy.leetcode.arrayAndMatrixAndSort.AL_215_findKthLargest;

public class AL_215 {
    public static void main(String[] args) {

        int[] a = {3, 2, 1, 5, 6, 4};
        int[] a1 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        System.out.println(new Official().findKthLargest(a, 3));
        System.out.println(new Official().findKthLargest2(a, 3));
        System.out.println(new Official().findKthLargest3(a, 3));


        System.out.println(new Official().findKthLargest(a1, 5));
        System.out.println(new Official().findKthLargest2(a1, 5));
        System.out.println(new Official().findKthLargest3(a1, 5));


    }
}
