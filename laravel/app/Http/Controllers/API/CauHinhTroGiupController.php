<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class CauHinhTroGiupController extends Controller
{
    public function getAllCauHinhTroGiup(){
        $cauHinhTroGiup = CauHinhTroGiup::all();
        $result = [
            'success' => true,
            'cau_hinh_tro_giup' => $cauHinhTroGiup
        ];
    
        return response() -> json($result);
    }

    public function getcauHinhTroGiupByID($id)
    {
        $cauHinhTroGiup = CauHinhTroGiup::find($id);
        if($cauHinhTroGiup == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'cau_hinh_tro_giup' => $cauHinhTroGiup
        ];
        return response() -> json($result);
    }
}
