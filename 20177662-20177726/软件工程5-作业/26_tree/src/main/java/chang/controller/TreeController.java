package chang.controller;

import chang.pojo.Children;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TreeController {

    @PostMapping("/getTree")
    public Children getTree(String data){


        /*
        导师：张三
            2016级博士生：天一、王二、吴五
            2015级硕士生：李四、王五、许六
            2016级硕士生：刘一、李二、李三
            2017级本科生：刘六、琪七、司四
         */

        System.out.println(data);
        String[] s = data.split("\n");

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

        return ds;
    }
}
