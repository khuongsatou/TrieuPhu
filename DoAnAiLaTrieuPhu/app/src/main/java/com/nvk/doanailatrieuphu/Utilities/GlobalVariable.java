package com.nvk.doanailatrieuphu.Utilities;

public class GlobalVariable {
    //-------------Hằng Support Order-------------

    //param
    public static final String KEY_ID = "id";
    public static final String KEY_CREDIT = "credit";
    public static final String KEY_PAGE = "page";
    public static final String KEY_LIMIT = "limit";

    //key check đăng nhập để intent
    public static final String KEY_DANGNHAP = "dn" ;
    public static final String KEY_LINHVUC = "lv";
    public static final int REQUEST_CODE_GALLERY = 123;

    //Key dùng cho việc loadmore adapter
    public static final int TYPE_LOADING = 0;
    public static final int TYPE_ITEM =1 ;
    //public static final int PAGE_SIZE = 25;

    //Page Size < Limit khởi tạo , không nó sẽ không vào onScrolled
    public static final int PAGE_SIZE = 2;
    public static final int PAGE_KHOI_TAO = 1;
    //public static final int LIMIT_KHOI_TAO = 25;
    public static final int LIMIT_KHOI_TAO = 3;


    //key dùng thời gian cho 1 câu hỏi
    public static final int TOTAL_TIME_TIMER = 10000;
    public static final int TIME_CHUYEN_CAU_HOI = 2000;
    public static final int COUNT_TIME = 1000;
    public static final String KEY_CH_POSITION = "position";

    //key dùng credit cho 1 câu hỏi
    public static final int GIA_DAP_AN = 100;
    public static final int GIA_LUOT_CHOI = 100;
    public static final int GIA_DIEM = 50;

}
