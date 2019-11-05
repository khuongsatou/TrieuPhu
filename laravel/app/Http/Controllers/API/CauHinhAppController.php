<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\CauHinhApp;

class CauHinhAppController extends Controller
{
    public function getAllCauHinhApp()
    {
        $cauHinhApp = CauHinhApp::all();
        $result = [
            'success'=>true,
            'cau_hinh_app'=>$cauHinhApp
        ];
        return response()->json($result);
    }

    public function getCauHinhAppByID($id)
    {
        $cauHinhApp = CauHinhApp::find($id);
        if ($cauHinhApp == null) {
            return response()->json(['success'=>false]);
        }
        $result = [
            'success'=>true,
            'cau_hinh_app'=>$cauHinhApp
        ];
        return response()->json($result);
    }
}
