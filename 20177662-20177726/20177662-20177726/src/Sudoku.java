
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class Sudoku {
	
	public static int N;  //n*n矩阵,从0开始计数
	public static int M;  //1-m矩阵 m<=9
	public static int[][] arr = new int[9][9];
	public static String path = System.getProperty("user.dir");
	public static FileOutputStream out = null;
	
	public static void main(String[] args) {
		//Sudoku.exe -m 9 -n 2 -i input.txt -o output.txt
		N = Integer.parseInt(args[1])-1;		
		M = N + 1;
		if(N + 1<3||N + 1>9) {
			System.out.println("参数错误");
			System.exit(0);
		}
		//初始化N。M，arr,传入文件
		FileInputStream file = null;
		try {
		    file = new FileInputStream(path+File.separator+args[5]);
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件，文件参数错误！");
		}
		try {
			out = new FileOutputStream(path+File.separator+args[7]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(file);
		int k = Integer.parseInt(args[3]);
		for (int w = 0; w < k; w++) {
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= N; j++) {
					arr[i][j]  = sc.nextInt();
				}
			}
			dfs(0);
		}
		if(file!=null) {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(out!=null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sc!=null) {
			sc.close();
		}
		
	}
	
	public static void dfs(int cut) {
		int n = N + 1 ;
		if(cut==Math.pow(n, 2)) {
			print_arr();
			return;
		}
		//通过cut求出行与列（i,j）
		int i = cut / n;
		int j = cut % n;
		
		//已知条件以读入的数据
		if(arr[i][j] != 0) {
			dfs(cut + 1);
			return;
		}
		
		//arr填入1-M试探`
		for(int t = 1;t <= M; t++) {
			if(isColAndRow(i,j,t)&&isGrid(i,j,t)) {
				arr[i][j] = t;
				dfs(cut + 1);
				//回溯
				arr[i][j] = 0;
			}
		}
	}

	/**
	 * 打印出结果	
	 */
	public static void print_arr() {
		for(int i = 0 ;i <= N;i++ ) {
			for(int j = 0;j <= N;j++) {
				//System.out.print(arr[i][j]+"  ");
				try {
					out.write((arr[i][j]+" ").getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//System.out.println();
			try {
				out.write("\n".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
		//System.out.println();
		try {
			out.write("\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	
	/**
	 * i行j列填入k是否满足条件（仅判断行与列）
	 * @param i 测试行
	 * @param j 测试列
	 * @param k 当前试探值
	 * @return 是否满足条件
	 */
	public static boolean isColAndRow(int i,int j,int k) {
		//列不变
		for(int p = 0;p <= N ;p++) {
			if(p!=i&&arr[p][j]==k) {
				return false;
			}
		}
		//行
        for(int p = 0;p <= N ;p++) {
			if(j!=p&&arr[i][p]==k) {
				return false;
			}
		}
    return true;
	}
	/**
	 * 判断在当前块是否满足条件
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public static boolean isGrid(int i,int j,int k) {
		int n = N +1;
		if(105%n==0) {
			return true;
		}
		//方块的c行与r列
		int c = 0,r = 0;
		if(n%3==0) {
			 c = 3;
			 r = n/3;
		}else if(n%2==0) {
			 c = 2;
			 r = n/2;
		}
		
		//算出具体的格子大小
		c = n / c;
		r = n / r;
		//通过i,j知道在那一个方格中
		int in_c = (i/c)*c;
		int in_r = (j/r)*r;
		for(int p =in_c;p < in_c + c;p++) {
			for(int q = in_r;q < in_r + r;q++) {
				if(arr[p][q]==k) {
					if(p==i&&q==j) {
						continue;
					}
					return false;
				}
			}
			
		}
		return true;
	}
	
}
