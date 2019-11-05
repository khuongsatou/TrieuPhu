<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\LuotChoi;
class LuotChoiController extends Controller
{
    public function getAllLuotChoi(){
        $luotChoi = LuotChoi::all();
        $result = [
            'success' => true,
            'luot_choi' => $luotChoi
        ];
    
        return response() -> json($result);
    }

    public function getLuotChoiByID($id)
    {
        $luotChoi = LuotChoi::find($id);
        if($luotChoi == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'luot_choi' => $luotChoi
        ];
        return response() -> json($result);
    }
}
