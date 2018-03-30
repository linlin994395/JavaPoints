package cn.edu.neu.xl.Sort;


/*
 * 对同一对象的排序可能有多种不同的排序方式。相比于自然排序，它的一个好处是将排序算法和具体的实体类分离了。
 * Comparator 接口中定义了两个方法： compare(Object o1, Object o2) 和 equals 方法，由于 equals 方法所有对象都有的方法，因此当我们实现 Comparator 接口时，我们只需重写 compare 方法，而不需重写 equals 方法。
 */
public class ComparatorSort  {

	//在用到的时候直接用下面的方法即可。不用单独的写一个Comparator
	/* Comparator<AQIRankCity> comparator = new Comparator<AQIRankCity>() {
         public int compare(AQIRankCity o1, AQIRankCity o2) {
             return o1.getRank() > o2.getRank() ? 1 : -1;
         }
     };*/

}
