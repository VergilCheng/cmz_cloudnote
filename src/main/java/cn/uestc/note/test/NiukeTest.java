package cn.uestc.note.test;

import java.util.*;

public class NiukeTest {//牛客网统计人数问题

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//客人数量
        if (n > 100) {
            System.out.println("输入的n的值应该小于等于100");
            return;
        }
        int[][] value = new int[n][2];
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            String[] split = str.split(",");
            value[i][0] = Integer.parseInt(split[0]);
            value[i][1] = Integer.parseInt(split[1]);
        }
        System.out.println("-1,-1");

        HashMap<String, Integer> hashMap = new HashMap<>();


        int floorTime = 12;
        int count = 0;

        while (floorTime < 20) {
            for (int[] element : value) {
                if (element[1] > floorTime && element[0] <= floorTime) {
                    count++;
                }
            }
            hashMap.put("[" + floorTime + "," + (floorTime + 1) + ")", count);
            count = 0;
            floorTime++;
        }
        floorTime = 12;
        while (floorTime < 20) {
            System.out.println("[" + floorTime + "," + (floorTime + 1) + ")" +
                    hashMap.get("[" + floorTime + "," + (floorTime + 1) + ")"));
            floorTime++;
        }

    }


}
