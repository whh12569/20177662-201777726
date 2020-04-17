package chang;

import chang.pojo.Children;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
                /*
        导师：张三
            2016级博士生：天一、王二、吴五
            2015级硕士生：李四、王五、许六
            2016级硕士生：刘一、李二、李三
            2017级本科生：刘六、琪七、司四

            导师：张三 2016级博士生：天一、王二、吴五 2015级硕士生：李四、王五、许六 2016级硕士生：刘一、李二、李三 2017级本科生：刘六、琪七、司四
         */

        Scanner sc = new Scanner(System.in);
        String sssss = sc.nextLine();
        String[] s = sssss.split("\n");

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

        Children ds = null;
        for (int i = 0; i < s.length; i++) {
            if(i == 0){
                ds = new Children( s[i].trim().split("：")[1], new ArrayList<>());
            }else{
                String[] ss = s[i].trim().split("：");
                // 创建班级
                Children bj = new Children(ss[0],new ArrayList<>());
                // 创建班级学生集合
                ArrayList stus = (ArrayList) bj.getChildren();
                String[] sss = ss[1].split("、");
                for (int j = 0; j < sss.length; j++) {
                    stus.add(new Children(sss[j]));
                }
                bj.setChildren(stus);
                ds.getChildren().add(bj);
            }
        }

        System.out.println(ds);

    }

}
