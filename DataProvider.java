package com.example.epsflashcardpro;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<FlashcardItem> getAllItems() {
        List<FlashcardItem> items = new ArrayList<>();

        add(items,1,"사람","orang");
        add(items,1,"이름","nama");
        add(items,1,"국적","kewarganegaraan");
        add(items,1,"누구","siapa");
        add(items,1,"어느","yang mana");
        add(items,1,"한국","Korea");
        add(items,1,"대한민국","Korea Selatan");
        add(items,1,"네팔","Nepal");
        add(items,1,"동티모르","Timor Leste");
        add(items,1,"라오스","Laos");
        add(items,1,"몽골","Mongolia");
        add(items,1,"미얀마","Myanmar");
        add(items,1,"방글라데시","Bangladesh");
        add(items,1,"베트남","Vietnam");
        add(items,1,"인도네시아","Indonesia");
        add(items,1,"중국","China");
        add(items,1,"태국","Thailand");
        add(items,1,"필리핀","Filipina");
        add(items,1,"학생","siswa");
        add(items,1,"선생님","guru");
        add(items,1,"회사원","pegawai kantoran");
        add(items,1,"근로자","pekerja");
        add(items,1,"경찰관","polisi");
        add(items,1,"소방관","pemadam kebakaran");
        add(items,1,"공무원","PNS");
        add(items,1,"점원","pelayan toko");
        add(items,1,"의사","dokter");
        add(items,1,"간호사","perawat");
        add(items,1,"요리사","koki");
        add(items,1,"운전기사","sopir");
        add(items,1,"기술자","teknisi");
        add(items,1,"목수","tukang kayu");
        add(items,1,"농부","petani");
        add(items,1,"어부","nelayan");

        add(items,2,"이것","ini");
        add(items,2,"그것","itu");
        add(items,2,"저것","itu (jauh)");
        add(items,2,"뭐","apa");
        add(items,2,"무엇","apa");
        add(items,2,"연필","pensil");
        add(items,2,"볼펜","pulpen");
        add(items,2,"가위","gunting");
        add(items,2,"칼","pisau");
        add(items,2,"시계","jam");
        add(items,2,"달력","kalender");
        add(items,2,"컴퓨터","komputer");
        add(items,2,"안경","kacamata");
        add(items,2,"휴대폰","telepon genggam");
        add(items,2,"휴대전화","telepon genggam");
        add(items,2,"계산기","kalkulator");
        add(items,2,"컵","gelas/cangkir");
        add(items,2,"휴지","tisu");
        add(items,2,"가족사진","foto keluarga");
        add(items,2,"여권","paspor");
        add(items,2,"지갑","dompet");
        add(items,2,"열쇠","kunci");
        add(items,2,"가방","tas");
        add(items,2,"우산","payung");
        add(items,2,"화장품","kosmetik");
        add(items,2,"거울","cermin");
        add(items,2,"빗","sisir");
        add(items,2,"베개","bantal");
        add(items,2,"이불","selimut");

        add(items,3,"에어컨","AC");
        add(items,3,"침대","tempat tidur");
        add(items,3,"의자","kursi");
        add(items,3,"책상","meja belajar");
        add(items,3,"옷장","lemari pakaian");
        add(items,3,"소파","sofa");
        add(items,3,"텔레비전","TV");
        add(items,3,"전자레인지","microwave");
        add(items,3,"세탁기","mesin cuci");
        add(items,3,"냉장고","kulkas");
        add(items,3,"청소기","vacuum cleaner");
        add(items,3,"식탁","meja makan");
        add(items,3,"회사","perusahaan");
        add(items,3,"식당","restoran");
        add(items,3,"은행","bank");
        add(items,3,"커피숍","kedai kopi");
        add(items,3,"편의점","minimarket");
        add(items,3,"시장","pasar");
        add(items,3,"미용실","salon");
        add(items,3,"슈퍼마켓","supermarket");
        add(items,3,"약국","apotek");
        add(items,3,"병원","rumah sakit");
        add(items,3,"우체국","kantor pos");
        add(items,3,"집","rumah");
        add(items,3,"방","kamar");
        add(items,3,"화장실","toilet");
        add(items,3,"거실","ruang tamu");
        add(items,3,"부엌","dapur");
        add(items,3,"기숙사","asrama");
        add(items,3,"여기","di sini");
        add(items,3,"거기","di sana");
        add(items,3,"저기","di situ");
        add(items,3,"어디","di mana");

        add(items,4,"일하다","bekerja");
        add(items,4,"공부하다","belajar");
        add(items,4,"운동하다","berolahraga");
        add(items,4,"전화하다","menelepon");
        add(items,4,"요리하다","memasak");
        add(items,4,"쇼핑하다","berbelanja");
        add(items,4,"쉬다","istirahat");
        add(items,4,"자다","tidur");
        add(items,4,"가다","pergi");
        add(items,4,"오다","datang");
        add(items,4,"하다","melakukan");
        add(items,4,"밥","nasi");
        add(items,4,"빵","roti");
        add(items,4,"사과","apel");
        add(items,4,"과자","snack");
        add(items,4,"먹다","makan");
        add(items,4,"마시다","minum");
        add(items,4,"물","air");
        add(items,4,"주스","jus");
        add(items,4,"우유","susu");
        add(items,4,"커피","kopi");
        add(items,4,"보다","menonton / melihat");
        add(items,4,"텔레비전","televisi");
        add(items,4,"영화","film");
        add(items,4,"읽다","membaca");
        add(items,4,"책","buku");
        add(items,4,"신문","koran");
        add(items,4,"지금","sekarang");
        add(items,4,"오늘","hari ini");

        return items;
    }

    public static List<FlashcardItem> getByChapter(int chapter) {
        List<FlashcardItem> filtered = new ArrayList<>();
        for (FlashcardItem item : getAllItems()) {
            if (item.chapter == chapter) filtered.add(item);
        }
        return filtered;
    }

    private static void add(List<FlashcardItem> items, int chapter, String korean, String indonesian) {
        items.add(new FlashcardItem(chapter, korean, indonesian));
    }
}
