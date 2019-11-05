<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//Home
Route::prefix('/')->group(function(){
    Route::name('.')->group(function(){
        Route::get('/','HomeController@index')->name('index');
        Route::get('/login','HomeController@login')->name('login');
        
    });
});


//Bảng
Route::prefix('linh_vuc')->group(function(){
    Route::name('linh_vuc.')->group(function(){
        Route::get('/','LinhVucController@index')->name('danh_sach');
        Route::get('/chi_tiet/{id}','LinhVucController@show')->name('chi_tiet');
        Route::get('/them','LinhVucController@create')->name('them');
        Route::post('/them','LinhVucController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','LinhVucController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','LinhVucController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','LinhVucController@destroy')->name('xoa');
    });
});

Route::prefix('nguoi_choi')->group(function(){
    Route::name('nguoi_choi.')->group(function(){
        Route::get('/','NguoiChoiController@index')->name('danh_sach');
        Route::get('/chi_tiet/{name}/{id}','NguoiChoiController@show')->name('chi_tiet');
        Route::get('/them','NguoiChoiController@create')->name('them');
        Route::post('/them','NguoiChoiController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','NguoiChoiController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','NguoiChoiController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','NguoiChoiController@destroy')->name('xoa');
        
    });
});

Route::prefix('luot_choi')->group(function(){
    Route::name('luot_choi.')->group(function(){
        Route::get('/','LuotChoiController@index')->name('danh_sach');
        Route::get('/chi_tiet/{id}','LuotChoiController@show')->name('chi_tiet');
        Route::get('/them','LuotChoiController@create')->name('them');
        Route::post('/them','LuotChoiController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','LuotChoiController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','LuotChoiController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','LuotChoiController@destroy')->name('xoa');
        
    });
});


Route::prefix('chi_tiet_luot_choi')->group(function(){
    Route::name('chi_tiet_luot_choi.')->group(function(){
        Route::get('/','ChiTietLuotChoiController@index')->name('danh_sach');
        Route::get('/chi_tiet/{id}','ChiTietLuotChoiController@show')->name('chi_tiet');
        Route::get('/them','ChiTietLuotChoiController@create')->name('them');
        Route::post('/them','ChiTietLuotChoiController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','ChiTietLuotChoiController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','ChiTietLuotChoiController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','ChiTietLuotChoiController@destroy')->name('xoa');
        
    });
});

Route::prefix('lich_su_mua_credit')->group(function(){
    Route::name('lich_su_mua_credit.')->group(function(){
        Route::get('/','LichSuMuaCreditController@index')->name('danh_sach');
        Route::get('/them','LichSuMuaCreditController@create')->name('them');
        Route::post('/them','LichSuMuaCreditController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','LichSuMuaCreditController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','LichSuMuaCreditController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','LichSuMuaCreditController@destroy')->name('xoa');
        
    });
});


Route::prefix('cau_hoi')->group(function(){
    Route::name('cau_hoi.')->group(function(){
        Route::get('/','CauHoiController@index')->name('danh_sach');
        Route::get('/them','CauHoiController@create')->name('them');
        Route::post('/them','CauHoiController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','CauHoiController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','CauHoiController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','CauHoiController@destroy')->name('xoa');
    });
});

Route::prefix('lich_su_mua_credit')->group(function(){
    Route::name('lich_su_mua_credit.')->group(function(){
        Route::get('/','LichSuMuaCreditController@index')->name('danh_sach');
        Route::get('/nguoi_choi_id/{id}','LichSuMuaCreditController@show')->name('danh_sach_id');
        Route::get('/them','LichSuMuaCreditController@create')->name('them');
        Route::post('/them','LichSuMuaCreditController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','LichSuMuaCreditController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','LichSuMuaCreditController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','LichSuMuaCreditController@destroy')->name('xoa');
    });
});


Route::prefix('goi_credit')->group(function(){
    Route::name('goi_credit.')->group(function(){
        Route::get('/','GoiCreditController@index')->name('danh_sach');
        Route::get('/them','GoiCreditController@create')->name('them');
        Route::post('/them','GoiCreditController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','GoiCreditController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','GoiCreditController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','GoiCreditController@destroy')->name('xoa');
    });
});

// Cấu Hình
Route::prefix('cau_hinh_diem_cau_hoi')->group(function(){
    Route::name('cau_hinh_diem_cau_hoi.')->group(function(){
        Route::get('/','CauHinhDiemCauHoiController@index')->name('danh_sach');
        Route::get('/them','CauHinhDiemCauHoiController@create')->name('them');
        Route::post('/them','CauHinhDiemCauHoiController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','CauHinhDiemCauHoiController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','CauHinhDiemCauHoiController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','CauHinhDiemCauHoiController@destroy')->name('xoa');
    });
});


Route::prefix('quan_tri_vien')->group(function(){
    Route::name('quan_tri_vien.')->group(function(){
        Route::get('/','QuanTriVienController@index')->name('danh_sach');
        Route::get('/them','QuanTriVienController@create')->name('them');
        Route::post('/them','QuanTriVienController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','QuanTriVienController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','QuanTriVienController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','QuanTriVienController@destroy')->name('xoa');
    });
});

Route::prefix('cau_hinh_app')->group(function(){
    Route::name('cau_hinh_app.')->group(function(){
        Route::get('/','CauHinhAppController@index')->name('danh_sach');
        Route::get('/them','CauHinhAppController@create')->name('them');
        Route::post('/them','CauHinhAppController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','CauHinhAppController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','CauHinhAppController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','CauHinhAppController@destroy')->name('xoa');
    });
});


Route::prefix('cau_hinh_tro_giup')->group(function(){
    Route::name('cau_hinh_tro_giup.')->group(function(){
        Route::get('/','CauHinhTroGiupController@index')->name('danh_sach');
        Route::get('/them','CauHinhTroGiupController@create')->name('them');
        Route::post('/them','CauHinhTroGiupController@store')->name('xu_li_them');
        Route::get('/cap_nhat/{id}','CauHinhTroGiupController@edit')->name('cap_nhat');
        Route::post('/cap_nhat/{id}','CauHinhTroGiupController@update')->name('xu_li_cap_nhat');
        Route::get('/xoa/{id}','CauHinhTroGiupController@destroy')->name('xoa');
    });
});


