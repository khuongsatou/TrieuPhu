<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\LuotChoi;
class LuotChoiController extends Controller
{

    public function store(Request $request){
        $luotChoi = new LuotChoi();
        $luotChoi->nguoi_choi_id = $request->nguoi_choi_id;
        $luotChoi->so_cau = $request->so_cau;
        $luotChoi->diem = $request->diem;
        $luotChoi->ngay_gio = $request->ngay_gio;
        $luotChoi->save();
        return response()->json(['success'=>true]);
    }




    public function getLuotChoiByID(Request $request)
    {
        $page = $request->page;
        $limit = $request->limit;
        $luotChoi = LuotChoi::where('nguoi_choi_id',$request->nguoi_choi_id)->orderBy('id',"DESC")->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success' => true,
            'total' => LuotChoi::where('nguoi_choi_id',$request->nguoi_choi_id)->count(),
            'luot_choi' => $luotChoi
        ];
        return response()->json($result);
    }
}
