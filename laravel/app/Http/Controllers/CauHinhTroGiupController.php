<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CauHinhTroGiup;
class CauHinhTroGiupController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cauHinhTroGiups = CauHinhTroGiup::all();
        return View('cau_hinh_tro_giup.ds_cau_hinh_tro_giup',compact('cauHinhTroGiups'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('cau_hinh_tro_giup.xl_cau_hinh_tro_giup');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $cauHinhTroGiup = new CauHinhTroGiup();
        $cauHinhTroGiup->loai_tro_giup = $request->loai_tro_giup;
        $cauHinhTroGiup->thu_tu = $request->thu_tu;
        $cauHinhTroGiup->save();
        return redirect()->route('cau_hinh_tro_giup.danh_sach');
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
        $cauHinhTroGiup = CauHinhTroGiup::find($id);
        return View('cau_hinh_tro_giup.xl_cau_hinh_tro_giup',compact('cauHinhTroGiup'));
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
        $cauHinhTroGiup =  CauHinhTroGiup::find($id);
        $cauHinhTroGiup->loai_tro_giup = $request->loai_tro_giup;
        $cauHinhTroGiup->thu_tu = $request->thu_tu;
        $cauHinhTroGiup->credit = $request->credit;
        $cauHinhTroGiup->save();
        return redirect()->route('cau_hinh_tro_giup.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $cauHinhTroGiup =  CauHinhTroGiup::find($id);
        $cauHinhTroGiup->delete();
        return redirect()->route('cau_hinh_tro_giup.danh_sach');
    }
}
