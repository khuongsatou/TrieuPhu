<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CauHinhApp;
class CauHinhAppController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cauHinhApps = CauHinhApp::all();
        return View('cau_hinh_app.ds_cau_hinh_app',compact('cauHinhApps'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return redirect()->route('cau_hinh_app.xl_dang_nhap');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $cauHinhApps =new CauHinhApp();
        $cauHinhApps->co_hoi_sai = $request->co_hoi_sai;
        $cauHinhApps->thoi_gian_tra_loi = $request->thoi_gian_tra_loi;
        $cauHinhApps->save();
        return redirect()->route('cau_hinh_app.danh_sach');
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
        $cauHinhApp = CauHinhApp::find($id);
        return View('cau_hinh_app.xl_cap_nhat',compact('cauHinhApp'));
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
        $cauHinhApp = CauHinhApp::find($id);
        $cauHinhApp->co_hoi_sai = $request->co_hoi_sai;
        $cauHinhApp->thoi_gian_tra_loi = $request->thoi_gian_tra_loi;
        $cauHinhApp->save();
        return redirect()->route('cau_hinh_app.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $cauHinhApp = CauHinhApp::find($id);
       
        $cauHinhApp->delete();
        return redirect()->route('cau_hinh_app.danh_sach');
    }
}
