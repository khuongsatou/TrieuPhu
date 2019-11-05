<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\NguoiChoi;

class NguoiChoiController extends Controller
{
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
