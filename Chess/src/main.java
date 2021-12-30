import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class main {
	static double black=0; //siyah taslar icin puan degiskeni
	static double white=0; //beyaz taslar icin puan degiskeni
	static Piece[][] table = new Piece[8][8]; // Dosyadan okunan tahtanin 2 boyutlu tas nesnesi(Piece) dizisi olarak tutulan nesne
	
	public static void main(String[] args) throws IOException {
		
		//Mevcut konum icin File objesi olusturulur ve satranc tahtalarini filtrelemek icin sonu ".txt" ile biten dosyalar secilir
	      File directoryPath = new File(".");
	      FilenameFilter textFilefilter = new FilenameFilter(){
	         public boolean accept(File dir, String name) {
	            String lowercaseName = name.toLowerCase();
	            if (lowercaseName.endsWith(".txt")) {
	               return true;
	            } else {
	               return false;
	            }
	         }
	      }; 
	      System.out.println("Tahta Dosya Adi\t\t" + "Sonuclar");
	      
	      //Mevcut konumdaki satranc tahtalarinin dosya isimleri, puan hesaplamasi icin calculateRank fonksiyonuna gonderilir
	      String filesList[] = directoryPath.list(textFilefilter);
	      for(String fileName : filesList) {
	         calculateRank(fileName);
	      }

	}

	//Veerilen dosya ismindeki tahtanin puan hesaplamasini saglayan fonksiyon
	private static void calculateRank(String fileName) throws IOException {
		
		ReadTable r = new ReadTable();// readTable class'i sayesinde dosyadan tahta okunur ve ayrica vezir, at, piyon tehditleri kontrolunu de saglayan metod saglar
		table = r.readTable(fileName); //verilen dosya ismindeki satranc tahtasi okunup table ismindeki 2 boyutlu tas nesnesi dizisinde saklanmaktadir
		for(int i=0; i<8; i++) //tahta uzerinde tum karelerde puan hesaplamasi icin gezilir
		{
			for(int j=0; j<8; j++)
			{
				if(table[i][j].getRank()!=0) // mevcut i,j konumunda tas olup olmadigi kontrol edilir 
				{
					if(r.isUnderThreat(i,j)){ // Tas herhangi bir tehdit altinda olup olmadigini kontrol eder
						if(table[i][j].getColor().equals("s")) //Tas tehdit altindaysa ve rengi siyah ise siyah icin o tasin yari puani verilir
							black+=table[i][j].getRank()/2;
						else
							white+=table[i][j].getRank()/2; //Tas tehdit altindaysa ve rengi beyaz ise beyaz icin o tasin yari puani verilir
					}
					else
					{
						if(table[i][j].getColor().equals("s")) // tas tehdit altinda degil ise o tasin tam puani verilir
							black+=table[i][j].getRank();
						else
							white+=table[i][j].getRank();
					}
				}
			}
		}
		
		System.out.println(fileName + "\t" + "Siyah:" + black + " " + "Beyaz:" + white); //tahta ismi ve puanlar console'a yazdirilir
		black=white=0;// yeni tahta hesaplamasi icin puanlar sifirlanir.
		
	}

}
