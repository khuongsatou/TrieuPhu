<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\QuanTriVien;
use Illuminate\Support\Facades\Hash;

class QuanTriVienController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function hienThiFormDangNhap(){
        return view('login');
    }
    public function xuLyDangNhap(Request $req){
        $credentials = [
            'ten_dang_nhap'=>$req->ten_dang_nhap,
            'password'=>$req->mat_khau
        ];
        if(!auth('web')->attempt($credentials)){
            return "Đăng Nhập Không Thành Công";
        }
        return redirect()->route('trang_chu');
    }
    public function dangXuat(){
        auth('web')->logout();
        return redirect()->route('dang_nhap');
    }


    public function index()
    {
        $quanTriViens = QuanTriVien::all();
        return View('quan_tri_vien.ds_quan_tri_vien',compact('quanTriViens'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('quan_tri_vien.xl_quan_tri_vien');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $quanTriVien = new QuanTriVien();
        $quanTriVien->ten_dang_nhap = $request->ten_dang_nhap;
        $quanTriVien->mat_khau = Hash::make($request->mat_khau);
        $quanTriVien->ho_ten = $request->ho_ten;
        $quanTriVien->save();
        return redirect()->route('quan_tri_vien.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $quanTriVien =  QuanTriVien::find($id);
        return View('quan_tri_vien.xl_quan_tri_vien',compact('quanTriVien'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $quanTriVien = QuanTriVien::find($id);
        $quanTriVien->ten_dang_nhap = $request->ten_dang_nhap;
        $quanTriVien->mat_khau = Hash::make($request->mat_khau);
        $quanTriVien->ho_ten = $request->ho_ten;
        $quanTriVien->save();
        return redirect()->route('quan_tri_vien.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $quanTriVien =QuanTriVien::find($id);
        $quanTriVien->delete();
        return redirect()->route('quan_tri_vien.danh_sach');
    }
}
