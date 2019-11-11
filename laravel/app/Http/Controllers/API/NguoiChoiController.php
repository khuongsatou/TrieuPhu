<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\NguoiChoi;

class NguoiChoiController extends Controller
{
    public function getResultLogin(Request $request){
        $nguoiChoi = NguoiChoi::where('ten_dang_nhap',$request->ten_dang_nhap)->where('mat_khau',$request->mat_khau)->first();
        if($nguoiChoi == null){
            return response()->json(['success'=>false]);
        }
        $result = ['success'=>true,'nguoi_choi'=>$nguoiChoi];
        return response()->json($result);
    }

    public function getPassWord(Request $request){
        $nguoiChoi = NguoiChoi::where('ten_dang_nhap',$request->ten_dang_nhap)->where('email',$request->email)->first();
        if($nguoiChoi == null){
            return response()->json(['success'=>false]);
         }
         $result = [
             'success'=>true,
             'nguoi_choi'=>$nguoiChoi
         ];
         return response()->json($result);
    }

    public function store(Request $request){
        $checkUser = NguoiChoi::where('ten_dang_nhap',$request->ten_dang_nhap)->orWhere('email',$request->email)->first();
        if($checkUser != null){
            return response()->json(['success'=>false]);
         }
        $nguoiChoi = new NguoiChoi();
        $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        $nguoiChoi->mat_khau = $request->mat_khau;
        $nguoiChoi->email = $request->email;
        $nguoiChoi->hinh_dai_dien = "";
        $nguoiChoi->diem_cao_nhat = 0;
        $nguoiChoi->credit = 0;
        $nguoiChoi->save();
        return response()->json(['success'=>true]);
    }

    public function update(Request $request){
        $nguoiChoi = NguoiChoi::where('id',$request->id)->first();
        if($nguoiChoi == null){
            return response()->json(['success'=>false]);
         }
        //$nguoiChoi =NguoiChoi::find($request->id);
        $nguoiChoi->ten_dang_nhap = $request->ten_dang_nhap;
        $nguoiChoi->mat_khau = $request->mat_khau;
        $nguoiChoi->email = $request->email;
        $nguoiChoi->hinh_dai_dien = "";
        $nguoiChoi->save();
        return response()->json(['success'=>true]);
    }


    public function getAllNguoiChoi(){
        $nguoiChoi = NguoiChoi::all();
        $result = [
            'success' => true,
            'linh_vuc' => $nguoiChoi
        ];
    
        return response() -> json($result);
    }


    public function getNguoiChoiById($id)
    {
        $nguoiChoi = NguoiChoi::find($id);
        if ($nguoiChoi == null) {
            return response()->json(['success' => false]);
        }
        $result =[
            'success'=>true,
            'nguoi_choi'=> $nguoiChoi
        ];
        return response()->json($result);

    }
}
