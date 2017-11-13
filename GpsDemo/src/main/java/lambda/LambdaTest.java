package lambda;

import lambda.entity.Developer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hyq on 2017/9/15.
 */
public class LambdaTest {
    public static void main(String[] args) {
        //common1();
       // map_lambda(); {70000=2, 80000=2, 100000=1}
        // / map_collectoerTest();
    }



    public static void map_collectoerTest(){
        List<Developer> list = getDevelopers();
        //按salary分类,并且输出总量
        //Map<BigDecimal, Long> collectResult = list.stream().collect(
        //      Collectors.groupingBy(Developer::getSalary, Collectors.counting())
        //);

        //统计按salary分类,列出相同salary 人员的姓名{70000=[mkyong, iris], 80000=[alvin, 2], 100000=[jason]}
        /*Map<BigDecimal, List<String>> collectResult = list.stream().
                collect(Collectors.groupingBy(Developer::getSalary,
                        Collectors.mapping(Developer::getName, Collectors.toList())));
        */
        //同理，可以按姓名分类按姓名排序（如果key重复的情况）{2b=[80000, 80000], mkyong=[70000], iris=[70000], jason=[100000], alvin=[80000]}
        /*Map<String, List<BigDecimal>> collectResult = list.stream().collect(Collectors.groupingBy(Developer::getName,
                Collectors.mapping(Developer::getSalary, Collectors.toList())));*/

        //sumint分组求年龄总和. {2b=106, mkyong=33, iris=55, jason=10, alvin=20}
        /*Map<String, Integer> collectResult = list.stream()
                .collect(Collectors.groupingBy(Developer::getName, Collectors.summingInt(po -> po.getAge())));*/

        //list.stream().collect(Collectors.groupingBy(Developer::getName));//默认分组是输出为List的

        String testStr = "2,3,45,6,54,44";
        String[] array = testStr.split(",");

        for (int i = 0; i < array.length; i++) {
            System.out.println("当前元素是："+array[i]);
        }
        System.out.println(Arrays.asList(array));


        /*
        * 当前元素是：2
当前元素是：3
当前元素是：45
当前元素是：6
当前元素是：54
当前元素是：44
[2, 3, 45, 6, 54, 44]
[2, 3, 45, 6, 54, 44]
        *
        * */


        //当前元素是：2
        //当前元素是：3
        //当前元素是：45
        //当前元素是：6
        //当前元素是：54
        //当前元素是：44
        //[2, 3, 45, 6, 54, 44]
        //[2, 3, 45, 6, 54, 44]

        //String[] array = new String[]{"1","2","4","5555"};
        // int[] ints = new int[]{1,2,3,4,5,6};
       // Arrays.stream(ints).boxed()[1, 2, 4, 5555]
        List<String> collectResult = Arrays.stream(array).collect(Collectors.toList());
        System.out.println(collectResult);

    }

    public static void map_lambda(){
        List<Developer> list = getDevelopers();
        //如果key值重复就抛出异常。
        Map<String, Developer> map1 = list.stream().collect(Collectors.toMap(Developer::getName, p -> p));
        Map<Integer, String> map2 = list.stream().collect(Collectors.toMap(Developer::getAge, Developer::getName));
        //Map<BigDecimal, String> ma3 = list.stream().collect(Collectors.toMap(Developer::getSalary, Developer::getName));
        //按slary分类, value总数
        //按salary分类,value: name逗号隔开

        Map<BigDecimal, Long> map4 = list.stream().collect(Collectors.groupingBy(Developer::getSalary, Collectors.counting()));
        System.out.println("group by salary： "+map4);
                //Employee::getGender,
                //Collectors.groupingBy(p ->  p.getDob().getMonth(),
                //Collectors.mapping(Employee::getName,Collectors.joining(", ")))));

        //list.stream().collect(Collectors.groupingBy(Developer::getSalary,Collectors.joining()));
        Map<BigDecimal, String> map5 = list.stream().collect(Collectors.groupingBy(Developer::getSalary,
                Collectors.mapping(Developer::getName,Collectors.joining(",","前缀","后缀"))));
        System.out.println("group by salary,list names: "+map5);

        //Map<String, Developer> map4 = list.stream()
                //.collect(Collectors.toCollection(ArrayList<Developer>::new));
        //System.out.println("aaaaa");


        /*
        Map<Integer, Integer> result1 = list.stream().collect(
                Collectors.toMap(Developer::getAge,Developer::getAge));
         */

      // * @param <T> the type of the input elements  Developer::getName, Developer::getName)

        //* @param <K> the output type of the key mapping function
     //* @param <U> the output type of the value mapping function
     //* @param keyMapper a mapping function to produce keys
    //* @param valueMapper a mapping function to produce values
                //.collect(Collectors.toList());
        //list.forEach(n -> System.out.println(n.getAge()));
    }

    /**
     * 普通排序
     */
    public static void common1(){
        List<Developer> listDevs = getDevelopers();
        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer.getAge());
        }
        //sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        System.out.println("After  Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer.getAge());
        }
    }

    private static List<Developer> getDevelopers() {
        List<Developer> result = new ArrayList<Developer>();
        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("70000"), 55));
        result.add(new Developer("2b", new BigDecimal("80000"), 53));
        result.add(new Developer("2b", new BigDecimal("80000"), 53));
        return result;

    }


}
