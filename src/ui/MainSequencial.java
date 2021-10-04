package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainSequencial {
	
	private BufferedReader buffer;
	private int[] bp;
	
	public MainSequencial() {
		buffer = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String [] juank) throws IOException {
		
		MainSequencial ppal = new MainSequencial();
		
		//System.out.println("Por favor ingrese el numero de libros a registrar:");
		int N = Integer.parseInt(ppal.buffer.readLine());
		
		int i = 0;
		while(i < N) {
			//System.out.println("Porfavor ingrese los precios correspondientes a los libros, separandolos por un espacio:");
			String []n = ppal.buffer.readLine().split(" ");

			ppal.bp = new int[N];

			if(n.length == N) {
				ppal.bp = new int[N];
				boolean sentinel = false;
				for (int j = 0; j < n.length && !sentinel; j++) {
					if(Integer.parseInt(n[j]) < 1000001) {
						ppal.bp[j] = Integer.parseInt(n[j]);
					} else {
						sentinel = true;
					}
				}

				if(sentinel == false) {
					i = N;
				}

			} else {
				System.out.println("El numero de precios ingresados supera al limite de libros aceptados");
			}
		}
		
		//System.out.println("Ingrese el valor correspondiente al monto de dinero que posee:");
		int money = Integer.parseInt(ppal.buffer.readLine());
		ppal.buffer.close();
		
		String toPrint = ppal.searchPairsBook(money);
		
		System.out.println(toPrint);
	}
	
	public String searchPairsBook(int m) {
		
		int numb1 = 0;
		int numb2 = 0;
		int dbn = -1;
		
		for (int i = 0; i < bp.length; i++) {
			if((m-bp[i]) != 0) {
				System.out.println(m + " - " + bp[i]);
				if(sequencialSearch((m-bp[i]), m) != -1) {
					if(dbn < 0) {
						numb1 = bp[i];
						numb2 = bp[sequencialSearch((m-bp[i]), m)];
						dbn = calculateDifference(numb1, numb2);
					} else {
						int temp1 = bp[i];
						int temp2 = bp[sequencialSearch((m-bp[i]), m)];
						if(dbn > calculateDifference(temp1, temp2)) {
							dbn = calculateDifference(temp1, temp2);
							numb1 = temp1;
							numb2 = temp2;
						}
					}
				}
			}
		}
		
		
		
		if(numb1 != 0 && numb2 != 0) {
			return "Peter should buy books whose prices are " + numb1 + " and "+ numb2 + ".\n\n";
		} else {
			return "Peter cannot buy two books, because it is not possible to make the sum of the prices equal to Peter's money.";
		}
	}
	
	public int calculateDifference(int n1, int n2) {
		if(n1 > n2) {
			return n1-n2;
		} else if(n1 < n2) {
			return n2-n1;
		} else {
			return 0;
		}
	}
	
	public int sequencialSearch(int rm, int money) {
		Arrays.sort(bp);
		int pos = -1;
		
		
		for(int i = 0; i < bp.length; i++) {
			System.out.println(bp[i] + " + " + rm + " = " + money);
			if((rm + bp[i]) == money) {
				pos = i;
				System.out.println("pos: " + pos);
			}
		}
		
		System.out.println("======================================");
		System.out.println(pos);
		return pos;
	}
	
}

