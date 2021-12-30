import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTable {
	Piece[][] table = new Piece[8][8]; //Satranc tahtasinin saklandigi 2 boyutlu tas dizisi
	
	// Dosyadan satranc tahtasini okumayi saglayan fonksiyon.
	public Piece[][] readTable(String path) throws IOException {
		//Piece[][] table = new Piece[8][8];
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				table[i][j]=new Piece();
		
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		
		int i=0;
		while((line = br.readLine()) != null)
		{
			String[] split = line.split(" ");
			int j=0;
			for(String str: split)
			{
				if(str.charAt(0)=='p') { 
					table[i][j].setName("piyon"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(1); 
				}
				if(str.charAt(0)=='a') { 
					table[i][j].setName("at"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(3); 
				}
				if(str.charAt(0)=='f') { 
					table[i][j].setName("fil"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(3); 
				}
				if(str.charAt(0)=='k') { 
					table[i][j].setName("kale"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(5); 
				}
				if(str.charAt(0)=='v') { 
					table[i][j].setName("vezir"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(9); 
				}
				if(str.charAt(0)=='s') { 
					table[i][j].setName("sah"); 
					table[i][j].setColor(Character.toString(str.charAt(1))); 
					table[i][j].setRank(100); 
				}
				
				j++;
			}
			i++;
		}		
		br.close();
		return table;
	}

	
	// Gonderilen konumdaki tasin tehdit altinda olup olmadigini kontrol eden genel fonksiyon
	public boolean isUnderThreat(int n, int m) {
		
		if(isUnderQueenThreat(n,m)) return true;  // Vezir tehdidi olup olmadigini kontrol eder
		if(isUnderKnightThreat(n,m)) return true; // At tehdidi olup olmadigini kontrol eder
		if(isUnderPawnThreat(n,m)) return true;   // Piyon tehdidi olup olmadigini kontrol eder
		
		return false;
	}


	// Piyon Tehdidini kontrol eden fonksiyon
	private boolean isUnderPawnThreat(int n, int m) {
		
		String threatColor,friendColor;
		if(table[n][m].getColor().equals("s")) { threatColor="b"; friendColor="s"; } // kontrol edilen tasin rakip ve takim rengi belirleniyor
		else { threatColor="s"; friendColor="b"; } // kontrol edilen tasin rakip ve takim rengi belirleniyor
		
		if(friendColor.equals("s"))
		{
			if(n+1<8 && m+1<8) // Satranc tahtasi siniri
				if(table[n+1][m+1].getName().equals("piyon") && table[n+1][m+1].getColor().equals("b")) //sag alt caprazda beyaz piyon tedidi var mi
					return true;

			if(n+1<8 && m-1>=0) // Satranc tahtasi siniri
				if(table[n+1][m-1].getName().equals("piyon") && table[n+1][m-1].getColor().equals("b")) //sol alt caprazda beyaz piyon tedidi var mi
					return true;
		}
		if(friendColor.equals("b"))
		{
			if(n-1>=0 && m+1<8) // Satranc tahtasi siniri
				if(table[n-1][m+1].getName().equals("piyon") && table[n-1][m+1].getColor().equals("s")) //sag ust caprazda siyah piyon tedidi var mi
					return true;

			if(n-1>=0 && m-1>=0) // Satranc tahtasi siniri
				if(table[n-1][m-1].getName().equals("piyon") && table[n-1][m-1].getColor().equals("s")) //sol ust caprazda siyah piyon tedidi var mi
					return true;
		}
		
		return false;
	}

	// At tehdidini kontrol eden fonksiyon
	private boolean isUnderKnightThreat(int n, int m) {
		String threatColor,friendColor;
		if(table[n][m].getColor().equals("s")) { threatColor="b"; friendColor="s"; } // kontrol edilen tasin rakip ve takim rengi belirleniyor
		else { threatColor="s"; friendColor="b"; } // kontrol edilen tasin rakip ve takim rengi belirleniyor
		
		int i,j;
		
		i=n+2; j=m+1;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n+1; j=m+2;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n-1; j=m+2;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n-2; j=m+1;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n-2; j=m-1;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n-1; j=m-2;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n+1; j=m-2;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		i=n+2; j=m-1;
		if(i<8 && i>=0 && j<8 && j>=0) // Satranc tahtasi SINIR kontrolu
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("at")) //rakip at tehdidi kontrolu
				return true;
		
		return false;
	}


	// Vezir tehdidini kontrol eden fonksiyon
	private boolean isUnderQueenThreat(int n, int m) {
		
		boolean threat=false;
		String threatColor,friendColor;
		if(table[n][m].getColor().equals("s")) { threatColor="b"; friendColor="s"; }
		else { threatColor="s"; friendColor="b"; }
		
		for(int i=n+1; i<8; i++)
		{
			if(table[i][m].getColor().equals(friendColor) || (table[i][m].getColor().equals(threatColor) && !table[i][m].getName().equals("vezir"))) break;
			if(table[i][m].getColor().equals(threatColor) && table[i][m].getName().equals("vezir")) return true;
		}
		for(int i=m+1; i<8; i++)
		{
			if(table[n][i].getColor().equals(friendColor) || (table[n][i].getColor().equals(threatColor) && !table[n][i].getName().equals("vezir"))) break;
			if(table[n][i].getColor().equals(threatColor) && table[n][i].getName().equals("vezir")) return true;
		}
		for(int i=n-1; i>=0; i--)
		{
			if(table[i][m].getColor().equals(friendColor) || (table[i][m].getColor().equals(threatColor) && !table[i][m].getName().equals("vezir"))) break;
			if(table[i][m].getColor().equals(threatColor) && table[i][m].getName().equals("vezir")) return true;
		}
		for(int i=m-1; i>=0; i--)
		{
			if(table[n][i].getColor().equals(friendColor) || (table[n][i].getColor().equals(threatColor) && !table[n][i].getName().equals("vezir"))) break;
			if(table[n][i].getColor().equals(threatColor) && table[n][i].getName().equals("vezir")) return true;
		}
		for(int i=n+1,j=m+1; i<8 && j<8; i++,j++)
		{
			if(table[i][j].getColor().equals(friendColor) || (table[i][j].getColor().equals(threatColor) && !table[i][j].getName().equals("vezir"))) break;
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("vezir")) return true;
		}
		for(int i=n+1,j=m-1; i<8 && j>=0; i++,j--)
		{
			if(table[i][j].getColor().equals(friendColor) || (table[i][j].getColor().equals(threatColor) && !table[i][j].getName().equals("vezir"))) break;
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("vezir")) return true;
		}
		for(int i=n-1,j=m+1; i>=0 && j<8; i--,j++)
		{
			if(table[i][j].getColor().equals(friendColor) || (table[i][j].getColor().equals(threatColor) && !table[i][j].getName().equals("vezir"))) break;
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("vezir")) return true;
		}
		for(int i=n-1,j=m-1; i>=0 && j>=0; i--,j--)
		{
			if(table[i][j].getColor().equals(friendColor) || (table[i][j].getColor().equals(threatColor) && !table[i][j].getName().equals("vezir"))) break;
			if(table[i][j].getColor().equals(threatColor) && table[i][j].getName().equals("vezir")) return true;
		}
		
		return false;
	}

}
