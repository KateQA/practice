package etc;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "singleParameter")
    public static Object[][] singleParameter() {//должен быть статик, двухмерный массив
        System.out.println("Calling Data provider method...");//может принимать методы

        String[][] result = {{"abc"}, {"def"}, {"ghi"}};//сразу инициализированный массив, состоящий из трех массивов строк

        return  result;
    }

    @DataProvider(name = "multiParameters")
    public static Object[][] multiParameters() {
        System.out.println("Calling Data provider method...");

          return new Object[][] { //массив из трех массивов обьектов(т.к. у нас тут не только строки, но и инты)
                  {"abc", 123, 2},
                  {"def", 345, 2},
                  {"ghi", 567, 2}
          };


    }

    @DataProvider(name = "positiveValues")
    public static Object[][] dataForErrorsInLogin(){

        return new Object[][]{
                {"testkatytest@gmail.com", "A5fg48BW378O"},
                {"toseney@dumoac.net", "123123"},
                {"pokijoli@yk20.com", "321321"}
        };
    }

    @DataProvider(name = "negativeValues")
    public static Object[][] negativeValues(){

        return new Object[][]{
                {"", true, "", true},
                {"abc", true, "", true},
                {"abc@abc.com", false, "password", false},
        };
    }
}
