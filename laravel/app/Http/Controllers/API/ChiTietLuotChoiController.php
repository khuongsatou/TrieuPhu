<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class ChiTietLuotChoiController extends Controller
{
    public function getAllChiTietLuotChoi(){
        $chiTietLuotChoi = ChiTietLuotChoi::all();
        $result = [
            'success' => true,
            'chi_tiet_luot_choi' => $chiTietLuotChoi
        ];
    
        return response() -> json($result);
    }

    public function getChiTietLuotChoiByID($id)
    {
        $chiTietLuotChoi = ChiTietLuotChoi::find($id);
        if($chiTietLuotChoi == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'chi_tiet_luot_choi' => $chiTietLuotChoi
        ];
        return response() -> json($result);
    }
}
