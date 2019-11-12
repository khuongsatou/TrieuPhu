<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\GoiCredit;
class GoiCreditController extends Controller
{
    public function getGoiCredit(){
        $goiCredit = GoiCredit::all();
        $result = [
            'success' => true,
            'goi_credit' => $goiCredit
        ];
    
        return response() -> json($result);
    }

}
