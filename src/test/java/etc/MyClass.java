package etc;

import java.util.ArrayList;
import java.util.List;

public class MyClass implements Cloneable {

    private String name = " abc ";

    private Integer age = 11;

    private List<String> list = new ArrayList<>();

    @Override
    public MyClass clone() {
        try {
            List<String> newList = new ArrayList<>();
            for (String item : this.list) {
                newList.add(item);
            }

            MyClass cloneResult = (MyClass) super.clone();
            cloneResult.list = newList;
            return cloneResult;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public class InnerClass {

        private String innerName;

        private Integer innerAge;

        public void innerMethod() {
            System.out.println(name);
        }
    }
}
