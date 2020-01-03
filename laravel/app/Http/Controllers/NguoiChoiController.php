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
        $messages = [
            'ten_dang_nhap.required' => 'Chưa nhập',
            'ten_dang_nhap.regex' => 'Sai định dạng',
            'mat_khau.required' => 'Chưa nhập',
            'email.required' => 'Chưa nhập',
            'email.email' => 'Sai định dạng',
            'hinh_dai_dien.required' => 'Chưa nhập',
            'diem_cao_nhat.required' => 'Chưa nhập',
            'diem_cao_nhat.regex' => 'Sai Định dạng',
            'credit.required' => 'Chưa nhập',
            'credit.regex' => 'Sai Định dạng'
        ];
        $this->validate($request,[
            'ten_dang_nhap' => 'required|regex:/^[A-Za-z.-_]+$/',
            'mat_khau' => 'required',
            'email' => 'required|email',
            'hinh_dai_dien' => 'required',
            'diem_cao_nhat' => 'required|regex:/^[0-9]+$/',
            'credit' => 'required|regex:/^[0-9]+$/'
        ],$messages);

        $nguoiChoi = new NguoiChoi();
        $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        $nguoiChoi->mat_khau = Hash::make($request->mat_khau);
        $nguoiChoi->email = $request->email;
        $nguoiChoi->hinh_dai_dien = $request->hinh_dai_dien;
        $nguoiChoi->diem_cao_nhat = $request->diem_cao_nhat;
        $nguoiChoi->credit = $request->credit;
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
        $messages = [
            'ten_dang_nhap.required' => 'Chưa nhập',
            'ten_dang_nhap.regex' => 'Sai định dạng',
            'mat_khau.required' => 'Chưa nhập',
            'email.required' => 'Chưa nhập',
            'email.email' => 'Sai định dạng',
            'hinh_dai_dien.required' => 'Chưa nhập',
            'diem_cao_nhat.required' => 'Chưa nhập',
            'diem_cao_nhat.regex' => 'Sai Định dạng',
            'credit.required' => 'Chưa nhập',
            'credit.regex' => 'Sai Định dạng'
        ];
        $this->validate($request,[
            'ten_dang_nhap' => 'required|regex:/^[A-Za-z.-_]+$/',
            'mat_khau' => 'required',
            'email' => 'required|email',
            'hinh_dai_dien' => 'required',
            'diem_cao_nhat' => 'required|regex:/^[0-9]+$/',
            'credit' => 'required|regex:/^[0-9]+$/'
        ],$messages);
        $nguoiChoi = NguoiChoi::find($id);
        $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        $nguoiChoi->mat_khau = Hash::make($request->mat_khau);
        $nguoiChoi->email = $request->email;
        $nguoiChoi->hinh_dai_dien = $request->hinh_dai_dien;
        $nguoiChoi->diem_cao_nhat = $request->diem_cao_nhat;
        $nguoiChoi->credit = $request->credit;
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
