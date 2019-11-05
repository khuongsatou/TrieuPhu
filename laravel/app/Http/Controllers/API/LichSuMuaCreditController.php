<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class LichSuMuaCreditController extends Controller
{
    public function getAllLichSuMuaCredit(){
        $lichSuMuaCredit = LichSuMuaCredit::all();
        $result = [
            'success' => true,
            'lich_su_mua_credit' => $lichSuMuaCredit
        ];
    
        return response() -> json($result);
    }

    public function getLichSuMuaCreditByID($id)
    {
        $lichSuMuaCredit = LichSuMuaCredit::find($id);
        if($lichSuMuaCredit == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'lich_su_mua_credit' => $lichSuMuaCredit
        ];
        return response() -> json($result);
    }
}
