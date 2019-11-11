<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\LuotChoi;
class LuotChoiController extends Controller
{





    public function store(Request $request){
        $checkUser = LuotChoi::where('ten_dang_nhap',$request->ten_dang_nhap)->orWhere('email',$request->email)->first();
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






    public function getLuotChoiByID(Request $request)
    {
        $luotChoi = LuotChoi::find($request->nguoi_choi_id);
        $page = $request->page;
        $limit = $request->limit;
        $luotChoi = LuotChoi::orderBy('id',"DESC")->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success' => true,
            'total' => LuotChoi::find($request->nguoi_choi_id)->count(),
            'luot_choi' => $luotChoi
        ];
        return response()->json($result);
    }
}
