public class Lab1{
	public static void main(String[] args) {
		String[] limbaje = {"C#", "C++", "C", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		int n = (int) (Math.random() * 1_000_000);
		n=n*3;
		n= n + 0b10101;
		n= n + 0xFF;
		n= n * 6;
		System.out.println(n);
		int suma = 0;
		int sumafinala = 0;
		while(n!=0)
		{
			suma = suma + n%10;
			n = n/10;
		}
		while(suma>9)
		{
			int cif, cifr;
			cif = suma % 10;
			suma = suma/10;
			cifr = suma % 10;
			sumafinala = cif + cifr;
		}
		System.out.println(sumafinala);
		System.out.println( "Willy-nilly, this semester I will learn " + limbaje[sumafinala]);
	}
}