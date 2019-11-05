<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\LinhVuc;
use App\CauHoi;
class LinhVucController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $linhVucs = LinhVuc::all();
        return View('linh_vuc.ds_linh_vuc',compact('linhVucs'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('linh_vuc.xl_linh_vuc');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $linhVuc = new LinhVuc();
        $linhVuc->ten_linh_vuc = $request->ten_linh_vuc;
        $linhVuc->save();
        return redirect()->route('linh_vuc.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $cauHois = CauHoi::where('linh_vuc_id',$id)->get();
        $linhVuc = LinhVuc::find($id);
        return View('cau_hoi.ds_cau_hoi',compact('cauHois','linhVuc'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $linhVuc = LinhVuc::find($id);
        return View('linh_vuc.xl_linh_vuc',compact('linhVuc'));
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
        $linhVuc = LinhVuc::find($id);
        $linhVuc->ten_linh_vuc = $request->ten_linh_vuc;
        $linhVuc->save();
        return redirect()->route('linh_vuc.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $linhVuc = LinhVuc::find($id);
        $linhVuc->delete();
        return redirect()->route('linh_vuc.danh_sach');
    }
}
