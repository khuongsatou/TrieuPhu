<?php

use Illuminate\Http\Request;
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/
//Test get App
Route::prefix('nguoi_choi')->group(function(){
    Route::post('danh_sach','API\NguoiChoiController@getAllNguoiChoi');
    Route::post('tim','API\NguoiChoiController@getNguoiChoiById');
    Route::post('dang_nhap','API\NguoiChoiController@getResultLogin');
    Route::post('mat_khau','API\NguoiChoiController@getPassWord');
    Route::post('them','API\NguoiChoiController@store');
    Route::post('cap_nhat','API\NguoiChoiController@update');
});

Route::prefix('luot_choi')->group(function(){
    Route::post('tim','API\LuotChoiController@getLuotChoiById');
    Route::post('them','API\LuotChoiController@store');
    Route::post('cap_nhat','API\LuotChoiController@update');
});


//endTest



//Lấy ALL
Route::get('linh_vuc','API\LinhVucController@getAllLinhVuc');

Route::get('cau_hinh_app','API\CauHinhAppController@getAllCauHinhApp');
Route::get('cau_hinh_diem_cau_hoi','API\CauHinhDiemCauHoiController@getAllCauHinhDiemCauHoi');
Route::get('cau_hinh_diem_tro_giup','API\CauHinhTroGiupController@getAllCauHinhTroGiup');
Route::get('cau_hoi','API\CauHoiController@getAllCauHoi');
Route::get('chi_tiet_luot_choi','API\ChiTietLuotChoiController@getAllChiTietLuotChoi');
Route::get('goi_credit','API\GoiCreditController@getAllGoiCredit');
Route::get('lich_su_mua_credit','API\LichSuMuaCreditController@getAllLichSuMuaCredit');

Route::get('quan_tri_vien','API\QuanTriVienController@getAllQuanTriVien');

//GET ID
Route::get('linh_vuc/{id}','API\LinhVucController@getLinhVucByID');
Route::get('cau_hinh_app/{id}','API\CauHinhAppController@getCauHinhAppByID');
Route::get('cau_hinh_diem_cau_hoi/{id}','API\CauHinhDiemCauHoiController@getCauHinhDiemCauHoiByID');
Route::get('cau_hinh_diem_tro_giup/{id}','API\CauHinhTroGiupController@getcauHinhTroGiupByID');
Route::get('cau_hoi/{id}','API\CauHoiController@getCauHoiByID');
Route::get('chi_tiet_luot_choi/{id}','API\ChiTietLuotChoiController@getChiTietLuotChoiByID');
Route::get('goi_credit/{id}','API\GoiCreditController@getGoiCreditByID');
Route::get('lich_su_mua_credit/{id}','API\LichSuMuaCreditController@getLichSuMuaCreditByID');
Route::get('quan_tri_vien/{id}','API\QuanTriVienController@getQuanTriVienByID');



Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
