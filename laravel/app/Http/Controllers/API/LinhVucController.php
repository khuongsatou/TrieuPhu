<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\LinhVuc;
class LinhVucController extends Controller
{
    public function getLinhVuc(Request $request)
    {
        $page = $request->page;
        $limit = $request->limit;
        $linhVuc = LinhVuc::orderBy('ten_linh_vuc',"DESC")->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success' => true,
            'total' => LinhVuc::count(),
            'linh_vuc' => $linhVuc
        ];
        return response()->json($result);
    }

}
