
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class Sudoku {
	
	public static int N;  //n*n����,��0��ʼ����
	public static int M;  //1-m���� m<=9
	public static int[][] arr = new int[9][9];
	public static String path = System.getProperty("user.dir");
	public static FileOutputStream out = null;
	
	public static void main(String[] args) {
		//Sudoku.exe -m 9 -n 2 -i input.txt -o output.txt
		N = Integer.parseInt(args[1])-1;		
		M = N + 1;
		if(N + 1<3||N + 1>9) {
			System.out.println("��������");
			System.exit(0);
		}
		//��ʼ��N��M��arr,�����ļ�
		FileInputStream file = null;
		try {
		    file = new FileInputStream(path+File.separator+args[5]);
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ����ļ����ļ���������");
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
		//ͨ��cut��������У�i,j��
		int i = cut / n;
		int j = cut % n;
		
		//��֪�����Զ��������
		if(arr[i][j] != 0) {
			dfs(cut + 1);
			return;
		}
		
		//arr����1-M��̽`
		for(int t = 1;t <= M; t++) {
			if(isColAndRow(i,j,t)&&isGrid(i,j,t)) {
				arr[i][j] = t;
				dfs(cut + 1);
				//����
				arr[i][j] = 0;
			}
		}
	}

	/**
	 * ��ӡ�����	
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
	 * i��j������k�Ƿ��������������ж������У�
	 * @param i ������
	 * @param j ������
	 * @param k ��ǰ��ֵ̽
	 * @return �Ƿ���������
	 */
	public static boolean isColAndRow(int i,int j,int k) {
		//�в���
		for(int p = 0;p <= N ;p++) {
			if(p!=i&&arr[p][j]==k) {
				return false;
			}
		}
		//��
        for(int p = 0;p <= N ;p++) {
			if(j!=p&&arr[i][p]==k) {
				return false;
			}
		}
    return true;
	}
	/**
	 * �ж��ڵ�ǰ���Ƿ���������
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
		//�����c����r��
		int c = 0,r = 0;
		if(n%3==0) {
			 c = 3;
			 r = n/3;
		}else if(n%2==0) {
			 c = 2;
			 r = n/2;
		}
		
		//�������ĸ��Ӵ�С
		c = n / c;
		r = n / r;
		//ͨ��i,j֪������һ��������
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
