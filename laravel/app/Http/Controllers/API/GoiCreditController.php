<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\GoiCredit;
class GoiCreditController extends Controller
{
    public function getAllGoiCredit(){
        $goiCredit = GoiCredit::all();
        $result = [
            'success' => true,
            'goi_credit' => $goiCredit
        ];
    
        return response() -> json($result);
    }

    public function getGoiCreditByID($id)
    {
        $goiCredit = GoiCredit::find($id);
        if($goiCredit == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'goi_credit' => $goiCredit
        ];
        return response() -> json($result);
    }
}
