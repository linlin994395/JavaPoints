package cn.edu.neu.xl.Sort;

import java.util.ArrayList;

public class Sort {
	
	/*解析java.util.Arrays中的sort()方法，此方法有18个覆盖版本，其中4个是为对象数组定义的，除了boolean类型外其他的基本类型都定义了两个方法。
	 * 例如：public static void sort(int[] a) public static void sort(int[] a,int fromIndex,int toIndex)  该排序算法是经过调优的快速排序算法   fromIndex(包括) toIndex（不包括）
	 * public static void sort(Object[] a)   public static void sort(Object[] a,int fromIndex,int toIndex) 该排序算法是经过修改的合并排序算法
	 * 根据元素的自然顺序对指定对象数组按升序进行排序。数组中的所有元素都必须实现 Comparable 接口。
	 * 此外，数组中的所有元素都必须是可相互比较的（也就是说，对于数组中的任何 e1 和 e2 元素而言，e1.compareTo(e2) 不得抛出 ClassCastException）。
	 * public static <T> void sort(T[] a,Comparator<? super T> c)   a - 要排序的数组    c - 确定数组顺序的比较器。null 值指示应该使用元素的自然顺序。 
	 * public static <T> void sort(T[] a,int fromIndex,int toIndex,Comparator<? super T> c)
	 */
	/*
	 * public interface Comparable<T> 此接口强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序，类的 compareTo 方法被称为它的自然比较方法。
	 * 实现此接口的对象列表（和数组）可以通过 Collections.sort（和 Arrays.sort）进行自动排序。实现此接口的对象可以用作有序映射中的键或有序集合中的元素，无需指定比较器。
	 * 对于类 C 的每一个 e1 和 e2 来说，当且仅当 e1.compareTo(e2) == 0 与 e1.equals(e2) 具有相同的 boolean 值时，类 C 的自然排序才叫做与 equals 一致。
	 * 注意，null 不是任何类的实例，即使 e.equals(null) 返回 false，e.compareTo(null) 也将抛出 NullPointerException。
	 * int compareTo(T o) 比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。 
	 */
	/*
	 * public interface Comparator<T> 强行对某个对象 collection 进行整体排序 的比较函数。
	 * 当且仅当对于一组元素 S 中的每个 e1 和 e2 而言，c.compare(e1, e2)==0 与 e1.equals(e2) 具有相等的布尔值时，Comparator c 强行对 S 进行的排序才叫做与 equals 一致 的排序。
	 * 通常来说，让 Comparator 也实现 java.io.Serializable 是一个好主意，因为它们在可序列化的数据结构（像 TreeSet、TreeMap）中可用作排序方法。
	 * 为了成功地序列化数据结构，Comparator（如果已提供）必须实现 Serializable。
	 * int compare(T o1,T o2) 比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
	 * boolean equals(Object obj) obj - 要进行比较的引用对象。 仅当指定的对象也是一个 Comparator，并且强行实施与此 Comparator 相同的排序时才返回 true。
	 */
	/*
	 * java.io.Serializable 可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，仅用于标识可序列化的语义。   一定要实现无参数构造方法
	 */
	
	/*
	 * 冒泡排序：时间复杂度 n*n    算法稳定性：稳定
	 * 思想：将第一个记录的关键字和第二个记录的关键字进行比较，如果后面的比前面的小则交换，然后比较第二个和第三个，依次类推。
	 * 比完一趟，最大的那个已经放到了最后的位置，这样就可以对前面N-1个数再循环比较。
	 */
	public static void bubbleSort(int [] array){
		if(array == null || array.length <= 0){
			return ;
		}
		//最多做 n-1 趟排序
		for(int i = 0;i < array.length-1;i++){
			//对当前无序区间score[0......length-i-1]进行排序
			for(int j=0;j<array.length-1-i;j++){
				if(array[j] > array[j+1]){
					Swap(array[j], array[j+1]);
				}
			}
			
		}
	}
	/*
	 * 选择排序： 时间复杂度 n*n 算法稳定性：不稳定
	 * 思想：从所有元素选择最小的放到第一个位置，从剩余元素中选择最小的放在第二个位置。。。
	 * 每一趟扫描中，只是在找到了最小元素后才进行一次位置的交换。效率会比冒泡更高一些。
	 */
	public static void selectSort(int [] array){
		if(array == null || array.length <= 0){
			return ;
		}
		//最多做 n-1 趟排序
		for(int i = 0;i<array.length-1;i++){
			int j=i; //临时保存最小元素的索引，若序列中存在比当前最小元素索引值还小的元素，责其索引值为当前最小元素的索引
			for(int k = i+1;k < array.length;k++){
				if(array[j] > array[k]){
					j = k;
				}
			}
			//如果最小元素的索引发生变化，则进行元素交换
			if(j != i)
			{
				Swap(array[j], array[i]);
			}
		}
	}
	/*
	 * 插入排序,时间复杂度为 n*n  为稳定算法                对于一个已排序的序列，插入排序的时间复杂度为 n
	 * 思想：每步将一个待排序元素，按其排序码的大小插入到前面已经排好的一组元素的适当位置，直到元素全部插入为止。
	 */
	public static void insertSort(int [] array,int beg,int end){
		for (int i = 1; i < array.length; i++) {
			if(array[i] <= array[i-1])
			{
				int temp = array[i];
				int j=i-1;
				for(;j>=0 && temp < array[j];j--)  //temp<array[j]即插入到合适的位置，  此时 temp 大于等于  array[j]  小于 array[j+1]
				{
					array[j+1] = array[j]; 
				}
				array[j+1] = temp; 
			}
		}
	}
	
	/*
	 * 折半插入排序,时间复杂度为 nlogn  为稳定算法
	 * 
	 */
	public static void binaryInsertSort(int [] array,int beg,int end){
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int low = 0, high = i-1;
			
			//确定low位置，将此位置之后的所有元素后移
			while(low<=high)
			{
				int mid = (low+high)/2;
				if(temp < array[mid])   //注意一定是  <
				{
					high = mid-1;
				}
				else
				{
					low = mid+1;
				}
			}
			
			for(int j= i;j>low;j--)
			{
				array[j] = array[j-1];
			}
			
			array[low] = temp;
		}		
	}
	
	/*
	 * 希尔排序（最小增量排序） 算法稳定性：不稳定  时间复杂度：O（nlogn）～O（n2），平均时间复杂度大致是 O(n√n)
	 * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d。
	 * 对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。
	 * 当增量减到1时，进行直接插入排序后，排序完成。
	 * 希尔排序间隔序列函数 h = h * 3+ 1
	 * 希尔排序比插入排序快很多的原因：当h值很大时，数据项每一趟排序移动的元素个数少，但移动的距离很长，这是非常高效的；
	 * 当h值减小时，每一趟排序移动的元素个数增加，但此时的数据项已经接近于他们最终排序后的位置，插入排序可以更有效
	 */
	//参考：http://blog.csdn.net/zhqingyun163/article/details/8961396
	public static void shellSort(int [] array){
		int len = array.length;
		int h = 1;  //初始间隔值
		//计算间隔h的最大值   注意是序列的间隔      分割后的第一子序列应该是这样一个序列，0, h, 2h, 3h, ... 
		while(h < (len/3)){  //len = 15  h = 13 
			h = h * 3 + 1;
		}
		// 能否继续通过缩小间隔h来分割数据列的判定  
		while(h>0){
			int out ; //外层循环初始变量
			int in ;  //内层循环初始变量
			// 外层通过out确定每组插入排序的第二个数据项 
			/*
			 * 插入排序的while循环是从1开始的，因为第一个数始终有序，不需要比较，这个需要了解插入排序的算法，
			 * 所以比较是从第二个数据线，就是数组的第h个下标开始 
			 */
			for(out = h;out < len;out++){
				// 以下代码就是对子序列进行的插入排序算法 
				int temp = array[out];
				in = out;
				//对于确定增量，每一组进行插入排序、
				// in > h-1 解释：0-h-1分别是不同分组的第一个值，而在插入排序中，第一个值默认是有序的
				for(;in > h-1 && array[in-h] >= temp;in = in - h){  
					array[in] = array[in-h];
				}
				/*while(in > h-3 && array[in-h] >= temp){
					array[in] = array[in-h];
					in -= h;
				}*/
				array[in] = temp;
			}
			
			//缩小间隔
			h = (h-1) / 3;			
		}
	}
	
	/*
	 * 快排算法。时间复杂度为 nlogn 。为不稳定算法 ，基于分治法。
	 */
	public static void quickSort(int [] array,int beg,int end){  //end是指数组最后一个元素的位置
		if(beg >= end || array == null)
		{
			return ;
		}
		 int pivotpos = partition(array,beg,end);   //pivotpos  为第一个基准点的位置
		 quickSort(array,beg,pivotpos-1);
		 quickSort(array,pivotpos+1,end);
	}
	
	/*
	 * 注意此方法的修饰符是 private 
	 */
	private static int partition(int [] array,int beg,int end){
		int pivotpos = beg ;   int pivot = array[beg];   //pivot为基准元素的值  区别于基准元素的位置         
		
		/*
		 * 由第一个非基准位置开始直到序列的最后进行迭代；当某一个元素的值比基准值小时，基准位置要加一，并且此时该元素的位置与基准位置不一致时，
		 * 要进行位置互换,以保证基准位置本身及基准位置以前的数据都比基准值小（此时基准值在第一个元素的位置，之后要换回到它应该在的位置
		 * 例子：21 25 49 25 16 08
		 */
		for(int i = beg+1;i <= end;i++)
		{
			if(array[i]<pivot){
				pivotpos ++ ;
				if(pivotpos != i) Swap(array[pivotpos],array[i]);
			}
		}
		/*
		 * 当迭代结束之后，可以保证所有的比基准小的 序列元素 都已经连续分布在 基准值后面  此时要把基准值和最后一个比基准值小的 元素 进行位置互换。
		 * 之后基准值就会出现在它该出现的位置。
		 */
		array[beg] = array[pivotpos] ;                   
		array[pivotpos] = pivot;
		return pivotpos;	
	}
	
	private static void Swap(int a,int b){
		int temp = a;
		a = b;
		b= temp;
	}
	
	/*
	 * 两路归并排序：时间复杂度为nlogn，为稳定的排序算法。  基于分治法。
	 * 思想：将待排序的的元素序列分成两个长度相等的子序列，为每一个子序列排序，然后再将它们合并成一个序列。
	 * 先把待归并元素序列 array 复制到辅助数组 array2 中 ，再从 array2 中归并到 array 中;
	 * array2 是新的序列，array中有两个排好序的有序序列。(从beg到mid 从mid+1到end)，此时把array中的元素复制到array2中，
	 * 使array2中有两个排好序的序列，然后从array2归并到array中
	 */
	public static void mergeSort(int [] array,int [] array2,int beg,int end){  //end是指数组最后一个元素的位置
		if(beg>=end){
			return ;
		}
		
		int mid = (beg+end)/2 ;
		mergeSort(array,array2,beg,mid);
		mergeSort(array, array2, mid+1, end);
		merge(array,array2,beg,mid,end);
	}
	
	private static void merge(int [] array,int [] array2,int beg,int mid,int end){  //end是指数组最后一个元素的位置
		for (int i = beg; i <= end; i++) {
			array2[i] = array[i];
		}
		
		int s1 = beg, s2 = mid+1;  //s1 s2 是 array2中的检测索引
		int t = beg;    // t 是 array 中的存放索引
		
		//将 array2中的两个有序序列值分别比较并按升序依次放入array中。
		while(s1<=mid && s2<=end){
			if(array2[s1] <= array2[s2])
			{
				array[t++] = array2[s1++];
			}
			else
			{
				array[t++] = array2[s2++];
			}
		}
		
		//若经过上面的操作之后，array2中的第一个序列还有未比较元素而第二个序列已经没有为比较元素，则此时将第一个序列中的未比较元素依次加入到array中。
		while(s1<=mid)
		{
			array[t++] = array2[s1++];
		}
		
		//若经过上面的操作之后，array2中的第二个序列还有未比较元素而第一个序列已经没有为比较元素，则此时将第二个序列中的未比较元素依次加入到array中。
		while(s2<=end)
		{
			array[t++] = array2[s2++];
		}
	}
	
	/*
	 * 堆是一种完全二叉树   堆排序是一种树形选择排序    堆的删除总是删除根节点，堆的插入总是插入在最后（数组最后）。
	 * 堆排序  时间复杂度  nlgn  算法稳定性：不稳定   基于分治法
	 * 堆排序也是作用与数组或向量。不同的是，堆排序的底层使用的是数组的堆表示（二叉树）形式。
	 * 如果它有左子树，那么左子树的位置是2i+1（i从0到n-1，下同），如果有右子树，右子树的位置是2i+2，如果有父节点，父节点的位置是(n-1)/2取整。    
	 * 关键点：堆的结构与数组位置的对应性  堆是一层一层在数组中存储的
	 * 分为最大堆和最小堆，最大堆的任意子树根节点    不小于   任意子结点（左右子树都是最大堆），最小堆的根节点不大于任意子结点。
	 * 思想：初始时把要排序的数的序列看作是一棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，这时堆的根节点的数最大。
	 * 然后将根节点与堆的最后一个节点交换。然后对前面(n-1)个数重新调整使之成为堆。依此类推，直到只有两个节点的堆，并对它们作交换，最后得到有n个节点的有序序列。
	 */
	public static void heapSort(int [] array){
		if (array == null || array.length <= 1) {  
            return;  
        } 
		/*构建一个大顶堆
		 * i = array.length/2-1 到  i = 0 都是有孩子的结点，
		 * 将待排序的序列构建成一个大顶堆，其实就是从下到上 从右到左，将每一个非终点结点（非叶结点）当做根结点，将其和其子树调整成大顶堆。
		 */
		for(int i = array.length/2-1;i>=0;i--){
			heapifty(array,i,array.length);
		}
		
		//正式排序 从 数组的后面往前排
		for(int i = array.length-1;i>0;i--){
			Swap(array[0], array[i]);
			heapifty(array, 0, i);
		}
	}
	/*
	 * 堆调整算法
	 * @param i 要调整的父节点  
	 * @param j 数组array的长度  用以判断 i 是否有左子树和右子树
	 */
	private static void heapifty(int [] array,int i,int j){
		 int temp = array[i];
		 int k = 0;
		 //2*i+1 < j  i结点存在左子树
		 while(2*i+1 < j){
			 k = 2*i+1;
			 //判断是否有右子树，如果有，判断左子树和右子树哪个大，并确定k的值，k的值为子树中值大的那个树的索引
			 if(k+1 < j && array[k+1] > array[k]){
				 k++;
			 }
			 //如果父节点比左右子节点都大，则无需调整，退出循环即可；否则调整
			 if(temp > array[k]){
				 break;
			 }
			 array[i] = array[k];
			 //调整之后子节点在继续调整使后面的都成为大顶堆
			 i = k;
		 }
		 array[k] = temp ;
	}
	
	/*
	 * 桶排序：  时间复杂度为 n  算法稳定性  是稳定的  
	 * 分配排序  根据排序准则将元素分配到多个桶中，在利用另外的排序算法来对每个桶中的元素进行排序。桶 i 中的所有元素都大于或者等于 桶 i-1 中的所有元素；
	 * 同时又小于或等于 桶 i+1 中的所有元素
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void bucketSort(int [] array){
		int min = miniMum(array);
		int max = maxiMum(array);
		int n = array.length;       
		int bucketNum = 10;    //桶的个数，可以适当的自己给定数值
		
		ArrayList bucket[] = new ArrayList [bucketNum];
		for(int j=0;j<bucketNum;j++){
			bucket[j] = new ArrayList();
		}
		
		for(int i=0;i<n;i++){
			int j = bucketNum * (array[i] - min) / (max + 1 - min );
			bucket[j].add(array[i]);
		}
		int i = 0;
		for(int j=0;j<bucketNum;j++){
			ArrayList bucketj = bucket[j];
			
			Integer [] arrayInt = (Integer [])bucketj.toArray(new Integer[bucketj.size()]); 
			insertSort(arrayInt, 0, arrayInt.length-1);    //调用额外的排序算法
			for(int k=0;k<arrayInt.length;k++){
				array[i++] = arrayInt[k];
			}
		}
		
	}
	
	private static void insertSort(Integer[] array, int beg, int end) {
		// TODO Auto-generated method stub
		for (int i = 1; i < array.length; i++) {
			if(array[i] <= array[i-1])
			{
				int temp = array[i];
				int j=i-1;
				for(;j>=0 && temp < array[j];j--)  //temp<array[j]即插入到合适的位置，  此时 temp 大于等于  array[j]  小于 array[j+1]
				{
					array[j+1] = array[j]; 
				}
				array[j+1] = temp; 
			}
		}
		
	}
	private static int miniMum(int [] array){
		int k = array[0];
		for(int i =1;i<array.length;i++){
			if(array[i] < k){
				k = array[i];
			}
		}
		return k;
	}
	
	private static int maxiMum(int [] array){
		int k = array[0];
		for(int i =1;i<array.length;i++){
			if(array[i] > k){
				k = array[i];
			}
		}
		return k;
	}
	
	/*
	 * 基数排序：更多地像一种排序方法的应用。基数排序必须依赖于另外的排序算法。
	 * 思路：将待排序数据拆分成多个关键字进行排序，也就是说，基数排序的实质是多关键字排序。根据子关键字对待排序数据进行排序。
	 * 方案： 最高位优先法（MSD）(Most Significant Digit first)     最低位优先法（LSD）(Least Significant Digit first)
	 * 基数排序方法对任一子关键字排序时必须借助于另一种排序方法，而且这种排序方法必须是  稳定  的。
	 * 对于多关键字拆分出来的子关键字，它们一定位于0-9这个可枚举的范围内，这个范围不大，因此用桶式排序效率非常好。
	 * 对于多关键字排序来说，程序将待排数据拆分成多个子关键字后，对子关键字排序既可以使用桶式排序，也可以使用任何一种稳定的排序方法。
	 */
	/*
	 * 
	 * @param distance 排序元素的最大位数
	 * LSD   此方法内用了计数排序
	 */
	public static void radixSortLSD(int[] array, int distance){
		int k = 0; //计数用   用于标注每一次LSD后array重组时的递增顺序
		int n = 1; //用于描述几位数  1 1位数  10 2位数
		int m= 1; //控制键值排序依据在哪一位
		int [] [] temp = new int[10][array.length]; //数组的第一维表示可能的余数0-9   每一位的排序中记录该位的值
		int [] order = new int[10];
		while(m <= distance){
			for(int i=0;i<array.length;i++){
				int lsd = ((array[i]/n)%10);
				temp[lsd][order[lsd]] = array[i];
				order[lsd]++;
			}
			for(int i =0;i<order.length;i++){
				if(order[i] != 0){
					for(int j=0;j<order[i];j++){
						array[k] = temp[i][j];
						k++;
					}
					order[i] = 0;
				}
			}
			
			k = 0;
			m ++;
			n *= 10;
		}
	}
	/*
	 * 未完成----有难度
	 * @param distance 排序元素的最大位数
	 * MSD   此方法内用了计数排序
	 */
	public static void radixSortMSD(int [] array,int distance){
		int k = 0; //计数用   用于标注每一次LSD后array重组时的递增顺序
		int n = (int) Math.pow(10, distance-1); //用于描述几位数  1 1位数  10 2位数
		int m= distance; //控制键值排序依据在哪一位
		int [] [] temp = new int[10][array.length]; //数组的第一维表示可能的余数0-9   每一位的排序中记录该位的值
		int [] order = new int[10];
		while(m > 0){
			for(int i=0;i<array.length;i++){
				int lsd = ((array[i]/n)%10);
				temp[lsd][order[lsd]] = array[i];
				order[lsd]++;
			}
		}
	}
	
	/*
	 * 计数排序：  时间复杂度 N   算法稳定性：稳定
	 */
	public static void countSort(int [] array){
		int [] resultArray = new int[array.length];
		int min = miniMum(array); 
		int max = maxiMum(array);
		int k = max - min + 1;   //计数数组的大小确定
		int [] countArray = new int[k];
		for(int i=0;i<array.length;i++){
			countArray[array[i]-min] += 1;
		}
		//计算countArray[i]中最后一个元素在resultArray的位置
		for(int i=1;i<countArray.length;i++){
			countArray[i] = countArray[i] + countArray[i-1];
		}
		
		for(int i=array.length-1;i>=0;i--){
			resultArray[--countArray[array[i] - min]] = array[i];
		}
		//return resultArray;   //有返回结果的
		for(int i=0;i<array.length;i++)
		{
			array[i] = resultArray[i];
		}
	}

}
