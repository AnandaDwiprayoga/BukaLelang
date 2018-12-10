import java.util.Scanner;
public class ProjekFixx{
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
        Scanner inS = new Scanner(System.in);
        
    	int orang = 0;
    	int dis = 0;
        int or = 0;
        int lewat = 0, tanda = 0, klik = 0;
        int maks = 0, banding = 0;
        boolean ulang = true, stop = false;
        String nama = " KOSONG", harga = " 00,-";
        String login = "";
        
        String[][] data = new String[orang][6];
        String[][] data1 = new String[or][2];
        while(ulang){
            menuPil();
            System.out.println();
            System.out.print("     Pilih Menu: ");
            int pilih = in.nextInt();
            System.out.println();

            if(pilih == 1){
                orang = 1;
            		data = new String[orang][6];  
            		data = input(data, orang, dis);
        	    if(tanda == 0) {
        	    	login = data[orang-1][0];
        	    	tanda+=1;
        	    	if(data[orang-1][3].equals("gagal")) {
        	    		data = new String[orang][6];
        	    		orang -=1;
        	    		tanda = 0;
        	    	}
        	    }
             }else if(pilih == 2){
                if(orang == 0){
                    System.out.println("   ---------------------------------------");
                    System.out.println("              BARANG MASIH KOSONG  ");
                    System.out.println("   ---------------------------------------");
                }else{
                	 System.out.println("   PILIHAN BARANG :");
                     tampilkan(data, orang);
                     if(stop) {
                    	 System.out.println("\t-----------------LELANG CLOSED--------------------");
                    	 System.out.println();
                    	 System.out.println("\t Pemenang Lelang : " + nama);
                    	 System.out.println("\t Jumlah Lelang   : " + harga);
                    	 System.out.println("     ==================================================");
            			 System.out.println("           LELANG TELAH DITUTUP OLEH PENJUAL         ");
            			 System.out.println("     ==================================================");
                     }else {
                    	 if(lewat >= 1) {
                    		 if(banding == maks) {
                    			 nama = data1[or-1][0];
                    			 harga = data1[or-1][1];
                    			 tampilAkhir(nama, harga);
                    		 }else {
                    			 tampilAkhir(nama, harga);
                    		 }
                    	 }
                    	 System.out.println();
                    	 System.out.print("     IKUT LELANG [ya/tidak]: ");
                    	 String beli = inS.nextLine();
                    	 if(beli.equalsIgnoreCase("ya")){
                    		 or+=1;
                    		 data1 = new String[or][2];
                    		 beli(data1,or);
                    		 if(lewat == 0) {
                    			 maks = Integer.parseInt(data[orang-1][5]);
                    			 banding = Integer.parseInt(data1[or-1][1]);
                    		 }else {
                    			 banding = Integer.parseInt(data1[or-1][1]);
                    		 }
                    		 if(banding >= maks) {
                    			 System.out.println("   ---------------------------------------");
                    			 System.out.println("          SELAMAT LELANG ANDA BERHASIL ");
                    			 System.out.println("   ---------------------------------------");
                    			 System.out.println();
                    			 tampilkan(data, orang);
                    			 tampilkan1(data1, or);
                    			 maks = banding;
                    			 klik =1;
                    		 }else {
                    			 System.out.println("   ---------------------------------------");
                    			 System.out.println("         LELANG ANDA TERLALU KECIL  ");
                    			 System.out.println("   ---------------------------------------");
                    			 klik = 0;
                    		 }
                    		 lewat++;
                    	 }else if(beli.equalsIgnoreCase("tidak")){
                    		 System.out.println("TERIMAKASIH TELAH MENGGUNAKAN JASA KAMI :)");
                    	 }else{
                    		 System.out.println("MAAF KEYWORD YANG ANDA MASUKKAN SALAH");
                    	 }
                     }
                }
             }else if(pilih == 3) {
            	 String sign = "";
            	 if(tanda >=1) {
                 	sign = masuk();
                 	if(sign.equals(login)) {
                 		System.out.println();
                 		System.out.println("   STATUS BARANG ANDA :");
                 		tampilkan(data, orang);
                 		if(klik == 1) {
                 			nama = data1[or-1][0];
                 			harga = data1[or-1][1];
                 		}
                 		tampilAkhir(nama, harga);
                 		stop = tutup();
                 	}else if(sign.equals("gagal")) {
                 		gagal();
                 	}else {
                 		terlebihdulu();
                 	}
                 }else {
                	 sign = masuk();
                	 if(sign.equals("gagal")) {
                  		gagal();
                	 }else {
                		 terlebihdulu();
                	 }
                 }
        	 }else{
                System.out.println("MAAF KEYWORD YANG ANDA MASUKKAN SALAH");
             }

        System.out.print("Ingin Transaksi Lagi ?[ya/tidak]: ");
        String lagi = inS.nextLine();
        if(lagi.equalsIgnoreCase("ya")){
            ulang = true;
        }else if(lagi.equalsIgnoreCase("tidak")){
            ulang = false;
        }else{
            System.out.println("MAAF KEYWORD YANG ANDA MASUKKAN SALAH");
            ulang = false;
            }
        }
    }
    public static String[][] input(String[][] data, int orang, int dis){
    	Scanner in = new Scanner(System.in);
        if(dis < 6){
        	jenis(dis);
        	data[orang-1][dis] = in.nextLine();
            if(dis == 0){
                return input(data, orang, ++dis);
            }else if(data[orang-1][0].equals(data[orang-1][1])){
                if(dis == 1){
                   berhasil(); 
                   return input(data, orang, ++dis);
                }else if(dis == 5){
                    selamat();
                }else{
                   return input(data, orang, ++dis);  
                }
            }else{
                gagal();
                data[orang-1][3] = "gagal";
                return data;
            }
        	
        }
        return data;
    }
    public static void jenis(int indeks){
    	if(indeks == 0){
    		System.out.print("\t  Masukkan Username Anda: ");
    	}else if(indeks == 1){
    		System.out.print("\t  Masukkan Password anda: ");
    	}else if(indeks == 2){
            System.out.println();
    		System.out.print("\t1.Nama Barang                     : ");
    	}else if(indeks == 3){
    		System.out.print("\t2.Jenis Barang[ex. hp/laptop/dll] : ");
    	}else if(indeks == 4){
            System.out.print("\t3.Kondisi Barang[baru/bekas]      : ");
        }else if(indeks == 5){
            System.out.print("\t4.Buka Harga                      : Rp.");
        }
    }
    public static void menuPil(){
        System.out.println("   ||====================================||");
        System.out.println("   ||-------->>SELAMAT DATANG<<<---------||");
        System.out.println("   ||          ------DI-------           ||");
        System.out.println("   ||*********|| BUKA LELANG ||**********||");
        System.out.println("   ||2018      ----------------  anandaDP||");
        System.out.println("   ||====================================||");
        System.out.println();
        System.out.println();
        System.out.println("   ========================================");
        System.out.println("                 MENU PILIHAN             ");
        System.out.println("   ========================================");
        System.out.println("\t1. Jual Barang");
        System.out.println("\t2. Lelang Barang");
        System.out.println("\t3. Status Lelang Anda");
    }
    public static void tampilkan(String[][] data, int orang){

        for(int i = 0; i < orang; i++){
            for(int j = 2; j < 6; j++){
                    jenis(j);
                   System.out.println(data[i][j]);            
            }
            System.out.println();
        }
    }
    public static void berhasil(){
         System.out.println("   ----------------------------------");
         System.out.println("      SELAMAT LOGIN ANDA BERHASIL    ");
         System.out.println("   ----------------------------------");
         System.out.println("\t\tMASUKKAN DESKRIPSI BARANG ANDA !! ");
    }
    public static void gagal(){
        System.out.println("   ---------------------------------------");
        System.out.println("      USERNAME ATAU PASSWORD ANDA SALAH   ");
        System.out.println("   ---------------------------------------");
    }
    public static void selamat(){
        System.out.println("   ---------------------------------------");
        System.out.println("           BARANG BERHASIL TERINPUT       ");
        System.out.println("   ---------------------------------------");
    }
    public static void beli(String[][] array, int indeks){
    Scanner in = new Scanner(System.in);

    System.out.println();
    System.out.print("\tMasukkan nama anda  :  ");
    array[indeks-1][0] = in.nextLine();
    System.out.print("\tMau lelang berapa ? : Rp.");
    array[indeks-1][1] = in.nextLine();

    }
  
    public static void tampilAkhir(String nama, String harga) {
        System.out.println("   ------------------------------------------------------");
        System.out.println("\t\t   TOP LELANG: ");
        System.out.println("\t\tNama          :" + nama);
        System.out.println("\t\tJumlah Lelang : Rp." +harga);
        System.out.println("   ------------------------------------------------------");
    	
    }
    public static void tampilkan1(String[][] data1, int or) {
        System.out.println("   ------------------------------------------------------");
        System.out.println("\t\t   TOP LELANG: ");
        System.out.println("\t\tNama          :" + data1[or-1][0]);
        System.out.println("\t\tJumlah Lelang : Rp." +data1[or-1][1]);
        System.out.println("   ------------------------------------------------------");
	
    }
    public static String masuk() {
    	Scanner in = new Scanner(System.in);
    	
    	System.out.print("\t  Masukkan Username Anda: ");
    	String nama = in.nextLine();
    	System.out.print("\t  Masukkan Password anda: ");
    	String password = in.nextLine();
    	
    	if(nama.equals(password)) {
    		return nama;
    	}else {
    		return "gagal";
    	}
    	
    }
    public static void terlebihdulu() {
    	 System.out.println("   ---------------------------------------");
         System.out.println("        JUAL BARANG TERLEBIH DAHULU  ");
         System.out.println("   ---------------------------------------");
    }
    public static boolean tutup() {
    	Scanner in = new Scanner(System.in);
    	
    	System.out.print("\t TUTUP PROSES LELANG ?[ya/tidak]: ");
 		String tutup = in.nextLine();
 		
 		if(tutup.equalsIgnoreCase("ya")) {
 			return true;
 		}else if(tutup.equalsIgnoreCase("tidak")) {
 			return false;
 		}else {
 			System.out.println("MAAF KEYWORD YANG ANDA MASUKKAN SALAH");
 		}
 		return false;
    }
    
}

