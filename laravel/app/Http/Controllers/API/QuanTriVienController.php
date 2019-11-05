<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\QuanTriVien;

class QuanTriVienController extends Controller
{
    public function getAllQuanTriVien(){
        $quanTriVien = QuanTriVien::all();
        $result = [
            'success' => true,
            'quan_tri_vien' => $quanTriVien
        ];
    
        return response() -> json($result);
    }

    public function getQuanTriVienByID($id)
    {
        $quanTriVien = QuanTriVien::find($id);
        if($quanTriVien == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'quan_tri_vien' => $quanTriVien
        ];
        return response() -> json($result);
    }
}
