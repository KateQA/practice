package etc;

import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "singleParameter")//указываем к чему обращаться, откуда брать данные для выполнения, обращается к имени(name)
    public void dataProviderTest(String parameter) {//надо указать верное количество параметров, все которые есть в датапровайдер(сколько в каждом массиве), иначе выкинет ошибку при исполнении
        System.out.println("Parameter: " + parameter);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "multiParameters")
    public void dataProviderTest2(String parameter1, Integer parameter2, Integer parameter3) {
        System.out.println("Parameter1: " + parameter1 + "; " + "Parameter2*Parameter3: " + parameter2 * parameter3);//с параметрами при вызове можно взаимодействовать как угодно, как и всегда
    }

}
