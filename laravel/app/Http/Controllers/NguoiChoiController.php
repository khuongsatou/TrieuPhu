<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;
use App\NguoiChoi;
use App\LichSuMuaCredit;
use App\LuotChoi;

class NguoiChoiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $nguoiChois = NguoiChoi::all();
        return View('nguoi_choi.ds_nguoi_choi',compact('nguoiChois'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('nguoi_choi.xl_nguoi_choi');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $nguoiChoi = new NguoiChoi();
        //ten
        if (strlen($request->ten_dang_nhap)>50) {
            $loi_ten_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('loi_ten_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        }
        //mat khau
        if (strlen($request->mat_khau)>250) {
            $loi_mk_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('loi_mk_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->mat_khau = Hash::make($request->mat_khau);
        }
        
        //email
        if (preg_match ("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+\.[A-Za-z]{2,6}$/", $request->email))
        {
            $nguoiChoi->email = $request->email;
        }
        else
        {
            $loi_email_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('loi_email_nguoi_choi'));
        }
        //hinh dai dien
        $nguoiChoi->hinh_dai_dien = $request->hinh_dai_dien;

        //diêm
        
        if (strlen($request->diem_cao_nhat)>6) {
            $loi_diem_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('loi_diem_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->diem_cao_nhat = $request->diem_cao_nhat;
        }
        //credit
        if (strlen($request->credit)>6) {
            $loi_credit_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('loi_credit_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->credit = $request->credit;
        }
        
        $nguoiChoi->save();
        return redirect()->route('nguoi_choi.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($name,$id)
    {
        if($name == "lich_su_mua_credit"){
            $lichSuMuaCredits = LichSuMuaCredit::where('nguoi_choi_id',$id)->get();
            $nguoiChoi = NguoiChoi::find($id);
            return View('lich_su_mua_credit.ds_lich_su_mua_credit',compact('lichSuMuaCredits','nguoiChoi'));
        }else if($name == "luot_choi"){
            $luotChois = LuotChoi::where('nguoi_choi_id',$id)->get();
            $nguoiChoi = NguoiChoi::find($id);
            return View('luot_choi.ds_luot_choi',compact('luotChois','nguoiChoi'));
        }
        return "null";
    }

    
    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $nguoiChoi = NguoiChoi::find($id);
        return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi'));
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
        $nguoiChoi = NguoiChoi::find($id);
        // $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        // $nguoiChoi->mat_khau = Hash::make($request->mat_khau);
        // $nguoiChoi->email = $request->email;
        // $nguoiChoi->hinh_dai_dien = $request->hinh_dai_dien;
        // $nguoiChoi->diem_cao_nhat = $request->diem_cao_nhat;
        // $nguoiChoi->credit = $request->credit;
        //ten
        if (strlen($request->ten_dang_nhap)>50) {
            $loi_ten_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi','loi_ten_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        }
        //mat khau
        if (strlen($request->mat_khau)>250) {
            $loi_mk_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi','loi_mk_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->mat_khau = Hash::make($request->mat_khau);
        }
        
        //email
        if (preg_match ("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+\.[A-Za-z]{2,6}$/", $request->email))
        {
            $nguoiChoi->email = $request->email;
        }
        else
        {
            $loi_email_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi','loi_email_nguoi_choi'));
        }
        //hinh dai dien
        $nguoiChoi->hinh_dai_dien = $request->hinh_dai_dien;

        //diêm
        
        if (strlen($request->diem_cao_nhat)>6) {
            $loi_diem_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi','loi_diem_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->diem_cao_nhat = $request->diem_cao_nhat;
        }
        //credit
        if (strlen($request->credit)>6) {
            $loi_credit_nguoi_choi='1';
            return View('nguoi_choi.xl_nguoi_choi',compact('nguoiChoi','loi_credit_nguoi_choi'));
        }
        else
        {
            $nguoiChoi->credit = $request->credit;
        }
        $nguoiChoi->save();
        return redirect()->route('nguoi_choi.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $nguoiChoi = NguoiChoi::find($id);
        $nguoiChoi->delete();
        return redirect()->route('nguoi_choi.danh_sach');
    }

    
}
