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
Route::middleware(['assign.guard:api'],'jwt.auth')->group(function(){
    Route::post('lay_thong_tin','API\NguoiChoiController@layThongTin');
});

Route::prefix('nguoi_choi')->group(function(){
    Route::post('danh_sach','API\NguoiChoiController@getNguoiChoi');
    Route::post('dang_nhap','API\NguoiChoiController@dangNhap');
    Route::post('mat_khau','API\NguoiChoiController@getPassWord');
    Route::post('them','API\NguoiChoiController@store');
    Route::post('cap_nhat','API\NguoiChoiController@update');
    Route::post('cap_nhat_credit','API\NguoiChoiController@updateCredit');
    Route::post('upload','API\NguoiChoiController@upload');
});


Route::prefix('luot_choi')->group(function(){
    Route::post('tim','API\LuotChoiController@getLuotChoiById');
    Route::post('them','API\LuotChoiController@store');
});

Route::prefix('goi_credit')->group(function(){
    Route::post('danh_sach','API\GoiCreditController@getGoiCredit');
});

Route::prefix('linh_vuc')->group(function(){
    Route::post('danh_sach','API\LinhVucController@getLinhVuc');
});

Route::prefix('cau_hoi')->group(function(){
    Route::post('tim','API\CauHoiController@getCauHoiById');
});
//endTest




Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
