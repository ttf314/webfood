package com.javapandeng.utils;

public class test {
    public static void main(String[] args) {
        String inputString = "烤韭菜_3_|重庆麻辣牛油锅_3_|麻辣拌_3_|小份薯条_3_";
        String[] fields = inputString.split("\\|");

        for (String field : fields) {
            String[] parts = field.split("_");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            System.out.println("name:"+name);
            System.out.println("quantity:"+quantity);
            // 你可以将这些字段存入数据库中
            // 例如：调用一个保存到数据库的方法
            //saveToDatabase(name, quantity);
        }
    }
}
