<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\CauHoi;
class CauHoiController extends Controller
{

    public function getCauHoiById(Request $request)
    {
        $page = $request->page;
        $limit = $request->limit;
        $cauHoi = CauHoi::where('linh_vuc_id',$request->linh_vuc_id)->orderBy('id',"ASC")->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success' => true,
            'total' => CauHoi::count(),
            'cau_hoi' => $cauHoi
        ];
        return response()->json($result);
    }
}
