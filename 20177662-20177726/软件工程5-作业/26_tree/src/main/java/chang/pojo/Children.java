package chang.pojo;

import java.util.List;

public class Children {

    private String title;
    private List<Children> children;

    public Children(){};

    public Children(String title,List children){
        this.title = title;
        this.children = children;
    }
    public Children(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Children{" +
                "title='" + title + '\'' +
                ", children=" + children +
                '}';
    }
}
