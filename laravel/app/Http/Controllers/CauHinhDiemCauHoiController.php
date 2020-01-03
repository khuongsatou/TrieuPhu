<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CauHinhDiemCauHoi;
class CauHinhDiemCauHoiController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cauHinhDiemCauHois = CauHinhDiemCauHoi::all();
        return View('cau_hinh_diem_cau_hoi.ds_cau_hinh_diem_cau_hoi',compact('cauHinhDiemCauHois'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('cau_hinh_diem_cau_hoi.xl_cau_hinh_diem_cau_Hoi');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $cauHinhDiemCauHoi  =new CauHinhDiemCauHoi();
        $cauHinhDiemCauHoi->thu_tu = $request->thu_tu;
        if (strlen($request->diem)>6) {
            $loi_diem='1';
            return View('cau_hinh_diem_cau_hoi.xl_cau_hinh_diem_cau_Hoi',compact('loi_diem'));
        }
        else{
             $cauHinhDiemCauHoi->diem = $request->diem;
        }
       
        $cauHinhDiemCauHoi->save();
        return redirect()->route('cau_hinh_diem_cau_hoi.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $cauHinhDiemCauHoi  = CauHinhDiemCauHoi::find($id);
        return View('cau_hinh_diem_cau_hoi.xl_cau_hinh_diem_cau_hoi',compact('cauHinhDiemCauHoi'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $cauHinhDiemCauHoi  =CauHinhDiemCauHoi::find($id);
        $cauHinhDiemCauHoi->thu_tu = $request->thu_tu;
        if (strlen($request->diem)>6) {
            $loi_diem='1';
            return View('cau_hinh_diem_cau_hoi.xl_cau_hinh_diem_cau_Hoi',compact('cauHinhDiemCauHoi','loi_diem'));
        }
        else{
             $cauHinhDiemCauHoi->diem = $request->diem;
        }
        $cauHinhDiemCauHoi->save();
        return redirect()->route('cau_hinh_diem_cau_hoi.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $cauHinhDiemCauHoi  =CauHinhDiemCauHoi::find($id);
        $cauHinhDiemCauHoi->delete();
        return redirect()->route('cau_hinh_diem_cau_hoi.danh_sach');
    }
}
