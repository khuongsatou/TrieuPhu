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
//Lấy ALL
Route::get('linh_vuc','API\LinhVucController@getAllLinhVuc');
Route::get('nguoi_choi','API\NguoiChoiController@getAllNguoiChoi');
Route::get('cau_hinh_app','API\CauHinhAppController@getAllCauHinhApp');
Route::get('cau_hinh_diem_cau_hoi','API\CauHinhDiemCauHoiController@getAllCauHinhDiemCauHoi');
Route::get('cau_hinh_diem_tro_giup','API\CauHinhTroGiupController@getAllCauHinhTroGiup');
Route::get('cau_hoi','API\CauHoiController@getAllCauHoi');
Route::get('chi_tiet_luot_choi','API\ChiTietLuotChoiController@getAllChiTietLuotChoi');
Route::get('goi_credit','API\GoiCreditController@getAllGoiCredit');
Route::get('lich_su_mua_credit','API\LichSuMuaCreditController@getAllLichSuMuaCredit');
Route::get('luot_choi','API\LuotChoiController@getAllLuotChoi');
Route::get('quan_tri_vien','API\QuanTriVienController@getAllQuanTriVien');

//GET ID
Route::get('linh_vuc/{id}','API\LinhVucController@getLinhVucByID');
Route::get('nguoi_choi/{id}','API\NguoiChoiController@getNguoiChoiById');
Route::get('cau_hinh_app/{id}','API\CauHinhAppController@getCauHinhAppByID');
Route::get('cau_hinh_diem_cau_hoi/{id}','API\CauHinhDiemCauHoiController@getCauHinhDiemCauHoiByID');
Route::get('cau_hinh_diem_tro_giup/{id}','API\CauHinhTroGiupController@getcauHinhTroGiupByID');
Route::get('cau_hoi/{id}','API\CauHoiController@getCauHoiByID');
Route::get('chi_tiet_luot_choi/{id}','API\ChiTietLuotChoiController@getChiTietLuotChoiByID');
Route::get('goi_credit/{id}','API\GoiCreditController@getGoiCreditByID');
Route::get('lich_su_mua_credit/{id}','API\LichSuMuaCreditController@getLichSuMuaCreditByID');
Route::get('luoc_choi/{id}','API\LuotChoiController@getLuotChoiByID');
Route::get('quan_tri_vien/{id}','API\QuanTriVienController@getQuanTriVienByID');



Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
